package io.github.xiefrish2021.compound;

import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import io.github.xiefrish2021.api.Compound;
import io.github.xiefrish2021.api.ITag;
import io.github.xiefrish2021.tag.TagType;
import org.jetbrains.annotations.Nullable;

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
    public @NotNull Compound put(@NotNull String key, @NotNull ITag value) {
        compounds.put(key, value);
        return this;
    }

    @Override
    public @NotNull Compound putAll(@NotNull Compound compound) {
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
        this.remove(key, get(key).asTag());
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
        this.replace(key, get(key).asTag(), newValue);
        return this;
    }

    @Override
    public @NotNull NBTElement get(@NotNull String key) {
        return new NBTElement(compounds.get(key));
    }

    @Override
    public @NotNull NBTElement get(@NotNull String key, @NotNull ITag defaultValue) {
        return new NBTElement(compounds.getOrDefault(key, defaultValue));
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

    @Override
    public @NotNull NBTElement getValue(@Nullable Void nothing, @NotNull KProperty<?> property) {
        return get(property.getName());
    }
}
