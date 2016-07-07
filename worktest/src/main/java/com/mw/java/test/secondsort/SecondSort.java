package com.mw.java.test.secondsort;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

/**
 * Created by mawei on 2016/7/5.
 * 具体实现步骤：
 * 1、按照Ordered和Serial实现自定义排序的key
 * 2、将要进行二次排序的文件加载进来，形成key-value类型的Rdd
 * 3、使用sortBykey基于自定义的key进行二次排序
 * 4、去除掉排序的key，只保留排序的结果
 */
public class SecondSort {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("secondSort").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile("E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\secondsort\\sencondsort");
        JavaPairRDD<SecondSortKey, String> pairs = lines.mapToPair(new PairFunction<String, SecondSortKey, String>() {
            @Override
            public Tuple2<SecondSortKey, String> call(String line) throws Exception {
                String[] split = line.split("  ");
                SecondSortKey secdKey = new SecondSortKey(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
                return new Tuple2<SecondSortKey, String>(secdKey, line);
            }
        });
        /*完成二次排序*/
        JavaPairRDD<SecondSortKey, String> sorted = pairs.sortByKey();
           /*输出排序的结果*/
        sorted.foreach(new VoidFunction<Tuple2<SecondSortKey, String>>() {
            @Override
            public void call(Tuple2<SecondSortKey, String> secondSortKeyStringTuple2) throws Exception {
                System.out.println(secondSortKeyStringTuple2);
            }
        });
        /*过滤掉排序后自定义的key，保留排序的结果*/
        JavaRDD<String> sortResult = sorted.map(new Function<Tuple2<SecondSortKey, String>, String>() {
            @Override
            public String call(Tuple2<SecondSortKey, String> sortContext) throws Exception {
                return sortContext._2;
            }
        });
        /*输出排序的结果*/
        sortResult.foreach(new VoidFunction<String>() {
            @Override
            public void call(String sortResult) throws Exception {
                System.out.println(sortResult);
            }
        });
    }
}
