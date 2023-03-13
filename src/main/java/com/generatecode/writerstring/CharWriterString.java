package com.generatecode.writerstring;

public class CharWriterString implements WriterString {
    @Override
    public String writeString(String fieldName, String fieldValue) {
        if (fieldValue.length() != 1) {
            throw new IllegalArgumentException("char value must be 1 character");
        }
        return fieldName + " = " + "'" + fieldValue + "'";
    }

    @Override
    public String writeStringInSetMode(String objectName, String setterMethodName, String fieldValue) {
        return objectName + "." + setterMethodName + "('" + fieldValue + "')";
    }

    @Override
    public String writeConstructString(String fieldValue) {
        return "'" + fieldValue + "'";
    }
}
