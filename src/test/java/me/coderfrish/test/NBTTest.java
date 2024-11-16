package me.coderfrish.test;

import me.coderfrish.nbt.NBTReader;
import me.coderfrish.nbt.NBTWriter;
import me.coderfrish.nbt.types.TagCompound;
import me.coderfrish.nbt.types.TagInt;
import me.coderfrish.nbt.types.TagString;
import me.coderfrish.nbt.types.array.TagList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class NBTTest {
    @Test
    public void test() {
        /* write */
        List<TagCompound> compounds = new ArrayList<>();
        compounds.add(new TagCompound().putString("hello", "Frish2021"));
        compounds.add(new TagCompound().putString("dsgs", "gdsgs"));
        compounds.add(new TagCompound());

        try(OutputStream os = new FileOutputStream("D:\\NBT\\src\\test\\resources\\test.nbt")) {
            TagCompound test = new TagCompound((tag) -> {
                tag.put("hello", new TagString("Frish2021"));
                tag.put("test1", new TagString("CoderFrish"));
                tag.put("test2", new TagInt(114514));

                TagList<TagCompound> list = new TagList<>(compounds);
                list.add(new TagCompound().putInt("hdfg", 114514));
                tag.put("list", list);

                tag.replace("test1", tag.get("test2"));
            });

            NBTWriter.writeNBTFile(test, os);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /* read */
//        try(InputStream os = new FileInputStream("D:\\NBT\\src\\test\\resources\\test.nbt")) {
//            for (ITag list : NBTReader.readNBTFile(os).getList("list").entry()) {
//                System.out.println(list);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}
