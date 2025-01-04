package me.coderfrish.nbt.common;

import me.coderfrish.nbt.type.*;

import static me.coderfrish.nbt.common.Utility.*;

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
        } throw new RuntimeException("The string is no longer readable.");
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
            throw new RuntimeException("String can no longer be read.");
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
            throw new RuntimeException("String can no longer be read.");
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
                    throw new RuntimeException();
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

        throw new RuntimeException();
    }

    private String readQuotedString() {
        if (!this.canRead()) {
            return "";
        } else {
            char next = this.peek();
            if (!isQuotedStringStart(next)) {
                throw new RuntimeException("String is not quoted string start.");
            } else {
                this.skip();
                return this.readStringUntil(next);
            }
        }
    }

    private Object readValue() {
        skipWhitespace();
        if (!canRead()) {
            throw new RuntimeException("Unexpected end of tag.");
        } else {
            char c0 = peek();
            if (c0 == '{') {
                return this.readStruct();
            } else {
                return c0 == '[' ? readList() : readTypedValue();
            }
        }
    }

    private Object readTypedValue() {
        skipWhitespace();
        int i = this.readerIndex;
        if (isQuotedStringStart(peek())) {
            return readQuotedString();
        } else {
            String s = this.readUnquotedString();
            if (s.isEmpty()) {
                this.readerIndex = i;
                throw new RuntimeException("Empty tag found.");
            } else {
                return snbtType(s);
            }
        }
    }

    private Object readList() {
        return this.canRead(3) && !isQuotedStringStart(peek(1)) && peek(2) == ';' ? this.readArrayTag() : this.readListTag();
    }

    private Object readArrayTag() {
        this.expect('[');
        int i = this.readerIndex;
        char c0 = this.readChar();
        this.readChar();
        this.skipWhitespace();
        if (!this.canRead()) {
            throw new RuntimeException("Unexpected end of tag.");
        } else if (c0 == 'B') {
            return new TagByteArray(convertListToByteArray(this.readArray()));
        } else if (c0 == 'L') {
            return new TagLongArray(convertListToLongArray(this.readArray()));
        } else if (c0 == 'I') {
            return new TagIntArray(convertListToIntArray(this.readArray()));
        } else {
            this.readerIndex = i;
            throw new RuntimeException("String can no longer be read.");
        }
    }

    private List<Object> readArray() {
        List<Object> list = new ArrayList<>();

        while(true) {
            if (peek() != ']') {
                Object tag = this.readValue();

                list.add(tag);

                if (hasElementSeparator()) {
                    if (!canRead()) {
                        throw new RuntimeException("Unexpected end of tag.");
                    }
                    continue;
                }
            }

            expect(']');
            return list;
        }
    }

    private Object readListTag() {
        expect('[');
        skipWhitespace();
        if (!canRead()) {
            throw new RuntimeException("Unexpected end of tag.");
        } else {
            TagList listtag = new TagList();
            TagType tagtype = null;

            while(peek() != ']') {
                int i = this.readerIndex;
                Object tag = this.readValue();
                TagType tagtype1 = getType(tag);
                if (tagtype == null) {
                    tagtype = tagtype1;
                } else if (tagtype1 != tagtype) {
                    this.readerIndex = i;
                    throw new RuntimeException("Unknown reason.");
                }

                listtag.add(tag);
                if (!hasElementSeparator()) {
                    break;
                }

                if (!canRead()) {
                    throw new RuntimeException("Unexpected end of tag.");
                }
            }

            expect(']');
            return listtag;
        }
    }

    private TagObject readStruct() {
        TagObject entries = new TagObject();

        expect('{');
        skipWhitespace();
        while (canRead() && peek() != '}'){
            int mark = this.readerIndex;
            String key = readKey();
            if (key.isEmpty()) {
                this.readerIndex = mark;
                throw new RuntimeException("Empty tag found.");
            }

            expect(':');
            entries.set(key, readValue());
            if (!hasElementSeparator()) {
                break;
            }
            if (!canRead()) {
                throw new RuntimeException("Unexpected end of tag.");
            }
        }

        expect('}');
        return entries;
    }

    public static byte[] convertListToByteArray(List<Object> list) {
        byte[] byteArray = new byte[list.size()];
        int index = 0;

        for (Object obj : list) {
            if (obj instanceof Byte) {
                byteArray[index++] = (Byte) obj;
            } else {
                throw new IllegalArgumentException("List contains non-byte elements");
            }
        }

        return byteArray;
    }

    public static int[] convertListToIntArray(List<Object> list) {
        int[] intArray = new int[list.size()];
        int index = 0;

        for (Object obj : list) {
            if (obj instanceof Integer) {
                intArray[index++] = (Integer) obj;
            } else if (obj instanceof Byte) {
                intArray[index++] = (Byte) obj;
            } else if (obj instanceof Long) {
                intArray[index++] = ((Long) obj).intValue();
            } else {
                throw new IllegalArgumentException("List contains non-integer elements");
            }
        }

        return intArray;
    }

    public static long[] convertListToLongArray(List<Object> list) {
        long[] longArray = new long[list.size()];
        int index = 0;

        for (Object obj : list) {
            if (obj instanceof Long) {
                longArray[index++] = (Long) obj;
            } else if (obj instanceof Integer) {
                longArray[index++] = (Integer) obj;
            } else if (obj instanceof Byte) {
                longArray[index++] = (Byte) obj;
            } else {
                throw new IllegalArgumentException("List contains non-long elements");
            }
        }

        return longArray;
    }

    public TagObject parserSNBT() {
        TagObject entries = this.readStruct();
        skipWhitespace();

        if (canRead()) {
            throw new RuntimeException("Failed to parser SNBT.");
        } return entries;
    }
}
