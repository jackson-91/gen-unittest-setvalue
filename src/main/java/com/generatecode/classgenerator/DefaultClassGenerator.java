package com.generatecode.classgenerator;

import com.generatecode.ovsetter.ObjectGenerator;
import com.generatecode.utils.ObjectUtils;

public class DefaultClassGenerator {

    public String generateClass(String type) throws ClassNotFoundException {
        Class<?> javaClass = null;
        try {
            javaClass = Class.forName(type);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (javaClass == null) {
            return "";
        }
        ObjectGenerator objectGenerator = new ObjectGenerator();

        String objectName = ObjectUtils.getObjectName(javaClass.getSimpleName());
        String declareString = objectGenerator.declareObject(javaClass.getSimpleName(), objectName);
        String objectGenString = objectGenerator.generateFieldValue(javaClass, objectName);

        return declareString + objectGenString;
    }

}
