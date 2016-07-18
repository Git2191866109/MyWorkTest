//package com.mw.java.test.spark.streaming;
//
//import com.zgxcw.search.searchHisSpark.util.CommonUtil;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.function.FlatMapFunction;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.api.java.function.VoidFunction;
//import org.apache.spark.streaming.Duration;
//import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaPairDStream;
//import org.apache.spark.streaming.api.java.JavaStreamingContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import scala.Tuple2;
//
//import java.util.function.Consumer;
//
///**
// * Created by mawei on 2016/7/11.
// */
//public class TestStreaming {
//    final static Logger loger = LoggerFactory.getLogger(TestStreaming.class);
//
//    public static void main(String[] args) {
//        SparkConf sparkConf = new SparkConf().setAppName("searchHisSpark").setMaster("local[2]");
//        JavaStreamingContext context = new JavaStreamingContext(sparkConf, new Duration(2000));
////        JavaReceiverInputDStream messages = context.socketTextStream("172.31.10.151", 9999);
//        JavaDStream<String> lines = context.textFileStream("E:\\search\\BigData\\search\\BigData\\searchHisSpark\\searchHisSpark\\src\\main\\resources\\sparktext.txt");
////                            JavaRDD<String> lines = jsc.textFile("E:\\search\\BigData\\search\\BigData\\searchHisSpark\\searchHisSpark\\src\\main\\resources\\sparktext.txt").distinct();
//        //TODO 缓存
//        /*call方法的返回类型是Tuple2<String, Tuple3<String,String, String>>，对应的Function的第二个参数就是call方法的返回类型*/
//        JavaPairDStream<String, Iterable<Tuple2<String, String>>> links = lines.mapToPair(
//                new PairFunction<String, String, Tuple2<String, String>>() {
//                    public Tuple2<String, Tuple2<String, String>> call(String v1) {
//                        String[] splitResut = v1.split(CommonUtil.VALUE_JOIN_TYPE);
//                        String userid = splitResut[0].trim();
//                        String fromtype = splitResut[2].trim();
//                        String kw = splitResut[4].trim();
//                        String key = CommonUtil.KEY_TOP + CommonUtil.KEY_JOIN_TYPE + userid + CommonUtil.KEY_JOIN_TYPE + fromtype;
//                        /*这里的new对象就是JavaRDD的返回类型，也是call方法的返回类型*/
//                        return new Tuple2<String, Tuple2<String, String>>(kw, new Tuple2<String, String>(key, v1));
//                    }
//                }).groupByKey().cache();
//        JavaDStream<Tuple2<String, String>> javaRDD = links.flatMap(new FlatMapFunction<Tuple2<String, Iterable<Tuple2<String, String>>>, Tuple2<String, String>>() {
//            public Iterable<Tuple2<String, String>> call(Tuple2<String, Iterable<Tuple2<String, String>>> stringIterableTuple2) throws Exception {
//                Iterable<Tuple2<String, String>> iterator = stringIterableTuple2._2();
//                return iterator;
//            }
//        });
//        JavaPairDStream<Object, Tuple2<String, String>> pairRDD = javaRDD.mapToPair(new PairFunction<Tuple2<String, String>, Object, Tuple2<String, String>>() {
//            public Tuple2<Object, Tuple2<String, String>> call(Tuple2<String, String> stringStringTuple2) throws Exception {
//                return new Tuple2<Object, Tuple2<String, String>>(stringStringTuple2._1(), new Tuple2<String, String>(stringStringTuple2._1(), stringStringTuple2._2()));
//            }
//        });
//        JavaDStream<Tuple2<String, String>> resultRDD = pairRDD.map(new Function<Tuple2<Object, Tuple2<String, String>>, Tuple2<String, String>>() {
//            public Tuple2<String, String> call(Tuple2<Object, Tuple2<String, String>> v1) throws Exception {
//                return new Tuple2<String, String>(v1._2()._1(), v1._2()._2());
//            }
//        });
//        resultRDD.print();
//
//        resultRDD.foreachRDD(new Function<JavaRDD<Tuple2<String, String>>, Void>() {
//            public Void call(JavaRDD<Tuple2<String, String>> v1) throws Exception {
//                JavaPairRDD<String, Iterable<Tuple2<String, String>>> resultPairRDD = v1.groupBy(new Function<Tuple2<String, String>, String>() {
//                    public String call(Tuple2<String, String> v1) throws Exception {
//                        return v1._1();
//                    }
//                });
//                resultPairRDD.foreach(new VoidFunction<Tuple2<String, Iterable<Tuple2<String, String>>>>() {
//                    public void call(final Tuple2<String, Iterable<Tuple2<String, String>>> stringIterableTuple2) throws Exception {
//                        stringIterableTuple2._2().forEach(new Consumer<Tuple2<String, String>>() {
//                            public void accept(Tuple2<String, String> stringStringTuple2) {
//                                loger.info(stringIterableTuple2._1() + ": " + stringStringTuple2._2());
//                                System.out.println(stringIterableTuple2._1() + ": " + stringStringTuple2._2());
//
//                            }
//                        });
//                    }
//                });
//                return null;
//            }
//        });
//        context.start();
//        context.awaitTermination();
//    }
//}
