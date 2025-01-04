package me.coderfrish.test;

import me.coderfrish.nbt.reflect.Serialization;

@Serialization
public class Student {
    public String name;
    public int age;
    public String clazz;

    public Student(String name, int age, String clazz) {
        this.name = name;
        this.age = age;
        this.clazz = clazz;
    }
}
