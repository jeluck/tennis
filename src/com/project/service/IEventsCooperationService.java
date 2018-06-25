package com.project.service;

import java.util.List;

import com.project.pojo.EventsCooperation;

public interface IEventsCooperationService {

	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public EventsCooperation saveEventsCooperation(EventsCooperation o) throws Exception;
	
	
	/**
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public EventsCooperation updateEventsCooperation(EventsCooperation o) throws Exception;
	
	/**
	 * 根据编号查询
	 * @param id
	 * @return
	 */
	public EventsCooperation getEventsCooperationById(int id);
	
	
	/**
	 * 根据活动编号查询集合
	 * @param eid
	 * @return
	 */
	public List<EventsCooperation> getEventsCooperationByEid(int eid);
	
	/**
	 * 删除
	 * @param o
	 * @throws Exception
	 */
	public void deleteEventsCooperation(EventsCooperation o) throws Exception;
}
