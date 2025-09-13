package me.coderfrish.test;

import me.coderfrish.nbt4j.StreamUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class ClassReferenceTest {
    @Test
    public void test() {
        Number i = 114514;
        System.out.println(i.getClass());
    }
}
