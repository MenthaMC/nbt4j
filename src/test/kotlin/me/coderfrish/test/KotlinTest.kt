package me.coderfrish.test

import io.github.xiefrish2021.NBT
import io.github.xiefrish2021.tag.compound.NBTElement
import java.io.FileInputStream

fun main() {
    val nbt = NBT.getInstance()
//    val compound = CompoundTag()

//    compound.put("hello", StringTag("Frish2021"))
//    nbt.writeUnnamedNBT(compound, FileOutputStream("D:\\NBT\\src\\test\\resources\\test1.nbt"))

    val compound = nbt.readUnnamedNBT(FileInputStream("D:\\NBT\\src\\test\\resources\\test1.nbt"))
//    println(compound["hello"].asString())
//    val hello: NBTElement by compound
//    println(hello.asString)
    println(compound["hello"].asString)
//    println(hello)
}
