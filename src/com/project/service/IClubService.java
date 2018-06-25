package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Club;

public interface IClubService {
	
	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Club addClub(Club o) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Club> getPageFindeClub(Club o, int pageNumber, int pageSize);
	
	/**
     * 根据ID获得数据
     * @param oid
     * @return
     */
    public Club getclubById(int oid);
    
    /***
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Club mergeClub(Club o) throws Exception;
	
	
	public List<Club> getAllClub();
}
