package io.github.xiefrish2021.tag.array;

import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.util.CommonUtil;

@SuppressWarnings("all")
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
        builder.append("[B;");

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
