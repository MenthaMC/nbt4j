package io.github.xiefrish2021.array;

import io.github.xiefrish2021.tag.TagType;
import io.github.xiefrish2021.util.CommonUtil;

public class IntArrayTag extends ArrayTag<Integer> {
    public IntArrayTag(int[] array) {
        super(CommonUtil.toPrimitive(array));
    }

    @Override
    public TagType type() {
        return TagType.INT_ARRAY;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[I;");

        int i = 0;
        for (int value : this) {
            builder.append(value);
            if (i < size() - 1) {
                builder.append(", ");
            }

            i++;
        }
        builder.append("]");

        return builder.toString();
    }
}
