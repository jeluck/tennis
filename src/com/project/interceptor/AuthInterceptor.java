package com.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.auth.Authority;

/**
 * 后台权限拦截器
 * @author strawxdl
 * 
 */
public class AuthInterceptor extends HandlerInterceptorAdapter{  
	
    @Override
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {
    	HandlerMethod authhandler=(HandlerMethod) handler;
    	Authority auth = authhandler.getMethodAnnotation(Authority.class);
    	if(auth == null){
    		//没有声明权限，直接放行
    		return true;
    	}
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        return true;
    }
}
