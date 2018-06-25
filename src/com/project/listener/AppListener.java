package com.project.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * Application Lifecycle Listener implementation class AppListener
 * 当服务器启动时调用
 */
public class AppListener implements ServletContextListener {
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent context) {
    	System.setProperty("root", context.getServletContext().getRealPath("/"));
    	System.setProperty("log_dir", context.getServletContext().getRealPath(""));
    	
    	//TranxConfig.init(); 	//初始化值
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg) {
    	
    }
}
