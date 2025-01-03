package me.coderfrish.nbt;

import me.coderfrish.nbt.type.TagObject;

import java.io.File;

public class NBT {
    /**
     * @param name NBT name.
     * @param out NBT file output stream.
     * @param tag NBT compound tag.
     */
    public static void writeNamedNBT(String name, TagObject tag, File out) {
        try(NBTOutput output = new NBTOutput(out)) {
            output.write(name, tag);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param out NBT file output stream.
     * @param tag NBT compound tag.
     */
    public static void writeUnnamedNBT(TagObject tag, File out) {
        writeNamedNBT("", tag, out);
    }

    /**
     * @param in NBT file input stream.
     */
    public static TagObject readUnnamedNBT(File in) {
        try(NBTInput input = new NBTInput(in)) {
            return input.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param snbt SNBT input.
     */
    public static TagObject readUnnamedSNBT(String snbt) {
        try {
            return new SNBTReader(snbt).parserSNBT();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
