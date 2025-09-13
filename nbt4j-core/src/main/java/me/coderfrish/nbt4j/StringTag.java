package me.coderfrish.nbt4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

class StringTag extends ElementTag {
    private String string;

    public StringTag(String string) {
        this.string = string;
    }

    @Override
    public String getAsString() {
        return this.string;
    }

    @Override
    public TagType type() {
        return TagType.STRING;
    }

    @Override
    void write(DataOutput output) throws IOException {
        output.writeUTF(string);
    }

    @Override
    void read(DataInput input) throws IOException {
        this.string = input.readUTF();
    }
}
