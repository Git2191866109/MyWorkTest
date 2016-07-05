package com.mw.java.test.FreeLoadFile;

import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * Created by mawei on 2016/6/24.
 */
public class FreeLoadFile {
    /**
     * 功能：在系统中随意添加properties文件
     * 在spring中只需要配置改文件的路径
     * 然后我们在系统中使用的时候，只需要提供想要的key，然后输入，然后就能产出我们已经配置好的值
     * 最终的目标是：我们按照一定的规则对文件消息
     */
    @Test
    public void getProValue() {
        MessageSource source = new ClassPathXmlApplicationContext("testapp.xml");
        String message_format = source.getMessage("message", null, "Default", null);
        String message_expection = source.getMessage("argument.required", new Object[]{"第一个测试参数", "第二个测试参数"}, "Default", Locale.US);
        String message_test = source.getMessage("key1", null, "Default", Locale.US);
        System.out.println(message_format);
        System.out.println(message_expection);
        System.out.println(message_test);
    }
}
