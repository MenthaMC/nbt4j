package me.coderfrish;

public interface ITag {
    default TagType type() {
        return null;
    }
}
