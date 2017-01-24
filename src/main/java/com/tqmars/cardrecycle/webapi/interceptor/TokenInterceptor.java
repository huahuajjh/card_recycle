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
        String token = httpServletRequest.getParameter(Const.TOKEN);
        Object sessionToken = httpServletRequest.getSession().getAttribute(Const.TOKEN);
        if(null == token || null == sessionToken || !token.equals(sessionToken.toString())){
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.getWriter().write(Serialization.toJsonWithFormatter(null,"authentication failed", Code.AUTH_FAIL));
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
