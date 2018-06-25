package com.project.service;

import java.util.List;

import com.project.pojo.TimeMoeny;

public interface ITimeMoenyService {
	
	/**
	 * 保存时间段价格
	 * @param tm
	 * @return
	 */
	public TimeMoeny saveTimeMoeny(TimeMoeny tm) throws Exception;
	
	/**
	 * 根据场地ID去获取对象
	 * @param spaceId
	 * @return
	 */
	public List<TimeMoeny> getTimeMoenyBySpaceId(int spaceId);

	/**
	 * 修改价格
	 * @param price
	 * @return
	 */
	public TimeMoeny updateTimeMoeny(TimeMoeny o);
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public TimeMoeny getTimeMoenyById(int id);
	
	/**
	 * 清楚查询缓存
	 */
	public void flush();
	
	/**
	 * 根据场馆编号查询数据
	 * @param pdId
	 * @return
	 */
	public TimeMoeny getTimeMoenyByPdId(int pdId);
	
	/**
	 * 根据场地ID时间点和类型去查询数据
	 * @param spaceId
	 * @param hour
	 * @param type
	 * @return
	 */
	public TimeMoeny getTimeMoenyBySpaHourType(int spaceId,int hour,int type);
	
	public void deleteTimeMoeny(TimeMoeny tm) throws Exception;
}
