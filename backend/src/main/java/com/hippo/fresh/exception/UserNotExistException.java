package com.hippo.fresh.exception;

public class UserNotExistException extends RuntimeException{
    private Object date;

    public UserNotExistException(Object date) {
        this.date = date;
    }

    public Object getDate() {
        return date;
    }
}