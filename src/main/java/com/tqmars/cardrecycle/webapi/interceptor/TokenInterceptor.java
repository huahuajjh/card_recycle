package com.tqmars.cardrecycle.webapi.interceptor;

import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.webapi.Const;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jjh on 1/20/17.
 */
public class TokenInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        httpServletResponse.setHeader("Access-Control-Allow-Origin","http://192.168.1.4:8080");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        httpServletResponse.setContentType("application/json; charset=utf-8");


        boolean admin = httpServletRequest.getRequestURI().contains("/admin/");

        if(admin){
            String adminToken = httpServletRequest.getParameter(Const.TOKEN);
            Object adminSessionToken = httpServletRequest.getSession().getAttribute(Const.ADMIN_TOKEN);
            if(null == adminToken || null == adminSessionToken || !adminToken.equals(adminSessionToken.toString())){
                httpServletResponse.getWriter().write(Serialization.toJsonWithFormatter(null,"authentication failed | token失效,请重新获取", Code.BACKGROUND_AUTH_FAIL));
                return false;
            }
            return true;
        }

        String token = httpServletRequest.getParameter(Const.TOKEN);
        Object sessionToken = httpServletRequest.getSession().getAttribute(Const.TOKEN);
        if (null == token || null == sessionToken || !token.equals(sessionToken.toString())){
            httpServletResponse.getWriter().write(Serialization.toJsonWithFormatter(null,"authentication failed | token失效,请重新获取", Code.AUTH_FAIL));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
