package test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class TestClass {
    /**
     * Created by mawei on 2016/7/7.
     * 利用hashmap计算数据出现的次数
     * 利用hashmap中的包含和key的唯一性
     */
    @Test
    public void testMapCount() {
        String lines = "长沙,湘潭,湘西,长沙,娄底,株洲,娄底";
        String[] citys = lines.split(",");
        Map<String, Object> cityMap = new HashMap<String, Object>();
        for (int i = 0; i < citys.length; i++) {
            if (cityMap.containsKey(citys[i])) {
                Integer count = (Integer) cityMap.get(citys[i]);
//                count++;
                cityMap.put(citys[i], ++count);
            } else {
                cityMap.put(citys[i], 1);
            }
        }
        System.out.printf(cityMap.toString());
    }
}
