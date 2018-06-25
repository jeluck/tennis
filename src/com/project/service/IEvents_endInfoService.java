package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Events;
import com.project.pojo.Events_endInfo;

public interface IEvents_endInfoService {
	/**
	 * 保存赛后信息
	 * @param a
	 * @return
	 */
	public Events_endInfo saveEvents_endInfo(Events_endInfo e) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Events_endInfo> getPageFindeEvents_endInfo(Events_endInfo o, int pageNumber, int pageSize,int oid);
	
	/**
	 * 更新赛后信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Events_endInfo updateEvents_endInfo(Events_endInfo o) throws Exception;
	
	/**
     * 根据ID获得赛后数据
     * @param oid
     * @return
     */
    public Events_endInfo getEvents_endInfoById(int oid);
}
