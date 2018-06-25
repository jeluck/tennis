package com.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.auth.WsLoginAuth;
import com.project.common.Settings;
import com.project.util.CommonUtil;

/**
 * 移动端拦截器
 * @author strawxdl
 *
 */
public class WsLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {
    	request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    	HandlerMethod authhandler=(HandlerMethod) handler;
    	WsLoginAuth loginauth = authhandler.getMethodAnnotation(WsLoginAuth.class);
    	CommonUtil.printHTTP(request);
//S 代码未理解,暂时注释		edit by lxc	2015-12-19
//    	if(loginauth != null){
//    		if (isLogin(request)) {//已登录用户放行
//                return true;
//            }else{
//            	request.getRequestDispatcher("nologin.do").forward(request, response);//转发到此地址输出响应
//            	return false;
//            }
//    	}else{
    		return true;
//    	}
    	//E		    	
    	
    	
    	
//    	return true;
    }
    private boolean isLogin(HttpServletRequest request){
    	Object obj = request.getSession().getAttribute(Settings.USER_SESSION);
        if (null == obj) {
            return false;
        }else{
        	return true;
        }
    }
}
