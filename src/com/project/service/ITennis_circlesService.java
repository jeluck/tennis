package com.project.service;

import java.util.List;

import com.project.pojo.Tennis_circles;

public interface ITennis_circlesService {

	/***
	 * 获得用户网球圈信息
	 * @param userid	用户ID
	 * @return
	 */
	public List<Tennis_circles> getshoShoppingcartCommodities(int userid);

	/***
	 * 查询网球圈
	 * @param id	ID
	 * @return
	 */
	public Tennis_circles getTennis_circlesById(int id);
	
	/**
	 * 保存网球圈
	 * @param sc
	 * @return
	 */
	public Tennis_circles saveTennis_circles(Tennis_circles sc);
	

	
}
