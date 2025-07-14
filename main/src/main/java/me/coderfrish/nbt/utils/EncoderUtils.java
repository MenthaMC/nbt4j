package me.coderfrish.nbt.utils;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.utils.codec.Encoder;
import me.coderfrish.nbt.utils.io.OutputBuffer;

import java.util.LinkedHashMap;
import java.util.Map;

public class EncoderUtils {
    private static final Encoder STRING_TAG_ENCODER = OutputBuffer::writeTagUTF;
    private static final Encoder INT_TAG_ENCODER = OutputBuffer::writeTagInt;
    private static final Encoder FLOAT_TAG_ENCODER = OutputBuffer::writeTagFloat;
    private static final Encoder LONG_TAG_ENCODER = OutputBuffer::writeTagLong;
    private static final Encoder DOUBLE_TAG_ENCODER = OutputBuffer::writeTagDouble;
    private static final Encoder BYTE_TAG_ENCODER = OutputBuffer::writeTagByte;
    private static final Encoder SHORT_TAG_ENCODER = OutputBuffer::writeTagShort;
    private static final Encoder COMPOUND_TAG_ENCODER = OutputBuffer::writeTagCompound;

    private static final Map<TagType, Encoder> encoders;

    static  {
        encoders = new LinkedHashMap<>() {
            {
                put(TagType.STRING, STRING_TAG_ENCODER);
                put(TagType.INT, INT_TAG_ENCODER);
                put(TagType.FLOAT, FLOAT_TAG_ENCODER);
                put(TagType.LONG, LONG_TAG_ENCODER);
                put(TagType.DOUBLE, DOUBLE_TAG_ENCODER);
                put(TagType.BYTE, BYTE_TAG_ENCODER);
                put(TagType.SHORT, SHORT_TAG_ENCODER);
                put(TagType.COMPOUND, COMPOUND_TAG_ENCODER);
            }
        };
    }

    public static Map<TagType, Encoder> getEncoders() {
        return encoders;
    }
}
