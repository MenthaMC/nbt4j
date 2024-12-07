package xyz.frish2021.nbt.util;

import xyz.frish2021.nbt.tag.ITag;

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

    public static int[] intList2Array(List<? extends Integer> list) {
        int[] array = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static long[] longList2Array(List<? extends Long> list) {
        long[] array = new long[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static byte[] byteList2Array(List<? extends Byte> list) {
        byte[] array = new byte[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static <V extends ITag> List<V> ListTagToList(xyz.frish2021.nbt.list.List<V> list) {
        List<V> result = new ArrayList<>();

        for(V v : list) {
            result.add(v);
        }

        return result;
    }
}
