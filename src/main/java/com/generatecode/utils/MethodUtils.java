package com.generatecode.utils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class MethodUtils {

    public static Set<String> getClassFieldSetterMethods(Class klass) {

        Method[] methods = klass.getMethods();
        Set<String> methodsNameSet = new HashSet<>();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                methodsNameSet.add(method.getName());
            }
        }
        return methodsNameSet;
    }

}
