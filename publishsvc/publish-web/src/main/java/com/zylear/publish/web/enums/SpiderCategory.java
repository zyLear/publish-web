package com.zylear.publish.web.enums;

/**
 * Created by xiezongyu on 2018/8/7.
 */
public enum SpiderCategory {

    unknown(-1),
    pubg(1),
    lol(2);


    SpiderCategory(Integer value) {
        this.value = value;
    }

    private Integer value;


    public Integer getValue() {
        return value;
    }

}
