package io.github.xiefrish2021.compound

import io.github.xiefrish2021.api.ITag
import kotlin.reflect.KProperty

interface Getter {
    operator fun get(key: String): NBTElement

    fun get(key: String, defaultValue: ITag): NBTElement

    operator fun getValue(nothing: Nothing?, property: KProperty<*>): NBTElement
}
