package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.User_level;

public interface IUser_levelService {
	
	/**
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public User_level updateUser_level(User_level o) throws Exception;
	
	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public User_level saveUser_level(User_level o) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteUser_level(int id) throws Exception;

	
	/**
	 * 查询全部
	 * @return
	 */
	public List<User_level> getUserLevleList();
	
	/**
	 * 根据编号查询
	 * @return
	 */
	public User_level getUserLevelById(int id);
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<User_level> getPageFindeUser_level(User_level o, int pageNumber, int pageSize);
}
