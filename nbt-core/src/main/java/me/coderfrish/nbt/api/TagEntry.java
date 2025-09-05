package me.coderfrish.nbt.api;

import me.coderfrish.nbt.type.ElementTag;

public interface TagEntry {
    ElementTag getValue();

    String getKey();
}
