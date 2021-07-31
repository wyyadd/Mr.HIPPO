package com.hippo.fresh.handler;

import com.hippo.fresh.exception.CommentNotExistException;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommentNotExistHandler{
    @ExceptionHandler(CommentNotExistException.class)
    @ResponseBody
    public ResponseUtils handle(CommentNotExistException commentNotExistException){
        return ResponseUtils.response(404,"找不到相关评论", commentNotExistException.getData());
    }
}
