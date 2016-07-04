package com.mw.java.test.initdata;

import java.util.List;
import java.util.Map;

/**
 * Created by mawei on 2016/6/24.
 */
public class InitializeData {
    /**
     * 系统配置文件数据初始化存储对象
     */
    public Map<String, List<Object>> configFileData;

    public Map<String, List<Object>> getConfigFileData() {
        return configFileData;
    }

    public void setConfigFileData(Map<String, List<Object>> configFileData) {
        this.configFileData = configFileData;
    }
}
