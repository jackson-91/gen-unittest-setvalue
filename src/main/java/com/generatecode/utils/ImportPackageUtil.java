package com.generatecode.utils;

import com.generatecode.fvgenerator.FieldValueGenerator;
import com.generatecode.fvgenerator.ImportGenerator;

import java.util.HashSet;
import java.util.Set;

public class ImportPackageUtil {

    private static Set<String> importStringSet = new HashSet<>();
    private static FieldValueGenerator importGenerator = new ImportGenerator();

    public static void addImportPackage(String fullClassName) throws ClassNotFoundException {
        importStringSet.add(importGenerator.generateFieldValue(fullClassName));
    }

    public static Set<String> getImportSet() {
        return importStringSet;
    }

}
