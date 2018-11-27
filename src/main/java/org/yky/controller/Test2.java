package org.yky.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by hp on 2018/2/27.
 */
@ConfigurationProperties(prefix = "yky1")
@Component
public class Test2 {
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
