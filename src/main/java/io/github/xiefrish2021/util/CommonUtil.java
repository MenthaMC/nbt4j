package io.github.xiefrish2021.util;

import io.github.xiefrish2021.api.ITag;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class CommonUtil {
    public static Byte[] toPrimitive(byte[] bytes) {
        if (bytes == null) {
            return null;
        } else if (bytes.length == 0) {
            return new Byte[0];
        }

        Byte[] buffer = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            buffer[i] = bytes[i];
        }

        return buffer;
    }

    public static Long[] toPrimitive(long[] longs) {
        if (longs == null) {
            return null;
        } else if (longs.length == 0) {
            return new Long[0];
        }

        Long[] buffer = new Long[longs.length];
        for (int i = 0; i < longs.length; i++) {
            buffer[i] = longs[i];
        }

        return buffer;
    }

    public static Integer[] toPrimitive(int[] ints) {
        if (ints == null) {
            return null;
        } else if (ints.length == 0) {
            return new Integer[0];
        }

        Integer[] buffer = new Integer[ints.length];
        for (int i = 0; i < ints.length; i++) {
            buffer[i] = ints[i];
        }

        return buffer;
    }

    public static int[] numberList2IntArray(List<Number> list) {
        return numberArray2intArray(numberList2Array(list));
    }

    public static byte[] numberList2ByteArray(List<Number> list) {
        return numberArray2ByteArray(numberList2Array(list));
    }

    public static long[] numberList2LongArray(List<Number> list) {
        return numberArray2LongArray(numberList2Array(list));
    }

    private static Number[] numberList2Array(List<Number> list) {
        Number[] array = new Number[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private static long[] numberArray2LongArray(Number[] list) {
        long[] array = new long[list.length];

        for (int i = 0; i < list.length; i++) {
            array[i] = list[i].longValue();
        }
        return array;
    }


    private static byte[] numberArray2ByteArray(Number[] list) {
        byte[] array = new byte[list.length];

        for (int i = 0; i < list.length; i++) {
            array[i] = list[i].byteValue();
        }
        return array;
    }


    private static int[] numberArray2intArray(Number[] list) {
        int[] array = new int[list.length];

        for (int i = 0; i < list.length; i++) {
            array[i] = list[i].intValue();
        }
        return array;
    }

    private static <V extends ITag> List<V> ListTagToList(io.github.xiefrish2021.api.List<V> list) {
        List<V> result = new ArrayList<>();

        for(V v : list) {
            result.add(v);
        }

        return result;
    }
}
