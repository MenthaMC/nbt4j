package me.coderfrish.nbt4j;

public class StringTag extends ElementTag {
    private final String value;

    public StringTag(String value) {
        this.value = value;
    }

    @Override
    public String getAsString() {
        return this.value;
    }
}
