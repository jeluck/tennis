package com.project.service;

import java.util.List;

import com.project.pojo.Coach_reserve_time;

public interface ICoach_reserve_timeService {
	
	/**
	 * 保存预定了的教练的时间
	 * @param coachId
	 * @param hour
	 * @return
	 */
	public Coach_reserve_time saveCrt(Coach_reserve_time o)throws Exception;
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public List<Coach_reserve_time> getByTime(String data);
	
	/**
	 * 根据ID删除对象
	 * @param id
	 */
	public void deleteCrt(Integer id) throws Exception;
}
