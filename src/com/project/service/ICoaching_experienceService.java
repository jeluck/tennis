package com.project.service;

import java.util.List;

import com.project.pojo.Coaching_experience;

public interface ICoaching_experienceService {

	/**
	 * 保存
	 * @param o
	 * @return
	 */
	public Coaching_experience saveCoaching_experience(Coaching_experience o) throws Exception;
	
	/**
	 * 根据教练编号查询全部
	 * @param coachId
	 * @return
	 */
	public List<Coaching_experience> getCoaching_experienceListByCoachId(int coachId);
	
	/**
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Coaching_experience updateCoaching_experience(Coaching_experience o) throws Exception;
	
	/**
	 * 删除
	 * @param o
	 * @throws Exception
	 */
	public void deleteCoaching_experience(Coaching_experience o) throws Exception;
}
