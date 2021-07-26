package com.hippo.fresh.handler;

import com.hippo.fresh.exception.ForumNotExistException;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ForumNotExistHandler {
    @ExceptionHandler(ForumNotExistException.class)
    @ResponseBody
    public ResponseUtils handle(ForumNotExistException forumNotExistException){
        return ResponseUtils.response(404,"找不到相关帖子", forumNotExistException.getData());
    }
}
