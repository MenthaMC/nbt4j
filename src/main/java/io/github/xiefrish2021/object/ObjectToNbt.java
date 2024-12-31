package io.github.xiefrish2021.object;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.NBTException;
import io.github.xiefrish2021.tag.*;
import io.github.xiefrish2021.tag.array.ByteArrayTag;
import io.github.xiefrish2021.tag.array.IntArrayTag;
import io.github.xiefrish2021.tag.array.LongArrayTag;
import io.github.xiefrish2021.tag.compound.CompoundTag;
import io.github.xiefrish2021.tag.list.ListTag;

import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.util.Collection;

public class ObjectToNbt {
    private final Object object;
    private final CompoundTag entries = new CompoundTag();

    public ObjectToNbt(Object object) {
        this.object = object;
        this.serialization();
    }

    private void serialization() {
        Class<?> ref = object.getClass();
        if (ref.isRecord()) {
            for (RecordComponent component : ref.getRecordComponents()) {
                try {
                    String key = component.getName();
                    Object invoke = ref.getMethod(key).invoke(object);
                    if (invoke != null) {
                        ITag value = readValue(invoke);

                        entries.put(key, value);
                    }
                } catch (Exception e) {
                    throw new NBTException(e);
                }
            }
        } else {
            for (Field field : ref.getFields()) {
                try {
                    Object o = field.get(object);
                    if (o != null) {
                        String key = field.getName();
                        ITag value = readValue(o);

                        entries.put(key, value);
                    }
                } catch (Exception e) {
                    throw new NBTException(e);
                }
            }
        }
    }

    private ITag readValue(Object value) {
        switch (value) {
            case String string -> {
                return new StringTag(string);
            }
            case Integer integer -> {
                return new IntTag(integer);
            }
            case Float floats -> {
                return new FloatTag(floats);
            }
            case Double doubles -> {
                return new DoubleTag(doubles);
            }
            case Byte bytes -> {
                return new ByteTag(bytes);
            }
            case Short shorts -> {
                return new ShortTag(shorts);
            }
            case Long longs -> {
                return new LongTag(longs);
            }
            case int[] ints -> {
                return new IntArrayTag(ints);
            }
            case byte[] bytes -> {
                return new ByteArrayTag(bytes);
            }
            case long[] longs -> {
                return new LongArrayTag(longs);
            }
            case Collection<?> collection -> {
                ListTag<ITag> tags = new ListTag<>();
                collection.forEach(values -> {
                    tags.add(readValue(values));
                });
                return tags;
            }
            case ITag tag -> {
                return tag;
            }
            default -> {
                return new ObjectToNbt(object).toNBT();
            }
        }
    }

    public CompoundTag toNBT() {
        return entries;
    }
}
