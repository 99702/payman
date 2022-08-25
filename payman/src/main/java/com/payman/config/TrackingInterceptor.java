package com.payman.config;

import com.payman.errors.PaymanException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TrackingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // if our header all are null we throw error
        try{
            if(
                    request.getHeader("ip") == null
                            || request.getHeader("latitude")  == null
                            || request.getHeader("longitude") == null
                            || request.getHeader("screen") == null
                            || request.getHeader("clientDateTime") == null
                            || request.getHeader("language") == null
            ){
                throw new PaymanException("UNAUTHORIZED USER");
            }
        } catch (Exception e){
            throw  new PaymanException(e.getMessage());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}