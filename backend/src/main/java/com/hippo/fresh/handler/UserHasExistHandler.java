package com.hippo.fresh.handler;

import com.hippo.fresh.exception.UserHasExistException;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserHasExistHandler {
    @ExceptionHandler(UserHasExistException.class)
    @ResponseBody
    public ResponseUtils handle(UserHasExistException userHasExistException){
        return ResponseUtils.response(409,"用户名已存在", userHasExistException.getData());
    }
}
