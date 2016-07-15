package com.mw.java.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

import java.io.IOException;
import java.net.URI;

/**
 * Created by mawei on 2016/7/15.
 * java操作HDFS
 * 有了这个就可以在rdd的foreach中随机的加载数据，但是随着数据的增大这种方式显然不好
 */
public class JavaHDSF {
    public void WriteFile(String hdfs) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(hdfs), conf);
        FSDataOutputStream hdfsOutStream = fs.create(new Path(hdfs));
        hdfsOutStream.writeChars("hello lisi");
        hdfsOutStream.close();
        fs.close();
    }

    public void ReadFile(String hdfs) throws IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(hdfs), conf, "bigdata");
        FSDataInputStream hdfsInStream = fs.open(new Path(hdfs));

        byte[] ioBuffer = new byte[1024];
        int readLen = hdfsInStream.read(ioBuffer);
        while (readLen != -1) {
            System.out.write(ioBuffer, 0, readLen);
            readLen = hdfsInStream.read(ioBuffer);
        }
        hdfsInStream.close();
        fs.close();
    }

    //获取HDFS集群上所有节点名称信息
    public static void getDateNodeHost(String hdfsPath) throws IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf, "bigdata");
        DistributedFileSystem hdfs = (DistributedFileSystem) fs;
        DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();
        for (int i = 0; i < dataNodeStats.length; i++) {
            System.out.println("DataNode_" + i + "_Name:" + dataNodeStats[i].getHostName());
        }
    }

    /*
      * upload the local file to the hds
      * 路径是全路径
      */
    public static void uploadLocalFile2HDFS(String s, String d, String hdfsPath)
            throws IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf, "bigdata");
        Path src = new Path(s);
        Path dst = new Path(d);
        fs.copyFromLocalFile(src, dst);
        fs.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String hdfs = "hdfs://172.31.10.151:8020/testjava2hdfs.txt";
        JavaHDSF t = new JavaHDSF();
//        t.WriteFile(hdfs);
//        t.ReadFile(hdfs);
        getDateNodeHost(hdfs);
    }
}
