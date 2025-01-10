package me.coderfrish.nbt.common;

import me.coderfrish.nbt.type.*;

import java.util.Collection;

public class Utility {
    public Utility() {
        throw new UnsupportedOperationException("Utility class no initialization is required.");
    }

    public static TagType type(NBTPrimitive primitive) {
        Object value = primitive.getAsObject();

        if (value instanceof String) {
            return TagType.STRING;
        } else if (value instanceof Integer) {
            return TagType.INT;
        } else if (value instanceof Long) {
            return TagType.LONG;
        } else if (value instanceof Float) {
            return TagType.FLOAT;
        } else if (value instanceof Double) {
            return TagType.DOUBLE;
        } else if (value instanceof Short) {
            return TagType.SHORT;
        } else if (value instanceof Byte || value instanceof Boolean) {
            return TagType.BYTE;
        } else if (value instanceof NBTByteArray || value instanceof byte[]) {
            return TagType.BYTE_ARRAY;
        } else if (value instanceof NBTIntArray || value instanceof int[]) {
            return TagType.INT_ARRAY;
        } else if (value instanceof NBTLongArray || value instanceof long[]) {
            return TagType.LONG_ARRAY;
        } else if (value instanceof NBTList) {
            return TagType.LIST;
        } else {
            if (value instanceof Collection<?>) {
                throw new UnsupportedOperationException("Collection type not supported.");
            }

            return TagType.COMPOUND;
        }
    }
}
