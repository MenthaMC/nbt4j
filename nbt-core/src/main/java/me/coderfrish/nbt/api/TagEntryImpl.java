package me.coderfrish.nbt.api;

import me.coderfrish.nbt.type.ElementTag;

public class TagEntryImpl implements TagEntry {
    private final String key;
    private final ElementTag value;

    public TagEntryImpl(String key, ElementTag value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public ElementTag getValue() {
        return value;
    }

    @Override
    public String getKey() {
        return key;
    }
}
