package io.github.xiefrish2021.compound

import io.github.xiefrish2021.api.ITag
import io.github.xiefrish2021.api.List
import io.github.xiefrish2021.array.ByteArrayTag
import io.github.xiefrish2021.array.IntArrayTag
import io.github.xiefrish2021.array.LongArrayTag
import io.github.xiefrish2021.list.ListTag
import io.github.xiefrish2021.primitive.PrimitiveTag
import io.github.xiefrish2021.primitive.StringTag
import io.github.xiefrish2021.primitive.number.*

@Suppress("unused")
class NBTElement(val tag: ITag) {
    fun asPrimitive(): PrimitiveTag<*> {
        if (tag is PrimitiveTag<*>) {
            return tag
        }

        return null!!
    }

    fun asString(): String {
        if (tag is StringTag) {
            return tag.value()
        }

        return null!!
    }

    fun asInt(): Int {
        if (tag is IntTag) {
            return tag.value()
        }

        return null!!
    }

    fun asFloat(): Float {
        if (tag is FloatTag) {
            return tag.value()
        }

        return null!!
    }

    fun asDouble(): Double {
        if (tag is DoubleTag) {
            return tag.value()
        }

        return null!!
    }

    fun asByte(): Byte {
        if (tag is ByteTag) {
            return tag.value()
        }

        return null!!
    }

    fun asLong(): Long {
        if (tag is LongTag) {
            return tag.value()
        }

        return null!!
    }

    fun asShort(): Short {
        if (tag is ShortTag) {
            return tag.value()
        }

        return null!!
    }

    fun asList(): List<*> {
        if (tag is ListTag<*>) {
            return tag
        }

        return null!!
    }

    fun asByteArray(): ByteArrayTag {
        if (tag is ByteArrayTag) {
            return tag
        }

        return null!!
    }

    fun asIntArray(): IntArrayTag {
        if (tag is IntArrayTag) {
            return tag
        }

        return null!!
    }

    fun asLongArray(): LongArrayTag {
        if (tag is LongArrayTag) {
            return tag
        }

        return null!!
    }

    fun asCompound(): CompoundTag {
        if (tag is CompoundTag) {
            return tag
        }

        return null!!
    }

    fun asTag(): ITag {
        return tag
    }
}
