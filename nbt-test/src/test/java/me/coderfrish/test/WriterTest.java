package me.coderfrish.test;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import me.coderfrish.nbt.type.iterator.CompoundTag;
import me.coderfrish.nbt.type.iterator.IntArrayTag;
import me.coderfrish.nbt.type.iterator.ListTag;
import me.coderfrish.nbt.type.iterator.LongArrayTag;
import me.coderfrish.nbt.type.primitive.ByteTag;
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
        compound.put("test", new ByteTag((byte) 8));
        compound.put("grades", new IntArrayTag(new int[]{25,26, 24, 88, 90, 100}));
        compound.put("tests", new LongArrayTag(new long[]{25654365465465465L,2654763453456465465L, 2454543254254545L, 8854725452454L, 90252435432545L, 10065435432435L}));

        ListTag listTag = new ListTag();
        listTag.add(new StringTag("Code"));
        listTag.add(new StringTag("Eat"));
        listTag.add(new StringTag("Sleep"));
        compound.put("hobbies", listTag);

        try(FileOutputStream output = new FileOutputStream("C:\\NBT\\test\\src\\test\\resources\\test.nbt")) {
            StreamUtils.toStream(compound, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
