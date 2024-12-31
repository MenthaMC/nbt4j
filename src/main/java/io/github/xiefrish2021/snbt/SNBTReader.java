package io.github.xiefrish2021.snbt;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.NBTException;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.tag.ByteTag;
import io.github.xiefrish2021.tag.IntTag;
import io.github.xiefrish2021.tag.LongTag;
import io.github.xiefrish2021.tag.StringTag;
import io.github.xiefrish2021.tag.array.ByteArrayTag;
import io.github.xiefrish2021.tag.array.IntArrayTag;
import io.github.xiefrish2021.tag.array.LongArrayTag;
import io.github.xiefrish2021.tag.compound.CompoundTag;
import io.github.xiefrish2021.tag.list.ListTag;
import io.github.xiefrish2021.util.CommonUtil;
import io.github.xiefrish2021.util.ReaderUtil;

import java.util.ArrayList;
import java.util.List;

public class SNBTReader {
    private final String snbt;
    private int readerIndex = 0;

    public SNBTReader(String snbt) {
        this.snbt = snbt;
    }

    private char readChar() {
        if (canRead()) {
            return snbt.charAt(readerIndex++);
        } throw new NBTException("The string is no longer readable.");
    }

    private boolean canRead(int offset) {
        return this.readerIndex + offset <= snbt.length();
    }

    private boolean canRead() {
        return canRead(1);
    }

    private void expect(char c) {
        if (this.canRead() && this.peek() == c) {
            this.skip();
        } else {
            throw new NBTException("String can no longer be read.");
        }
    }

    private char peek() {
        return peek(0);
    }

    private char peek(int offset) {
        return snbt.charAt(this.readerIndex + offset);
    }

    private void skip() {
        readerIndex++;
    }

    private void skipWhitespace() {
        while(this.canRead() && Character.isWhitespace(this.peek())) {
            this.skip();
        }
    }

    private boolean hasElementSeparator() {
        this.skipWhitespace();
        if (this.canRead() && this.peek() == ',') {
            this.skip();
            this.skipWhitespace();
            return true;
        } else {
            return false;
        }
    }

    private String readKey() {
        this.skipWhitespace();
        if (!this.canRead()) {
            throw new NBTException("String can no longer be read.");
        } else {
            return this.readString();
        }
    }

    private String readString() {
        if (!this.canRead()) {
            return "";
        } else {
            char next = this.peek();
            if (isQuotedStringStart(next)) {
                this.skip();
                return this.readStringUntil(next);
            } else {
                return this.readUnquotedString();
            }
        }
    }

    private boolean isQuotedStringStart(char c) {
        return c == '"' || c == '\'';
    }

    private boolean isAllowedInUnquotedString(char c) {
        return c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '_' || c == '-' || c == '.' || c == '+';
    }

    private String readUnquotedString() {
        int start = this.readerIndex;

        while(this.canRead() && isAllowedInUnquotedString(this.peek())) {
            this.skip();
        }

        return snbt.substring(start, this.readerIndex);
    }

