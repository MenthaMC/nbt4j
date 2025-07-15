package me.coderfrish.test;

import me.coderfrish.nbt.type.iterator.IntArrayTag;
import org.junit.jupiter.api.Test;

public class ArrayTest {
    @Test
    public void test() {
        IntArrayTag tag = new IntArrayTag(15);

        tag.set(0, 52);
        for (int i : tag) {
            System.out.println(i);
        }
    }
}
