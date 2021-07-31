package com.hippo.fresh.handler;

import com.hippo.fresh.exception.ProductNotExistException;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ProductNotExistHandler {
    @ExceptionHandler(ProductNotExistException.class)
    @ResponseBody
    public ResponseUtils handle(ProductNotExistException productNotExistException){
        return ResponseUtils.response(404,"找不到相关商品", productNotExistException.getData());
    }
}
