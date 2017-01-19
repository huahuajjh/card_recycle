package com.tqmars.cardrecycle.webapi.controller;

import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by jjh on 1/14/17.
 */
public abstract class ControllerBase {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected <TAppService> TAppService getService(String serviceId, Class<TAppService> serviceClass) {
        return ServiceLocator.getInstance().getService(serviceId, serviceClass);
    }

    public ControllerBase(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    protected HttpSession getSession() {
        return this.request.getSession();
    }

    protected String toSucessMsg() {
        return toSucessMsg("操作成功");
    }

    protected String toSucessMsg(String msg) {
        return Serialization.toJsonWithFormatter(null, msg, Code.SUCCESS);
    }

    protected String toSuccessMsg(String msg, int code) {
        return Serialization.toJsonWithFormatter(null, msg, code);
    }

    protected String toJsonWithFormatter(Object data, String msg, int code) {
        return Serialization.toJsonWithFormatter(data, msg, code);
    }

    protected String toJsonWithPageFormatter(Object data, String msg, int code, int count) {
        return Serialization.toJsonWithPageFormatter(data, msg, code, count);
    }

    protected String toFailMsg(String msg, int code) {
        return toSuccessMsg(msg, code);
    }

    protected String toFailMsg(String msg) {
        return toFailMsg(msg, Code.FAIL);
    }

}
