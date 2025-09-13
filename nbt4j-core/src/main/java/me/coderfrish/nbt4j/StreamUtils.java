package me.coderfrish.nbt4j;

import java.io.*;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static me.coderfrish.nbt4j.NumberTag.*;

public class StreamUtils {
    public StreamUtils() {
        throw new UnsupportedOperationException("Class `me.coderfrish.nbt4j.StreamUtils` don`t need to be instantiated.");
    }

    public static void toStream(String name, CompoundTag compound, OutputStream output) throws IOException {
        try(DataOutputStream doutput = new DataOutputStream(output)) {
            writeType(TagType.COMPOUND, doutput);
            doutput.writeUTF(name);
            compound.write(doutput);
        }
    }

    public static void toStream(CompoundTag compound, OutputStream output) throws IOException {
        StreamUtils.toStream("", compound, output);
    }

    public static CompoundTag fromStream(InputStream stream) throws IOException {
        try(DataInputStream dinput = new DataInputStream(stream)) {
            if (readType(dinput) != TagType.COMPOUND)
                throw new IllegalArgumentException("First tag must be compound tag.");
            dinput.readUTF();

            /* New a Compound Instance to read nbt file. */
            CompoundTag tag = new CompoundTag();
            tag.read(dinput);
            return tag;
        }
    }

    static void writeType(TagType type, DataOutput output) throws IOException {
        output.writeByte(type.ordinal());
    }

    static TagType readType(DataInput input) throws IOException {
        return TagType.values()[input.readByte()];
    }

    private static final Map<Class<? extends Number>, Function<Number, ElementTag>> numberMapper = new WeakHashMap<>() {
        {
            put(Integer.class, IntTag::new);
            put(Long.class, LongTag::new);
            put(Short.class, ShortTag::new);
            put(Byte.class, ByteTag::new);
            put(Float.class, FloatTag::new);
            put(Double.class, DoubleTag::new);
        }
    };

    static ElementTag createNumberTag(Number value) {
        Function<Number, ElementTag> function = numberMapper.get(value.getClass());
        if (function == null) {
            throw new IllegalArgumentException(String.format("Unknown number type: %s", value.getClass()));
        }

        return function.apply(value);
    }

    private static final Map<TagType, Supplier<ElementTag>> tagMapper = new WeakHashMap<>() {
        {
            put(TagType.BYTE, () -> new ByteTag(null));
            put(TagType.INT, () -> new IntTag(null));
            put(TagType.LONG, () -> new LongTag(null));
            put(TagType.SHORT, () -> new StringTag(null));
            put(TagType.FLOAT, () -> new FloatTag(null));
            put(TagType.DOUBLE, () -> new DoubleTag(null));
            put(TagType.STRING, () -> new StringTag(null));
            put(TagType.COMPOUND, CompoundTag::new);
        }
    };

    static ElementTag createTagInstance(TagType type) {
        return tagMapper.get(type).get();
    }
}
