package com.generatecode.fvgenerator;

import com.generatecode.utils.ObjectUtils;

public class EnumGenerator implements FieldValueGenerator{

    public static final String ENUM_VALUE_TEMPLATE = "${typeName}.${fieldValue}";

    @Override
    public String generateFieldValue(String fieldType) throws ClassNotFoundException {
        Class<?> fieldClass = Class.forName(fieldType);
        Object[] enumConstants = fieldClass.getEnumConstants();
        String replace = ENUM_VALUE_TEMPLATE.replace(ObjectUtils.TYPE_NAME, fieldClass.getSimpleName())
                .replace(ObjectUtils.FIELD_VALUE, enumConstants[0].toString());
        return  replace;
    }
}
