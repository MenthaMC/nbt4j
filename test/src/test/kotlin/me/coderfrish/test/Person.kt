package me.coderfrish.test

import me.coderfrish.nbt.reflect.At
import me.coderfrish.nbt.reflect.Serialization

@Serialization(At.K_Data)
data class Person(val name: String)
