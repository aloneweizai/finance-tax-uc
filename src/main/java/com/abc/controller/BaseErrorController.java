package com.abc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by stuy on 2017/8/11.
 */
@Controller
@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(BaseErrorController.class);

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping
    public String error(HttpServletResponse response, HttpServletRequest request) {
        String uri=request.getRequestURI();
        logger.info("ErrorController错误地址:"+uri);
        return getErrorPath();
    }
}
