package com.project.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class BeanUtil {
	private static ApplicationContext app = null;
	/**
	 * 得到Spring管理Bean的 实例
	 * @param resouceName
	 * @return
	 */
	public static Object getBean(String resouceName){
		if(app==null){
			String path="/web/WEB-INF/springMVC.xml"; 
			app = new FileSystemXmlApplicationContext(path);
			//app = new ClassPathXmlApplicationContext( "/springMVC.xml");
			
		}
		return app.getBean(resouceName);
	}
}
