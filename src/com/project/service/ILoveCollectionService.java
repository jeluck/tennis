package com.project.service;

import com.project.pojo.LoveCollection;

public interface ILoveCollectionService {
	
	/**
	 * 收藏
	 * @param l
	 * @return
	 */
	public LoveCollection saveLoveCollection(LoveCollection l) throws Exception;
	
	/**
	 * 根据用户ID和类型编号和类型去数据
	 * @param userId
	 * @param activeId
	 * @param type
	 * @return
	 */
	public LoveCollection getLcByUserAndActive(Integer userId,Integer activeId,Integer type);
	
	/**
	 * 删除数据
	 * @param l
	 * @throws Exception
	 */
	public void deleteLoveCollection(LoveCollection l)throws Exception;
}
