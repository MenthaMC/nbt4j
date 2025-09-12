package me.coderfrish.nbt4j;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CompoundTag extends ElementTag {
    private final Map<String, ElementTag> tags = new ConcurrentHashMap<>();

    public void add(String key, ElementTag value) {
        tags.put(key, value);
    }

    public void addProperty(String key, String value) {
        tags.put(key, new StringTag(value));
    }

    public void addProperty(String key, Number value) {
        tags.put(key, new NumberTag(value));
    }

    public ElementTag get(String key) {
        return tags.get(key);
    }

    public boolean containsKey(String key) {
        return tags.containsKey(key);
    }

    public boolean containsValue(ElementTag value) {
        return tags.containsValue(value);
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public boolean replace(String key, ElementTag oldValue, ElementTag newValue) {
        return tags.replace(key, oldValue, newValue);
    }

    public boolean remove(String key, ElementTag value) {
        return tags.remove(key, value);
    }

    public ElementTag remove(String key) {
        return tags.remove(key);
    }

    public ElementTag replace(String key, ElementTag newValue) {
        return tags.replace(key, newValue);
    }

    public int size() {
        return tags.size();
    }

    public void clear() {
        tags.clear();
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
    public CompoundTag getAsCompound() {
        return this;
    }
}
