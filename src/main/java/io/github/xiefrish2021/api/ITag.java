package io.github.xiefrish2021.api;

import io.github.xiefrish2021.tag.TagType;

public interface ITag {
    default TagType type() {
        return null;
    }
}
