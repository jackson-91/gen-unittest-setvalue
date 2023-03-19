# genUnitTestSetValue
生成单元测试类的赋值代码


## _Demo 1_

1. 首先新建 `Student`, `Score`, `SexEnum`, `PositionEnum`, `Course` 这几个类
2. 测试生成对象代码：

```
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

    }
```

运行以上代码会在控制台输出：
```
    import com.test.generatecode.entity.Student;
    import com.test.generatecode.entity.Course;
    import com.test.generatecode.entity.Score;
    import java.lang.String;
    import com.test.generatecode.entity.SexEnum;
    import com.test.generatecode.entity.PositionEnum;
    import java.util.List;
    import java.util.ArrayList;
    
    Student student;
    Score score;
    score = new Score();
    score.setMath(52);
    score.setEng(86);
    student = new Student(score,"hello");
    student.setStudentNo("hello");
    student.setStudentName("hello");
    student.setSex(SexEnum.M);
    student.setAge(84);
    student.setAddress("hello");
    student.setPosition(PositionEnum.MONITOR);
    Score score1;
    score1 = new Score();
    score1.setMath(40);
    score1.setEng(83);
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
```

3. 将以上输出的内容贴到类中执行，并将生成的 `Student` 打印出来

```
    private static String testObjectString() {
        /**
         *  此处粘贴上述生成的代码，这里省略
         */
    
        return JSON.toJSONString(student);
    }
```

运行赋值代码后输出：

```
[{"courseName":["hello","hello","hello"]},{"courseName":["hello","hello","hello"]},{"courseName":["hello","hello","hello"]}]
```




## _Demo 2_

1. 测试生成列表代码：
```
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

    }

```

运行以上代码会在控制台输出：
```
    import com.test.generatecode.entity.Course;
    import java.lang.String;
    import java.util.List;
    import java.util.ArrayList;
    
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
```

2. 将以上内容贴到类中运行，并将list序列化打印出来
```
    private String testListString() {
        /**
         * 此处粘贴上面控制台输出的内容，这里省略
         * /

        return JSON.toJSONString(list);
    }

```

序列化list后的结果是：
```
[{"courseName":["hello","hello","hello"]},{"courseName":["hello","hello","hello"]},{"courseName":["hello","hello","hello"]}]
```
