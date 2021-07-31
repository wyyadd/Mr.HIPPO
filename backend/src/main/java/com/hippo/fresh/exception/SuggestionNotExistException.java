package com.hippo.fresh.exception;

public class SuggestionNotExistException extends RuntimeException{
    private Object data;
    public SuggestionNotExistException(Object data){
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
