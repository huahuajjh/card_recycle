package com.tqmars.cardrecycle.webapi.controller.content;

import com.tqmars.cardrecycle.application.content.FileUtil;
import com.tqmars.cardrecycle.infrastructure.StringTools.PropertiesFileTool;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jjh on 2/8/17.
 */
@RestController
@RequestMapping(value = "/content",method = {RequestMethod.GET,RequestMethod.POST})
public class ContentController extends ControllerBase {
    private String path = PropertiesFileTool.readByKey("contentPath");

    public ContentController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "type") String type){
        if(null == type || type.equals("")){
            return toJsonWithFormatter(null,"参数错误",Code.FAIL);
        }
        return toJsonWithFormatter(FileUtil.getInstance().readFileContent(path,type+".txt"),"success", Code.SUCCESS);
    }

}

