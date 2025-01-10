package me.coderfrish.nbt.type;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class NBTCompound implements Iterable<Map.Entry<String, NBTPrimitive>> {
    private final Map<String, NBTPrimitive> primitiveMap;

    public NBTCompound() {
        this.primitiveMap = new LinkedHashMap<>();
    }

    public NBTCompound put(String key, Object value) {
        this.primitiveMap.put(key, new NBTPrimitive(value));
        return this;
    }

    public NBTPrimitive get(String key) {
        return this.primitiveMap.get(key);
    }

    @Override
    public Iterator<Map.Entry<String, NBTPrimitive>> iterator() {
        return primitiveMap.entrySet().iterator();
    }
}
