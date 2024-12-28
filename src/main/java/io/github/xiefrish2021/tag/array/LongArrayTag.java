package io.github.xiefrish2021.tag.array;

import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.util.CommonUtil;

@SuppressWarnings("all")
public class LongArrayTag extends ArrayTag<Long> {
    public LongArrayTag(long[] array) {
        super(CommonUtil.toPrimitive(array));
    }

    @Override
    public TagType type() {
        return TagType.LONG_ARRAY;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[L;");

        int i = 0;
        for (long value : this) {
            builder.append(value).append("L");
            if (i < size() - 1) {
                builder.append(", ");
            }

            i++;
        }
        builder.append("]");

        return builder.toString();
    }
}
