package io.github.xiefrish2021.tag.array;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.exception.NBTWriteException;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

@ApiStatus.Internal
public class ArrayTag<V> implements Iterable<V>, ITag {
    private final V[] array;

    public ArrayTag(V[] array) {
        if (array == null) {
            throw new NBTWriteException("The array type cannot be null.");
        }

        this.array = array;
    }

    public int size() {
        return array.length;
    }

    @Override
    public @NotNull Iterator<V> iterator() {
        return Arrays.asList(array).iterator();
    };

    public V get(int index) {
        return array[index];
    }

    public void forEach(Consumer<? super V> action) {
        for (V v : array) {
            action.accept(v);
        }
    }

    public Spliterator<V> spliterator() {
        return Arrays.spliterator(array);
    }
}
