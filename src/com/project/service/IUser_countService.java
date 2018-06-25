package com.project.service;

import com.project.pojo.User_count;

public interface IUser_countService {
	/**
	 * 保存
	 * @param o
	 * @throws Exception
	 */
	public User_count saveuser_Count(User_count o) throws Exception;
	
	/**
	 * 根据编号查询
	 * @param id
	 * @return
	 */
	public User_count getUser_countById(int id);
	
	/**
	 * 修改用户计量数据
	 * @param u
	 * @return
	 */
	public User_count updateUser_count(User_count u);
	
	/**
	 * 根据用户ID获取数据
	 * @param userId
	 * @return
	 */
	public User_count getUser_countByUserId(Integer userId);
	
	/**
	 * 根据用户编号和教练编号新增学员人数
	 * @param userId
	 * @param coachId
	 */
	public void saveUser_countByUserIdAndCoachId(int userId,int coachId) throws Exception;
}
