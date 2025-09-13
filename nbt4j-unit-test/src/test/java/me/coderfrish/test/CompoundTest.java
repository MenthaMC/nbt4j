package me.coderfrish.test;

import me.coderfrish.nbt4j.*;
import me.coderfrish.nbt4j.CompoundTag;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CompoundTest {
//    @Test
//    @JunitPerfConfig(threads = 5, warmUp = 1_000, duration = 10_000, reporter = {HtmlReporter.class})
    public void test() {
        me.coderfrish.nbt4j.CompoundTag tags = new me.coderfrish.nbt4j.CompoundTag();
        assert tags.type() == TagType.COMPOUND;

        tags.addProperty("name", "Frish2021");
        tags.addProperty("age", 15);

        ListTag friends = new ListTag();
        friends.addProperty("Friends0");
        friends.addProperty("Friends1");
        friends.addProperty("Friends2");
        friends.addProperty("Friends3");
        friends.addProperty("Friends4");
        friends.addProperty("Friends5");
        friends.addProperty("Friends6");

        tags.add("friends", friends);

        try {
            byte[] png = Files.readAllBytes(Path.of("C:\\Users\\徐智轩\\Pictures\\5a2c6e120bc1ab4ee5d0e9542a507e0d30c415d5.jpg"));
            tags.addProperty("image", png);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long[] test = {5464645642346547555L, 57454532423545234L, 3524723453432654534L};
        tags.addProperty("test", test);

        int[] test0 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        tags.addProperty("test0", test0);

        tags.addProperty("id", 114514191981056546L);
        tags.addProperty("id0", (short) 10);
        tags.addProperty("id1", 114514);
        tags.addProperty("id2", (byte) 5);
        tags.addProperty("id3", 1.1F);
        tags.addProperty("id4", 1.1D);

        try(FileOutputStream outputStream = new FileOutputStream("D:\\nbt\\nbt4j-unit-test\\src\\test\\resources\\test" + System.currentTimeMillis() + ".nbt")) {
            StreamUtils.toStream(tags, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
    public void testWrite() {
        me.coderfrish.nbt4j.CompoundTag tags = new me.coderfrish.nbt4j.CompoundTag();

        ListTag tag = new ListTag();
        tag.addProperty("Frish2021");
        tag.addProperty("CoderFrish");

        tags.add("list", tag);

        try(FileOutputStream outputStream = new FileOutputStream("D:\\nbt\\nbt4j-unit-test\\src\\test\\resources\\test.nbt")) {
            StreamUtils.toStream(tags, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
    public void testRead() {
        try(FileInputStream inputStream = new FileInputStream("D:\\nbt\\nbt4j-unit-test\\src\\test\\resources\\test.nbt")) {
            CompoundTag compoundTag = StreamUtils.fromStream(inputStream);
            assert compoundTag.get("list").getAsList().getFirst().getAsString().equals("Frish2021");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
