package com.project.service;

import com.project.pojo.AdminMenu;
import com.project.pojo.AdminRole;
import com.project.pojo.AdminRoleRight;
import com.project.pojo.Manager;

import java.util.List;

public interface IRightService {
	/**
	 * 保存菜单权限项
	 * @param sysMenu
	 * @return
	 */
	public boolean saveAdminMenu(AdminMenu menu);
	
	/***
	 * 根據菜單ID,獲得數據
	 * @param id
	 * @return
	 */
	public AdminMenu getAdminMenuById(int id);
	
	/**
	 * 修改菜单项信息
	 * @param sysMenu
	 * @return
	 */
	public boolean updateAdminMenu(AdminMenu menu);
	/**
	 * 得到所有菜单项信息
	 * @return
	 */
	public List<AdminMenu> getAllAdminMenu();
	/**
	 * 得到所有角色信息
	 * @return
	 */
	public List<AdminRole> getAllAdminRole();
	/**
	 * 保存角色信息
	 * @param role
	 * @return
	 */
	public boolean saveAdminRole(AdminRole role);
	/**
	 * 更新角色信息
	 * @param role
	 * @return
	 */
	public boolean updateAdminRole(AdminRole role);
	/**
	 * 得到角色权限列表
	 * @param role_id
	 * @return
	 */
	public List<AdminMenu> getRoleRights(String role_code);
	/**
	 * 保存角色权限信息
	 * @param menu_ids
	 * @param role_code
	 * @return
	 */
	public boolean saveRoleRights(String menu_ids, String role_code);
	/**
	 * 得到角色用户信息
	 * @param role_code
	 * @param eq
	 * @return
	 */
	public List<Manager> getRoleUsers(String role_code, boolean eq);
	/**
	 * 保存角色用户信息
	 * @param role_code
	 * @param users
	 * @return
	 */
	public boolean saveRoleUsers(String role_code, String users);
	/**
	 * 根据menu_code 得到菜单信息
	 * @param menu_code
	 * @return
	 */
	public AdminMenu getAdminMenuByCode(String menu_code);
	
	
	/** 根据id得到角色信息
	 * @param id
	 * @return
	 */
	public AdminRole getAdminRoleById(int id);
	
	/**
	 * 根据Code得到角色信息
	 * @param menu_code
	 * @return
	 */
	public AdminRole getAdminRoleByCode(String role_code);
	/**
	 * 得到用户拥有的权限列表
	 * @param manager_id
	 * @return
	 */
	public List<AdminMenu> getUserMenus(int manager_id);
	
	/**
	 * 根据Code得到权限列表
	 * @param menu_code
	 * @return
	 */
	public List<AdminRoleRight> getAdminRoleRightByRole_code(String role_code);
	
	/**
	 * 根据编号和menu_code 检查是否有一样的 没有则返回true 有false
	 * @param menu_code
	 * @param id
	 * @return
	 */
	public boolean checkMenuCode(String menu_code,int id);
	
	/**
	 * 根据role_code 检查是否有一样的 没有则返回true 有false
	 * @param role_code
	 * @return
	 */
	public boolean checkPmRoleCode(String role_code,int id);
	
	/**
	 * 根据role_code 检查是否有一样的 没有则返回true 有false
	 * @param role_code
	 * @return
	 */
	public boolean checkRoleCode(String role_code,int id);
}
