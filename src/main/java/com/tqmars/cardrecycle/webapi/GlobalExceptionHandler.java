package com.tqmars.cardrecycle.webapi;

import com.tqmars.cardrecycle.application.content.FileUtil;
import com.tqmars.cardrecycle.infrastructure.StringTools.PropertiesFileTool;
import com.tqmars.cardrecycle.infrastructure.email.TqEmail;
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
        logger.error("global error message:"+e.getMessage());
        logger.error(e.getMessage(),e);

        //email notify
        String path = PropertiesFileTool.readByKey("emailTemplatePath");
        String t = FileUtil.getInstance().readFileContent(path,"email-template.html");
        String tmp = t.replace("{remote_address}",req.getRemoteAddr() == null ? "" : req.getRemoteAddr())
        .replace("{path_info}",req.getPathInfo() == null ? "" : req.getPathInfo())
        .replace("{request_uri}",req.getRequestURI() == null ? "" : req.getRemoteAddr())
        .replace("{remote_host}",req.getRemoteHost() == null ? "" : req.getRemoteAddr())
        .replace("{msg}",e.getMessage());

        String tmp1 = "";
        //stack info
        for (StackTraceElement ste : e.getStackTrace()) {
            if(ste.getClassName().contains("com.tqmars")){
                tmp1+=ste;
            }
        }

        TqEmail.sendWithHtml("card recycle project error report",tmp.replace("{details}",tmp1));

    }

}
