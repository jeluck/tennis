package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach_teach_person;

public interface ICoachTeachPersonService {
	
	/**
	 * 保存教练带人信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Coach_teach_person saveCoachTeachPerson(Coach_teach_person o) throws Exception;
	
	/**
	 * 根据编号删除教练带人
	 * @param id
	 * @throws Exception
	 */
	public void deleteCoachTeachPerson(int id) throws Exception;
	
	
	/**
	 * 修改教练带人信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Coach_teach_person updateCoachTeachPerson(Coach_teach_person o) throws Exception;
	
	/**
	 * 根据编号得到教练带人信息
	 * @param id
	 * @return
	 */
	public Coach_teach_person getCoachTeachPersonById(int id);
	
	/**
	 * 分页得到教练带人列表信息
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Coach_teach_person> getPageFindeCoachTeachPerson(Coach_teach_person o, int pageNumber, int pageSize);
}
