package xyz.frish2021.nbt.array;

import xyz.frish2021.nbt.tag.TagType;
import xyz.frish2021.nbt.util.CommonUtil;

public class ByteArrayTag extends ArrayTag<Byte> {
    public ByteArrayTag(byte[] array) {
        super(CommonUtil.toPrimitive(array));
    }

    @Override
    public TagType type() {
        return TagType.BYTE_ARRAY;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        int i = 0;
        for (byte value : this) {
            builder.append(value).append("B");
            if (i < size() - 1) {
                builder.append(", ");
            }

            i++;
        }
        builder.append("]");

        return builder.toString();
    }
}
