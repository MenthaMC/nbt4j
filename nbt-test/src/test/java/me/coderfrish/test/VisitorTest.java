package me.coderfrish.test;

import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.utils.NBTReader;
import me.coderfrish.nbt.visitor.NBTVisitor;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class VisitorTest {
    public static void main(String[] args) {
        try(InputStream is = new FileInputStream("D:\\nbt\\nbt-test\\src\\test\\resources\\test.nbt")) {
            NBTReader nbtReader = new NBTReader(is);
            nbtReader.accept(new NBTVisitor() {
                @Override
                public void visitElement(String key, ElementTag value) {
                    System.out.println(key);
                    super.visitElement(key, value);
                }
            });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
