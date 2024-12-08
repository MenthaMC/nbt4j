package io.github.xiefrish2021.api;

import io.github.xiefrish2021.array.ByteArrayTag;
import io.github.xiefrish2021.array.IntArrayTag;
import io.github.xiefrish2021.array.LongArrayTag;
import io.github.xiefrish2021.compound.CompoundTag;
import io.github.xiefrish2021.primitive.PrimitiveTag;

public interface Getter {
    ITag get(String key);

    ITag get(String key, ITag defaultValue);

    <V> PrimitiveTag<V> getPrimitive(String key);

    String getString(String key);

    int getInt(String key);

    float getFloat(String key);

    double getDouble(String key);

    byte getByte(String key);

    long getLong(String key);

    short getShort(String key);

    <V extends ITag> List<V> getList(String key);

    ByteArrayTag getByteArray(String key);

    IntArrayTag getIntArray(String key);

    LongArrayTag getLongArray(String key);

    CompoundTag getCompound(String key);
}
