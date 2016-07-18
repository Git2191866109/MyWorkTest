package com.mw.java.test.redis;

import com.mw.java.test.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by mawei on 2016/7/11.
 */
public class TestRedis {
    private static final Pattern SPACE = Pattern.compile(Constant.HDFSSPLIT);
    static String KEY_TOP = "ahisrd";

    public static void main(String[] args) {
        Logger loger = LoggerFactory.getLogger(TestRedis.class);
        String[] values = "qfqf111101sess1234101111111".split(" ");
        Jedis jedis = new Jedis("172.31.10.152");
        String key = KEY_TOP + Constant._;
        List<String> resultLists = jedis.lrange(key, 0, -1);
        System.out.println(resultLists.toString());
        jedis.del("ahisrd_qf_mw");
        List<String> tempList = new ArrayList<String>();
        tempList.add("qf qf11 mw 1 kkkk 1234 10 1111111");
        tempList.add("qf qf11 mw 1 cccc 1234 10 1111111");
        tempList.add("qf qf11 mw 1 cccc 1234 10 1111111");
        tempList.add("qf qf11 mw 1 cccc 1234 10 1111111");
        tempList.add("qf qf11 mw 1 aaaa 1234 10 1111111");
        for (String s : tempList) {
            jedis.lpush("ahisrd_qf_mw", s);
        }
//        System.out.println(tempList.toString());
        System.out.println(jedis.lrange("ahisrd_qf_mw", 0, -1));

//        SparkConf sparkConf = new SparkConf().setAppName("searchHisSpark").setMaster("local[2]");
//        JavaStreamingContext context = new JavaStreamingContext(sparkConf, new Duration(2000));
//        JavaReceiverInputDStream messages = context.socketTextStream("172.31.10.151", 9999);
//        JavaPairDStream<String, Integer> lastCounts = messages.map(new Function<Tuple2<String, String>, String>() {
//            public String call(Tuple2<String, String> tuple2) {
//                return tuple2._2();
//            }
//        }).flatMap(new FlatMapFunction<String, String>() {
//            public Iterable<String> call(String x) {
//                return Lists.newArrayList(SPACE.split(x));
//            }
//        }).mapToPair(new PairFunction<String, String, Integer>() {
//            public Tuple2<String, Integer> call(String s) {
//                return new Tuple2<String, Integer>(s, 1);
//            }
//        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
//
//            public Integer call(Integer x, Integer y) throws Exception {
//                return x.intValue() + y.intValue();
//            }
//        });
//        lastCounts.print();
//        context.start();
//        context.awaitTermination();
    }
}