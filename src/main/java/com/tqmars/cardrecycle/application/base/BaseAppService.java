package com.tqmars.cardrecycle.application.base;

import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;

/**
 * Created by jjh on 1/15/17.
 */
public abstract class BaseAppService {
    protected String toSucessMsg(){
        return toSucessMsg("操作成功");
    }

    protected String toSucessMsg(String msg){
        return Serialization.toJsonWithFormatter(null,msg, Code.SUCCESS);
    }

    protected String toSuccessMsg(String msg,int code){
        return Serialization.toJsonWithFormatter(null,msg,code);
    }

    protected String toJsonWithFormatter(Object data,String msg,int code){
        return Serialization.toJsonWithFormatter(data,msg,code);
    }

    protected String toJsonWithPageFormatter(Object data,String msg,int code,int count){
        return Serialization.toJsonWithPageFormatter(data,msg,code,count);
    }

}
