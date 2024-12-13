package io.github.xiefrish2021.api;

import io.github.xiefrish2021.array.ByteArrayTag;
import io.github.xiefrish2021.array.IntArrayTag;
import io.github.xiefrish2021.array.LongArrayTag;
import io.github.xiefrish2021.compound.CompoundTag;
import io.github.xiefrish2021.primitive.PrimitiveTag;

public interface Compound extends Iterable<Compound.Entry>, ITag {
    Compound put(String key, ITag value);

    Compound putAll(Compound compound);

    Compound remove(String key);

    Compound remove(String key, ITag value);

    Compound replace(String key, ITag oldValue, ITag newValue);

    Compound replace(String key, ITag newValue);

    boolean containsKey(String key);

    boolean containsValue(ITag value);

    boolean isEmpty();

    void clear();

    int size();

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

    interface Entry {
        String key();

        ITag value();
    }
}
