package me.coderfrish.nbt.utils.codec;

import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.utils.io.OutputBuffer;

import java.io.IOException;

public interface Encoder {
    void encode(OutputBuffer buffer, ElementTag tag) throws IOException;
}
