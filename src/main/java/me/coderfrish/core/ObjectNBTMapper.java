package me.coderfrish.core;

import me.coderfrish.ITag;
import me.coderfrish.tag.*;
import me.coderfrish.tag.array.ByteArrayTag;
import me.coderfrish.tag.array.IntArrayTag;
import me.coderfrish.tag.array.LongArrayTag;
import me.coderfrish.tag.compound.CompoundTag;
import me.coderfrish.tag.list.ListTag;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.RecordComponent;
import java.util.Collection;
import java.util.Map;

public class ObjectNBTMapper {
    private final Object object;

    public ObjectNBTMapper(Object object) {
        this.object = object;
    }

    public CompoundTag toNBT() {
        CompoundTag entries = new CompoundTag();
        Class<?> objectRef = object.getClass();
        try {
            if (objectRef.isRecord()) {
                for (RecordComponent component : objectRef.getRecordComponents()) {
                    String key = component.getName();
                    Object invoked = objectRef.getMethod(key).invoke(object);
                    if (invoked != null) {
                        entries.put(key, writePrimitives(invoked));
                    }
                }
            } else if (isPlainClass(objectRef)){
                for (Field field : objectRef.getFields()) {
                    String key = field.getName();
                    Object invoked = objectRef.getField(key).get(object);

                    if (invoked != null) {
                        entries.put(key, writePrimitives(invoked));
                    }
                }
            } else {
                throw new NBTException("This feature can only support simple Java classes and Java record classes.");
            }
        } catch (Exception e) {
            throw new NBTException(e);
        }

        return entries;
    }

    private static boolean isPlainClass(Class<?> clazz) {
        return !clazz.isAnnotation() && !clazz.isInterface() && !clazz.isEnum() && !Modifier.isAbstract(clazz.getModifiers());
    }

    private static ITag writePrimitives(Object object) {
        return switch (object) {
            case String string -> new StringTag(string);
            case Integer integer -> new IntTag(integer);
            case Float f -> new FloatTag(f);
            case Long l -> new LongTag(l);
            case Double d -> new DoubleTag(d);
            case Short s -> new ShortTag(s);
            case Byte b -> new ByteTag(b);
            case byte[] array -> new ByteArrayTag(array);
            case int[] array -> new IntArrayTag(array);
            case long[] array -> new LongArrayTag(array);
            case ITag tag -> tag;
            case Collection<?> collection -> {
                ListTag listTag = new ListTag();
                for (Object o : collection) {
                    listTag.add(writePrimitives(o));
                }

                yield listTag;
            }
            case Boolean bool -> {
                if (bool) {
                    yield new ByteTag((byte) 1);
                } yield new ByteTag((byte) 0);
            }
            case Map<?, ?> ignored -> throw new NBTException("Map type is not supported.");
            case Object o -> new ObjectNBTMapper(o).toNBT();
        };
    }
}
