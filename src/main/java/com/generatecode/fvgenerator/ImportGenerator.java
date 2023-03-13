package com.generatecode.fvgenerator;

public class ImportGenerator implements FieldValueGenerator{
    @Override
    public String generateFieldValue(String type) throws ClassNotFoundException {
        return "import " + type;
    }
}
