package me.coderfrish.nbt;

import me.coderfrish.nbt.exceptions.NBTException;

import java.io.DataOutput;

public interface ITagBase {
    default void write(DataOutput output) throws Exception {}

    default String key() {
        return null;
    }

    default TagType type() {
        return null;
    }

    enum TagType {
        END,
        BYTE,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        BYTE_ARRAY,
        STRING,
        LIST,
        COMPOUND,
        INT_ARRAY,
        LONG_ARRAY;

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
}
