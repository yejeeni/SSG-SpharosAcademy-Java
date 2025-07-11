package com.ssg.hiberasync.exception;

public class FoodTypeException extends RuntimeException {
    public FoodTypeException() {
        super();
    }

    public FoodTypeException(String message) {
        super(message);
    }

    public FoodTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FoodTypeException(Throwable cause) {
        super(cause);
    }
}