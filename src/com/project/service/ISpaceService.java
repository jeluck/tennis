package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Space;

public interface ISpaceService {
	

	/**
	 * 根据场地Id获得数据
	 * @param id
	 * @return
	 */
	public Space getSpace(int id);
	/***
	 * 保存Space
	 * @param c
	 * @return
	 */
	public Space saveSpace(Space c) throws Exception;
	
	/***
	 * 更新
	 * @param o
	 * @return
	 */
	public Space updateSpace(Space o);
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Space> getPageFindeSpace(Space o, int pageNumber, int pageSize,int oid);

	/**
	 * 根据场馆ID获得场地
	 * @param playground_id
	 */
	public List<Space> getPlaygroundSpaceBy_PGid(int playground_id,Integer belongto);
	
	/**
	 * 获得所有场地数据
	 * @return
	 */
	public List<Space> getAll();
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Space> getPageFindeSpaceByCoach(Space o, int pageNumber, int pageSize,int oid);
	
}
