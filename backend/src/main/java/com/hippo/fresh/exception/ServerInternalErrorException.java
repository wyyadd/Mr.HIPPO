package com.hippo.fresh.exception;

public class ServerInternalErrorException extends RuntimeException{
    private Object data;
    public ServerInternalErrorException(Object data){
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
