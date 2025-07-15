package me.coderfrish.test;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.type.iterator.CompoundTag;
import me.coderfrish.nbt.utils.StreamUtils;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ReaderTest {
//    @JunitPerfConfig(warmUp = 1000, duration = 2000, reporter = HtmlReporter.class)
    @Test
    public void test() {
        try(FileInputStream stream = new FileInputStream("C:\\NBT\\test\\src\\test\\resources\\test.nbt")) {
            CompoundTag compound = StreamUtils.fromStream(stream);

            for (ElementTag grades : compound.get("hobbies").getAsList()) {
                System.out.println(grades.getAsString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
