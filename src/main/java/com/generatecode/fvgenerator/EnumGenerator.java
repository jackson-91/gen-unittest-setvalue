package com.generatecode.fvgenerator;

public class EnumGenerator implements FieldValueGenerator{
    @Override
    public String generateFieldValue(String fieldType) throws ClassNotFoundException {
        Class<?> fieldClass = Class.forName(fieldType);
        Object[] enumConstants = fieldClass.getEnumConstants();
        return fieldClass.getSimpleName() + "." + enumConstants[0].toString();
    }
}
