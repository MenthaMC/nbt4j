package me.coderfrish.util;

import java.util.List;

public class CommonUtil {
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
}
