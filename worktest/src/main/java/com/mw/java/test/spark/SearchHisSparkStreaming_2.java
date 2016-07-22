package com.mw.java.test.spark;

import com.mw.java.test.Constant;
import com.mw.java.test.utils.PropertiesUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import scala.Tuple2;

import java.net.URI;
import java.util.*;
import java.util.regex.Pattern;

public class SearchHisSparkStreaming_2 {
    final static Logger loger = LoggerFactory.getLogger(SearchHisSparkStreaming_2.class);
    private static final Pattern SPACE = Pattern.compile(Constant.VALUE_JOIN_TYPE);
    static Jedis jedis = new Jedis("172.31.10.152");

    /**
     * 需求：
     * 1、得到kafka中的消息数据（数据格式：qfqf111101sess1234101111111对应枚举HisEnum）
     * 2、保存到151hdfs中
     * 3、拼接key
     * 4、从redis中获取这个key的数据，
     * 分两中情况：一，得到redis中原始数据，有重复，需要去重
     * 二，redis中没有数据，然后到151Hdfs上去获取，规则：根据	USERID("用户id",0),
     * FROMTYPE("来源",2),获取，然后通过，KW("关键字",4)去重，并且得到topN
     * <p/>
     * 5、然后覆盖掉原来的redis中的数据
     */
    public static void main(String[] args) {
        String masterUrl = getMstURL(args);
        final SparkConf sparkConf = new SparkConf().setAppName("searchHisSpark").setMaster(masterUrl);
        final JavaStreamingContext context = new JavaStreamingContext(sparkConf, new Duration(10000));
        int numThreads = Integer.parseInt(PropertiesUtils.getValue(Constant.NUMTHREAD));
        Map<String, Integer> topicMap = new HashMap<String, Integer>();
        String[] topics = PropertiesUtils.getValue(Constant.TOPICSS).split(",");
        for (String topic : topics) {
            topicMap.put(topic, numThreads);
        }
        JavaPairReceiverInputDStream<String, String> messages = KafkaUtils.createStream(context, PropertiesUtils.getValue(Constant.ZKQUORUM), PropertiesUtils.getValue(Constant.GROUP), topicMap);
        loger.info("开始读取数据。。。。。。。");
        /*得到kafka中的消息数据（数据格式：qfqf111101sess1234101111111对应枚举HisEnum）*/
        JavaDStream<String> lines = messages.map(new Function<Tuple2<String, String>, String>() {
            public String call(Tuple2<String, String> tuple2) throws Exception {
                return tuple2._2();
            }
        });
        /*拼接key*/
        JavaPairDStream<String, String> pairDStream = lines.mapToPair(new PairFunction<String, String, String>() {
            public Tuple2<String, String> call(String line) throws Exception {
                String[] splitResut = line.split(" ");
                String userid = splitResut[0].trim();
                String fromtype = splitResut[2].trim();
                String kw = splitResut[4].trim();
                String key = Constant.KEY_TOP + Constant.KEY_JOIN_TYPE + userid + Constant.KEY_JOIN_TYPE + fromtype;
                /*这里的new对象就是JavaRDD的返回类型，也是call方法的返回类型*/
                return new Tuple2<String, String>(key, line);
            }
        });
        /*按照key分组*/
        JavaPairDStream<String, Iterable<String>> groupKeyResult = pairDStream.groupByKey();
        groupKeyResult.print();
        /*得到key，然后去redis中遍历*/
        groupKeyResult.foreachRDD(new VoidFunction<JavaPairRDD<String, Iterable<String>>>() {
            public void call(JavaPairRDD<String, Iterable<String>> stringIterableJavaPairRDD) throws Exception {
                Map<String, Iterable<String>> resultMap = stringIterableJavaPairRDD.collectAsMap();
                Set<String> tempSet = new HashSet<String>();
                Iterator<String> itStrTotal = resultMap.keySet().iterator();
                while (itStrTotal.hasNext()) {
                    String key = itStrTotal.next();
                    List<String> resultLists = jedis.lrange(key, 0, -1);
                    if (CollectionUtils.isNotEmpty(resultLists)) {
                        for (String s : resultLists) {
                            tempSet.add(s);
                        }
                        jedis.del(key);
                        loger.info("tempSet。。。。。。。" + tempSet.toString());
                        loger.info("key。。。。。。。" + key.toString());
                        if (!tempSet.isEmpty()) {
                            for (String value : tempSet) {
                                jedis.lpush(key, value);
                            }
                        }
                        loger.info("返回结果。。。。。。。" + jedis.lrange(key, 0, -1));
                    } else {
                        /*从外部加载数据*/
                        List<String> tempList = new ArrayList<String>();
                        loger.info("key不存在。。。。。。。");
                        /*从hdfs中获取*/
//                        JavaDStream<String> hdfsSource = context.textFileStream("E:\\search\\BigData\\search\\BigData\\searchHisSpark\\searchHisSpark\\src\\main\\resources\\sparktext.txt");
                        String hdfs = "hdfs://172.31.10.151:8020/kafkatest.txt";
                        Configuration conf = new Configuration();
                        FileSystem fs = FileSystem.get(URI.create(hdfs), conf, "bigdata");
                        FSDataInputStream hdfsInStream = fs.open(new Path(hdfs));

                        byte[] ioBuffer = new byte[1024];
                        int readLen = hdfsInStream.read(ioBuffer);
                        while (readLen != -1) {
                            System.out.write(ioBuffer, 0, readLen);
                            String s = new String(ioBuffer, "UTF-8");
                            tempList.add(s);
                            loger.info("读取的值为。。。。。。。" + s);
                            loger.info("读取的tempList值为。。。。。。。" + tempList.toString());
                            readLen = hdfsInStream.read(ioBuffer);
                        }
                        hdfsInStream.close();
                        fs.close();
                    }
                }
            }
        });
        context.start();
        context.awaitTermination();
    }

    private static String getMstURL(String[] args) {
        String masterUrl = "local[2]";
        if (args.length > 0) {
            masterUrl = args[0];
        }
        return masterUrl;
    }
}

