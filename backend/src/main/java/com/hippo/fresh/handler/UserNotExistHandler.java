package com.hippo.fresh.handler;

import com.hippo.fresh.exception.UserNotExistException;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserNotExistHandler {
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    public ResponseUtils handle(UserNotExistException userNotExistException){
        return ResponseUtils.response(404,"用户不存在", userNotExistException.getDate());
    }
}
