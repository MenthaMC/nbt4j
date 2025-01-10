package me.coderfrish.nbt.common;

import me.coderfrish.nbt.io.input.DataInputStream;
import me.coderfrish.nbt.io.output.DataOutputStream;
import me.coderfrish.nbt.type.NBTPrimitive;

import java.io.IOException;

public enum TagType {
    END(null, null),
    BYTE((dataOutput, o) -> {
        if (o.getAsObject() instanceof Boolean) {
            dataOutput.writeBoolean(o.getAsBoolean());
        } else {
            dataOutput.writeByte(o.getAsByte());
        }
    }, DataInputStream::readByte),
    SHORT((dataOutput, o) -> {
        dataOutput.writeShort(o.getAsShort());
    }, DataInputStream::readShort),
    INT((dataOutput, o) -> {
        dataOutput.writeInt(o.getAsInt());
    }, DataInputStream::readInt),
    LONG((dataOutput, o) -> {
        dataOutput.writeLong(o.getAsLong());
    }, DataInputStream::readLong),
    FLOAT((dataOutput, o) -> {
        dataOutput.writeFloat(o.getAsFloat());
    }, DataInputStream::readFloat),
    DOUBLE((dataOutput, o) -> {
        dataOutput.writeDouble(o.getAsDouble());
    }, DataInputStream::readDouble),
    BYTE_ARRAY((dataOutput, o) -> {
        dataOutput.writeByteArray(o.getAsByteArray());
    }, DataInputStream::readByteArray),
    STRING((dataOutput, o) -> {
        dataOutput.writeUTF(o.getAsString());
    }, DataInputStream::readUTF),
    LIST((dataOutput, o) -> {
        dataOutput.writeList(o.getAsList());
    }, DataInputStream::readList),
    COMPOUND((dataOutput, o) -> {
        dataOutput.writeObject(o.getAsObject());
    }, DataInputStream::readCompound),
    INT_ARRAY((dataOutput, o) -> {
        dataOutput.writeIntArray(o.getAsIntArray());
    }, DataInputStream::readIntArray),
    LONG_ARRAY((dataOutput, o) -> {
        dataOutput.writeLongArray(o.getAsLongArray());
    }, DataInputStream::readLongArray),;

    public final Encoder encoder;
    public final Decoder decoder;

    TagType(Encoder encoder, Decoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    @FunctionalInterface
    public interface Encoder {
        void encode(DataOutputStream output, NBTPrimitive primitive) throws IOException;
    }

    @FunctionalInterface
    public interface Decoder {
        Object decode(DataInputStream output) throws IOException;
    }
}
