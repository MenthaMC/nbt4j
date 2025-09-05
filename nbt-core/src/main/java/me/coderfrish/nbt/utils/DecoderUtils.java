package me.coderfrish.nbt.utils;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.utils.codec.Decoder;
import me.coderfrish.nbt.utils.io.InputBuffer;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DecoderUtils {
    private static final Decoder STRING_TAG_DECODER = InputBuffer::readTagUTF;
    private static final Decoder INT_TAG_DECODER = InputBuffer::readTagInt;
    private static final Decoder FLOAT_TAG_DECODER = InputBuffer::readTagFloat;
    private static final Decoder LONG_TAG_DECODER = InputBuffer::readTagLong;
    private static final Decoder DOUBLE_TAG_DECODER = InputBuffer::readTagDouble;
    private static final Decoder BYTE_TAG_DECODER = InputBuffer::readTagByte;
    private static final Decoder SHORT_TAG_DECODER = InputBuffer::readTagShort;
    private static final Decoder COMPOUND_TAG_DECODER = InputBuffer::readTagCompound;
    private static final Decoder INT_ARRAY_TAG_DECODER = InputBuffer::readTagIntArray;
    private static final Decoder LONG_ARRAY_TAG_DECODER = InputBuffer::readTagLongArray;
    private static final Decoder BYTE_ARRAY_TAG_DECODER = InputBuffer::readTagByteArray;
    private static final Decoder LIST_TAG_DECODER = InputBuffer::readTagList;

    private static final Map<TagType, Decoder> decoders;

    static  {
        decoders = Collections.unmodifiableMap(new LinkedHashMap<>() {
            {
                put(TagType.STRING, STRING_TAG_DECODER);
                put(TagType.INT, INT_TAG_DECODER);
                put(TagType.FLOAT, FLOAT_TAG_DECODER);
                put(TagType.LONG, LONG_TAG_DECODER);
                put(TagType.DOUBLE, DOUBLE_TAG_DECODER);
                put(TagType.BYTE, BYTE_TAG_DECODER);
                put(TagType.SHORT, SHORT_TAG_DECODER);
                put(TagType.COMPOUND, COMPOUND_TAG_DECODER);
                put(TagType.INT_ARRAY, INT_ARRAY_TAG_DECODER);
                put(TagType.LONG_ARRAY, LONG_ARRAY_TAG_DECODER);
                put(TagType.BYTE_ARRAY, BYTE_ARRAY_TAG_DECODER);
                put(TagType.LIST, LIST_TAG_DECODER);
            }
        });
    }

    public static ElementTag decode(TagType type, InputBuffer buffer) throws IOException {
        Decoder decoder = decoders.get(type);
        if (decoder == null)
            throw new IllegalArgumentException("Unknow decoder, id: " + type + ".");

        return decoder.decode(buffer);
    }
}
