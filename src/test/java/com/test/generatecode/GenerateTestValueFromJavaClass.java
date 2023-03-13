package com.test.generatecode;

import com.alibaba.fastjson.JSON;
import com.generatecode.DefaultListGenerator;
import com.generatecode.classgenerator.DefaultClassGenerator;
import com.generatecode.ovsetter.ObjectGenerator;
import com.generatecode.utils.ImportPackageUtil;
import com.test.generatecode.entity.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenerateTestValueFromJavaClass {


    @Test
    public void testGenerateObject() throws ClassNotFoundException {
        String className = "com.test.generatecode.entity.Student";

        DefaultClassGenerator defaultClassGenerator = new DefaultClassGenerator();
        String objectString = defaultClassGenerator.generateClass(className);


        Set<String> importString = ImportPackageUtil.getImportSet();
        System.out.println(String.join(ObjectGenerator.LINE_SEPARATOR, importString.toArray(new String[0])) + ";");

        System.out.println();

        // 输出赋值代码
        System.out.println(objectString);

        System.out.println(testObjectString());
        /**
         *
         * 运行赋值代码后输出:
         * {"address":"hello","age":16,"courses":[{"courseName":["hello","hello","hello"]},{"courseName":["hello","hello","hello"]},{"courseName":["hello","hello","hello"]}],"position":"MONITOR","score":{"eng":14,"math":94},"sex":"M","studentName":"hello","studentNo":"hello"}
         *
         */

    }

    @Test
    public void testGenerateList() throws ClassNotFoundException {
        String listClassName = "com.test.generatecode.entity.Course";
        String listTypeName = "java.util.List";
        DefaultListGenerator defaultListGenerator = new DefaultListGenerator();
        String listString = defaultListGenerator.generateClass(listTypeName, Class.forName(listClassName));

        Set<String> importString = ImportPackageUtil.getImportSet();
        System.out.println(String.join(ObjectGenerator.LINE_SEPARATOR, importString.toArray(new String[0])) + ";");

        System.out.println();

        // 输出赋值代码
        System.out.println(listString);

        System.out.println(testListString());
        /**
         *
         * 运行赋值代码后输出:
         * [{"courseName":["hello","hello","hello"]},{"courseName":["hello","hello","hello"]},{"courseName":["hello","hello","hello"]}]
         *
         */
    }


    private static String testObjectString() {
        Student student;
        Score score;
        score = new Score();
        score.setMath(21);
        score.setEng(41);
        student = new Student(score,"hello");
        student.setStudentNo("hello");
        student.setStudentName("hello");
        student.setSex(SexEnum.M);
        student.setAge(16);
        student.setAddress("hello");
        student.setPosition(PositionEnum.MONITOR);
        Score score1;
        score1 = new Score();
        score1.setMath(94);
        score1.setEng(14);
        student.setScore(score1);
        List list;
        list = new ArrayList(10);
        Course course;
        course = new Course();
        List list1;
        list1 = new ArrayList(10);
        String string;
        string = new String("hello");
        list1.add(string);
        String string1;
        string1 = new String("hello");
        list1.add(string1);
        String string2;
        string2 = new String("hello");
        list1.add(string2);
        course.setCourseName(list1);
        list.add(course);
        Course course1;
        course1 = new Course();
        List list2;
        list2 = new ArrayList(10);
        String string3;
        string3 = new String("hello");
        list2.add(string3);
        String string4;
        string4 = new String("hello");
        list2.add(string4);
        String string5;
        string5 = new String("hello");
        list2.add(string5);
        course1.setCourseName(list2);
        list.add(course1);
        Course course2;
        course2 = new Course();
        List list3;
        list3 = new ArrayList(10);
        String string6;
        string6 = new String("hello");
        list3.add(string6);
        String string7;
        string7 = new String("hello");
        list3.add(string7);
        String string8;
        string8 = new String("hello");
        list3.add(string8);
        course2.setCourseName(list3);
        list.add(course2);
        student.setCourses(list);

        return JSON.toJSONString(student);
    }

    private String testListString() {
        List list;
        list = new ArrayList(10);
        Course course;
        course = new Course();
        List list1;
        list1 = new ArrayList(10);
        String string;
        string = new String("hello");
        list1.add(string);
        String string1;
        string1 = new String("hello");
        list1.add(string1);
        String string2;
        string2 = new String("hello");
        list1.add(string2);
        course.setCourseName(list1);
        list.add(course);
        Course course1;
        course1 = new Course();
        List list2;
        list2 = new ArrayList(10);
        String string3;
        string3 = new String("hello");
        list2.add(string3);
        String string4;
        string4 = new String("hello");
        list2.add(string4);
        String string5;
        string5 = new String("hello");
        list2.add(string5);
        course1.setCourseName(list2);
        list.add(course1);
        Course course2;
        course2 = new Course();
        List list3;
        list3 = new ArrayList(10);
        String string6;
        string6 = new String("hello");
        list3.add(string6);
        String string7;
        string7 = new String("hello");
        list3.add(string7);
        String string8;
        string8 = new String("hello");
        list3.add(string8);
        course2.setCourseName(list3);
        list.add(course2);


        return JSON.toJSONString(list);
    }
}
