package xyz.frish2021.nbt.api;

import xyz.frish2021.nbt.array.ByteArrayTag;
import xyz.frish2021.nbt.array.IntArrayTag;
import xyz.frish2021.nbt.array.LongArrayTag;
import xyz.frish2021.nbt.compound.CompoundTag;
import xyz.frish2021.nbt.primitive.PrimitiveTag;

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
