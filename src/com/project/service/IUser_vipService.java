package com.project.service;


import com.project.orm.basedao.PageFinder;
import com.project.pojo.User_vip;

public interface IUser_vipService {
	
	
	/**
	 * 新增会员
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public User_vip saveUser_vip(User_vip o)throws Exception;
	
	/**
	 * 修改会员
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public User_vip updateUser_vip(User_vip o)throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<User_vip> getPageFindeUser_vip(User_vip o, int pageNumber, int pageSize);
	
	/**
	 * 根据编号查询会员
	 * @param id
	 * @return
	 */
	public User_vip getUser_vipById(int id);
}
