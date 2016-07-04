
package com.mw.java.test.utils;

import com.mw.java.test.Constant;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class CommonUtil {
    private static final String EMPTY = "";

    public static void testSplit() {
        String s = "\t\t";
        String[] split = s.split("\t", -1);
        System.out.println(split.length);
        for (String str : split) {
            System.out.println("->" + str);
        }

    }

    public static String getMd5BigInt(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return new BigInteger(md5_16(str), 16).toString();
    }

    public static String md5_32(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return DigestUtils.md5Hex(str);

    }

    public static String md5_16(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return md5_32(str).substring(8, 24);

    }

    public static byte[] intToByteArray(int num) {
        byte[] result = new byte[4];
        result[0] = (byte) (num >>> 24);// 取最高8位放到0下标
        result[1] = (byte) (num >>> 16);// 取次高8为放到1下标
        result[2] = (byte) (num >>> 8); // 取次低8位放到2下标
        result[3] = (byte) (num); // 取最低8位放到3下标
        return result;
    }

    public static boolean isPrettyText(String text) {
        return text.matches("[0-9a-zA-Z\\.,]*");
    }

    public static String rettyText(String text, String split) {
        String[] split2 = text.split(split, 0 - 1);
        List<String> list = new ArrayList<String>();
        for (String str : split2) {
            if (StringUtils.isNotBlank(str)) {
                list.add(str);
            }
        }
        return StringUtils.join(list, split);
    }

    public static String list2String(List<String> list, String split, boolean sortReverse) {
        if (sortReverse) {
            Collections.reverse(list);
        }
        return StringUtils.join(list, split);
    }

    public static void main(String[] args) throws IOException {
        // System.out.println(readFileInJar("/template/autoMall"));

        System.out.println(formatePriceString("0.0000001"));

    }

    public static String joinString(String split, String... args) {
        List<String> list = new ArrayList<String>();
        for (String str : args) {
            if (StringUtils.isNotEmpty(str)) {
                list.add(str);
            }
        }
        return StringUtils.join(list, split);
    }

    public static String null2empty(String s) {
        return StringUtils.isEmpty(s) ? EMPTY : s;
    }

    public static String checkNull(String s) {
        return StringUtils.isEmpty(s) ? EMPTY : Constant.SEMICOLON;
    }

    public static List<String> distinctList(List<String> list) {
        List<String> newList = new ArrayList<String>();
        Set<String> flagSet = new HashSet<String>();
        for (String s : list) {
            if (!flagSet.contains(s)) {
                newList.add(s);
            }
            flagSet.add(s);
        }
        return newList;
    }

    public static String readFileInJar(String filePath) throws IOException {
        InputStream input = CommonUtil.class.getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public static String returnZeroIfEmpty(String str) {
        return StringUtils.isBlank(str) ? Constant.ZERO : str;
    }

    public static String formatePriceString(String price) {
        if (StringUtils.isBlank(price)) {
            return Constant.EMPTY;
        }
        if (price.matches(".*\\.0+")) {
            return price.replaceAll("\\.0+", Constant.EMPTY);
        }
        return price;
    }
}
