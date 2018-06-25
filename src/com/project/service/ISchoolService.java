package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.School;

public interface ISchoolService {
	
	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public School seveSchool(School o) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<School> getPageFindeSchool(School o, int pageNumber, int pageSize);
	
	/**
     * 根据ID获得数据
     * @param oid
     * @return
     */
    public School getSchoolById(int oid);
    
    /***
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public School mergeSchool(School o) throws Exception;
	
	/***
	 * 根据编号删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteSchoolById(int id) throws Exception;
	
	/**
	 * 查询第一个学校
	 * @return
	 */
	public School getSchool();
}
