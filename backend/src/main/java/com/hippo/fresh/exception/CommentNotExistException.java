package com.hippo.fresh.exception;

public class CommentNotExistException extends RuntimeException {
    private Object data;
    public CommentNotExistException(Object data){
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
