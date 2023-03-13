package com.generatecode.ovsetter;

import java.util.ArrayList;

public interface CollectionValueGenerator {

    String DEFAULT_LIST_TYPE_NAME = ArrayList.class.getSimpleName();

    String DEFAULT_SET_TYPE_NAME = "HashSet";

    String DEFAULT_MAP_TYPE_NAME = "HashMap";

    String generateElement(Class genericType, String collectionObjectName, Integer num) throws ClassNotFoundException ;

    String constructDefaultList(String objectName, Integer num) throws ClassNotFoundException;

    String addObjectIntoCollection(String listObjName, String objectName);

}
