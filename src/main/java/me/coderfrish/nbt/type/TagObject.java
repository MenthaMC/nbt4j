package me.coderfrish.nbt.type;

import me.coderfrish.nbt.type.element.NBTElement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;

public class TagObject implements Iterable<TagObject.Entry> {
    private final Map<String, Object> objectMap = new HashMap<>();

    public TagObject set(String key, Object value) {
        objectMap.put(key, value);
        return this;
    }

    public NBTElement get(String key) {
        return new NBTElement(objectMap.get(key));
    }

    public boolean containsKey(String key) {
        return objectMap.containsKey(key);
    }

    @Override
    public Iterator<Entry> iterator() {
        return objectMap.entrySet().stream().map(
                entry -> new Entry(entry.getKey(), entry.getValue())
        ).iterator();
    }

    @Override
    public Spliterator<Entry> spliterator() {
        return objectMap.entrySet().stream().map(
                entry -> new Entry(entry.getKey(), entry.getValue())
        ).spliterator();
    }

    public static final class Entry {
        private final String key;
        private final Object value;

        private Entry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }
}
