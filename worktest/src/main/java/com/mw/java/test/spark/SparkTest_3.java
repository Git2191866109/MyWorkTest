//package com.mw.java.test.spark;
//
//import com.mw.java.test.Constant;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.FlatMapFunction;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.api.java.function.VoidFunction;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import scala.Tuple2;
//
//import java.util.List;
//import java.util.function.Consumer;
//
///**
// * Created by mawei on 2016/7/8.
// */
//public class SparkTest_3 {
//    final static Logger loger = LoggerFactory.getLogger(SparkTest_3.class);
//
//    /**
//     * 需求：
//     * 1、得到kafka中的消息数据（数据格式：qfqf111101sess1234101111111对应枚举HisEnum）
//     * 2、保存到151hdfs中
//     * 3、拼接key
//     * 4、从redis中获取这个key的数据，
//     * 分两中情况：一，得到redis中原始数据，有重复，需要去重
//     * 二，redis中没有数据，然后到151Hdfs上去获取，规则：根据	USERID("用户id",0),
//     * FROMTYPE("来源",2),获取，然后通过，KW("关键字",4)去重，并且得到topN
//     * <p/>
//     * 5、然后覆盖掉原来的redis中的数据
//     * USERID("用户id",0),
//     * MODERNIZED("设备id",1),
//     * FROMTYPE("来源",2),
//     * TYPE("类型",3),
//     * KW("关键字",4),
//     * CARSTYLEID("车型id",5),
//     * NUM("数量",6),
//     * TIMES("时间",7);
//     */
//    public static void main(String[] args) {
//
//        final String KEY_TOP = "ahisrd";
////        String key = CommonUtil.KEY_TOP + CommonUtil.KEY_JOIN_TYPE + values[HisEnum.USERID.getValue()] + CommonUtil.KEY_JOIN_TYPE + values[HisEnum.FROMTYPE.getValue()];
////        String path = "/Users/mawei/Documents/ideaworkspace/MyWorkTest/worktest/src/main/resources/source/sparktext";
//        String path = "E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\source\\sparktext";
//        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JavaWordCount");
//        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
////        ctx.setLogLevel("OFF");
//        final JavaRDD<String> lines = ctx.textFile(path).distinct();
//        lines.cache();
//         /*去重*/
//        List<String> linesResults = lines.collect();
//        for (String s : linesResults) {
////            System.out.println(s);
//        }
//        /*call方法的返回类型是Tuple2<String, Tuple3<String,String, String>>，对应的Function的第二个参数就是call方法的返回类型*/
//        JavaPairRDD<String, Iterable<Tuple2<String, String>>> links = lines.mapToPair(
//                new PairFunction<String, String, Tuple2<String, String>>() {
//                    @Override
//                    public Tuple2<String, Tuple2<String, String>> call(String v1) {
//                        String[] splitResut = v1.split(Constant.HDFSSPLIT);
//                        String userid = splitResut[0].trim();
//                        String fromtype = splitResut[2].trim();
//                        String kw = splitResut[4].trim();
//                        String key = KEY_TOP + Constant._ + userid + Constant._ + fromtype;
//
////                /*这里的new对象就是JavaRDD的返回类型，也是call方法的返回类型*/
//                        return new Tuple2<String, Tuple2<String, String>>(kw, new Tuple2<String, String>(key, v1));
//                    }
//                }).distinct().groupByKey().cache();
////        System.out.println(links.collect().toString());
//        JavaRDD<Tuple2<String, String>> javaRDD = links.flatMap(new FlatMapFunction<Tuple2<String, Iterable<Tuple2<String, String>>>, Tuple2<String, String>>() {
//            @Override
//            public Iterable<Tuple2<String, String>> call(Tuple2<String, Iterable<Tuple2<String, String>>> stringIterableTuple2) throws Exception {
//                Iterable<Tuple2<String, String>> iterator = stringIterableTuple2._2();
//                return iterator;
//            }
//        });
//        JavaPairRDD<Object, Tuple2<String, String>> pairRDD = javaRDD.mapToPair(new PairFunction<Tuple2<String, String>, Object, Tuple2<String, String>>() {
//            @Override
//            public Tuple2<Object, Tuple2<String, String>> call(Tuple2<String, String> stringStringTuple2) throws Exception {
//                return new Tuple2<Object, Tuple2<String, String>>(stringStringTuple2._1(), new Tuple2<String, String>(stringStringTuple2._1(), stringStringTuple2._2()));
//            }
//        });
//
//        JavaRDD<Tuple2<String, String>> resultRDD = pairRDD.map(new Function<Tuple2<Object, Tuple2<String, String>>, Tuple2<String, String>>() {
//            @Override
//            public Tuple2<String, String> call(Tuple2<Object, Tuple2<String, String>> v1) throws Exception {
//                return new Tuple2<String, String>(v1._2()._1(), v1._2()._2());
//            }
//        });
//
//        List<Tuple2<String, String>> wordsResults = resultRDD.collect();
//        for (Tuple2<String, String> s : wordsResults) {
////            System.out.println(s.toString());
//        }
//        JavaPairRDD<String, Iterable<Tuple2<String, String>>> resultPairRDD = resultRDD.groupBy(new Function<Tuple2<String, String>, String>() {
//            /**/
//            @Override
//            public String call(Tuple2<String, String> v1) throws Exception {
//                return v1._1();
//            }
//        });
//
////        resultPairRDD.foreach(new VoidFunction<Tuple2<String, Iterable<Tuple2<String, String>>>>() {
////            @Override
////            public void call(Tuple2<String, Iterable<Tuple2<String, String>>> stringIterableTuple2) throws Exception {
////                System.out.println(stringIterableTuple2._1() + ": " + stringIterableTuple2._2().toString());
////            }
////        });
//
////        resultPairRDD.foreach(new VoidFunction<Tuple2<String, Iterable<Tuple2<String, String>>>>() {
////            @Override
////            public void call(Tuple2<String, Iterable<Tuple2<String, String>>> stringIterableTuple2) throws Exception {
////                System.out.println(stringIterableTuple2._1() + ": " + stringIterableTuple2._2().toString());
////            }
////        });
//
//
////        System.out.println(resultPairRDD.collect().toString());
//        resultPairRDD.foreach(new VoidFunction<Tuple2<String, Iterable<Tuple2<String, String>>>>() {
//            @Override
//            public void call(final Tuple2<String, Iterable<Tuple2<String, String>>> stringIterableTuple2) throws Exception {
//                stringIterableTuple2._2().forEach(new Consumer<Tuple2<String, String>>() {
//                    @Override
//                    public void accept(Tuple2<String, String> stringStringTuple2) {
//                        System.out.println(stringIterableTuple2._1() + ": " + stringStringTuple2._2());
//                    }
//                });
//            }
//        });
//
//        ctx.stop();
//    }
//}
