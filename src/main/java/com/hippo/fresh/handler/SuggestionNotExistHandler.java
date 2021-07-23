package com.hippo.fresh.handler;

import com.hippo.fresh.exception.SuggestionNotExistException;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SuggestionNotExistHandler {
    @ExceptionHandler(SuggestionNotExistException.class)
    @ResponseBody
    public ResponseUtils handle(SuggestionNotExistException suggestionNotExistException){
        return ResponseUtils.response(404,"找不到相关建议", suggestionNotExistException.getData());
    }

}
