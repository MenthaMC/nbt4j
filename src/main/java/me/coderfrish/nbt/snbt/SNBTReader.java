package me.coderfrish.nbt.snbt;

import me.coderfrish.nbt.*;
import me.coderfrish.nbt.exceptions.NBTException;
import me.coderfrish.nbt.primitive.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class SNBTReader {
    private final StringReader reader;

    public SNBTReader(StringReader reader) {
        this.reader = reader;
    }

    public SNBTReader(String snbt) {
        this(new StringReader(snbt));
    }

    public CompoundTag parserSNBT() {
        CompoundTag compoundtag = this.readStruct();
        this.reader.skipWhitespace();
        if (this.reader.canRead()) {
            throw new NBTException("Failed to parser SNBT.");
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
                throw new NBTException("Empty tag found.");
            }

            this.reader.expect(':');
            compoundtag.put(s, this.readValue());
            if (!this.reader.hasElementSeparator()) {
                break;
            }

            if (!this.reader.canRead()) {
                throw new NBTException("Unexpected end of tag.");
            }
        }

        this.reader.expect('}');
        return compoundtag;
    }

    private ITagBase readValue() {
        this.reader.skipWhitespace();
        if (!this.reader.canRead()) {
            throw new NBTException("Unexpected end of tag.");
        } else {
            char c0 = this.reader.peek();
            if (c0 == '{') {
                return this.readStruct();
            } else {
                return c0 == '[' ? this.readList() : this.readTypedValue();
            }
        }
    }

    protected ITagBase readTypedValue() {
        this.reader.skipWhitespace();
        int i = this.reader.getReaderIndex();
        if (StringReader.isQuotedStringStart(this.reader.peek())) {
            return new StringTag(this.reader.readQuotedString());
        } else {
            String s = this.reader.readUnquotedString();
            if (s.isEmpty()) {
                this.reader.setReaderIndex(i);
                throw new NBTException("Empty tag found.");
            } else {
                return NBTUtil.snbtType(s);
            }
        }
    }

    private ITagBase readList() {
        return this.reader.canRead(3) && !StringReader.isQuotedStringStart(this.reader.peek(1)) && this.reader.peek(2) == ';' ? this.readArrayTag() : this.readListTag();
    }

    private ITagBase readArrayTag() {
        this.reader.expect('[');
        int i = this.reader.getReaderIndex();
        char c0 = this.reader.readChar();
        this.reader.readChar();
        this.reader.skipWhitespace();
        if (!this.reader.canRead()) {
            throw new NBTException("Unexpected end of tag.");
        } else if (c0 == 'B') {
            return new ByteArrayTag(NBTUtil.byteList2Array(this.readArray(ITagBase.TagType.BYTE)));
        } else if (c0 == 'L') {
            return new LongArrayTag(NBTUtil.longList2Array(this.readArray(ITagBase.TagType.LONG)));
        } else if (c0 == 'I') {
            return new IntegerArrayTag(NBTUtil.intList2Array(this.readArray(ITagBase.TagType.INT)));
        } else {
            this.reader.setReaderIndex(i);
            throw new NBTException();
        }
    }

    private <T extends Number> List<T> readArray(ITagBase.TagType p_129363_) {
        List<T> list = new ArrayList<>();

        while(true) {
            if (this.reader.peek() != ']') {
                int i = this.reader.getReaderIndex();
                ITagBase tag = this.readValue();
                ITagBase.TagType tagtype = tag.type();
                if (tagtype != p_129363_) {
                    this.reader.setReaderIndex(i);
                    throw new NBTException();
                }

                if (p_129363_ == ITagBase.TagType.BYTE) {
                    list.add((T)((ByteTag)tag).value());
                } else if (p_129363_ == ITagBase.TagType.LONG) {
                    list.add((T)((LongTag)tag).value());
                } else {
                    list.add((T)((IntegerTag)tag).value());
                }

                if (this.reader.hasElementSeparator()) {
                    if (!this.reader.canRead()) {
                        throw new NBTException("Unexpected end of tag.");
                    }
                    continue;
                }
            }

            this.reader.expect(']');
            return list;
        }
    }

    private ITagBase readListTag() {
        this.reader.expect('[');
        this.reader.skipWhitespace();
        if (!this.reader.canRead()) {
            throw new NBTException("Unexpected end of tag.");
        } else {
            ListTag<ITagBase> listtag = new ListTag<>();
            ITagBase.TagType tagtype = null;

            while(this.reader.peek() != ']') {
                int i = this.reader.getReaderIndex();
                ITagBase tag = this.readValue();
                ITagBase.TagType tagtype1 = tag.type();
                if (tagtype == null) {
                    tagtype = tagtype1;
                } else if (tagtype1 != tagtype) {
                    this.reader.setReaderIndex(i);
                    throw new NBTException();
                }

                listtag.addItem(tag);
                if (!this.reader.hasElementSeparator()) {
                    break;
                }

                if (!this.reader.canRead()) {
                    throw new NBTException("Unexpected end of tag.");
                }
            }

            this.reader.expect(']');
            return listtag;
        }
    }
}
