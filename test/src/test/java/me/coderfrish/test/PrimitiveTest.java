package me.coderfrish.test;

import me.coderfrish.nbt.type.NBTPrimitive;
import org.junit.jupiter.api.Test;

public class PrimitiveTest {
    @Test
    public void test() {
        NBTPrimitive primitive = new NBTPrimitive(10);
        System.out.println(primitive.getAsInt());
    }
}
