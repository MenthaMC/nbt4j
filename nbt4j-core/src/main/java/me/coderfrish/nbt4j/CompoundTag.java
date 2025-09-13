package me.coderfrish.nbt4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static me.coderfrish.nbt4j.StreamUtils.*;

public class CompoundTag extends ElementTag {
    private final Map<String, ElementTag> tags = new ConcurrentHashMap<>();

    public ElementTag get(String key) {
        return tags.get(key);
    }

    public void add(String key, ElementTag value) {
        tags.put(key, value);
    }

    public void addProperty(String key, String value) {
        tags.put(key, new StringTag(value));
    }

    public void addProperty(String key, Number value) {
        tags.put(key, createNumberTag(value));
    }

    public ElementTag remove(String key) {
        return tags.remove(key);
    }

    public boolean remove(String key, ElementTag value) {
        return tags.remove(key, value);
    }

    public ElementTag replace(String key, ElementTag newValue) {
        return tags.put(key, newValue);
    }

    public boolean replace(String key, ElementTag oldValue, ElementTag newValue) {
        return tags.replace(key, oldValue, newValue);
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    public boolean isNotEmpty() {
        return !tags.isEmpty();
    }

    public int size() {
        return tags.size();
    }

    public boolean containsKey(String key) {
        return tags.containsKey(key);
    }

    public boolean containsValue(ElementTag value) {
        return tags.containsValue(value);
    }

    public Stream<Map.Entry<String, ElementTag>> stream() {
        return tags.entrySet().stream();
    }

    public Set<String> keys() {
        return tags.keySet();
    }

    public Collection<ElementTag> values() {
        return tags.values();
    }

    public Set<Map.Entry<String, ElementTag>> entries() {
        return tags.entrySet();
    }

    @Override
    public TagType type() {
        return TagType.COMPOUND;
    }

    @Override
    void write(DataOutput output) throws IOException {
        for (Map.Entry<String, ElementTag> entry : entries()) {
            ElementTag value = entry.getValue();
            writeType(value.type(), output);
            output.writeUTF(entry.getKey());
            value.write(output);
        }

        writeType(TagType.END, output);
    }

    @Override
    void read(DataInput input) throws IOException {
        for (;;) {
            TagType type = readType(input);
            if (type == TagType.END) break;
            String key = input.readUTF();

            ElementTag tag = createTagInstance(type);
            tag.read(input);
            this.add(key, tag);
        }
    }
}
