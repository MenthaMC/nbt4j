package me.coderfrish.test;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.ConsoleReporter;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import me.coderfrish.nbt.NBTOutput;
import me.coderfrish.nbt.type.NBTCompound;

import java.nio.file.Files;
import java.nio.file.Paths;

public class OutputPerformanceTest {
    @JunitPerfConfig(threads = 10, warmUp = 1_000, duration = 30_000
            , reporter = {HtmlReporter.class, ConsoleReporter.class})
    public void benchmark() throws InterruptedException {
        try(NBTOutput output = new NBTOutput(Files.newOutputStream(Paths.get("D:\\NBT\\test\\src\\test\\resources\\test1.nbt")))) {
            NBTCompound object = new NBTCompound();
            object.put("name", "Frish2021");
            object.put("age", 15);
            object.put("test", 1156465465L);
            object.put("tests", (short) 54);
            object.put("testss", 1.5F);
            object.put("testsss", 1.5);

            output.write("hello", object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
