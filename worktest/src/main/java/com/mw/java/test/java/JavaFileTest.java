package com.mw.java.test.java;

import org.junit.Test;

import java.io.*;

/**
 * Created by wei.ma on 2016/12/21.
 */
public class JavaFileTest {

    /**
     * 分割文件：
     */
    @Test
    public void splitFile() {
        splitFileDemo(new File("D:\\wallpaper_161103.zip"), 3);
    }

    private static void splitFileDemo(File src, int m) {
        if (src.isFile()) {
            //获取文件的总长度
            long l = src.length();
            //获取文件名
            String fileName = src.getName().substring(0, src.getName().indexOf("."));
            //获取文件后缀
            String endName = src.getName().substring(src.getName().lastIndexOf("."));
            System.out.println(endName);
            InputStream in = null;
            try {
                in = new FileInputStream(src);
                for (int i = 1; i <= m; i++) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(src.getParent()).append("\\").append(fileName)
                            .append("_data").append(i).append(endName);
                    System.out.println(sb.toString());
                    File file2 = new File(sb.toString());  //创建写文件的输出流 21
                    OutputStream out = new FileOutputStream(file2);
                    int len = -1;
                    byte[] bytes = new byte[3 * 1024 * 1024];
                    while ((len = in.read(bytes)) != -1) {
                        out.write(bytes, 0, len);
                        if (file2.length() > (l / m)) {
                            break;
                        }
                    }
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void joinFile() {
        joinFileDemo("D:\\wallpaper_161103_data1.zip", "D:\\wallpaper_161103_data2.zip", "D:\\wallpaper_161103_data3.zip");
    }

    private static void joinFileDemo(String... src) {
        for (int i = 0; i < src.length; i++) {
            File file = new File(src[i]);
            String fileName = file.getName().substring(0, file.getName().indexOf("_"));
            String endName = file.getName().substring(file.getName().lastIndexOf("."));
            StringBuffer sb = new StringBuffer();
            sb.append(file.getParent()).append("\\").append(fileName).append(endName);
            System.out.println(sb.toString());
            InputStream in = null;
            OutputStream out = null;
            try { //读取小文件的输入流
                in = new FileInputStream(file);
                //写入大文件的输出流
                File file2 = new File(sb.toString());
                out = new FileOutputStream(file2, true);
                int len = -1;
                byte[] bytes = new byte[3 * 1024 * 1024];
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("文件合并完成！");
    }

}


//文件分割的方法（方法内传入要分割的文件路径以及要分割的份数） 2 { 3  { 4  5  6  8  10  11  12 try { 13 in = new FileInputStream(src); 14 for(int i = 1; i <= m; i++) { 15 StringBuffer sb = new StringBuffer; 16 sb.append(src.getParent).append("\\").append(fileName) 17 .append("_data").append(i).append(endName); 18 System.out.println(sb.toString); 19 File file2 = new File(sb.toString); 20 //创建写文件的输出流 21 OutputStream out = new FileOutputStream(file2); 22 int len = -1; 23 byte bytes = new byte[10*1024*1024]; 24 while((len = in.read(bytes))!=-1) { 25 out.write(bytes, 0, len); 26 if(file2.length > (l / m)) { 27 break; 28 } 29 } 30 out.close; 31 } 32 } catch (Exception e) { 33 e.printStackTrace; 34 } 35 finally { 36 try { 37 in.close; 38 } catch (IOException e) { 39 e.printStackTrace; 40 } 41 } 42 } 43 }
//        Java合并文件的方法：
//        1 //文件合并的方法（传入要合并的文件路径） 2
