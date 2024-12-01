package me.coderfrish.nbt;

import java.io.DataOutput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings("all")
public record CompoundTag(String key, Map<String, ITagBase> map) implements ITagBase, Iterable<Map.Entry<String, ITagBase>> {
    public CompoundTag(String key, CompoundTag tag) {
        this(key, tag.map);
    }

    public CompoundTag(CompoundTag tag) {
        this("", tag);
    }

    public CompoundTag(String key) {
        this(key, new HashMap<>());
    }

    public CompoundTag() {
        this("");
    }

    /**
     * @deprecated
     * {#{@link CompoundTag#forEach(Consumer)}}
     * {#{@link CompoundTag#iterator()}}
     * <code>
     * CompoundTag root = ...;
     * for(ITagBase item : root) {
     *     System.out.println(item.type());
     * }
     * </code>
     */
    @Override
    @Deprecated
    public Map<String, ITagBase> map() {
        return map;
    }

    @Override
    public void write(DataOutput output) throws Exception {
        for (Map.Entry<String, ITagBase> entry : map.entrySet()) {
            ITagBase tag = entry.getValue();

            output.write(tag.type().ordinal());
            output.writeUTF(entry.getKey());
            tag.write(output);
        }

        output.write(TagType.END.ordinal());
    }

    public ITagBase get(String key) {
        return map.get(key);
    }

    public CompoundTag put(ITagBase value) {
        map.put(value.key(), value);
        return this;
    }

    public CompoundTag put(String key, ITagBase value) {
        map.put(key, value);
        return this;
    }

    @Override
    public TagType type() {
        return TagType.COMPOUND;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public Iterator<Map.Entry<String, ITagBase>> iterator() {
        return map.entrySet().iterator();
    }

    @Override
    public void forEach(Consumer<? super Map.Entry<String, ITagBase>> action) {
        for (Map.Entry<String, ITagBase> entry : map.entrySet()) {
            action.accept(entry);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        int index = 0;
        for (Map.Entry<String, ITagBase> entry : this) {
            builder.append(entry.getKey() + ": " + entry.getValue().toString());

            if (index < map.size() - 1) builder.append(", ");
            index++;
        }
        builder.append("}");

        return builder.toString();
    }
}
