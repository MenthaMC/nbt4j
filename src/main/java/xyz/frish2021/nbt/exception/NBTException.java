package xyz.frish2021.nbt.exception;

public class NBTException extends RuntimeException {
    public NBTException(String message) {
        super(message);
    }

    public NBTException(Throwable cause) {
        super(cause);
    }
}
