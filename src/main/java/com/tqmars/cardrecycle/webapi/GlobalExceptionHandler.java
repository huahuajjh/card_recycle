package com.tqmars.cardrecycle.webapi;

import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jjh on 2/4/17.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public void defaultExceptionHandler(HttpServletRequest req, Exception e){
        LoggerFactory.getLogger().error(e.getMessage());
    }
}
