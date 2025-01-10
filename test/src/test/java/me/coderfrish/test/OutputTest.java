package me.coderfrish.test;

import me.coderfrish.nbt.NBTOutput;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OutputTest {
    @Test
    public void test() {
        try(NBTOutput output = new NBTOutput(new FileOutputStream("D:\\NBT\\test\\src\\test\\resources\\test.nbt"))) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("key", "value");
            objectMap.put("safa", "dfs");
            objectMap.put("aa", "aa");
            objectMap.put("aas", true);

            output.write(objectMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
