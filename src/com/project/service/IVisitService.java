package com.project.service;

import java.util.List;

import com.project.pojo.Visit;

public interface IVisitService {
	
	/**
	 * 保存
	 * @param o
	 * @return
	 */
	public Visit saveVisit(Visit o) throws Exception;
	
	
	/**
	 * 根据教练编号查询全部
	 * @param coachId
	 * @return
	 */
	public List<Visit> getVisitListByCoachId(int coachId,int viType) throws Exception;
	
	/**
	 * 删除
	 * @param o
	 */
	public void deleteVisit(Visit o) throws Exception;
	
}