    private String readStringUntil(char terminator) {
        StringBuilder result = new StringBuilder();
        boolean escaped = false;

        while(this.canRead()) {
            char c = this.readChar();
            if (escaped) {
                if (c != terminator && c != '\\') {
                    this.readerIndex = this.readerIndex - 1;
                    throw new NBTException();
                }

                result.append(c);
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else {
                if (c == terminator) {
                    return result.toString();
                }

                result.append(c);
            }
        }

        throw new NBTException();
    }

    private String readQuotedString() {
        if (!this.canRead()) {
            return "";
        } else {
            char next = this.peek();
            if (!isQuotedStringStart(next)) {
                throw new NBTException("String is not quoted string start.");
            } else {
                this.skip();
                return this.readStringUntil(next);
            }
        }
    }

    private ITag readValue() {
        skipWhitespace();
        if (!canRead()) {
            throw new NBTException("Unexpected end of tag.");
        } else {
            char c0 = peek();
            if (c0 == '{') {
                return this.readStruct();
            } else {
                return c0 == '[' ? readList() : readTypedValue();
            }
        }
    }

    private ITag readTypedValue() {
        skipWhitespace();
        int i = this.readerIndex;
        if (isQuotedStringStart(peek())) {
            return new StringTag(readQuotedString());
        } else {
            String s = this.readUnquotedString();
            if (s.isEmpty()) {
                this.readerIndex = i;
                throw new NBTException("Empty tag found.");
            } else {
                return ReaderUtil.snbtType(s);
            }
        }
    }

    private ITag readList() {
        return this.canRead(3) && !isQuotedStringStart(peek(1)) && peek(2) == ';' ? this.readArrayTag() : this.readListTag();
    }

    private ITag readArrayTag() {
        this.expect('[');
        int i = this.readerIndex;
        char c0 = this.readChar();
        this.readChar();
        this.skipWhitespace();
        if (!this.canRead()) {
            throw new NBTException("Unexpected end of tag.");
        } else if (c0 == 'B') {
            return new ByteArrayTag(CommonUtil.numberList2ByteArray(this.readArray(TagType.BYTE)));
        } else if (c0 == 'L') {
            return new LongArrayTag(CommonUtil.numberList2LongArray(this.readArray(TagType.LONG)));
        } else if (c0 == 'I') {
            return new IntArrayTag(CommonUtil.numberList2IntArray(this.readArray(TagType.INT)));
        } else {
            this.readerIndex = i;
            throw new NBTException("String can no longer be read.");
        }
    }

    private List<Number> readArray(TagType p_129363_) {
        List<Number> list = new ArrayList<>();

        while(true) {
            if (peek() != ']') {
                int i = readerIndex;
                ITag tag = this.readValue();
                TagType tagtype = tag.type();
                if (tagtype != p_129363_) {
                    this.readerIndex = i;
                    throw new NBTException("Unknown reason.");
                }

                if (p_129363_ == TagType.BYTE) {
                    list.add(((ByteTag)tag).value());
                } else if (p_129363_ == TagType.LONG) {
                    list.add(((LongTag)tag).value());
                } else {
                    list.add(((IntTag)tag).value());
                }

                if (hasElementSeparator()) {
                    if (!canRead()) {
                        throw new NBTException("Unexpected end of tag.");
                    }
                    continue;
                }
            }

            expect(']');
            return list;
        }
    }

    private ITag readListTag() {
        expect('[');
        skipWhitespace();
        if (!canRead()) {
            throw new NBTException("Unexpected end of tag.");
        } else {
            ListTag<ITag> listtag = new ListTag<>();
            TagType tagtype = null;

            while(peek() != ']') {
                int i = this.readerIndex;
                ITag tag = this.readValue();
                TagType tagtype1 = tag.type();
                if (tagtype == null) {
                    tagtype = tagtype1;
                } else if (tagtype1 != tagtype) {
                    this.readerIndex = i;
                    throw new NBTException("Unknown reason.");
                }

                listtag.add(tag);
                if (!hasElementSeparator()) {
                    break;
                }

                if (!canRead()) {
                    throw new NBTException("Unexpected end of tag.");
                }
            }

            expect(']');
            return listtag;
        }
    }

    private CompoundTag readStruct() {
        CompoundTag entries = new CompoundTag();

        expect('{');
        skipWhitespace();
        while (canRead() && peek() != '}'){
            int mark = this.readerIndex;
            String key = readKey();
            if (key.isEmpty()) {
                this.readerIndex = mark;
                throw new NBTException("Empty tag found.");
            }

            expect(':');
            entries.put(key, readValue());
            if (!hasElementSeparator()) {
                break;
            }
            if (!canRead()) {
                throw new NBTException("Unexpected end of tag.");
            }
        }

        expect('}');
        return entries;
    }

    public CompoundTag parserSNBT() {
        CompoundTag entries = this.readStruct();
        skipWhitespace();

        if (canRead()) {
            throw new NBTException("Failed to parser SNBT.");
        } return entries;
    }
}
