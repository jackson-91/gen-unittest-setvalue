package com.generatecode.fvgenerator;

import java.util.Random;

public class IntegerGenerator implements FieldValueGenerator{
    @Override
    public String generateFieldValue(String type) {
        Random random = new Random();
        return Integer.valueOf(random.nextInt(100)).toString();
    }
}
