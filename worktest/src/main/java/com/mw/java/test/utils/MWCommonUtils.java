package com.mw.java.test.utils;

import com.mw.java.test.Constant;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

/**
 * Created by mawei on 2016/6/23.
 */
public class MWCommonUtils {
    /**
     * 从jar包中读取文件
     * this是不能在static（静态）方法或者static块中使用的，原因是static类型的方法或者代码块是属于类本身的，不属于某个对象，
     * 而this本身就代表当前对象，而静态方法或者块调用的时候是不用初始化对象的。
     *
     * @param path
     * @return
     */
    public static String readFileInJar(String path) throws IOException {
//        InputStream inputStream = MWCommonUtils.class.getResourceAsStream(path);
        InputStream inputStream = Object.class.getResourceAsStream(path);
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
        String sourceStr = Constant.EMPTY;

        while ((sourceStr = bufReader.readLine()) != null) {
            System.out.println(sourceStr.toString());
        }
        return sourceStr;
    }

    /**
     * 读取Properties文件的例子
     * File: TestProperties.java
     */
    @Test
    public void testProperties() throws IOException {
        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream("/properties/test.properties");
        prop.load(in);
        String value = prop.getProperty("key1").trim();
        System.out.println(value);
    }

    /**
     * 获取项目的相对路径下文件的绝对路径
     * 目标文件的父目录,例如说,工程的目录下,有lib与bin和conf目录,那么程序运行于lib or
     * bin,那么需要的配置文件却是conf里面,则需要找到该配置文件的绝对路径
     * 文件名
     *
     * @return一个绝对路径
     */
    @Test
    public void testGetPath() {
        String path = null;
        String fileName = "CarParts";
        String parentDir = "properties";
        String userdir = System.getProperty("user.dir");
        String userdirName = new File(userdir).getName();
        if (userdirName.equalsIgnoreCase("lib") || userdirName.equalsIgnoreCase("bin")) {
            File newf = new File(userdir);
            File newp = new File(newf.getParent());
            if (fileName.trim().equals("")) {
                path = newp.getPath() + File.separator + parentDir;
            } else {
                path = newp.getPath() + File.separator + parentDir + File.separator + fileName;
            }
        } else {
            if (fileName.trim().equals("")) {
                path = userdir + File.separator + parentDir;
            } else {
                path = userdir + File.separator + parentDir + File.separator
                        + fileName;
            }
        }
        System.out.println(path);
//        return path;
    }

    public static void main(String[] args) {
        String path = "/template/carParts.txt";
        try {
            System.out.println(readFileInJar(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
