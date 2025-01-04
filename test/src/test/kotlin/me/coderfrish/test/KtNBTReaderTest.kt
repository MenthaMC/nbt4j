package me.coderfrish.test

import me.coderfrish.nbt.io.NBTInput
import org.junit.jupiter.api.Test
import java.io.File

class KtNBTReaderTest {
    @Test
    fun test() {
        NBTInput(File("D:\\NBT\\test\\src\\test\\resources\\test.nbt")).use {
            println(it.read())
        }
    }
}
