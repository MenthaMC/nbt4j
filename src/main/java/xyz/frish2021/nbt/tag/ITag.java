package xyz.frish2021.nbt.tag;

public interface ITag {
    default TagType type() {
        return null;
    }
}
