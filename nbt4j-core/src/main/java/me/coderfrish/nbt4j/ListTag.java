package me.coderfrish.nbt4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

import static me.coderfrish.nbt4j.StreamUtils.*;

public class ListTag extends ElementTag implements Iterable<ElementTag> {
    private final List<ElementTag> tags = new CopyOnWriteArrayList<>();

    public void add(ElementTag tag) {
        tags.add(tag);
    }

    public void add(int index, ElementTag tag) {
        tags.add(index, tag);
    }

    public void addProperty(String tag) {
        tags.add(new StringTag(tag));
    }

    public void addProperty(int index, String tag) {
        tags.add(index, new StringTag(tag));
    }

    public void addProperty(Number tag) {
        tags.add(createNumberTag(tag));
    }

    public void addProperty(int index, Number tag) {
        tags.add(index, createNumberTag(tag));
    }

    public ElementTag get(int index) {
        return tags.get(index);
    }

    public ElementTag remove(int index) {
        return tags.remove(index);
    }

    public ElementTag replace(int index, ElementTag tag) {
        return tags.set(index, tag);
    }

    public int size() {
        return tags.size();
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    public boolean isNotEmpty() {
        return !tags.isEmpty();
    }

    public boolean contains(ElementTag tag) {
        return tags.contains(tag);
    }

    public ElementTag getFirst() {
        return tags.getFirst();
    }

    public ElementTag getLast() {
        return tags.getLast();
    }

    @Override
    public ListTag getAsList() {
        return this;
    }

    @Override
    public TagType type() {
        return TagType.LIST;
    }

    public Stream<ElementTag> stream() {
        return tags.stream();
    }

    @Override
    public Iterator<ElementTag> iterator() {
        return tags.iterator();
    }

    @Override
    void write(DataOutput output) throws IOException {
        if (isEmpty()) {
            writeType(TagType.END, output);
            output.writeInt(0);
            return;
        }

        TagType type = getFirst().type();
        writeType(type, output);
        output.writeInt(size());
        for (ElementTag tag : this) {
            tag.write(output);
        }
    }

    @Override
    void read(DataInput input) throws IOException {
        TagType type = readType(input);
        if (type == TagType.END) {
            return;
        }

        int length = input.readInt();
        for (int i = 0; i < length; i++) {
            ElementTag tag = createTagInstance(type);
            tag.read(input);
            this.add(tag);
        }
    }
}
