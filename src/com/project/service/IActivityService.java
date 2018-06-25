package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Activity;

public interface IActivityService {
	
	/**
	 * 保存活动信息
	 * @param a
	 * @return
	 */
	public Activity saveActivity(Activity a) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Activity> getPageFindeActivity(Activity o, int pageNumber, int pageSize);
	
	/**
	 * 更新活动信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Activity updateActivity(Activity o) throws Exception;
	
	/**
     * 根据ID获得活动数据
     * @param oid
     * @return
     */
    public Activity getActivityById(int oid);
    
    /**
     * 根据场馆编号获得活动
     * @param id
     * @return
     */
    public Activity getActivityByPdId(int id);
}
