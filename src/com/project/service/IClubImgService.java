package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.ClubImg;

public interface IClubImgService {
	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ClubImg seveClubImg(ClubImg o) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<ClubImg> getPageFindeClubImg(ClubImg o, int pageNumber, int pageSize);
	
	/**
     * 根据ID获得数据
     * @param oid
     * @return
     */
    public ClubImg getClubImgById(int oid);
    
    /***
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ClubImg mergeClubImg(ClubImg o) throws Exception;
	
	/***
	 * 根据编号删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteClubImgById(int id) throws Exception;
	
	/**
     * 根据俱乐部ID获得第一条数据
     * @param oid
     * @return
     */
    public ClubImg getClubImgByClubId(int cid);
    /**
     * 根据俱乐部ID获得数据
     * @param oid
     * @return
     */
    public List<ClubImg> getClubImgsByClubId(int cid);
}


