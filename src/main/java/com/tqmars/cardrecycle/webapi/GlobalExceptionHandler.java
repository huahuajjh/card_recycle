package com.tqmars.cardrecycle.webapi;

import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import org.apache.log4j.Logger;
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
    public void defaultExceptionHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        Logger logger = LoggerFactory.getLogger();
        logger.error("error message:"+e.getMessage());
        logger.error(e.getMessage(),e);

    }
}
