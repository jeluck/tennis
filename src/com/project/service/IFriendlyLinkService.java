package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.FriendlyLink;

public interface IFriendlyLinkService {
	
	
	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public FriendlyLink saveFriendlyLink(FriendlyLink o) throws Exception;
	
	/**
	 * 根据编号删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteFriendlyLink(int id) throws Exception;
	
	
	/**
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public FriendlyLink updateFriendlyLink(FriendlyLink o) throws Exception;
	
	/**
	 * 根据编号查询
	 * @param id
	 * @return
	 */
	public FriendlyLink getFriendlyLinkById(int id);
	
	/**
	 * 列表信息
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<FriendlyLink> getPageFindeFriendlyLink(FriendlyLink o, int pageNumber, int pageSize);
}
