package xyz.frish2021.nbt.compound;

import xyz.frish2021.nbt.tag.ITag;

public interface Compound extends Iterable<Compound.Entry>, ITag, Getter {
    Compound put(String key, ITag value);

    Compound putAll(Compound compound);

    Compound remove(String key);

    Compound remove(String key, ITag value);

    Compound replace(String key, ITag oldValue, ITag newValue);

    Compound replace(String key, ITag newValue);

    boolean containsKey(String key);

    boolean containsValue(ITag value);

    boolean isEmpty();

    void clear();

    int size();

    interface Entry {
        String key();

        ITag value();
    }
}
