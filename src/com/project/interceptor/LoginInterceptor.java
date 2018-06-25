package com.project.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.auth.NoLoginAuth;
import com.project.common.Settings;
import com.project.util.CommonUtil;

/**
 * 后台登录拦截器
 * 
 * @author strawxdl
 * 
 */
@Repository
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static String[] nologin_filter_uri = new String[]{"login.do"};
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String uri = request.getRequestURI();
		HttpSession session = request.getSession();

		// 打印请求情况
		CommonUtil.printHTTP(request);
		if (uri.indexOf("admin/") != -1) {// 目前只对后台请求进行登录验证
			HandlerMethod authhandler = (HandlerMethod) handler;
			NoLoginAuth nologin = authhandler.getMethodAnnotation(NoLoginAuth.class);
			if (nologin != null) {
				// 只要不声明NoLoginAuth的都需要做登录校验，声明了的可以直接放行
				return filterURL(request,response);
			}
			if (isLogin(request)) {// 已登录用户放行
				//------- 验证用户菜单资源权限 --------
				String all_menu_urls = System.getProperty("all_menu_urls");
				String real_uri = uri.substring(uri.lastIndexOf("/")+1,uri.length());
				String user_menu_urls = (String)session.getAttribute("menu_urls");
				if(all_menu_urls.contains(real_uri)){
					if(!user_menu_urls.contains(real_uri)){
						session.setAttribute("msg", "用户非法访问资源！！！");
						session.removeAttribute(Settings.MANAGER_SESSION);
						response.sendRedirect(request.getContextPath()+ "/admin/login.do");// 未登录用户跳转到登陆页
						return false;
					}
				}
				//--------- end ---------
				return true;
			} else {
				response.sendRedirect(request.getContextPath()+ "/admin/login.do");// 未登录用户跳转到登陆页
				return false;
			}
		}
		return true;
	}

	private boolean isLogin(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(Settings.MANAGER_SESSION);
		if (null == obj) {
			return false;
		} else {
			return true;
		}
	}
	//过滤请求
	public boolean filterURL(HttpServletRequest request,HttpServletResponse response) throws IOException{
		boolean pass = true;
		if(isLogin(request)){
		for (String uri : nologin_filter_uri) {
			if(request.getRequestURI().endsWith(uri)){
				response.sendRedirect(request.getContextPath()+"/admin/index.do");//未登录用户跳转到登陆页
				pass = false;
				break;
			}
		}}
		return pass;
	}
}