package me.coderfrish.nbt.utils;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.utils.codec.Decoder;
import me.coderfrish.nbt.utils.codec.Encoder;
import me.coderfrish.nbt.utils.io.InputBuffer;
import me.coderfrish.nbt.utils.io.OutputBuffer;

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

    private static final Map<TagType, Decoder> decoders;

    static  {
        decoders = new LinkedHashMap<>() {
            {
                put(TagType.STRING, STRING_TAG_DECODER);
                put(TagType.INT, INT_TAG_DECODER);
                put(TagType.FLOAT, FLOAT_TAG_DECODER);
                put(TagType.LONG, LONG_TAG_DECODER);
                put(TagType.DOUBLE, DOUBLE_TAG_DECODER);
                put(TagType.BYTE, BYTE_TAG_DECODER);
                put(TagType.SHORT, SHORT_TAG_DECODER);
                put(TagType.COMPOUND, COMPOUND_TAG_DECODER);
            }
        };
    }

    public static Map<TagType, Decoder> getDecoders() {
        return decoders;
    }
}
