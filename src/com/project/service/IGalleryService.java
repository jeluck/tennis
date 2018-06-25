package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Gallery;

public interface IGalleryService {

	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Gallery seveClubImg(Gallery o) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Gallery> getPageFindeClubGallery(Gallery o, int pageNumber, int pageSize);
	
	/**
     * 根据ID获得数据
     * @param oid
     * @return
     */
    public Gallery getGalleryById(int oid);
    
    /***
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Gallery mergeGallery(Gallery o) throws Exception;
	
	/***
	 * 根据编号删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteGalleryById(int id) throws Exception;
	
	
	/**
	 * 根据类型和编号查询
	 * @param type
	 * @param cid
	 * @return
	 */
	public List<Gallery> getGalleryByCidAndType(int type,int cid);
}
