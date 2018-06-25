package com.project.dao;

import com.project.orm.IHibernateEntityDao;
import com.project.pojo.SystemConfig;

/**
 * 
 * 类 系统配置
 * @author  
 */
public interface ISystemConfigDao extends IHibernateEntityDao<SystemConfig> {

	public SystemConfig findSystemConfigByKey(String key);
	
}
