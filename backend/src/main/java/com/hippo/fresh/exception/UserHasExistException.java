package com.hippo.fresh.exception;

public class UserHasExistException extends RuntimeException {
    private Object data;
    public UserHasExistException(Object data){
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
