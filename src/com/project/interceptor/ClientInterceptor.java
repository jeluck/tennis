package com.project.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.common.Settings;

/**
 * PC网站前端拦截器
 * @author strawxdl
 * @since 2014-05-28
 */
public class ClientInterceptor  extends HandlerInterceptorAdapter {
	private String domain = Settings.SERVER_HOST;
	private String resource_domain = Settings.STATIC_SERVER_HOST;
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
		System.setProperty("SERVER_HOST", domain);
	}

	public String getResource_domain() {
		return resource_domain;
	}

	public void setResource_domain(String resource_domain) {
		this.resource_domain = resource_domain;
		System.setProperty("STATIC_SERVER_HOST", domain);
	}
}
