package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.orm.basedao.Query;
import com.project.pojo.SystemConfig;

import java.util.List;


/**
 * 
 *@author yhb
 * 
 * @version 创建时间：2011-3-30 下午02:44:41
 */
public interface ISystemConfigService {
	
	public PageFinder<SystemConfig> getSystemConfigList(int pageNumber, int pageSize, SystemConfig sc);
	
	/**
	 * 增加配置，已存的在键值 装将配置替换
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public SystemConfig addSystemConfig(SystemConfig config) throws Exception;
	
	/**
	 * 根据ID查询系统配置
	 * @param id
	 * @return
	 */
	public SystemConfig getSystemConfigById(int id);
	
	/**
	 * 获取所有系统正使用配置
	 * @return
	 */
	public List<SystemConfig> getAllUseSystemConfig(String configname, String configkey);
	
	
	/**
	 * 获取所有系统配置
	 * @return
	 */
	public List<SystemConfig> getAllSystemConfig(String configname, String configkey);
	
	/**
	 *根据键值获取配置,如果没有则增加
	 * @return
	 * @throws Exception 
	 */
	public SystemConfig getSystemConfigByKey(String key) throws Exception;
	
	/**
	 *根据键值获取配置(通过CriteriaAdapter)
	 * @return
	 * @throws Exception 
	 */
	public SystemConfig querySystemConfigByKey(String key) throws Exception;
	
	/**
	 * 根据key查询对象
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public SystemConfig getSystemConfigBykey(String key) throws Exception;
	
	/**
	 * 根据键值获取配置,为空情况，返回空
	 * @param key
	 * @return
	 */
	public String getConfigValueByKey(String key);
	
	/**
	 * 根据键值获取配置,为空情况，返回默认值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getConfigValueByKey(String key, String defaultValue);
	
	
	
	/**
	 *读缓存根据键值获取配置
	 * @return
	 * @throws Exception 
	 */
	public SystemConfig getCacheSystemConfigByKey(String key);
	/**
	 * 修改系统配置
	 * @param Menu
	 * @return
	 * @throws Exception 
	 */
	public SystemConfig updateSystemConfig(SystemConfig SystemConfig) throws Exception;

	/**
	 * 删除节点
	 * @param funsetId
	 * @return
	 */
	public int removeSystemConfig(int resourceid)  throws Exception;
	
	/**
	 * 启用配置
	 * @return
	 */
	public String useConfig(int id);
	
	/**
	 * 关闭
	 * @param configId
	 * @return
	 * @throws Exception
	 */
	public int closeSystemConfig(int configId);
	
	/**
	 * 查询没有删除的
	 * @return
	 */
	public List<SystemConfig> getAllUseSystemConfigNoCache();
	
	/**
	 * 分页查询(不包括有删除标记的)
	 * @MethodName queryPageSystemConfig
	 * @Description
	 * @author chengui
	 * @date 2012-8-16 下午5:02:06
	 * @param configname
	 * @param configkey
	 * @param query
	 * @return PageFinder
	 */
	public PageFinder<SystemConfig> queryPageSystemConfig(String configname, String configkey, Query query);
	
	/**
	 * 分页查询(包括有删除标记的)
	 * @MethodName queryPageSystemConfig
	 * @Description
	 * @author chengui
	 * @date 2012-8-16 下午5:02:06
	 * @param configname
	 * @param configkey
	 * @param query
	 * @return PageFinder
	 */
	public PageFinder<SystemConfig> queryPageAllSystemConfig(String configname, String configkey, Query query);


}
