package com.tqmars.cardrecycle.application.base;

import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.infrastructure.serialization.Json;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;

/**
 * Created by jjh on 1/15/17.
 */
public abstract class BaseAppService {
    protected String toSuccessMsg(){
        return toSuccessMsg("操作成功");
    }

    protected String toSuccessMsg(String msg){
        return Serialization.toJsonWithFormatter(null,msg, Code.SUCCESS);
    }

    protected String toSuccessMsg(String msg,int code){
        return Serialization.toJsonWithFormatter(null,msg,code);
    }

    protected String toJsonWithFormatter(Object data,String msg,int code){
        return Serialization.toJsonWithFormatter(data,msg,code);
    }

    protected String toJsonWithPageFormatter(Object data,String msg,int code,int count){
        return Json.getInstance().setDateFormat("yyyy-MM-dd HH:mm:ss").toJsonWithPageFormatter(data,msg,code,count);
    }

}
