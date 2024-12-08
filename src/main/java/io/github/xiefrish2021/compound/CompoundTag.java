package io.github.xiefrish2021.compound;

import io.github.xiefrish2021.primitive.number.*;
import org.jetbrains.annotations.NotNull;
import io.github.xiefrish2021.api.Compound;
import io.github.xiefrish2021.array.ByteArrayTag;
import io.github.xiefrish2021.array.IntArrayTag;
import io.github.xiefrish2021.array.LongArrayTag;
import io.github.xiefrish2021.primitive.PrimitiveTag;
import io.github.xiefrish2021.primitive.StringTag;
import io.github.xiefrish2021.api.ITag;
import io.github.xiefrish2021.tag.TagType;

import java.util.*;
import java.util.function.Consumer;

public class CompoundTag implements Compound {
    private final Map<String, ITag> compounds = new LinkedHashMap<>();

    @Override
    public TagType type() {
        return TagType.COMPOUND;
    }

    @Override
    public @NotNull Iterator<Entry> iterator() {
        List<Entry> list = new ArrayList<>();
        compounds.forEach((key, value) -> list.add(new Entry() {
            @Override
            public String key() {
                return key;
            }

            @Override
            public ITag value() {
                return value;
            }
        }));

        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super Entry> action) {
        for (Map.Entry<String, ITag> entry : compounds.entrySet()) {
            action.accept(new Entry() {
                @Override
                public String key() {
                    return entry.getKey();
                }

                @Override
                public ITag value() {
                    return entry.getValue();
                }
            });
        }
    }

    @Override
    public Spliterator<Entry> spliterator() {
        List<Entry> list = new ArrayList<>();
        compounds.forEach((key, value) -> list.add(new Entry() {
            @Override
            public String key() {
                return key;
            }

            @Override
            public ITag value() {
                return value;
            }
        }));

        return list.spliterator();
    }

    @Override
    public Compound put(String key, ITag value) {
        compounds.put(key, value);
        return this;
    }

    @Override
    public Compound putAll(Compound compound) {
        for (Entry entry : this) {
            if (compound.containsKey(entry.key())) {
                replace(entry.key(), entry.value());
            } else {
                put(entry.key(), entry.value());
            }
        }

        return this;
    }

    @Override
    public Compound remove(String key) {
        this.remove(key, get(key));
        return this;
    }

    @Override
    public Compound remove(String key, ITag value) {
        compounds.remove(key, value);
        return this;
    }

    @Override
    public Compound replace(String key, ITag oldValue, ITag newValue) {
        compounds.replace(key, oldValue, newValue);
        return this;
    }

    @Override
    public Compound replace(String key, ITag newValue) {
        this.replace(key, get(key), newValue);
        return this;
    }

    @Override
    public ITag get(String key) {
        return compounds.get(key);
    }

    @Override
    public ITag get(String key, ITag defaultValue) {
        return compounds.getOrDefault(key, defaultValue);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V> PrimitiveTag<V> getPrimitive(String key) {
        return (PrimitiveTag<V>) get(key);
    }

    @Override
    public String getString(String key) {
        return ((StringTag) get(key)).value();
    }

    @Override
    public int getInt(String key) {
        return ((IntTag) get(key)).value();
    }

    @Override
    public float getFloat(String key) {
        return ((FloatTag) get(key)).value();
    }

    @Override
    public double getDouble(String key) {
        return ((DoubleTag) get(key)).value();
    }

    @Override
    public byte getByte(String key) {
        return ((ByteTag) get(key)).value();
    }

    @Override
    public long getLong(String key) {
        return ((LongTag) get(key)).value();
    }

    @Override
    public short getShort(String key) {
        return ((ShortTag) get(key)).value();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V extends ITag> io.github.xiefrish2021.api.List<V> getList(String key) {
        return ((io.github.xiefrish2021.api.List<V>) get(key));
    }

    @Override
    public ByteArrayTag getByteArray(String key) {
        return (ByteArrayTag) get(key);
    }

    @Override
    public IntArrayTag getIntArray(String key) {
        return (IntArrayTag) get(key);
    }

    @Override
    public LongArrayTag getLongArray(String key) {
        return (LongArrayTag) get(key);
    }

    @Override
    public CompoundTag getCompound(String key) {
        return ((CompoundTag) get(key));
    }

    @Override
    public boolean containsKey(String key) {
        return compounds.containsKey(key);
    }

    @Override
    public boolean containsValue(ITag value) {
        return compounds.containsValue(value);
    }

    @Override
    public boolean isEmpty() {
        return compounds.isEmpty();
    }

    @Override
    public void clear() {
        compounds.clear();
    }

    @Override
    public int size() {
        return compounds.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        int i = 0;
        for (Compound.Entry entry : this) {
            if (entry.key().matches("[0-9A-Za-z_\\-.+]+")) {
                builder.append(entry.key());
            } else {
                builder.append("\"").append(entry.key()).append("\"");
            }

            builder.append(":").append(" ");
            builder.append(entry.value().toString());

            if (i < size() - 1) {
                builder.append(",");
            }

            i++;
        }
        builder.append("}");

        return builder.toString();
    }
}
