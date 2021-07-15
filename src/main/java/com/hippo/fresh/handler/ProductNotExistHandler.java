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
    public void handle(ProductNotExistException productNotExistException){
        ResponseUtils.response(404,"商品查找失败", productNotExistException.getData());
    }
}
