package com.zylear.publish.web.enums;

/**
 * Created by xiezongyu on 2018/8/7.
 */
public enum SourceType {

    unknown(-1),
    web_78g(1),
    duowan(2),
    yxdown(3);


    SourceType(Integer value) {
        this.value = value;
    }

    private Integer value;


    public Integer getValue() {
        return value;
    }

    public static SourceType valueOf(Integer value) {
        for (SourceType sourceType : values()) {
            if (sourceType.getValue().equals(value)) {
                return sourceType;
            }
        }
        throw new RuntimeException("get sourceType fail. value:"+value);
    }

}
