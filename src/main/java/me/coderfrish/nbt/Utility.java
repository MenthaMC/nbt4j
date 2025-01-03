package me.coderfrish.nbt;

import me.coderfrish.nbt.type.*;
import me.coderfrish.nbt.type.element.NBTElement;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

class Utility {
    private static final Pattern DOUBLE_PATTERN_NOSUFFIX = Pattern.compile("[-+]?(?:[0-9]+[.]|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?", Pattern.CASE_INSENSITIVE);
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", Pattern.CASE_INSENSITIVE);
    private static final Pattern FLOAT_PATTERN = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?f", Pattern.CASE_INSENSITIVE);
    private static final Pattern BYTE_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)b", Pattern.CASE_INSENSITIVE);
    private static final Pattern LONG_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)l", Pattern.CASE_INSENSITIVE);
    private static final Pattern SHORT_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)s", Pattern.CASE_INSENSITIVE);
    private static final Pattern INT_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)");

    public static Object snbtType(String value) {
        try {
            if (FLOAT_PATTERN.matcher(value).matches()) {
                return Float.parseFloat(value.substring(0, value.length() - 1));
            }

            if (BYTE_PATTERN.matcher(value).matches()) {
                return Byte.parseByte(value.substring(0, value.length() - 1));
            }

            if (LONG_PATTERN.matcher(value).matches()) {
                return Long.parseLong(value.substring(0, value.length() - 1));
            }

            if (SHORT_PATTERN.matcher(value).matches()) {
                return Short.parseShort(value.substring(0, value.length() - 1));
            }

            if (INT_PATTERN.matcher(value).matches()) {
                return Integer.parseInt(value);
            }

            if (DOUBLE_PATTERN.matcher(value).matches()) {
                return Double.parseDouble(value.substring(0, value.length() - 1));
            }

            if (DOUBLE_PATTERN_NOSUFFIX.matcher(value).matches()) {
                return Double.parseDouble(value);
            }

            if ("true".equalsIgnoreCase(value)) {
                return (byte) 1;
            }

            if ("false".equalsIgnoreCase(value)) {
                return (byte) 0;
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return value;
    }

    protected static boolean isPlainClass(Class<?> clazz) {
        return !clazz.isAnnotation() && !clazz.isInterface() && !clazz.isEnum() && !Modifier.isAbstract(clazz.getModifiers());
    }

    protected static void writeType(RandomAccessFile raf, TagType type) throws IOException {
        raf.writeByte(type.ordinal());
    }

    protected static TagType getType(Object object) {
        if (object instanceof String) {
            return TagType.STRING;
        } else if (object instanceof Integer) {
            return TagType.INT;
        } else if (object instanceof Long) {
            return TagType.LONG;
        } else if (object instanceof Float) {
            return TagType.FLOAT;
        } else if (object instanceof Double) {
            return TagType.DOUBLE;
        } else if (object instanceof Boolean || object instanceof Byte) {
            return TagType.BYTE;
        } else if (object instanceof Short) {
            return TagType.SHORT;
        } else if (object instanceof int[] || object instanceof TagIntArray) {
            return TagType.INT_ARRAY;
        } else if (object instanceof long[] || object instanceof TagLongArray) {
            return TagType.LONG_ARRAY;
        } else if (object instanceof byte[] || object instanceof TagByteArray) {
            return TagType.BYTE_ARRAY;
        } else if (object instanceof TagList || object instanceof Collection<?>) {
            return TagType.LIST;
        } else {
            return TagType.COMPOUND;
        }
    }

    protected static void writeString(RandomAccessFile raf, String value) throws IOException {
        if (value == null) {
            raf.writeInt(0);
            return;
        }

        byte[] strBytes = value.getBytes(StandardCharsets.UTF_8);
        raf.writeChar(strBytes.length);
        raf.write(strBytes);
    }

    protected static void writeObject(RandomAccessFile raf, TagObject object) throws IOException {
        for (TagObject.Entry entry : object) {
            TagType types = getType(entry.getValue());

            writeType(raf, types);
            writeString(raf, entry.getKey());
            writeValue(raf, entry.getValue(), types);
        }

        writeType(raf, TagType.END);
    }

    protected static void writeValue(RandomAccessFile raf, Object value, TagType type) throws IOException {
        if (type == TagType.STRING) {
            writeString(raf, (String) value);
        } else if (type == TagType.INT) {
            raf.writeInt((int) value);
        } else if (type == TagType.LONG) {
            raf.writeLong((long) value);
        } else if (type == TagType.FLOAT) {
            raf.writeFloat((float) value);
        } else if (type == TagType.DOUBLE) {
            raf.writeDouble((double) value);
        } else if (type == TagType.BYTE) {
            if (value instanceof Boolean) {
                raf.writeByte(((boolean) value) ? 1 : 0);
            } else {
                raf.writeByte((byte) value);
            }
        } else if (type == TagType.SHORT) {
            raf.writeShort((short) value);
        } else if (type == TagType.COMPOUND) {
            if (value instanceof TagObject) {
                writeObject(raf, (TagObject) value);
            } else if (value instanceof Map<?, ?>) {
                throw new UnsupportedOperationException("Map types are not supported.");
            } else {
                Class<?> bean = value.getClass();
                if (isPlainClass(bean)) {
                    try {
                        TagObject object = new TagObject();
                        for (Field field : bean.getFields()) {
                            String key = field.getName();
                            Object beanValue = field.get(value);

                            if (beanValue != null) {
                                object.set(key, beanValue);
                            }
                        }

                        writeObject(raf, object);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    throw new UnsupportedOperationException("Bean conversions do not support this type.");
                }
            }
        } else if (type == TagType.INT_ARRAY) {
            if (value instanceof TagIntArray) {
                TagIntArray array = (TagIntArray) value;

                raf.writeInt(array.size());
                for (int item : array) {
                    raf.writeInt(item);
                }
            } else {
                int[] array = (int[]) value;

                raf.writeInt(array.length);
                for (int item : array) {
                    raf.writeInt(item);
                }
            }
        } else if (type == TagType.LONG_ARRAY) {
            if (value instanceof TagLongArray) {
                TagLongArray array = (TagLongArray) value;

                raf.writeInt(array.size());
                for (long item : array) {
                    raf.writeLong(item);
                }
            } else {
                long[] array = (long[]) value;

                raf.writeInt(array.length);
                for (long item : array) {
                    raf.writeLong(item);
                }
            }
        } else if (type == TagType.BYTE_ARRAY) {
            if (value instanceof TagByteArray) {
                TagByteArray array = (TagByteArray) value;

                raf.writeInt(array.size());
                for (byte item : array) {
                    raf.writeByte(item);
                }
            } else {
                byte[] array = (byte[]) value;

                raf.writeInt(array.length);
                for (byte item : array) {
                    raf.writeByte(item);
                }
            }
        } else if (type == TagType.LIST) {
            if (value instanceof TagList) {
                TagList list = (TagList) value;

                if (list.isEmpty()) {
                    writeType(raf, TagType.END);
                    raf.writeInt(0);
                    return;
                }

                TagType types = getType(list.getFirst().getAsValue());
                writeType(raf, types);
                raf.writeInt(list.size());
                for (NBTElement tag : list) {
                    writeValue(raf, tag.getAsValue(), types);
                }
            } else if (value instanceof Collection<?>) {
                Collection<?> collection = (Collection<?>) value;

                if (collection.isEmpty()) {
                    writeType(raf, TagType.END);
                    raf.writeInt(0);
                    return;
                }

                TagType types = getType(collection.stream().findFirst().orElse(null));
                writeType(raf, types);
                raf.writeInt(collection.size());
                for (Object o : collection) {
                    writeValue(raf, o, types);
                }
            }
        }
    }

    protected static String readString(RandomAccessFile raf) throws IOException {
        byte[] strBytes = new byte[raf.readChar()];
        raf.readFully(strBytes);

        return new String(strBytes, StandardCharsets.UTF_8);
    }

    protected static TagType readType(RandomAccessFile raf) throws IOException {
        return TagType.values()[raf.readByte()];
    }

    protected static TagObject readObject(RandomAccessFile raf) throws IOException {
        TagObject tag = new TagObject();
        for (; ; ) {
            TagType type = readType(raf);
            if (type == TagType.END) break;

            String key = readString(raf);
            Object value = readValue(raf, type);
            tag.set(key, value);
        }

        return tag;
    }

    protected static Object readValue(RandomAccessFile raf, TagType type) throws IOException {
        switch (type) {
            case STRING:
                return readString(raf);
            case INT:
                return raf.readInt();
            case LONG:
                return raf.readLong();
            case FLOAT:
                return raf.readFloat();
            case DOUBLE:
                return raf.readDouble();
            case BYTE_ARRAY: {
                int length = raf.readInt();
                if (length < 0) {
                    throw new RuntimeException("The prefix is of negative length ):");
                }

                byte[] bytes = new byte[length];
                raf.readFully(bytes);

                return new TagByteArray(bytes);
            }
            case BYTE:
                return raf.readByte();
            case SHORT:
                return raf.readShort();
            case COMPOUND:
                return readObject(raf);
            case INT_ARRAY: {
                int length = raf.readInt();
                if (length < 0) {
                    throw new RuntimeException("The prefix is of negative length ):");
                }

                byte[] bytes = new byte[length * 4];
                raf.readFully(bytes);
                int byteIndex = 0;

                int[] intArray = new int[length];
                for (int i = 0; i < length; i++, byteIndex += 4) {
                    int element = 0;
                    int bitOffset = 24;
                    for (int b = 0; b < 4; b++, bitOffset -= 8) {
                        element |= (bytes[byteIndex + b] & 0xFF) << bitOffset;
                    }
                    intArray[i] = element;
                }

                return new TagIntArray(intArray);
            }
            case LONG_ARRAY: {
                int length = raf.readInt();
                if (length < 0) {
                    throw new RuntimeException("The prefix is of negative length ):");
                } else if (length == 0) {
                    return new TagLongArray(new long[0]);
                }

                byte[] bytes = new byte[length * 8];
                raf.readFully(bytes);
                int byteIndex = 0;

                long[] longArray = new long[length];
                for (int i = 0; i < length; i++, byteIndex += 8) {
                    long element = 0;
                    int bitOffset = 56;
                    for (int b = 0; b < 8; b++, bitOffset -= 8) {
                        element |= ((long) (bytes[byteIndex + b] & 0xFF)) << bitOffset;
                    }
                    longArray[i] = element;
                }
                return new TagLongArray(longArray);
            }
            case LIST: {
                TagList list = new TagList();
                TagType type0 = readType(raf);
                if (type0 == TagType.END) {
                    return new TagList();
                }

                int length = raf.readInt();
                for (int i = 0; i < length; i++) {
                    list.add(readValue(raf, type0));
                }

                return list;
            }
            default:
                throw new RuntimeException("Unknown tag type: " + type);
        }
    }
}
