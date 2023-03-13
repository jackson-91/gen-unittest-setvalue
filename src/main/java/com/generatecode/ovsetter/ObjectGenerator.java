package com.generatecode.ovsetter;


import com.generatecode.fvgenerator.EnumGenerator;
import com.generatecode.fvgenerator.FieldValueGenerator;
import com.generatecode.fvgenerator.IntegerGenerator;
import com.generatecode.fvgenerator.StringGenerator;
import com.generatecode.utils.ImportPackageUtil;
import com.generatecode.utils.MethodUtils;
import com.generatecode.utils.ObjectUtils;
import com.generatecode.writerstring.RawValueWriterString;
import com.generatecode.writerstring.StringValueWriter;
import com.generatecode.writerstring.WriterString;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class ObjectGenerator implements RefObjectGenerator {

    public static String LINE_SEPARATOR = ";" + LineSeparator.Unix;

    private WriterString rawTypeWriter = new RawValueWriterString();
    private WriterString stringTypeWriter = new StringValueWriter();


    private FieldValueGenerator intGenerator = new IntegerGenerator();
    private FieldValueGenerator stringGenerator = new StringGenerator();
    private FieldValueGenerator enumGenerator = new EnumGenerator();
    private static ObjectGenerator objectGenerator = new ObjectGenerator();

    private static ListGenerator listGenerator = new ListGenerator();




    @Override
    public String generateFieldValue(Class javaClass, String objectName) throws ClassNotFoundException {
        ImportPackageUtil.addImportPackage(javaClass.getName());

        StringBuilder objectString = new StringBuilder();
        Set<String> setterMethodSet = MethodUtils.getClassFieldSetterMethods(javaClass);

        String constructorString = constructObjectString(javaClass, objectName);
        objectString.append(constructorString);

        if (javaClass == int.class || javaClass == Integer.class
                || javaClass == String.class || javaClass.isEnum()
                || javaClass == List.class) {
            return objectString.toString();
        }

        Field[] fields = javaClass.getDeclaredFields();
        String initString = initializeObject(objectName, fields, setterMethodSet);
        objectString.append(initString);

        return objectString.toString();
    }

    private String constructObjectString(Class javaClass, String objectName) throws ClassNotFoundException {

        StringBuilder objectString = new StringBuilder();
        Constructor[] constructors = javaClass.getConstructors();

        Constructor constructor = contructorPriorityAlgo(constructors);

        List<String> contructorField = new ArrayList<>();
        for (Class constructorFieldClass : constructor.getParameterTypes()) {
            String consFieldSimpleName = constructorFieldClass.getSimpleName();
            String consFieldTypeName = constructorFieldClass.getTypeName();
            if (constructorFieldClass == int.class || constructorFieldClass == Integer.class) {
                contructorField.add(appendConstructString(intGenerator.generateFieldValue(""), rawTypeWriter));
            }  else if (constructorFieldClass == String.class) {
                contructorField.add(appendConstructString(stringGenerator.generateFieldValue(""), stringTypeWriter));
            }else if (constructorFieldClass.isEnum()) {
                ImportPackageUtil.addImportPackage(consFieldTypeName);
                String rawValue = enumGenerator.generateFieldValue(consFieldTypeName);
                contructorField.add(appendConstructString(rawValue, rawTypeWriter));
            } else {
                String subObjectName = ObjectUtils.getObjectName(consFieldSimpleName);
                objectString.append(declareObject(consFieldSimpleName, subObjectName));
                String subObjectString = generateFieldValue(constructorFieldClass, subObjectName);
                objectString.append(subObjectString);
                contructorField.add(appendConstructString(subObjectName, rawTypeWriter));
            }
        }


        String constructorString = constructObject(javaClass.getSimpleName(), objectName, contructorField);
        objectString.append(constructorString);
        return objectString.toString();
    }

    private String initializeObject(String objectName, Field[] fields, Set<String> setterMethodSet) throws ClassNotFoundException {
        StringBuilder objectString = new StringBuilder();
        for (Field field : fields) {
            String fieldName = field.getName();
            String fieldTypeName = field.getType().getTypeName();
            if (field.getType() == int.class || field.getType() == Integer.class) {
                objectString.append(appendInitString(objectName, fieldName, intGenerator.generateFieldValue(fieldTypeName), rawTypeWriter, setterMethodSet) + LINE_SEPARATOR);
            } else if (field.getType() == String.class) {
                objectString.append(appendInitString(objectName, fieldName, stringGenerator.generateFieldValue(fieldTypeName), stringTypeWriter, setterMethodSet) + LINE_SEPARATOR);
            } else if (field.getType().isEnum()){
                ImportPackageUtil.addImportPackage(fieldTypeName);
                String rawValue = enumGenerator.generateFieldValue(fieldTypeName);
                objectString.append(appendInitString(objectName, fieldName, rawValue, rawTypeWriter, setterMethodSet) + LINE_SEPARATOR);
            } else if (field.getType() == List.class) {
                ImportPackageUtil.addImportPackage(fieldTypeName);
                String subObjectName = ObjectUtils.getObjectName(field.getType().getSimpleName());
                String declareString = listGenerator.declareList(field.getType().getSimpleName(), subObjectName);
                objectString.append(declareString);
                String listConstrctString = listGenerator.constructDefaultList(subObjectName, 10);
                Class genericType = listGenerator.getListGenericType(field);
                ImportPackageUtil.addImportPackage(genericType.getName());
                String objectGenString = listGenerator.generateElement(genericType, subObjectName, 3);
                objectString.append(listConstrctString);
                objectString.append(objectGenString);
                objectString.append(appendInitString(objectName, fieldName, subObjectName, rawTypeWriter, setterMethodSet) + LINE_SEPARATOR);
            }else {
                String subObjectName = ObjectUtils.getObjectName(field.getType().getSimpleName());
                objectString.append(declareObject(field.getType().getSimpleName(), subObjectName));
                String subObjectString = objectGenerator.generateFieldValue(field.getType(), subObjectName);
                objectString.append(subObjectString);
                objectString.append(appendInitString(objectName, fieldName, subObjectName, rawTypeWriter, setterMethodSet) + LINE_SEPARATOR);
            }
        }
        return objectString.toString();
    }

    private String appendInitString(String objectName, String fieldName, String fieldValue, WriterString writerString, Set<String> setterMethodSet) {
        String fieldSetterMethod = "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
        if (setterMethodSet.contains(fieldSetterMethod)) {
            return writerString.writeStringInSetMode(objectName, fieldSetterMethod, fieldValue);
        } else {
            return writerString.writeString(fieldName, fieldValue);
        }
    }


    private String appendConstructString(String fieldValue, WriterString writerString) {
        return writerString.writeConstructString(fieldValue);
    }


    public String declareObject(String type, String objectName) {
        return type + " " + objectName + LINE_SEPARATOR ;
    }


    private Constructor contructorPriorityAlgo(Constructor[] constructors) {
        Map<Integer, Constructor> priorityMap = new TreeMap<>();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            Integer weight = 0;
            for (Class parameterType : parameterTypes) {

                if (parameterType.isPrimitive() || parameterType.equals(Integer.class) || parameterType.equals(String.class)) {  // do nothing

                } else {
                    weight += 2;
                }
            }
            priorityMap.put(weight, constructor);
        }
        Set<Integer> prioritySet = priorityMap.keySet();
        return priorityMap.get(prioritySet.stream().findFirst().get());
    }

    private String constructObject(String objectTypeName, String objectName, List<String> constructorFields) {
        String join = String.join(",", constructorFields);
        return objectName + " = " + "new " + objectTypeName + "(" + join + ")" + LINE_SEPARATOR;
    }
}
