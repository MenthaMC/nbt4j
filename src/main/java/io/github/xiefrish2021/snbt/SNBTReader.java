package io.github.xiefrish2021.snbt;

import io.github.xiefrish2021.exception.StringReaderException;
import io.github.xiefrish2021.tag.array.ByteArrayTag;
import io.github.xiefrish2021.tag.array.IntArrayTag;
import io.github.xiefrish2021.tag.array.LongArrayTag;
import io.github.xiefrish2021.tag.compound.CompoundTag;
import io.github.xiefrish2021.exception.SNBTReaderException;
import io.github.xiefrish2021.tag.list.ListTag;
import io.github.xiefrish2021.tag.StringTag;
import io.github.xiefrish2021.tag.ByteTag;
import io.github.xiefrish2021.tag.IntTag;
import io.github.xiefrish2021.tag.LongTag;
import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.util.CommonUtil;
import io.github.xiefrish2021.util.ReaderUtil;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

@ApiStatus.Internal
public class SNBTReader {
    private final StringReader reader;

    public SNBTReader(String snbt) {
        this(new StringReader(snbt));
    }

    private SNBTReader(StringReader reader) {
        this.reader = reader;
    }

    public CompoundTag parserSNBT() {
        CompoundTag compoundtag = this.readStruct();
        this.reader.skipWhitespace();
        if (this.reader.canRead()) {
            throw new SNBTReaderException("Failed to parser SNBT.");
        } else {
            return compoundtag;
        }
    }

    private CompoundTag readStruct() {
        this.reader.expect('{');
        CompoundTag compoundtag = new CompoundTag();
        this.reader.skipWhitespace();

        while(this.reader.canRead() && this.reader.peek() != '}') {
            int i = this.reader.getReaderIndex();
            String s = this.reader.readKey();
            if (s.isEmpty()) {
                this.reader.setReaderIndex(i);
                throw new SNBTReaderException("Empty tag found.");
            }

            this.reader.expect(':');
            compoundtag.put(s, this.readValue());
            if (!this.reader.hasElementSeparator()) {
                break;
            }

            if (!this.reader.canRead()) {
                throw new SNBTReaderException("Unexpected end of tag.");
            }
        }

        this.reader.expect('}');
        return compoundtag;
    }

    private ITag readValue() {
        this.reader.skipWhitespace();
        if (!this.reader.canRead()) {
            throw new SNBTReaderException("Unexpected end of tag.");
        } else {
            char c0 = this.reader.peek();
            if (c0 == '{') {
                return this.readStruct();
            } else {
                return c0 == '[' ? this.readList() : this.readTypedValue();
            }
        }
    }

    protected ITag readTypedValue() {
        this.reader.skipWhitespace();
        int i = this.reader.getReaderIndex();
        if (StringReader.isQuotedStringStart(this.reader.peek())) {
            return new StringTag(this.reader.readQuotedString());
        } else {
            String s = this.reader.readUnquotedString();
            if (s.isEmpty()) {
                this.reader.setReaderIndex(i);
                throw new SNBTReaderException("Empty tag found.");
            } else {
                return ReaderUtil.snbtType(s);
            }
        }
    }

    private ITag readList() {
        return this.reader.canRead(3) && !StringReader.isQuotedStringStart(this.reader.peek(1)) && this.reader.peek(2) == ';' ? this.readArrayTag() : this.readListTag();
    }

    private ITag readArrayTag() {
        this.reader.expect('[');
        int i = this.reader.getReaderIndex();
        char c0 = this.reader.readChar();
        this.reader.readChar();
        this.reader.skipWhitespace();
        if (!this.reader.canRead()) {
            throw new SNBTReaderException("Unexpected end of tag.");
        } else if (c0 == 'B') {
            return new ByteArrayTag(CommonUtil.numberList2ByteArray(this.readArray(TagType.BYTE)));
        } else if (c0 == 'L') {
            return new LongArrayTag(CommonUtil.numberList2LongArray(this.readArray(TagType.LONG)));
        } else if (c0 == 'I') {
            return new IntArrayTag(CommonUtil.numberList2IntArray(this.readArray(TagType.INT)));
        } else {
            this.reader.setReaderIndex(i);
            throw new SNBTReaderException("String can no longer be read.");
        }
    }

