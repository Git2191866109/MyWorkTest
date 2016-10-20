package com.mw.java.test.utils;

import org.junit.Test;

import java.io.File;

/**
 * Created by wei.ma on 2016/9/1.
 */
public class FileUtils {
    String path = "E:\\mojiworkspace\\MyWorkTest\\worktest\\src\\main\\resources\\sql\\sql";
    String path1 = "\\mojiworkspace\\MyWorkTest\\worktest\\src\\main\\resources\\sql\\sql";
    String path2 = "mojiworkspace\\MyWorkTest\\worktest\\src\\main\\resources\\sql\\sql";
    String path3 = "MyWorkTest\\worktest\\src\\main\\resources\\sql\\sql";
    @Test
    public void testGetFile() {
//        String fileName = "JSONUtils";
//        File file = org.apache.commons.io.FileUtils.getFile(fileName);
        getFile(path);
        getFile(path1);
        getFile(path2);
        getFile(path3);
        System.out.println(System.getProperty("user.dir"));

    }

    private void getFile(String path) {
        File file = new File(path);
//        System.out.println(file.getName());
//        System.out.println("############################");
//        System.out.println(file.getAbsoluteFile());
//        System.out.println("############################");
//        System.out.println(file.getPath());
//        System.out.println("############################");

    }
}
