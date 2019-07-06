package com.exception;

public class LockException extends RuntimeException {

    public LockException(String e) {
        super(e);
    }

    public LockException(String e, Throwable cause) {
        super(e, cause);
    }

}
