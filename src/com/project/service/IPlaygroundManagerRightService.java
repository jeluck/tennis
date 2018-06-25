package com.project.service;

import java.util.List;

import com.project.pojo.AdminMenu;
import com.project.pojo.AdminRoleRight;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.PlaygroundManagerAdminRole;

public interface IPlaygroundManagerRightService {
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
	 * 得到带menu_belong_to_role所有菜单项信息
	 * @return
	 */
	public List<AdminMenu> getAllAdminMenu();
	/**
	 * 根据cooperative_partner_id得到所有角色信息
	 * @return
	 */
	public List<PlaygroundManagerAdminRole> getAllPlaygroundManagerAdminRoleAdminRole(int cooperative_partner_id);
	/**
	 * 保存角色信息
	 * @param role
	 * @return
	 */
	public boolean savePlaygroundManagerAdminRole(PlaygroundManagerAdminRole role);
	/**
	 * 更新角色信息
	 * @param role
	 * @return
	 */
	public boolean updatePlaygroundManagerAdminRole(PlaygroundManagerAdminRole role);
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
	 * 根据cooperative_partner_id得到角色用户信息
	 * @param role_code
	 * @param eq
	 * @return
	 */
	public List<PlaygroundManager> getPlaygroundManagerRoleUsers(String role_code, boolean eq,int cooperative_partner_id);
	/**
	 * 保存角色用户信息
	 * @param role_code
	 * @param users
	 * @return
	 */
	public boolean savePlaygroundManagerRoleUsers(String role_code, String users,int cooperative_partner_id);
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
	public PlaygroundManagerAdminRole getPlaygroundManagerAdminRoleById(int id);
	
	/**
	 * 根据Code得到角色信息
	 * @param menu_code
	 * @return
	 */
	public PlaygroundManagerAdminRole getPlaygroundManagerAdminRoleByCode(String role_code);
	/**
	 * 得到用户拥有的权限列表
	 * @param manager_id
	 * @return
	 */
	public List<AdminMenu> getUserMenus(int playgroundmanager_id,int cooperative_partner_id);
	
	/**
	 * 根据Code得到权限列表
	 * @param menu_code
	 * @return
	 */
	public List<AdminRoleRight> getAdminRoleRightByRole_code(String role_code);
	
	/**
	 * 根据注册的场馆运营者,分配默认的权限
	 */
	public void defaultPGMAdminRoleRight(PlaygroundManager o);
}
