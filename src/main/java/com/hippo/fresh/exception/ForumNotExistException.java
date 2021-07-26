package com.hippo.fresh.exception;

public class ForumNotExistException extends RuntimeException{
    private Object data;
    public ForumNotExistException(Object data){
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
