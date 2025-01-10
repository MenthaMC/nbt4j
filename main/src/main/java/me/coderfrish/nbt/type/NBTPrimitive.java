package me.coderfrish.nbt.type;

public class NBTPrimitive {
    private final Object value;

    public NBTPrimitive(Object value) {
        this.value = value;
    }

    public Object getAsObject() {
        return value;
    }

    public String getAsString() {
        if (value instanceof String) return (String) value;
        throw new RuntimeException("This value is not a string");
    }

    public int getAsInt() {
        if (value instanceof Integer) return (int) value;
        throw new RuntimeException("This value is not a int");
    }

    public float getAsFloat() {
        if (value instanceof Float) return (float) value;
        throw new RuntimeException("This value is not a float");
    }

    public double getAsDouble() {
        if (value instanceof Double) return (double) value;
        throw new RuntimeException("This value is not a double");
    }

    public boolean getAsBoolean() {
        if (value instanceof Byte) return (byte) value == 1;
        else if (value instanceof Boolean) return (boolean) value;
        throw new RuntimeException("This value is not a boolean");
    }

    public byte getAsByte() {
        if (value instanceof Byte) return (byte) value;
        throw new RuntimeException("This value is not a byte");
    }

    public short getAsShort() {
        if (value instanceof Short) return (short) value;
        throw new RuntimeException("This value is not a short");
    }

    public long getAsLong() {
        if (value instanceof Long) return (long) value;
        throw new RuntimeException("This value is not a long");
    }

    public NBTByteArray getAsByteArray() {
        if (value instanceof NBTByteArray) return (NBTByteArray) value;
        else if (value instanceof byte[]) return new NBTByteArray((byte[]) value);
        throw new RuntimeException("This value is not a byte array");
    }

    public NBTIntArray getAsIntArray() {
        if (value instanceof NBTIntArray) return (NBTIntArray) value;
        else if (value instanceof int[]) return new NBTIntArray((int[]) value);
        throw new RuntimeException("This value is not a int array");
    }

    public NBTLongArray getAsLongArray() {
        if (value instanceof NBTLongArray) return (NBTLongArray) value;
        else if (value instanceof long[]) return new NBTLongArray((long[]) value);
        throw new RuntimeException("This value is not a long array");
    }

    public NBTList getAsList() {
        if (value instanceof NBTList) return (NBTList) value;
        throw new RuntimeException("This value is not a list");
    }

    public NBTCompound getAsCompound() {
        if (value instanceof NBTCompound) return (NBTCompound) value;
        throw new RuntimeException("This value is not a compound");
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
