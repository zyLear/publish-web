package com.zylear.publish.web.enums;

/**
 * Created by xiezongyu on 2018/8/7.
 */
public enum SourceType {

    unknown(-1),
    web_78g(1),
    duowan(2);


    SourceType(Integer value) {
        this.value = value;
    }

    private Integer value;


    public Integer getValue() {
        return value;
    }

}
