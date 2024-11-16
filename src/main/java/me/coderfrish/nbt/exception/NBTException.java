package me.coderfrish.nbt.exception;

@SuppressWarnings("all")
public class NBTException extends RuntimeException {
    public NBTException(String message) {
        super(message);
    }

    public NBTException(Throwable cause) {
      super(new NBTException(cause.getMessage()));
    }
}
