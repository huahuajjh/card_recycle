package com.tqmars.cardrecycle.webapi.controller.admin.content;

import com.tqmars.cardrecycle.application.content.FileUtil;
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
@RequestMapping(value = "/admin/content",method = {RequestMethod.POST})
public class AdminContentController extends ControllerBase{
    private String path = "./content";

    public AdminContentController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @RequestMapping(value = "/modify")
    public String modify(@RequestParam(value = "type") String type,
                         @RequestParam(value = "data") String data){

        if(null == type || type.equals("")){
            return toJsonWithFormatter(null,"参数错误", Code.FAIL);
        }

        FileUtil.getInstance().writeFileContent(path,type+".txt",data);
        return toJsonWithFormatter(null,"success", Code.SUCCESS);
    }
}
