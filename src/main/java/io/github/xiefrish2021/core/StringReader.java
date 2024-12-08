package io.github.xiefrish2021.core;

import io.github.xiefrish2021.exception.StringReaderException;

public class StringReader {
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
