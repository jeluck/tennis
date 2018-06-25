package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach_teach_person;
import com.project.pojo.Module;

public interface ICoach_teach_personService {
	/**
	 * 保存
	 * @param o
	 * @throws Exception
	 */
	public Coach_teach_person saveCoach_teach_person(Coach_teach_person o) throws Exception;
	
	/**
	 * 更新
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Coach_teach_person updateCoach_teach_person(Coach_teach_person o) throws Exception;
	
	/**
     * 根据教练ID获得人员数据
     * @param oid 场馆ID
     * @return
     */
    public List<Coach_teach_person> getCoach_teach_personByCoachId(int coachId);
    
    /**
     * 根据ID删除人员
     * @param id
     */
    public void deleteById(int id) throws Exception;
    
    /**
     * 根据ID查找数据
     * @param id
     * @return
     */
    public Coach_teach_person getCoach_teach_personById(Integer id);
    
    public Coach_teach_person getCoach_teach_personByCidAndper(Integer coachId);
    
    
    /**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Coach_teach_person> getPageFindeCoach_teach_person(Coach_teach_person o,int pageNumber, int pageSize);
}
