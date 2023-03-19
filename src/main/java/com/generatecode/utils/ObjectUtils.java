package com.generatecode.utils;

import java.util.HashMap;
import java.util.Map;

public class ObjectUtils {

    public static final String FIELD_NAME = "${fieldName}";

    public static final String FIELD_VALUE = "${fieldValue}";

    public static final String OBJECT_NAME = "${objectName}";

    public static final String SETTER_METHOD_NAME = "${setterMethodName}";

    public static final String TYPE_NAME = "${typeName}";

    static Map<String, Integer> objectNameNums = new HashMap<>();

    public static String getObjectName(String classSimpleName) {
        Integer objectNum = objectNameNums.getOrDefault(classSimpleName, 0);
        objectNameNums.put(classSimpleName, objectNum+1);
        String objectName = classSimpleName.substring(0, 1).toLowerCase() + classSimpleName.substring(1);
        if (objectNum.equals(0)) {
            return objectName;
        }
        return objectName+objectNum;

    }

}
