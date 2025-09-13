package me.coderfrish.test;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import me.coderfrish.nbt4j.*;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CompoundTest {
    @Test
    @JunitPerfConfig(threads = 5, warmUp = 1_000, duration = 10_000, reporter = {HtmlReporter.class})
    public void test() {
        CompoundTag tags = new CompoundTag();
        assert tags.type() == TagType.COMPOUND;

        tags.addProperty("name", "Frish2021");
        tags.addProperty("age", 15);
        tags.addProperty("long_test", 5L);
        tags.addProperty("byte_test", (byte) 1);

        try(FileOutputStream outputStream = new FileOutputStream("D:\\nbt\\nbt4j-unit-test\\src\\test\\resources\\test" + System.currentTimeMillis() + ".nbt")) {
            StreamUtils.toStream(tags, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testWrite() {
        CompoundTag tags = new CompoundTag();

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

    @Test
    public void testRead() {
        try(FileInputStream inputStream = new FileInputStream("D:\\nbt\\nbt4j-unit-test\\src\\test\\resources\\test.nbt")) {
            CompoundTag compoundTag = StreamUtils.fromStream(inputStream);
            assert compoundTag.get("list").getAsList().getFirst().getAsString().equals("Frish2021");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
