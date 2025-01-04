package me.coderfrish.nbt;

import me.coderfrish.nbt.common.SNBTReader;
import me.coderfrish.nbt.io.NBTInput;
import me.coderfrish.nbt.io.NBTOutput;
import me.coderfrish.nbt.reflect.SerializationContext;
import me.coderfrish.nbt.type.TagObject;

import java.io.File;

public class NBT {
    /**
     * @param name NBT name.
     * @param out NBT file output stream.
     * @param tag NBT Data.
     */
    public static void writeNamedNBT(String name, Object tag, File out) {
        try(NBTOutput output = new NBTOutput(out)) {
            if (tag instanceof TagObject) {
                output.write(name, (TagObject) tag);
            } else {
                output.write(name, new SerializationContext(tag).toTagObject());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param out NBT file output stream.
     * @param tag NBT Data.
     */
    public static void writeUnnamedNBT(Object tag, File out) {
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

    /**
     * @param tag NBT compound tag.
     */
    public String generateSNBT(TagObject tag) {
        return tag.toString();
    }
}
