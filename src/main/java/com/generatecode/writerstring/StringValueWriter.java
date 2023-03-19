package com.generatecode.writerstring;

import com.generatecode.utils.ObjectUtils;

import java.lang.ref.ReferenceQueue;

public class StringValueWriter implements WriterString {

    public static final String RAW_SET_VALUE_TEMPLATE = "${fieldName} = \"${fieldValue}\"";

    public static final String SETTER_VALUE_TEMPLATE = "${objectName}.${setterMethodName}(\"${fieldValue}\")";

    public static final String RAW_VALUE_TEMPLATE = "\"${fieldValue}\"";

    @Override
    public String writeString(String fieldName, String fieldValue) {
        String replace = RAW_SET_VALUE_TEMPLATE.replace(ObjectUtils.FIELD_NAME, fieldName).replace(ObjectUtils.FIELD_VALUE, fieldValue);
        return replace;
    }

    @Override
    public String writeStringInSetMode(String objectName, String setterMethodName, String fieldValue) {
        String replace = SETTER_VALUE_TEMPLATE.replace(ObjectUtils.OBJECT_NAME, objectName)
                .replace(ObjectUtils.SETTER_METHOD_NAME, setterMethodName)
                .replace(ObjectUtils.FIELD_VALUE, fieldValue);
        return replace;
    }

    @Override
    public String writeConstructString(String fieldValue) {
        String replace = RAW_VALUE_TEMPLATE.replace(ObjectUtils.FIELD_VALUE, fieldValue);
        return replace;
    }
}
