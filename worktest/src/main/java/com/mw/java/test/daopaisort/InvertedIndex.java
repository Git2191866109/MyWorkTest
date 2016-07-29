package com.mw.java.test.daopaisort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by mawei on 2016/7/29.
 */
public class InvertedIndex {
    final static Logger loger = LoggerFactory.getLogger(InvertedIndex.class);

    /**
     * map
     */
    public static class InvertedIndexMapper extends Mapper<Object, Text, Text, Text> {
        private Text keyInfo = new Text();  //存储单词和URI的组合
        private Text valueInfo = new Text();//存储词频
        private FileSplit split;            //存储Split对象

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            //获得<key,value>对所属的FileSplit对象
            split = (FileSplit) context.getInputSplit();
            StringTokenizer itr = new StringTokenizer(value.toString());

            while (itr.hasMoreTokens()) {
                //key值由单词和URI组成，如"MapReduce:1.txt"
                keyInfo.set(itr.nextToken() + ":" + split.getPath().getName().toString());
                // 词频初始为1
                valueInfo.set("1");
                loger.debug(keyInfo.toString());
                loger.debug(valueInfo.toString());
                context.write(keyInfo, valueInfo);
            }
        }
    }

    /**
     * combiner将key值相同的value值累加，得到一个单词在文档中的词频
     */
    public static class InvertedIndexCombiner extends Reducer<Text, Text, Text, Text> {
        private Text info = new Text();

        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            //统计词频
            int sum = 0;
            for (Text value : values) {
                sum += Integer.parseInt(value.toString());
            }
            int splitIndex = key.toString().indexOf(":");

            //重新设置value值由URI和词频组成
            info.set(key.toString().substring(splitIndex + 1) + ":" + sum);
            //重新设置key值为单词
            key.set(key.toString().substring(0, splitIndex));
            loger.debug(key.toString());
            loger.debug(info.toString());
            context.write(key, info);
        }
    }

    /**
     * reduce 上述两个过程后，Reduce过程只需将相同key值的value值组合成倒排索引文件所需的格式即可，剩下的事情就可以直接交给MapReduce框架进行处理了
     */

    public static class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {
        private Text result = new Text();

        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            String fileList = new String();
            for (Text value : values) {
                fileList += value.toString() + ";";
            }
            result.set(fileList);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
//        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
//        if (otherArgs.length != 2) {
//            System.err.println("Usage: wordcount <in> <out>");
//            System.exit(2);
//        }
        Job job = new Job(conf, "InvertedIndex");

        job.setJarByClass(InvertedIndex.class);
        job.setMapperClass(InvertedIndexMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setCombinerClass(InvertedIndexCombiner.class);
        job.setReducerClass(InvertedIndexReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        Path input = new Path("E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\daopaisort");
        Path output = new Path("E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\daopaisort\\out");
        recreateFolder(output, conf);
        FileInputFormat.addInputPath(job, input);
        FileOutputFormat.setOutputPath(job, output);
//        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
//        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    private static void recreateFolder(Path path, Configuration conf) throws IOException {
        FileSystem fs = path.getFileSystem(conf);
        if (fs.exists(path)) {
            fs.delete(path, true);
        }
    }
}
