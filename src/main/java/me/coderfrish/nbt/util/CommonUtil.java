package me.coderfrish.nbt.util;

import me.coderfrish.nbt.TagType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("all")
public class CommonUtil {
    public static String readString(DataInputStream input) {
        try {
            return input.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static TagType readType(DataInputStream out) {
        try {
            return TagType.getTypeById(out.readByte());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeString(DataOutputStream out, String value) {
        try {
            byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
            out.writeChar(bytes.length);
            out.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeType(DataOutputStream out, TagType type) {
        try {
            out.write(type.value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
