package me.coderfrish.nbt.utils.codec;

import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.utils.io.InputBuffer;

import java.io.IOException;

public interface Decoder {
    ElementTag decode(InputBuffer buffer) throws IOException;
}
