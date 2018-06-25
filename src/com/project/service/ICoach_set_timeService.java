package com.project.service;

import java.util.List;

import com.project.pojo.Coach_set_time;

public interface ICoach_set_timeService {
	/**
	 * 保存
	 * @param o
	 * @throws Exception
	 */
	public void saveCoach_set_time(Integer hour,Integer ciachId) throws Exception;
	
	/**
	 * 更新
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Coach_set_time updateCoach_set_time(Coach_set_time o) throws Exception;
	
	/**
     * 根据教练ID和类型获得时间数据
     * @param
     * @return
     */
    public List<Coach_set_time> getCoach_set_timeByCoachId(int coachId,int time_type,int coachType);
    
    /**
     * 根据ID删除时间
     * @param id
     */
    public void deleteById(int id) throws Exception;
    
    
    /**
     * 保存
     * @param o
     * @throws Exception
     */
    public Coach_set_time saveCoachSetTime(Coach_set_time o) throws Exception;
    
    /**
     * 删除时间
     * @param id
     */
    public void deleteBycoachId(Coach_set_time o) throws Exception;
    
    /**
     * 根据编号获得数据
     * @param id
     * @return
     */
    public Coach_set_time getCoach_set_timeById(int id);
    
    /**
     * 根据类型和点数查询
     * @param type
     * @param hour
     * @return
     */
    public Coach_set_time getCoach_set_timeByTypeAndHour(int coachId,int type,int hour);
    
    /**
     * 根据教练ID查找数据
     * @param coachId
     * @return
     */
    public List<Coach_set_time> getCoach_set_timeByCoachId(Integer coachId);
}
