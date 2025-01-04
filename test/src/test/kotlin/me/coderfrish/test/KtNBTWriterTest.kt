package me.coderfrish.test

import me.coderfrish.nbt.NBT
import me.coderfrish.nbt.io.NBTOutput
import me.coderfrish.nbt.reflect.SerializationContext
import me.coderfrish.nbt.type.TagObject
import org.junit.jupiter.api.Test
import java.io.File

class KtNBTWriterTest {
    @Test
    fun test() {
//        NBT.writeNamedNBT("", Person("CoderFrish", 15, "Grade 9 Class 40"), File("D:\\NBT\\test\\src\\test\\resources\\test.nbt"))

        NBTOutput(File("D:\\NBT\\test\\src\\test\\resources\\test.nbt")).use {
            val `object` = TagObject()
            `object`["name"] = "Frish2021"
            `object`["age"] = 15
            `object`["time"] = 15L

            println(`object`)
//            it.write("test", `object`)
        }
    }
}
