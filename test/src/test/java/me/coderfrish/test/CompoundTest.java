package me.coderfrish.test;

import me.coderfrish.nbt.type.NBTCompound;
import me.coderfrish.nbt.type.NBTPrimitive;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CompoundTest {
    @Test
    public void test() {
        NBTCompound compound = new NBTCompound();
        compound.put("name", "Frish2021");
        compound.put("age", 15);
        compound.put("time", 68465465L);
    }
}
