package me.coderfrish.test;

import io.github.xiefrish2021.tag.IntTag;
import io.github.xiefrish2021.tag.array.ByteArrayTag;
import io.github.xiefrish2021.tag.compound.CompoundTag;
import io.github.xiefrish2021.tag.StringTag;
import io.github.xiefrish2021.NBT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class NBTest {
    public static void main(String[] args) {
        test();
    }

//    @Test
    public static void test() {
        NBT nbt = NBT.getInstance();
        CompoundTag tag = new CompoundTag();
        tag.put("test", new StringTag("Frish2021"));
        tag.put("sfsdfs", new IntTag(4654));
        tag.put("he", new ByteArrayTag("sss".getBytes(StandardCharsets.UTF_8)));

        try {
            nbt.writeUnnamedNBT(tag, new FileOutputStream("D:\\NBT\\src\\test\\resources\\test.nbt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
