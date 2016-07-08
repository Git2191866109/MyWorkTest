package com.mw.java.test.spark;

import com.mw.java.test.Constant;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

import java.util.List;

/**
 * Created by mawei on 2016/7/8.
 */
public class SparkTest {
    /**
     * 需求：
     * 1、得到kafka中的消息数据（数据格式：qfqf111101sess1234101111111对应枚举HisEnum）
     * 2、保存到151hdfs中
     * 3、拼接key
     * 4、从redis中获取这个key的数据，
     * 			分两中情况：一，得到redis中原始数据，有重复，需要去重
     * 					   二，redis中没有数据，然后到151Hdfs上去获取，规则：根据	USERID("用户id",0),
     * 					   	   FROMTYPE("来源",2),获取，然后通过，KW("关键字",4)去重，并且得到topN
     *
     * 5、然后覆盖掉原来的redis中的数据
     */
    public static void main(String[] args) {
        String path = "E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\source\\sparktext";
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JavaWordCount");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        ctx.setLogLevel("OFF");
        JavaRDD<String> lines = ctx.textFile(path);
        List<String> linesResults = lines.collect();
//        for (String s : linesResults) {
//            System.out.println(s);
//        }
         /*将其组合成 (kw,（USERID_FROMTYPE，s))形式,然后按照key分组 */
        JavaRDD<Tuple2<String,Tuple2<String,String>>> splitLine = lines.map(new Function<String, Tuple2<String,Tuple2<String,String>>>() {
            @Override
            public Tuple2<String,Tuple2<String,String>> call(String v1) throws Exception {
                String [] splitResut = v1.split(Constant.HDFSSPLIT);
                String userid = splitResut[0].trim();
                String fromtype = splitResut[2].trim();
                String kw = splitResut[4].trim();
                String userid_fromtype = userid+Constant._+fromtype;
                return new Tuple2<String,Tuple2<String,String>>(userid_fromtype,new Tuple2<String, String>(v1,kw));
            }
        });
        List<Tuple2<String,Tuple2<String,String>>> wordsResults = splitLine.collect();
        for (Object s : wordsResults) {
            System.out.println(s.toString());
        }
//        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
//            @Override
//            public Iterable<String> call(String s) {
//                return Arrays.asList(s.split(Constant.HDFSSPLIT));
//            }
//        });
////
//        List<String> wordsResults = words.collect();
//        for (String s : wordsResults) {
//            System.out.printf(s);
//        }

//        JavaPairRDD<Object, Object> paris = words.mapToPair(new PairFunction<String, Object, Object>() {
//            @Override
//            public Tuple2<Object, Object> call(String str) throws Exception {
//                System.out.println(str.toString());
//                return null;
//            }
//        });
//
//        final JavaPairRDD<String, Integer> paris = words.mapToPair(new PairFunction<String, String, Integer>() {
//            @Override
//            public Tuple2<String, Integer> call(String word) {
//                return new Tuple2<String, Integer>(word, 1);
//            }
//        });
//        List<Tuple2<Object, Object>> output_1 = paris.collect();
//        for (Tuple2<?, ?> tuple : output_1) {
//            System.out.println(tuple._1() + ": " + tuple._2());
//        }
//        /**
//         * i1和i2都是输入
//         *  i1是累加的结果
//         *  i2是第二个加数
//         * 第三个参数是输出的结果
//         */
//        JavaPairRDD<String, Integer> counts = paris.reduceByKey(new Function2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer i1, Integer i2) {
//                return i1 + i2;
//            }
//        });
//        /*输出*/
//        counts.foreach(new VoidFunction<Tuple2<String, Integer>>() {
//            @Override
//            public void call(Tuple2<String, Integer> pairs) throws Exception {
//                System.out.println(pairs._1() + " : " + pairs._2());
//            }
//        });
//
//        List<Tuple2<String, Integer>> output = counts.collect();
//        for (Tuple2<?, ?> tuple : output) {
//            System.out.println(tuple._1() + ": " + tuple._2());
//        }
        ctx.stop();
    }
}
