package com.generatecode;

import com.generatecode.ovsetter.ListGenerator;
import com.generatecode.ovsetter.ObjectGenerator;
import com.generatecode.utils.ObjectUtils;

public class DefaultListGenerator {

    private ListGenerator listGenerator = new ListGenerator();

    public String generateClass(String type, Class genericType) throws ClassNotFoundException {
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
        String declareString = listGenerator.declareList(javaClass.getSimpleName(), objectName);
        String listConstrctString = listGenerator.constructDefaultList(objectName, 10);

//        listGenerator.generateElement(javaClass.ge)

        String objectGenString = listGenerator.generateElement(genericType, objectName, 3);

        return declareString + listConstrctString + objectGenString;
    }
}

// 通过反射获取泛型type
//    Field field = MyClass.class.getDeclaredField("myArrayListField");
//    Type genericFieldType = field.getGenericType();
//if(genericFieldType instanceof ParameterizedType){
//        ParameterizedType aType = (ParameterizedType) genericFieldType;
//        Type[] fieldArgTypes = aType.getActualTypeArguments();
//        for(Type fieldArgType : fieldArgTypes){
//        Class<?> fieldArgClass = (Class<?>) fieldArgType;
//        System.out.println("泛型类型: " + fieldArgClass);
//        }
//        }