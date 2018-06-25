package com.project.util;

import com.project.pojo.SystemConfig;
import com.project.service.ISystemConfigService;

import org.apache.commons.lang.StringUtils;


/**
 */
public class ConfigUtil {

	public static String getConfigValueByKey(String key){
		if(StringUtils.isEmpty(key)){
			return "";
		}
		String value = "";
		//ServiceLocator serviceLocator = ServiceLocator.getInstance();
		//ISystemConfigService configService = serviceLocator.getBeanFactory().getBean(SystemConfigServiceImpl.class);
		ISystemConfigService configService =  (ISystemConfigService) BeanUtil.getBean("systemConfigServiceImpl");
		try {
			SystemConfig config = configService.querySystemConfigByKey(key);
			
			if(config!=null){
				return config.getValue();
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 为空情况，返回默认值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getConfigValueByKey(String key,String defaultValue){
		if(StringUtils.isEmpty(key)){
			return defaultValue;
		}
		String value = "";
		//ServiceLocator serviceLocator = ServiceLocator.getInstance();
		//ISystemConfigService configService = serviceLocator.getBeanFactory().getBean(SystemConfigServiceImpl.class);
		ISystemConfigService configService =  (ISystemConfigService) BeanUtil.getBean("systemConfigServiceImpl");
		try {
			SystemConfig config = configService.querySystemConfigByKey(key);
			if(config!=null){
				value = config.getValue();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StringUtils.isEmpty(value)?defaultValue:value;
	}
}
