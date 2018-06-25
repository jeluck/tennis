package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.EventRegistration;
import com.project.pojo.School;

public interface IEventRegistrationService {
	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public EventRegistration seveEventRegistration(EventRegistration o) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<EventRegistration> getPageFindeEventRegistration(EventRegistration o, int pageNumber, int pageSize);
	
	/**
     * 根据ID获得数据
     * @param oid
     * @return
     */
    public EventRegistration getEventRegistrationById(int oid);
    
    /***
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public EventRegistration mergeEventRegistration(EventRegistration o) throws Exception;
	
	/***
	 * 根据编号删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteEventRegistrationById(int id) throws Exception;
}
