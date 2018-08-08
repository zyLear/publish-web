package com.zylear.publish.web.enums;

/**
 * Created by xiezongyu on 2018/8/7.
 */
public enum VideoType {

    unknown(-1),
    source_url(1),
    iframe(2),
    comtent_html(3);


    VideoType(Integer value) {
        this.value = value;
    }

    private Integer value;


    public Integer getValue() {
        return value;
    }

}
