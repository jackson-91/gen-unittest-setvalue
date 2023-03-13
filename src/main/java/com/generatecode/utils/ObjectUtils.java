package com.generatecode.utils;

import java.util.HashMap;
import java.util.Map;

public class ObjectUtils {

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
