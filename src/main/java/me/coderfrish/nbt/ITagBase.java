package me.coderfrish.nbt;

import java.io.DataOutputStream;

public interface ITagBase {
    void write(DataOutputStream out) throws Exception;

    TagType getType();
}
