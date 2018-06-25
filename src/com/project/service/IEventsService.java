package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Activity;
import com.project.pojo.Events;

public interface IEventsService {

	/**
	 * 保存赛事信息
	 * @param a
	 * @return
	 */
	public Events saveEvents(Events e) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Events> getPageFindeEvents(Events o, int pageNumber, int pageSize);
	
	/**
	 * 更新赛事信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Events updateEvents(Events o) throws Exception;
	
	/**
     * 根据ID获得赛事数据
     * @param oid
     * @return
     */
    public Events getEventsById(int oid);
    
    /**
     * 按条件查询赛事
     * @param o
     * @return
     */
    public List<Events> getEvents();
    
    /**
     * 查询最新赛事
     * @return
     */
    public Events getNewEvents();
}
