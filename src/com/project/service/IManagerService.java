package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Manager;

public interface IManagerService {
	/**
	 * 获取管理员列表
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Manager> getManagerList(int pageNumber, int pageSize);

	/**
	 * 新增管理员信息
	 * @param manager
	 * @return
	 */
	public Manager saveManager(Manager manager);

	/**
	 * 根据id获取管理员信息
	 * @param id
	 * @return
	 */
	public Manager getManagerById(int id);

	/**
	 * 根据登录名usercode获取管理员信息
	 * @param usercode
	 * @return
	 */
	public Manager getManagerByUsercode(String usercode);
	/**
	 * 根据id更新管理员的数据
	 * @param manager
	 * @return
	 */
	public boolean updateManager(Manager manager);
	
	/**
	 * 根据登录名和密码查找管理员
	 * @param uphone
	 * @param pass
	 * @return
	 */
	public Manager  managerlogin(String uphone, String pass);


	public void testDB();
	
	
	/**
	 * 根据电话判断管理员电话是否已存在
	 * @param phone
	 * @return
	 */
	public boolean checkPhone(String phone,int id);
}
