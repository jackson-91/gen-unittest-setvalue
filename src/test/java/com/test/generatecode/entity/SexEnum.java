package com.test.generatecode.entity;

public enum SexEnum {

    M(0, "M", "Men"),
    F(1, "F", "Female");

    private Integer digitCode;

    private String abbreviateCode;

    private String fullCode;

    SexEnum(Integer digitCode, String abbr, String fullCode) {
        this.digitCode = digitCode;
        this.abbreviateCode = abbr;
        this.fullCode = fullCode;
    }
}
