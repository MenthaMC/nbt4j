package io.github.xiefrish2021.exception;

public class NBTException extends RuntimeException {
    public NBTException(String message) {
        super(message);
    }

    public NBTException(Throwable cause) {
        super(cause);
    }
}
