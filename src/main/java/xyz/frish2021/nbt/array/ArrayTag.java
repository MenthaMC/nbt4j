package xyz.frish2021.nbt.array;

import org.jetbrains.annotations.NotNull;
import xyz.frish2021.nbt.api.Array;
import xyz.frish2021.nbt.exception.NBTWriteException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayTag<V> implements Array<V> {
    private final V[] array;

    public ArrayTag(V[] array) {
        if (array == null) {
            throw new NBTWriteException("The array type cannot be null.");
        }

        this.array = array;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public V get(int index) {
        return array[index];
    }

    @Override
    public @NotNull Iterator<V> iterator() {
        return Arrays.asList(array).iterator();
    }

    @Override
    public void forEach(Consumer<? super V> action) {
        for (V v : array) {
            action.accept(v);
        }
    }

    @Override
    public Spliterator<V> spliterator() {
        return Arrays.spliterator(array);
    }
}
