package me.coderfrish.test;

import java.util.Iterator;

public class ItrorTets {
    public static void main(String[] args) {
        int[] ints = {0, 50, 60, 70, 90};
        Itor itor = new Itor(ints);

        while (itor.hasNext()) {
            System.out.println(itor.next());
        }
    }

    public static class Itor implements Iterator<Integer> {
        private final int[] array;
        private int index = 0;

        public Itor(int[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public Integer next() {
            Integer value = array[index];
            index++;
            return value;
        }
    }
}
