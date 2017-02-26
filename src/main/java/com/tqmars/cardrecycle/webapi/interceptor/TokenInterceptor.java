package com.tqmars.cardrecycle.webapi.interceptor;

import com.tqmars.cardrecycle.application.User.IUserAppService;
import com.tqmars.cardrecycle.application.admin.user.IAdminUserAppService;
import com.tqmars.cardrecycle.infrastructure.StringTools.PropertiesFileTool;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
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
        httpServletResponse.setHeader("Access-Control-Allow-Origin",PropertiesFileTool.readByKey("crossAllow"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        httpServletResponse.setContentType("application/json; charset=utf-8");
//        ie跨域声明
//        httpServletResponse.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");


        boolean admin = httpServletRequest.getRequestURI().contains("/admin/");
        String callback = httpServletRequest.getParameter("callback");
        if(admin){
            String adminToken = httpServletRequest.getParameter(Const.TOKEN);
//            Object adminSessionToken = httpServletRequest.getSession().getAttribute(Const.ADMIN_TOKEN);

//            if(null == adminToken || null == adminSessionToken || !adminToken.equals(adminSessionToken.toString())){
//
//                httpServletResponse.getWriter().write(callback+"("+Serialization.toJsonWithFormatter(null,"authentication failed | token失效,请重新获取", Code.BACKGROUND_AUTH_FAIL)+")");
//                return false;
//            }
//            return true;
            IAdminUserAppService a = ServiceLocator.getInstance().getService("AdminUserAppService",IAdminUserAppService.class);
            if(a.auth(adminToken)){
                return true;
            }
            httpServletResponse.getWriter().write(callback+"("+Serialization.toJsonWithFormatter(null,"authentication failed | token失效,请重新获取", Code.BACKGROUND_AUTH_FAIL)+")");
            return false;

        }

        String token = httpServletRequest.getParameter(Const.TOKEN);
//        Object sessionToken = httpServletRequest.getSession().getAttribute(Const.TOKEN);
//        if (null == token || null == sessionToken || !token.equals(sessionToken.toString())){
//            httpServletResponse.getWriter().write(callback+"("+Serialization.toJsonWithFormatter(null,"authentication failed | token失效,请重新获取", Code.AUTH_FAIL)+")");
//            return false;
//        }
//        return true;
        IUserAppService u = ServiceLocator.getInstance().getService("UserAppService",IUserAppService.class);
        if(u.auth(token)){
            return true;
        }
        httpServletResponse.getWriter().write(callback+"("+Serialization.toJsonWithFormatter(null,"authentication failed | token失效,请重新获取", Code.AUTH_FAIL)+")");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
