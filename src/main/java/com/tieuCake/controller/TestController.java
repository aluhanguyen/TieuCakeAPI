package com.tieuCake.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by nguye on 04-Sep-17.
 */
@Controller

class TestController{

//    @RequestMapping(value = "/error")
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ModelAndView error(ModelAndView modelAndView) {
//        modelAndView.setViewName("hello");
//        return modelAndView;
//    }  implements ErrorController
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}
