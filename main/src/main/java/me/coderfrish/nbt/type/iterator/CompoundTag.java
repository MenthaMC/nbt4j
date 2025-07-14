package me.coderfrish.nbt.type.iterator;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CompoundTag extends ElementTag {
    private final Map<String, ElementTag> tags = new LinkedHashMap<>();

    public ElementTag get(String key) {
        return tags.get(key);
    }

    public ElementTag get(String key, ElementTag defaultValue) {
        return tags.getOrDefault(key, defaultValue);
    }

    public CompoundTag put(String key, ElementTag value) {
        tags.put(key, value);
        return this;
    }

    public Set<Map.Entry<String, ElementTag>> entrySet() {
        return tags.entrySet();
    }

    @Override
    public CompoundTag getAsCompound() {
        return this;
    }

    @Override
    public TagType type() {
        return TagType.COMPOUND;
    }
}