    private List<Number> readArray(TagType p_129363_) {
        List<Number> list = new ArrayList<>();

        while(true) {
            if (this.reader.peek() != ']') {
                int i = this.reader.getReaderIndex();
                ITag tag = this.readValue();
                TagType tagtype = tag.type();
                if (tagtype != p_129363_) {
                    this.reader.setReaderIndex(i);
                    throw new SNBTReaderException("Unknown reason.");
                }

                if (p_129363_ == TagType.BYTE) {
                    list.add(((ByteTag)tag).value());
                } else if (p_129363_ == TagType.LONG) {
                    list.add(((LongTag)tag).value());
                } else {
                    list.add(((IntTag)tag).value());
                }

                if (this.reader.hasElementSeparator()) {
                    if (!this.reader.canRead()) {
                        throw new SNBTReaderException("Unexpected end of tag.");
                    }
                    continue;
                }
            }

            this.reader.expect(']');
            return list;
        }
    }

    private ITag readListTag() {
        this.reader.expect('[');
        this.reader.skipWhitespace();
        if (!this.reader.canRead()) {
            throw new SNBTReaderException("Unexpected end of tag.");
        } else {
            ListTag<ITag> listtag = new ListTag<>();
            TagType tagtype = null;

            while(this.reader.peek() != ']') {
                int i = this.reader.getReaderIndex();
                ITag tag = this.readValue();
                TagType tagtype1 = tag.type();
                if (tagtype == null) {
                    tagtype = tagtype1;
                } else if (tagtype1 != tagtype) {
                    this.reader.setReaderIndex(i);
                    throw new SNBTReaderException("Unknown reason.");
                }

                listtag.add(tag);
                if (!this.reader.hasElementSeparator()) {
                    break;
                }

                if (!this.reader.canRead()) {
                    throw new SNBTReaderException("Unexpected end of tag.");
                }
            }

            this.reader.expect(']');
            return listtag;
        }
    }

    private static class StringReader {
        private final String string;
        private int readerIndex = 0;

        public StringReader(String string) {
            this.string = string;
        }

        public char readChar() {
            return string.charAt(readerIndex++);
        }

        public String getString() {
            return string;
        }

        public void setReaderIndex(int readerIndex) {
            this.readerIndex = readerIndex;
        }

        public int getReaderIndex() {
            return readerIndex;
        }

        public boolean canRead(int length) {
            return this.readerIndex + length <= string.length();
        }

        public boolean canRead() {
            return this.canRead(1);
        }

        public char peek() {
            return this.string.charAt(this.readerIndex);
        }

        public char peek(int offset) {
            return this.string.charAt(this.readerIndex + offset);
        }

        public void skip() {
            ++this.readerIndex;
        }

        public void skipWhitespace() {
            while(this.canRead() && Character.isWhitespace(this.peek())) {
                this.skip();
            }
        }

        public void expect(char c) {
            if (this.canRead() && this.peek() == c) {
                this.skip();
            } else {
                throw new StringReaderException("String can no longer be read.");
            }
        }

        public boolean hasElementSeparator() {
            this.skipWhitespace();
            if (this.canRead() && this.peek() == ',') {
                this.skip();
                this.skipWhitespace();
                return true;
            } else {
                return false;
            }
        }

        public String readKey() {
            this.skipWhitespace();
            if (!this.canRead()) {
                throw new StringReaderException("String can no longer be read.");
            } else {
                return this.readString();
            }
        }

        public String readString() {
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

        public static boolean isQuotedStringStart(char c) {
            return c == '"' || c == '\'';
        }

        public static boolean isAllowedInUnquotedString(char c) {
            return c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '_' || c == '-' || c == '.' || c == '+';
        }

        public String readUnquotedString() {
            int start = this.readerIndex;

            while(this.canRead() && isAllowedInUnquotedString(this.peek())) {
                this.skip();
            }

            return this.string.substring(start, this.readerIndex);
        }

        public String readStringUntil(char terminator) {
            StringBuilder result = new StringBuilder();
            boolean escaped = false;

            while(this.canRead()) {
                char c = this.readChar();
                if (escaped) {
                    if (c != terminator && c != '\\') {
                        this.setReaderIndex(this.getReaderIndex() - 1);
                        throw new StringReaderException();
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

            throw new StringReaderException();
        }

        public String readQuotedString() {
            if (!this.canRead()) {
                return "";
            } else {
                char next = this.peek();
                if (!isQuotedStringStart(next)) {
                    throw new StringReaderException("String is not quoted string start.");
                } else {
                    this.skip();
                    return this.readStringUntil(next);
                }
            }
        }
    }
}