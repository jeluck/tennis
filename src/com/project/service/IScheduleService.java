package com.project.service;

import com.project.pojo.Schedule;

public interface IScheduleService {
	/**
	 * 保存教练预约数据
	 * @param s
	 * @return
	 */
	public Schedule saveSchedule(Schedule s) throws Exception;
	
	/**
	 * 根据所有信息去查询数据
	 * @return
	 */
	public Schedule getScheduleAll(Integer timepoint,String week,Integer activeId,Integer belong);
	
	public void deleteSchedule(Schedule s) throws Exception;
}
