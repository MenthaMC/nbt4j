package me.coderfrish.test;

import org.junit.jupiter.api.Test;
import xyz.frish2021.nbt.api.ITag;
import xyz.frish2021.nbt.api.NBT;
import xyz.frish2021.nbt.array.ByteArrayTag;
import xyz.frish2021.nbt.array.IntArrayTag;
import xyz.frish2021.nbt.compound.CompoundTag;
import xyz.frish2021.nbt.primitive.StringTag;
import xyz.frish2021.nbt.primitive.number.ByteTag;
import xyz.frish2021.nbt.primitive.number.IntTag;

public class NBTest {
    @Test
    public void test() {
        NBT nbt = NBT.newInstance();
//        CompoundTag tag = new CompoundTag();
//        tag.put("test", new StringTag("Frish2021"));
//
//        CompoundTag tag1 = new CompoundTag();
//        tag1.put("test2", new StringTag("CoderFrish"));
//        tag1.put("test3", new IntTag(123456));
//
//        tag.put("test1", tag1);
//
//        System.out.println(nbt.generateSNBT(tag));

//        for (Byte array : nbt.readUnnamedSNBT("{name: Frish2021, array: [B;1B, 2B, 3B]}").getByteArray("array")) {
//            System.out.println(array);
//        }

//        System.out.println(nbt.readUnnamedSNBT("{sss: [I;1,2,3]}").get("sss").type());
    }
}
