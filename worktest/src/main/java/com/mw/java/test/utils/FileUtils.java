package com.mw.java.test.utils;

import org.junit.Test;

import java.io.File;

/**
 * Created by wei.ma on 2016/9/1.
 */
public class FileUtils {
    @Test
    public void testGetFile() {
        String fileName = "JSONUtils";
        File file = org.apache.commons.io.FileUtils.getFile(fileName);
        System.out.println(file.getName());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getPath());
    }
}
