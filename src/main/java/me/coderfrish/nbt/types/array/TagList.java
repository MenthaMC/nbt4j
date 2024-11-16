package me.coderfrish.nbt.types.array;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import static me.coderfrish.nbt.util.CommonUtil.writeType;

@SuppressWarnings("all")
public record TagList<T extends ITagBase>(List<T> list) implements ITagBase {
    public TagList() {
        this(new ArrayList<>());
    }

    @Override
    public void write(DataOutputStream out) throws Exception {
        if (list.isEmpty()) {
            writeType(out, TagType.END);
            out.writeInt(0);
            return;
        }

        writeType(out, list.getFirst().getType());
        out.writeInt(list.size());
        for (T tag : list) {
            tag.write(out);
        }
    }

    public T get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public TagList<T> add(T tag) {
        list.add(tag);
        return this;
    }

    public TagList<T> add(int index, T tag) {
        list.add(index, tag);
        return this;
    }

    public TagList<T> remove(int index) {
        list.remove(index);
        return this;
    }

    public TagList<T> remove(T tag) {
        list.remove(tag);
        return this;
    }

    public List<T> entry() {
        return list;
    }

    public int indexOf(T tag) {
        return list.indexOf(tag);
    }

    public int lastIndexOf(T tag) {
        return list.lastIndexOf(tag);
    }

    public void clear() {
        list.clear();
    }

    public TagList<T> set(int index, T tag) {
        list.set(index, tag);
        return this;
    }

    @Override
    public TagType getType() {
        return TagType.LIST;
    }

    @Override
    public String toString() {
        return "TagList{" +
                "list=" + list +
                '}';
    }
}
