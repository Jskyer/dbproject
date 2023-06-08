package com.example.dbproject.service.ex;

public class CartException extends ServiceException {
    public CartException() {
        super();
    }

    public CartException(String message) {
        super(message);
    }

    public CartException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartException(Throwable cause) {
        super(cause);
    }

    protected CartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
