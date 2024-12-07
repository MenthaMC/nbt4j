package me.coderfrish.test;

import org.junit.jupiter.api.Test;
import xyz.frish2021.nbt.NBT;
import xyz.frish2021.nbt.compound.CompoundTag;
import xyz.frish2021.nbt.primitive.StringTag;
import xyz.frish2021.nbt.primitive.number.IntTag;

public class NBTest {
    @Test
    public void test() {
        NBT nbt = new NBT();
        CompoundTag tag = new CompoundTag();
        tag.put("test", new StringTag("Frish2021"));

        CompoundTag tag1 = new CompoundTag();
        tag1.put("test2", new StringTag("CoderFrish"));
        tag1.put("test3", new IntTag(123456));

        tag.put("test1", tag1);

        System.out.println(nbt.generateSNBT(tag));
    }
}
