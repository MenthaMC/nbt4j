package me.coderfrish.test;

import me.coderfrish.nbt.NBT;
import me.coderfrish.nbt.NBTInput;
import me.coderfrish.nbt.NBTOutput;
import me.coderfrish.nbt.type.*;
import me.coderfrish.nbt.type.element.NBTElement;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class NBTest {
    @Test
    public void nbtTest() {
        long time = System.currentTimeMillis();
//        try(NBTOutput output = new NBTOutput(new File("D:\\NBT\\src\\test\\resources\\test.nbt"))) {
//            TagObject entries = new TagObject();
//            entries.set("Hello", "hello");
//            entries.set("ss", 5);
//
//            TagByteArray array = new TagByteArray(new byte[]{21, 61, 80, 91});
//            Collection<String> list = new HashSet<>();
//            list.add("Hello");
//            list.add("fsdf");
//            list.add("sss");
//
//            entries.set("sss", array);
//            entries.set("sss545", list);
//            entries.set("sssss", true);
//
//            me.coderfrish.test.Test test = new me.coderfrish.test.Test();
//            test.age = 14;
//            test.name = "Frish";
//            test.phone = 65465;
//
//            entries.set("sssssss", test);
//
//            output.writeUnnamed(entries);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        try(NBTInput input = new NBTInput(new File("D:\\NBT\\src\\test\\resources\\test.nbt"))) {
//            TagObject entries = input.readUnnamed();
//            System.out.println(entries.get("sssssss").getObject(me.coderfrish.test.Test.class));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try(RandomAccessFile raf = new RandomAccessFile(new File("D:\\NBT\\src\\test\\resources\\snbt.txt"), "rw")) {
//            byte[] buffer = new byte[(int) raf.length()];
//            raf.read(buffer);
//
//            System.out.println(NBT.readUnnamedSNBT(new String(buffer, StandardCharsets.UTF_8)).get("minecraft:dimension_type").getAsObject().get("type").getAsString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(System.currentTimeMillis() - time);
    }
}
