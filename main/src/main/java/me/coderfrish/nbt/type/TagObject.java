package me.coderfrish.nbt.type;

import me.coderfrish.nbt.common.TagType;
import me.coderfrish.nbt.type.element.NBTElement;

import java.util.*;

public class TagObject implements Iterable<TagObject.Entry> {
    private final Map<String, Object> objectMap = new HashMap<>();

    public boolean remove(String key) {
        return remove(key, get(key));
    }

    public boolean remove(String key, Object value) {
        return objectMap.remove(key, value);
    }

    public TagObject set(String key, Object value) {
        if (!containsKey(key)) {
            objectMap.put(key, value);
        } else {
            set(key, get(key), value);
        }

        return this;
    }

    public TagObject set(String key, Object value, Object newValue) {
        objectMap.replace(key, value, newValue);
        return this;
    }

    public NBTElement get(String key) {
        return new NBTElement(objectMap.get(key));
    }

    public NBTElement get(String key, Object defaultValue) {
        if (!containsKey(key)) return new NBTElement(defaultValue);
        return get(key);
    }

    public boolean containsKey(String key) {
        return objectMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return objectMap.containsValue(value);
    }

    public int size() {
        return objectMap.size();
    }

    public boolean isEmpty() {
        return objectMap.isEmpty();
    }

    public void clear() {
        objectMap.clear();
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        int i = 0;
        for (Entry entry : this) {
            if (entry.getKey().matches("[0-9A-Za-z_\\-.+]+")) {
                builder.append(entry.getKey());
            } else {
                builder.append("\"").append(entry.getKey()).append("\"");
            }

            builder.append(":").append(" ");
            Object value = entry.getValue();
            if (value instanceof String) {
                if (((String) value).matches("[0-9A-Za-z_\\-.+]+")) {
                    builder.append(value);
                } else {
                    builder.append("\"").append(value).append("\"");
                }
            } else if (value instanceof Float) {
                builder.append(value).append("F");
            } else if (value instanceof Double) {
                builder.append(value).append("D");
            } else if (value instanceof Integer) {
                builder.append(value);
            } else if (value instanceof Long) {
                builder.append(value).append("L");
            } else if (value instanceof Byte) {
                builder.append(value).append("B");
            } else if (value instanceof Short) {
                builder.append(value).append("S");
            } else {
                builder.append(value);
            }

            if (i < size() - 1) {
                builder.append(",");
            }

            i++;
        }
        builder.append("}");

        return builder.toString();
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
