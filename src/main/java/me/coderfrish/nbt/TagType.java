package me.coderfrish.nbt;

import me.coderfrish.nbt.exception.NBTException;
import me.coderfrish.nbt.types.*;
import me.coderfrish.nbt.types.array.TagByteArray;
import me.coderfrish.nbt.types.array.TagIntArray;
import me.coderfrish.nbt.types.array.TagList;
import me.coderfrish.nbt.types.array.TagLongArray;

public enum TagType {
    END(0),
    BYTE(1),
    SHORT(2),
    INT(3),
    LONG(4),
    FLOAT(5),
    DOUBLE(6),
    BYTE_ARRAY(7),
    STRING(8),
    LIST(9),
    COMPOUND(10),
    INT_ARRAY(11),
    LONG_ARRAY(12);

    public final byte value;

    TagType(int value) {
        this.value = (byte) value;
    }

    @SuppressWarnings("unused")
    public static TagType getType(Object value) {
        return switch (value) {
            case TagByte b -> TagType.BYTE;
            case TagShort i -> TagType.SHORT;
            case TagInt i -> TagType.INT;
            case TagLong l -> TagType.LONG;
            case TagFloat v -> TagType.FLOAT;
            case TagDouble v -> TagType.DOUBLE;
            case TagByteArray bytes -> TagType.BYTE_ARRAY;
            case TagString s -> TagType.STRING;
            case TagList<?> list -> TagType.LIST;
            case TagCompound tagCompound -> TagType.COMPOUND;
            case TagIntArray ints -> TagType.INT_ARRAY;
            case TagLongArray longs -> TagType.LONG_ARRAY;
            case null, default -> throw new NBTException("Unknown tag type: " + value);
        };
    }

    @SuppressWarnings("unused")
    public static TagType getTypeById(byte value) {
        return switch (value) {
            case 0 -> TagType.END;
            case 1 -> TagType.BYTE;
            case 2 -> TagType.SHORT;
            case 3 -> TagType.INT;
            case 4 -> TagType.LONG;
            case 5 -> TagType.FLOAT;
            case 6 -> TagType.DOUBLE;
            case 7 -> TagType.BYTE_ARRAY;
            case 8 -> TagType.STRING;
            case 9 -> TagType.LIST;
            case 10 -> TagType.COMPOUND;
            case 11 -> TagType.INT_ARRAY;
            case 12 -> TagType.LONG_ARRAY;
            default -> throw new NBTException("Unknown tag type: " + value);
        };
    }
}
