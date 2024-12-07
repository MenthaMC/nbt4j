package xyz.frish2021.nbt.array;

import xyz.frish2021.nbt.tag.TagType;
import xyz.frish2021.nbt.util.CommonUtil;

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
        builder.append("[");

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
