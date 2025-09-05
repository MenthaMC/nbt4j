package me.coderfrish.nbt.type.iterator;

import me.coderfrish.nbt.api.TagEntry;
import me.coderfrish.nbt.api.TagEntryImpl;
import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;

import java.util.*;

public class CompoundTag extends ElementTag implements Iterable<TagEntry> {
    private final Map<String, ElementTag> tags = new LinkedHashMap<>();
    private final Set<TagEntry> tagsSet = new LinkedHashSet<>();

    public ElementTag get(String key) {
        return tags.get(key);
    }

    public ElementTag get(String key, ElementTag defaultValue) {
        return tags.getOrDefault(key, defaultValue);
    }

    public CompoundTag put(String key, ElementTag value) {
        tags.put(key, value);
        tagsSet.add(new TagEntryImpl(key, value));
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

    @Override
    public Iterator<TagEntry> iterator() {
        return tagsSet.iterator();
    }
}
