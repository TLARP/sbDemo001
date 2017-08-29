package com.devi.test.xml;

import com.devi.test.SpringbootexampleApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package XmlTest
 * @Copyright: Copyright (c) 2016
 *
 * @date 2017/5/18 15:40
 * version V1.0.0
 */
public class XmlTest extends SpringbootexampleApplicationTests {

    @Autowired
    private HelloService helloService;

    @Test
    public void testSay(){
        helloService.say("你好吗？");
    }
}
