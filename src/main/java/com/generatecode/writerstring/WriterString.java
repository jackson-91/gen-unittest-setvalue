package com.generatecode.writerstring;

public interface WriterString {

    String writeString(String fieldName, String fieldValue);

    String writeStringInSetMode(String objectName, String setterMethodName, String fieldValue);

    String writeConstructString(String fieldValue);

}
