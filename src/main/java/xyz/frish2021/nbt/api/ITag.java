package xyz.frish2021.nbt.api;

import xyz.frish2021.nbt.tag.TagType;

public interface ITag {
    default TagType type() {
        return null;
    }
}
