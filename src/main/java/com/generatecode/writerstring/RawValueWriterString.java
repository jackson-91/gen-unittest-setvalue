package com.generatecode.writerstring;

public class RawValueWriterString implements WriterString {
    @Override
    public String writeString(String fieldName, String fieldValue) {
        return fieldName + " = " + fieldValue;
    }

    @Override
    public String writeStringInSetMode(String objectName, String setterMethodName, String fieldValue) {
        return objectName + "." + setterMethodName + "(" + fieldValue + ")";
    }

    @Override
    public String writeConstructString(String fieldValue) {
        return fieldValue;
    }
}
