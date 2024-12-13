package io.github.xiefrish2021.api;

import io.github.xiefrish2021.compound.CompoundTag;
import io.github.xiefrish2021.exception.NBTException;
import io.github.xiefrish2021.snbt.SNBTReader;
import io.github.xiefrish2021.tag.TagType;
import io.github.xiefrish2021.util.ReaderUtil;
import io.github.xiefrish2021.util.WriteUtil;

import java.io.*;

/**
 * @author Frish2021
 */
public final class NBT {
    private static NBT instance;

    /**
     * @param name NBT name.
     * @param out NBT file output stream.
     * @param tag NBT compound tag.
     */
    public void writeNamedNBT(String name, Compound tag, OutputStream out) {
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
    public void writeUnnamedNBT(Compound tag, OutputStream out) {
        writeNamedNBT("", tag, out);
    }

    /**
     * @param tag NBT compound tag.
     */
    public String generateSNBT(Compound tag) {
        return tag.toString();
    }

    /**
     * @param in NBT file input stream.
     */
    public CompoundTag readUnnamedNBT(InputStream in) {
        try(DataInputStream buffer = new DataInputStream(in)) {
            if (ReaderUtil.readType(buffer) != TagType.COMPOUND) {
                throw new NBTException("Invalid nbt.");
            }

            ReaderUtil.readString(buffer);
            return ReaderUtil.readCompound(buffer);
        } catch (IOException e) {
            throw new NBTException(e);
        }
    }

    /**
     * @param snbt SNBT input.
     */
    public CompoundTag readUnnamedSNBT(String snbt) {
        return new SNBTReader(snbt).parserSNBT();
    }

    /**
     * New a NBT instance.
     */
    public synchronized static NBT getInstance() {
        if (instance == null) {
            instance = new NBT();
        }

        return instance;
    }
}
