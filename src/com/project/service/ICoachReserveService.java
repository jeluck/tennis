package com.project.service;

import java.util.List;

import com.project.pojo.CoachReserve;

public interface ICoachReserveService {
	/**
	 * 保存信息
	 * @param c
	 * @return
	 */
	public CoachReserve saveCoachReserve(CoachReserve c)throws Exception;
	
	
	public CoachReserve getCoachReserve(String date,Integer coachId,Integer timepoint);
	
	public void deleteCoachReserve(CoachReserve c)throws Exception;
	
	public List<CoachReserve> getCoachReserveBySubNo(String subNo);
}
