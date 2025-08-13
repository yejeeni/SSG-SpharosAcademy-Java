package com.ssg.xmlapp.exception;

public class RestraurantException extends RuntimeException{
    public RestraurantException(String message){
        super(message);
    }
    public RestraurantException(String message, Throwable cause){
        super(message, cause);
    }
    public RestraurantException(Throwable cause){
        super(cause);
    }
}
