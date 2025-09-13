package me.coderfrish.nbt4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NumberTag extends ElementTag {
    protected Number number;

    public NumberTag(Number number) {
        this.number = number;
    }

    @Override
    public Number getAsNumber() {
        return this.number;
    }

    static class IntTag extends NumberTag {
        public IntTag(Number number) {
            super(number);
        }

        @Override
        public TagType type() {
            return TagType.INT;
        }

        @Override
        void write(DataOutput output) throws IOException {
            output.writeInt(number.intValue());
        }

        @Override
        void read(DataInput input) throws IOException {
            this.number = input.readInt();
        }
    }

    static class LongTag extends NumberTag {
        public LongTag(Number number) {
            super(number);
        }

        @Override
        public TagType type() {
            return TagType.LONG;
        }

        @Override
        void write(DataOutput output) throws IOException {
            output.writeLong(number.longValue());
        }

        @Override
        void read(DataInput input) throws IOException {
            this.number = input.readLong();
        }
    }

    static class ShortTag extends NumberTag {
        public ShortTag(Number number) {
            super(number);
        }

        @Override
        public TagType type() {
            return TagType.SHORT;
        }

        @Override
        void write(DataOutput output) throws IOException {
            output.writeShort(number.shortValue());
        }

        @Override
        void read(DataInput input) throws IOException {
            this.number = input.readShort();
        }
    }

    static class DoubleTag extends NumberTag {
        public DoubleTag(Number number) {
            super(number);
        }

        @Override
        public TagType type() {
            return TagType.DOUBLE;
        }

        @Override
        void write(DataOutput output) throws IOException {
            output.writeDouble(number.doubleValue());
        }

        @Override
        void read(DataInput input) throws IOException {
            this.number = input.readDouble();
        }
    }

    static class FloatTag extends NumberTag {
        public FloatTag(Number number) {
            super(number);
        }

        @Override
        public TagType type() {
            return TagType.FLOAT;
        }

        @Override
        void write(DataOutput output) throws IOException {
            output.writeFloat(number.floatValue());
        }

        @Override
        void read(DataInput input) throws IOException {
            this.number = input.readFloat();
        }
    }

    static class ByteTag extends NumberTag {
        public ByteTag(Number number) {
            super(number);
        }

        @Override
        public TagType type() {
            return TagType.BYTE;
        }

        @Override
        void write(DataOutput output) throws IOException {
            output.writeByte(number.byteValue());
        }

        @Override
        void read(DataInput input) throws IOException {
            this.number = input.readByte();
        }
    }
}
