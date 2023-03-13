package com.generatecode.ovsetter;

import com.generatecode.utils.ImportPackageUtil;
import com.generatecode.utils.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListGenerator implements CollectionValueGenerator {

    private static ObjectGenerator objectGenerator = new ObjectGenerator();

    @Override
    public String generateElement(Class genericType, String listObjectName, Integer num) throws ClassNotFoundException {
        StringBuilder listStringBuilder = new StringBuilder();
//        initializeObject()
        for (int i=0;i<num;i++) {
            String objectName = ObjectUtils.getObjectName(genericType.getSimpleName());
            listStringBuilder.append(objectGenerator.declareObject(genericType.getSimpleName(), objectName));
            listStringBuilder.append(objectGenerator.generateFieldValue(genericType, objectName));
            listStringBuilder.append(addObjectIntoCollection(listObjectName, objectName));
        }

        return listStringBuilder.toString();
    }

    @Override
    public String constructDefaultList(String objectName, Integer num) throws ClassNotFoundException {
        String numString = num == null || num.equals(0) ? "" : num.toString();
        ImportPackageUtil.addImportPackage(ArrayList.class.getName());
        return objectName + " = " + "new " + DEFAULT_LIST_TYPE_NAME + "(" + num + ")" + ObjectGenerator.LINE_SEPARATOR;
    }

    @Override
    public String addObjectIntoCollection(String listObjName, String objectName) {
        return listObjName + ".add(" + objectName + ")" + ObjectGenerator.LINE_SEPARATOR;
    }

    public String declareList(String type, String objectName) {
        return type + " " + objectName + ObjectGenerator.LINE_SEPARATOR;
    }

    public Class getListGenericType(Field field) {
        Type genericFieldType = field.getGenericType();
        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericFieldType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            return (Class) actualTypeArguments[0];
        }
        throw new RuntimeException("未定义List泛型");
    }
}
