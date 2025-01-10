package me.coderfrish.test;

import me.coderfrish.nbt.NBTInput;
import me.coderfrish.nbt.io.input.DataInputStream;
import me.coderfrish.nbt.io.output.DataOutputStream;
import me.coderfrish.nbt.type.NBTCompound;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class InputTest {
    @Test
    public void test() {
        try(NBTInput input = new NBTInput(new FileInputStream("D:\\NBT\\test\\src\\test\\resources\\test.nbt"))) {
            NBTCompound read = input.read();

            System.out.println(read.get("aas").getAsBoolean());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
