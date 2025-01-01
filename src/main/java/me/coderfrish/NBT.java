package me.coderfrish;

import me.coderfrish.core.NBTException;
import me.coderfrish.core.ObjectNBTMapper;
import me.coderfrish.core.SNBTReader;
import me.coderfrish.tag.compound.CompoundTag;
import me.coderfrish.util.ReaderUtil;
import me.coderfrish.util.WriteUtil;

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Frish2021
 */
@SuppressWarnings("unused")
public final class NBT {
    /**
     * @param name NBT name.
     * @param out NBT file output stream.
     * @param object Java Bean.
     * The authors of this method cannot guarantee its stability and may be removed in the future.
     */
    @Experimental
    public static void writeNamedNBT(String name, Object object, OutputStream out) {
        writeNamedNBT(name, new ObjectNBTMapper(object).toNBT(), out);
    }

    /**
     * @param out NBT file output stream.
     * @param object Java Bean.
     * The authors of this method cannot guarantee its stability and may be removed in the future.
     */
    @Experimental
    public static void writeUnnamedNBT(Object object, OutputStream out) {
        writeUnnamedNBT(new ObjectNBTMapper(object).toNBT(), out);
    }

    /**
     * @param name NBT name.
     * @param out NBT file output stream.
     * @param tag NBT compound tag.
     */
    public static void writeNamedNBT(String name, CompoundTag tag, OutputStream out) {
        try(DataOutputStream buffer = new DataOutputStream(out)) {
            WriteUtil.writeType(tag.type(), buffer);
            WriteUtil.writeString(name, buffer);
            WriteUtil.writeCompound(tag, buffer);
        } catch (Exception e) {
            throw new NBTException(e);
        }
    }

    /**
     * @param out NBT file output stream.
     * @param tag NBT compound tag.
     */
    public static void writeUnnamedNBT(CompoundTag tag, OutputStream out) {
        writeNamedNBT("", tag, out);
    }

    /**
     * @param tag NBT compound tag.
     */
    public static String generateSNBT(CompoundTag tag) {
        try {
            return tag.toString();
        } catch (Exception e) {
            throw new NBTException(e);
        }
    }

    /**
     * @param in NBT file input stream.
     */
    public static CompoundTag readUnnamedNBT(InputStream in) {
        try(DataInputStream buffer = new DataInputStream(in)) {
            if (ReaderUtil.readType(buffer) != TagType.COMPOUND) {
                throw new NBTException("Invalid nbt.");
            }

            ReaderUtil.readString(buffer);
            return ReaderUtil.readCompound(buffer);
        } catch (Exception e) {
            throw new NBTException(e);
        }
    }

    /**
     * @param snbt SNBT input.
     */
    public static CompoundTag readUnnamedSNBT(String snbt) {
        try {
            return new SNBTReader(snbt).parserSNBT();
        } catch (Exception e) {
            throw new NBTException(e);
        }
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.METHOD)
    private @interface Experimental {}
}
