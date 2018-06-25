package com.project.service.impl;


import com.project.common.Constants;
import com.project.dao.ISystemConfigDao;
import com.project.orm.basedao.CritMap;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.orm.basedao.Query;
import com.project.pojo.StationMessage;
import com.project.pojo.SystemConfig;
import com.project.service.ISystemConfigService;
import com.project.util.CommonUtil;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

@Service
public class SystemConfigServiceImpl implements ISystemConfigService {

	@Resource
	private ISystemConfigDao systemConfigDao;

	
	@Override
	public PageFinder<SystemConfig> getSystemConfigList(int pageNumber, int pageSize,SystemConfig sc) {
		CriteriaAdapter criteriaAdapter = systemConfigDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(CommonUtil.NotEmpty(sc.getConfigName())){
			criteria.add(Restrictions.like("configName", sc.getConfigName(), MatchMode.ANYWHERE));
		}if(CommonUtil.NotEmpty(sc.getKey())){
			criteria.add(Restrictions.like("key", sc.getKey(), MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.desc("id"));
		PageFinder<SystemConfig> querySystemConfigList = systemConfigDao.pagedByCriteria(criteria, pageNumber, pageSize);
		systemConfigDao.releaseHibernateSession(criteriaAdapter.getSession());
		
		return querySystemConfigList;
	}
	
	@Transactional
	public SystemConfig addSystemConfig(SystemConfig config) throws Exception{
		SystemConfig db_config = systemConfigDao.findSystemConfigByKey(config.getKey());
		if(db_config == null){
			config.setCreate_time(CommonUtil.getTimeNow());
			config.setUpdate_time(CommonUtil.getTimeNow());
			config.setDeleteFlag(Constants.NORMAL_FLAG);
			systemConfigDao.saveObject(config);
			db_config = config;
		}else{
			db_config.setValue(config.getValue());
			db_config.setConfigName(config.getConfigName());
			db_config.setRemark(config.getRemark());
			db_config.setDeleteFlag(Constants.NORMAL_FLAG);
			db_config.setUpdate_time(CommonUtil.getTimeNow());
			systemConfigDao.merge(db_config);
		}
		return db_config;
	}

	public SystemConfig getSystemConfigById(int id) {
		return systemConfigDao.getById(id);
	}

	/**
	 * 获取所有没有删除的配置
	 */
	public List<SystemConfig> getAllSystemConfig(String configname, String configkey) {
		CritMap critMap = new CritMap();
		critMap.addDesc("deleteFlag");
		if(null!=configname && !"".equals(configname.trim())) {
			critMap.addLike("configName", configname);
		}
		if(null!=configkey && !"".equals(configkey.trim())) {
			critMap.addLike("key", configkey);
		}
		return systemConfigDao.findByCritMap(critMap, true);
	}

	/**
	 * 获取所有没有删除的配置
	 */
	public List<SystemConfig> getAllUseSystemConfig(String configname, String configkey) {
		CritMap critMap = new CritMap();
		critMap.addEqual("deleteFlag", Constants.NORMAL_FLAG);
		if(null!=configname && !"".equals(configname.trim())) {
			critMap.addLike("configName", configname);
		}
		if(null!=configkey && !"".equals(configkey.trim())) {
			critMap.addLike("key", configkey);
		}
		return systemConfigDao.findByCritMap(critMap, true);
	}

	/**
	 * 获取所有没有删除的配置
	 */
	public List<SystemConfig> getAllUseSystemConfigNoCache() {
		return systemConfigDao.findBy("deleteFlag", Constants.NORMAL_FLAG,false);
	}
	
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	public SystemConfig updateSystemConfig(SystemConfig systemConfig)throws Exception {
		SystemConfig db_config = getSystemConfigById(systemConfig.getId());
		db_config.setValue(systemConfig.getValue());
		db_config.setKey(systemConfig.getKey());
		db_config.setRemark(systemConfig.getRemark());
		db_config.setConfigName(systemConfig.getConfigName());
		systemConfigDao.merge(db_config);
		
		return db_config;
	}

	@Transactional
	public int removeSystemConfig(int configId) throws Exception {
		SystemConfig db_config = getSystemConfigById(configId);
		db_config.setDeleteFlag(Constants.DETELE_FLAG);
		systemConfigDao.merge(db_config);
		return configId;
	}
	
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	public String useConfig(int id){
		SystemConfig db_config = getSystemConfigById(id);
		db_config.setDeleteFlag(Constants.NORMAL_FLAG);
		systemConfigDao.merge(db_config);
		return Constants.SUCCESS;
	}
	
	@Transactional
	public int closeSystemConfig(int configId){
		SystemConfig db_config = getSystemConfigById(configId);
		db_config.setDeleteFlag(Constants.DETELE_FLAG);
		systemConfigDao.merge(db_config);
		return configId;
	}

	public SystemConfig getSystemConfigByKey(String key) throws Exception{
		SystemConfig config = getCacheSystemConfigByKey(key);
		if(config == null){
			config = new SystemConfig();
			config.setValue(key);
			config.setKey(key);
			config.setConfigName(key);
			config.setRemark(key);
			config.setDeleteFlag(Constants.DETELE_FLAG);

			config = addSystemConfig(config);
		}

		return config;
	}
	
	public SystemConfig querySystemConfigByKey(String key) throws Exception{
		return systemConfigDao.findSystemConfigByKey(key);
	}

	/**
	 * 从缓存中取配置，如果缓存没有查询到数据，再查询数据库 加入缓存
	 */
	@Override
	public SystemConfig getCacheSystemConfigByKey(String key){
//		try{
//			IMemcachedCache commonOrderDetail = Cache.getCommonCacheInstatce();
//			if(commonOrderDetail!= null && commonOrderDetail.get(key)!=null){
//				SystemConfig config = (SystemConfig)commonOrderDetail.get(key);
//				return config;
//			}else{
//				SystemConfig config = systemConfigDao.findSystemConfigByKey(key);
//				if(config!=null){
//					if(commonOrderDetail!= null){
//						commonOrderDetail.put(key, config);
//					}
//					return config;
//				}
//			}
//		}catch(Exception e){
//			SystemConfig config = systemConfigDao.findSystemConfigByKey(key);
//			if(config!=null){
//				return config;
//			}
//		}
		return null;
	}
	
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
	public PageFinder<SystemConfig> queryPageSystemConfig(String configname, String configkey, Query query) {
		CritMap critMap = new CritMap();
		critMap.addEqual("deleteFlag", Constants.DETELE_FLAG);
		if(null!=configname && !"".equals(configname.trim())) {
			critMap.addLike("configName", configname.trim());
		}
		if(null!=configkey && !"".equals(configkey.trim())) {
			critMap.addLike("key", configkey.trim());
		}
		return systemConfigDao.pagedByCritMap(critMap, query.getPage(), query.getPageSize());
	}
	
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
	public PageFinder<SystemConfig> queryPageAllSystemConfig(String configname, String configkey, Query query) {
		CritMap critMap = new CritMap();
		critMap.addDesc("deleteFlag");
		if(null!=configname && !"".equals(configname.trim())) {
			critMap.addLike("configName", configname.trim());
		}
		if(null!=configkey && !"".equals(configkey.trim())) {
			critMap.addLike("key", configkey.trim());
		}
		return systemConfigDao.pagedByCritMap(critMap, query.getPage(), query.getPageSize());
	}

	@Override
	public String getConfigValueByKey(String key) {
		if(StringUtils.isEmpty(key)){
			return "";
		}
		String value = "";
		try {
			SystemConfig config = querySystemConfigByKey(key);
			if(config!=null){
				return config.getValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	@Override
	public String getConfigValueByKey(String key, String defaultValue) {
		if(StringUtils.isEmpty(key)){
			return defaultValue;
		}
		String value = "";
		try {
			SystemConfig config = querySystemConfigByKey(key);
			if(config!=null){
				value = config.getValue();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StringUtils.isEmpty(value)?defaultValue:value;
	}

	@Override
	public SystemConfig getSystemConfigBykey(String key) throws Exception {
		CriteriaAdapter criteriaAdapter = systemConfigDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("key", key));
        List<SystemConfig> list = criteria.list();
        systemConfigDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null && list.size() > 0){
        	return list.get(0);
        }
		return null;
	}

}
