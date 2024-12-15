package me.coderfrish.test

import io.github.xiefrish2021.api.NBT
import io.github.xiefrish2021.compound.CompoundTag
import io.github.xiefrish2021.compound.NBTElement
import io.github.xiefrish2021.primitive.StringTag
import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val nbt = NBT.getInstance()
//    val compound = CompoundTag()

//    compound.put("hello", StringTag("Frish2021"))
//    nbt.writeUnnamedNBT(compound, FileOutputStream("D:\\NBT\\src\\test\\resources\\test1.nbt"))

    val compound = nbt.readUnnamedNBT(FileInputStream("D:\\NBT\\src\\test\\resources\\test1.nbt"))
//    println(compound["hello"].asString())
    val hello: NBTElement by compound
    println(hello.asString())
//    println(hello)
}
