package me.coderfrish.test;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import me.coderfrish.nbt.type.iterator.CompoundTag;
import me.coderfrish.nbt.type.primitive.IntTag;
import me.coderfrish.nbt.type.primitive.LongTag;
import me.coderfrish.nbt.type.primitive.StringTag;
import me.coderfrish.nbt.utils.StreamUtils;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriterTest {
    @JunitPerfConfig(warmUp = 1000, duration = 2000, reporter = HtmlReporter.class)
    public void test() {
        CompoundTag compound = new CompoundTag();
        compound.put("name", new StringTag("CoderFrish"));
        compound.put("age", new IntTag(15));
        compound.put("id", new LongTag(5435413245324325432L));

        try(FileOutputStream output = new FileOutputStream("C:\\NBT\\test\\src\\test\\resources\\test.nbt")) {
            StreamUtils.toStream(compound, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
