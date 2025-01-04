package me.coderfrish.test;

import me.coderfrish.nbt.io.NBTOutput;
import me.coderfrish.nbt.type.TagObject;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Fork(1)
@OutputTimeUnit(TimeUnit.SECONDS)
public class NBTBenchmarkTest {
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void test() throws InterruptedException {
        try(NBTOutput output = new NBTOutput(new File("D:\\NBT\\test\\src\\test\\resources\\test1.nbt"))) {
            TagObject object = new TagObject();
            object.set("name", "Frish2021");
            object.set("age", 15);
            object.set("test", 1156465465L);

            output.write("hello", object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMain() {
        try {
            Options options = new OptionsBuilder()
                    .include(NBTBenchmarkTest.class.getSimpleName())
                    .warmupIterations(3)
                    .warmupTime(TimeValue.seconds(2))
                    .forks(1)
                    .build();
            new Runner(options).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
