package com.generatecode.fvgenerator;

import com.generatecode.utils.ImportPackageUtil;

public class ImportGenerator implements FieldValueGenerator{

    public static final String IMPORT_TEMPLATE = "import ${fullClassName}";

    @Override
    public String generateFieldValue(String type) throws ClassNotFoundException {
        String replace = IMPORT_TEMPLATE.replace(ImportPackageUtil.FULL_CLASS_NAME, type);
        return replace;
    }
}
