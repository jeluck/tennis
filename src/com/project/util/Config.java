package com.project.util;

import java.io.InputStream;
import java.util.Properties;

/**
 *<P>
 *  读取与SRC下的配置文件,取数据
 *<P>
 * 
 */

public class Config {

	private static Config instance = null;
	
	private Properties properties = null;
	
	private Config() {
		init();
	}
	
	public static Config getInstance() {
		
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}
	
	/**
	 * 初始化配置文件
	 */
	public void init(){
		
		try{
			InputStream is = Config.class.getResourceAsStream("/application.properties");
			properties = new Properties();
			properties.load(is);
			
		}catch (Exception e){
			throw new RuntimeException("Failed to get properties!");
		}
	}
	
	/**
	 * 根据key值取得对应的value值
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}
}
