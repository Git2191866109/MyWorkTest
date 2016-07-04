package com.mw.java.test;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mawei on 2016/6/17.
 */
public class WorldCount_J {
    public static void main(String[] args) {
        String path = "/Users/mawei/Documents/ZhGeTianXiaWork/zhgtxworkspace/Work_Test/src/main/resources/worldcount";
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JavaWordCount");
        /**
         * 创建SparkContext对象,sparkContext是所有功能的唯一入口,
         * 他的核心作用:初始化spark应用程序所需要的核心组件,包括:DAGScheduler\TaskScheduler\SchedulerBackend
         * 同时还会负责Spark程序向Master注册程序
         * JavaSparkContext的底层实际上就是Scala的SparkContext
         */
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        /**
         * 通过JavaSparkContext创建JavaRDD，
         */
        JavaRDD<String> lines = ctx.textFile(path);
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String s) {
                return Arrays.asList(s.split(" "));
            }
        });
        List<String> wordsResults = words.collect();
        for (String s : wordsResults) {
            System.out.printf(s);
        }

        final JavaPairRDD<String, Integer> paris = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String word) {
                return new Tuple2<String, Integer>(word, 1);
            }
        });
        List<Tuple2<String, Integer>> output_1 = paris.collect();
        for (Tuple2<?, ?> tuple : output_1) {
            System.out.println(tuple._1() + ": " + tuple._2());
        }
        /**
         * i1和i2都是输入
         *  i1是累加的结果
         *  i2是第二个加数
         * 第三个参数是输出的结果
         */
        JavaPairRDD<String, Integer> counts = paris.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer i1, Integer i2) {
                return i1 + i2;
            }
        });
        /*输出*/
        counts.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> pairs) throws Exception {
                System.out.println(pairs._1() + " : " + pairs._2());
            }
        });

        List<Tuple2<String, Integer>> output = counts.collect();
        for (Tuple2<?, ?> tuple : output) {
            System.out.println(tuple._1() + ": " + tuple._2());
        }
        ctx.stop();
    }
}
