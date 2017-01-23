package com.mw.java.test.framaker;

import com.mw.java.test.domain.User;
import com.mw.java.test.domain.Weibo;
import freemarker.template.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Created by wei.ma on 2017/1/23.
 */
public class FMTools {
    public static void printIT(Map root, String ftl) throws IOException, TemplateException {
        String ftlPath = FMTools.class.getClassLoader().getResource("com/mw/java/test/framaker").getPath().substring(1);
        System.out.println(ftlPath);
        Version version = new Version(2, 3, 23);
        Configuration cfg = new Configuration(version);
        /*指定模板存放位置*/
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        cfg.setObjectWrapper(new DefaultObjectWrapper(version));
        /*得到模板*/
        Template tmp = cfg.getTemplate(ftl);
        Writer out = new OutputStreamWriter(System.out);
        /*root 打印数据源*/
        tmp.process(root, out);
        out.flush();
        out.close();
    }

    @Test
    public void test_null() throws IOException, TemplateException {
        Map map = new HashMap<>();
        map.put("nullValue", null);
        map.put("nullValuestring", "nullValuestring");
        FMTools.printIT(map, "test_null.ftl");
    }


    @Test
    public void test_import() throws IOException, TemplateException {
        Map map = new HashMap<>();
        FMTools.printIT(map, "top_import.ftl");
    }


    @Test
    public void testBoolean_zhushi() throws IOException, TemplateException {
        User u = new User("zhang三", true, false);
        Map map = new HashMap<>();
        map.put("user", u);
        FMTools.printIT(map, "boolean.ftl");
    }

    @Test
    public void testmap_list_if() throws IOException, TemplateException {
        List list_str = new ArrayList<>();
        list_str.add("A1");
        list_str.add("A2");
        list_str.add("A3");

        List list_obj = new ArrayList<>();
        list_obj.add(new Weibo(1, "beijing"));
        list_obj.add(new Weibo(2, "shanghai"));
        list_obj.add(new Weibo(3, "广州"));

        Map map_list = new HashMap<>();
        map_list.put("list_str", list_str);
        map_list.put("list_obj", list_obj);

        Map map = new HashMap<>();
        map.put("map_list", map_list);
        FMTools.printIT(map, "map_list.ftl");
    }

    @Test
    public void testmap() throws IOException, TemplateException {
        Map map_str = new LinkedHashMap<>();
        map_str.put("key1", "A1");
        map_str.put("key2", "A2");
        map_str.put("key3", "A3");

        Map map_obj = new LinkedHashMap<>();
        map_obj.put("key1", new Weibo(1, "beijing"));
        map_obj.put("key2", new Weibo(2, "shanghai"));
        map_obj.put("key3", new Weibo(3, "广州"));

        Map map = new HashMap<>();
        map.put("map_str", map_str);
        map.put("map_obj", map_obj);
        FMTools.printIT(map, "map.ftl");
    }

    @Test
    public void testlist_map() throws IOException, TemplateException {
        Map map_str = new LinkedHashMap<>();
        map_str.put("key1", "A1");
        map_str.put("key2", "A2");
        map_str.put("key3", "A3");

        Map map_obj = new LinkedHashMap<>();
        map_obj.put("key1", new Weibo(1, "beijing"));
        map_obj.put("key2", new Weibo(2, "shanghai"));
        map_obj.put("key3", new Weibo(3, "广州"));

        List list_map = new ArrayList<>();
        list_map.add(map_str);
        list_map.add(map_obj);

        Map map = new HashMap<>();
        map.put("list_map", list_map);
        FMTools.printIT(map, "list_map.ftl");
    }

    @Test
    public void testset() throws IOException, TemplateException {
        Set set_str = new LinkedHashSet<>();
        set_str.add("A1");
        set_str.add("A2");
        set_str.add("A3");

        Set set_obj = new LinkedHashSet<>();
        set_obj.add(new Weibo(1, "beijing"));
        set_obj.add(new Weibo(2, "shanghai"));
        set_obj.add(new Weibo(3, "广州"));

        Map map = new HashMap<>();
        map.put("set_str", set_str);
        map.put("set_obj", set_obj);
        FMTools.printIT(map, "set.ftl");
    }

    @Test
    public void testlist() throws IOException, TemplateException {
        List list_str = new ArrayList<>();
        list_str.add("A1");
        list_str.add("A2");
        list_str.add("A3");

        List list_obj = new ArrayList<>();
        list_obj.add(new Weibo(1, "beijing"));
        list_obj.add(new Weibo(2, "shanghai"));
        list_obj.add(new Weibo(3, "广州"));

        Map map = new HashMap<>();
        map.put("list_str", list_str);
        map.put("list_obj", list_obj);
        FMTools.printIT(map, "list.ftl");
    }

    @Test
    public void testObjectArray() throws IOException, TemplateException {
        Weibo[] weibos = new Weibo[3];
        weibos[0] = new Weibo(1, "beijing");
        weibos[1] = new Weibo(2, "shanghai");
        weibos[2] = new Weibo(3, "广州");
        Map map = new HashMap<>();
        map.put("weibos", weibos);
        FMTools.printIT(map, "objArray.ftl");
    }

    @Test
    public void testArray() throws IOException, TemplateException {
        String[] strArray = new String[]{"a", "b", "c", "d"};
        Map map = new HashMap<>();
        map.put("strArray", strArray);
        FMTools.printIT(map, "strArray.ftl");
    }


    @Test
    public void testHelloworld() throws IOException, TemplateException {
        byte byteValue = -128;
        short shortValue = 10000;
        int intValue = 2000000;
        long longValue = 20000L;
        float floatValue = 100000F;
        double doubleValue = 20000D;
        char charValue = 'A';
        boolean booleanValue = false;

        Map map = new HashMap();
        map.put("byteValue", byteValue);
        map.put("shortValue", shortValue);
        map.put("intValue", intValue);
        map.put("longValue", longValue);
        map.put("floatValue", floatValue);
        map.put("doubleValue", doubleValue);
        map.put("charValue", charValue);
        map.put("booleanValue", booleanValue);

        FMTools.printIT(map, "test.ftl");
    }


}
