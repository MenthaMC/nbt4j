package xyz.frish2021.nbt.snbt;

import xyz.frish2021.nbt.array.ByteArrayTag;
import xyz.frish2021.nbt.array.IntArrayTag;
import xyz.frish2021.nbt.array.LongArrayTag;
import xyz.frish2021.nbt.compound.CompoundTag;
import xyz.frish2021.nbt.core.StringReader;
import xyz.frish2021.nbt.exception.SNBTReaderException;
import xyz.frish2021.nbt.list.ListTag;
import xyz.frish2021.nbt.primitive.StringTag;
import xyz.frish2021.nbt.primitive.number.ByteTag;
import xyz.frish2021.nbt.primitive.number.IntTag;
import xyz.frish2021.nbt.primitive.number.LongTag;
import xyz.frish2021.nbt.api.ITag;
import xyz.frish2021.nbt.tag.TagType;
import xyz.frish2021.nbt.util.CommonUtil;
import xyz.frish2021.nbt.util.ReaderUtil;

import java.util.ArrayList;
import java.util.List;

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
}