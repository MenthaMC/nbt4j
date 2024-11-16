package me.coderfrish.nbt.types;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;
import me.coderfrish.nbt.types.array.TagByteArray;
import me.coderfrish.nbt.types.array.TagIntArray;
import me.coderfrish.nbt.types.array.TagList;
import me.coderfrish.nbt.types.array.TagLongArray;

import java.io.DataOutputStream;
import java.util.*;
import java.util.function.Consumer;

import static me.coderfrish.nbt.util.CommonUtil.*;

@SuppressWarnings("all")
public class TagCompound implements ITagBase {
    private final Map<String, ITagBase> map;

    public TagCompound(Map<String, ITagBase> map) {
        this.map = map;
    }

    public TagCompound() {
        this(new LinkedHashMap<>());
    }

    public TagCompound(Consumer<TagCompound> tag) {
        this();
        tag.accept(this);
    }

    @Override
    public void write(DataOutputStream out) throws Exception {
        for (Map.Entry<String, ITagBase> entry : map.entrySet()) {
            writeType(out, entry.getValue().getType());
            writeString(out, entry.getKey());
            entry.getValue().write(out);
        }

        out.write(TagType.END.value);
    }

    public TagCompound put(String key, ITagBase tag) {
        map.put(key, tag);
        return this;
    }

    public TagCompound putString(String key, String value) {
        return this.put(key, new TagString(value));
    }

    public TagCompound putByte(String key, byte value) {
        return this.put(key, new TagByte(value));
    }

    public TagCompound putDouble(String key, double value) {
        return this.put(key, new TagDouble(value));
    }

    public TagCompound putFloat(String key, float value) {
        return this.put(key, new TagFloat(value));
    }

    public TagCompound putInt(String key, int value) {
        return this.put(key, new TagInt(value));
    }

    public TagCompound putLong(String key, long value) {
        return this.put(key, new TagLong(value));
    }

    public TagCompound putShort(String key, short value) {
        return this.put(key, new TagShort(value));
    }

    public TagCompound putCompound(String key, TagCompound value) {
        return this.put(key, value);
    }

    public TagCompound putByteArray(String key, byte[] value) {
        return this.put(key, new TagByteArray(value));
    }

    public TagCompound putIntArray(String key, int[] value) {
        return this.put(key, new TagIntArray(value));
    }

    public TagCompound putLongArray(String key, long[] value) {
        return this.put(key, new TagLongArray(value));
    }

    public <V extends ITagBase> TagCompound putList(String key, List<V> value) {
        return this.put(key, new TagList<>(value));
    }

    public ITagBase get(String key) {
        return map.get(key);
    }

    public String getString(String key) {
        return ((TagString) get(key)).value();
    }

    public byte getByte(String key) {
        return ((TagByte) get(key)).value();
    }

    public double getDouble(String key) {
        return ((TagDouble) get(key)).value();
    }

    public Float getFloat(String key) {
        return ((TagFloat) get(key)).value();
    }

    public int getInt(String key) {
        return ((TagInt) get(key)).value();
    }

    public long getLong(String key) {
        return ((TagLong) get(key)).value();
    }

    public short getShort(String key) {
        return ((TagShort) get(key)).value();
    }

    public TagCompound getCompound(String key) {
        return ((TagCompound) get(key));
    }

    public byte[] getByteArray(String key) {
        return ((TagByteArray) get(key)).array();
    }

    public int[] getIntArray(String key) {
        return ((TagIntArray) get(key)).array();
    }

    public long[] getLongArray(String key) {
        return ((TagLongArray) get(key)).array();
    }

    public TagList<?> getList(String key) {
        return ((TagList<?>) get(key));
    }

    public TagCompound replace(String key, ITagBase value) {
        map.replace(key, value);
        return this;
    }

    public int size() {
        return map.size();
    }

    public Set<Map.Entry<String, ITagBase>> entrySet() {
        return map.entrySet();
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public Set<ITagBase> valueSet() {
        return new LinkedHashSet<>(map.values());
    }

    @Override
    public String toString() {
        return "TagCompound{" +
                "map=" + map +
                '}';
    }

    @Override
    public TagType getType() {
        return TagType.COMPOUND;
    }
}
