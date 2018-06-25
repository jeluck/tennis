package com.project.util;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 从spring容器中获取Bean
 *
 * @date 2011-6-3
 *
 */
@Component
public class ServiceLocator implements ApplicationContextAware  {
//	private static ServiceLocator serviceLocator = null;
//	private static BeanFactory beanFactory = null;

	private static ApplicationContext ctx;	
	
//	@SuppressWarnings("static-access")
//	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//		this.beanFactory = beanFactory;
//	}
//
//	public BeanFactory getBeanFactory() {
//		return beanFactory;
//		
//		
//	}
//
//	public static ServiceLocator getInstance() {
//		if (serviceLocator == null){
//			serviceLocator = (ServiceLocator) beanFactory.getBean("serviceLocator");
//			
//		}
//		return serviceLocator;
//
//	}

	/**
     * 通过spring配置文件中配置的bean id取得bean对象
     * @param id spring bean ID值
     * @return spring bean对象
     */
    public static Object getBean(String id) {
        if (ctx == null) {
            throw new NullPointerException("ApplicationContext is null");
        }
        return ctx.getBean(id);
    }

  @Override
  public void setApplicationContext(ApplicationContext applicationcontext)
      throws BeansException {
    ctx = applicationcontext;
  }
}