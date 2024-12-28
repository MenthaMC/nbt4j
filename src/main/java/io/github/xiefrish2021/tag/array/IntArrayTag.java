package io.github.xiefrish2021.tag.array;

import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.util.CommonUtil;

@SuppressWarnings("all")
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
