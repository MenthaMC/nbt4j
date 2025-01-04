package me.coderfrish.test;

import me.coderfrish.nbt.NBT;
import me.coderfrish.nbt.io.NBTOutput;
import me.coderfrish.nbt.type.TagObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NBTWriterTest {
    @Test
    public void test() {
//        TagObject object = new TagObject();
//        object.set("key", );

//        try(NBTOutput output = new NBTOutput(new File("D:\\NBT\\test\\src\\test\\resources\\test.nbt"))) {
//            output.write("test", object);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        NBT.writeNamedNBT(
                "student",
                new Student("Frish2021", 15, "Grade 9 Class 40"),
                new File("D:\\NBT\\test\\src\\test\\resources\\test.nbt")
        );
    }
}
