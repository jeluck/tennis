package com.project.service;

import java.util.List;

import com.project.pojo.Business_time;

public interface IBusiness_timeService {
	
	/**
	 * 保存信息
	 * @param b
	 * @return
	 */
	public Business_time saveBusiness_time(Business_time b) throws Exception;

	
	/**
	 * 根据场馆ID查找信息
	 * @param playgroundId
	 * @return
	 */
	public Business_time findByplaygroundId(int playgroundId,int dateType);
	
	/**
	 * 修改数据
	 * @param b
	 * @return
	 */
	public Business_time updateBusiness_time(Business_time b);
	
	public Business_time findById(Integer id);
	
	
	/**
	 * 根据场馆ID查找信息
	 * @param playgroundId
	 * @return
	 */
	public List<Business_time> findByplaygroundId(int playgroundId);
	
}
