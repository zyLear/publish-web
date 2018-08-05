package com.zylear.publish.web.manager;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by xiezongyu on 2018/8/5.
 */
@Component
public class TestManager {

    @PostConstruct
    public void init() {
        System.out.println("something");
    }

}
