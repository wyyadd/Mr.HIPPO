package com.hippo.fresh.handler;

import com.hippo.fresh.exception.ServerInternalErrorException;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ServerInternalErrorHandler {
    @ExceptionHandler(ServerInternalErrorException.class)
    @ResponseBody
    public ResponseUtils handle(ServerInternalErrorException serverInternalErrorException){
        return ResponseUtils.response(500,"服务器内部出错", serverInternalErrorException.getData());
    }
}
