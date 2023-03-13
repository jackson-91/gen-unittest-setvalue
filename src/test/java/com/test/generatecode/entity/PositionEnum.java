package com.test.generatecode.entity;

public enum PositionEnum {

    MONITOR("Monitor", "班长"),
    VICE_MONITOR("Vice Monitor", "副班长"),
    CLASSMATE("Classmate", "同学");

    private String enPositionName;

    private String chPositionName;

    PositionEnum(String enPositionName, String chPositionName) {
        this.enPositionName = enPositionName;
        this.chPositionName = chPositionName;
    }
}
