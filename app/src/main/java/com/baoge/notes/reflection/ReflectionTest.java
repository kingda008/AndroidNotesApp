package com.baoge.notes.reflection;

import java.util.HashMap;
import java.util.Map;

public class ReflectionTest extends ReflectionSuperTest {

    public String name;
    public int sex;
    private String age;
    String school;
    private Map<String , Integer> score = new HashMap<>();
    public ReflectionTest() {
    }

    public ReflectionTest(String age, int sex) {
        this.age = age;
        this.sex = sex;
    }

    private ReflectionTest(String age) {
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setName2(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
