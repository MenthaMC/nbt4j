package me.coderfrish.nbt;

import me.coderfrish.nbt.types.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static me.coderfrish.nbt.util.CommonUtil.*;
import static me.coderfrish.nbt.util.ReaderUtil.*;

@SuppressWarnings("all")
public class NBTReader {
    public static TagCompound readNBTFile(InputStream input) {
        Map<String, ITagBase> compounds = new LinkedHashMap<>();
        try(DataInputStream inp = new DataInputStream(input)) {
            readType(inp);
            readString(inp);

            return readCompound(inp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static TagCompound readNBTFile(File file) {
        try {
            return readNBTFile(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
