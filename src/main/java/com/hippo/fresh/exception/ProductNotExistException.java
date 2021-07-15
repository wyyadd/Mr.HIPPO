package com.hippo.fresh.exception;

public class ProductNotExistException extends RuntimeException{
    private Object data;
    public ProductNotExistException(Object data){
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
