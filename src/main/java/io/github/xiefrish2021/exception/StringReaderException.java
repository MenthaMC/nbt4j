package io.github.xiefrish2021.exception;

public class StringReaderException extends NBTReaderException {
    public StringReaderException(String message) {
        super(message);
    }

    public StringReaderException() {
        super("StringReader throw exception!!");
    }
}
