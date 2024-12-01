package me.coderfrish.nbt.exceptions;

public class NBTException extends RuntimeException {
  public NBTException(String message) {
    super(message);
  }

  public NBTException(Throwable message) {
    super(message);
  }

  public NBTException() {
    super();
  }
}
