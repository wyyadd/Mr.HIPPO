package com.hippo.fresh.controller;

import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuccessController {
    @PostMapping("/")
    public ResponseUtils success(){
      //  System.out.println("In");
        String name = "Success!";
        return ResponseUtils.success(name);
    }
}