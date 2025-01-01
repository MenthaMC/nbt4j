package me.coderfrish.core;

public class NBTException extends RuntimeException {
    public NBTException(String message) {
        super(message);
    }

    public NBTException(Throwable cause) {
        super(cause);
    }

    public NBTException() {
        super("Unknown exception.");
    }
}
