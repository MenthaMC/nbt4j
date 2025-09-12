package me.coderfrish.nbt4j;

public class NumberTag extends ElementTag {
    private final Number value;

    public NumberTag(Number value) {
        this.value = value;
    }

    @Override
    public Number getAsNumber() {
        return this.value;
    }
}
