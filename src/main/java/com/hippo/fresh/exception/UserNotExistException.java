package com.hippo.fresh.exception;

import com.alibaba.fastjson.JSONObject;

public class UserNotExistException extends RuntimeException{
    private Object date;

    public UserNotExistException(Object date) {
        this.date = date;
    }

    public Object getDate() {
        return date;
    }
}