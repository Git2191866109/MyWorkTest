package com.mw.java.test.initdata;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by mawei on 2016/6/24.
 */
public class TestGetInitData implements InitializingBean{
    @Autowired
    private InitializeData initializeData;

    /**
     * 获取初始化的数据
     *
     * @param key
     * @return
     */
    public String getStringInitData(String key) {
        String result = null;
        //获取系统初始化配置appid
        if (initializeData != null) {
            Map<String, List<Object>> map = initializeData.getConfigFileData();
            List<Object> dmList = map.get(key);
            //如果没有找到匹配的appid，认为是非法访问，进入错误页
            if (dmList != null) {
                //读取个系统的域名或IP
                for (Object obj : dmList) {
                    if (obj instanceof String) {
                        result = obj.toString();
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TestGetInitData testGetInitData = new TestGetInitData();
        String resutl = testGetInitData.getStringInitData("redis_expire_time");
        System.out.println(resutl);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
