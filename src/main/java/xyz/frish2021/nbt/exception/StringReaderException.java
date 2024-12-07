package xyz.frish2021.nbt.exception;

public class StringReaderException extends NBTReaderException {
    public StringReaderException(String message) {
        super(message);
    }

    public StringReaderException() {
        super("StringReader throw exception!!");
    }
}
