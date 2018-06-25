package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.User_team;

public interface IUserTeamService {
	
	/**
	 * 保存球队信息
	 * @param userTeam
	 * @return
	 * @throws Exception
	 */
	public User_team saveUserTeam(User_team userTeam) throws Exception;
	
	/**
	 * 根据编号删除球队
	 * @param id
	 * @throws Exception
	 */
	public void deleteUserTeam(int id) throws Exception;
	
	
	/**
	 * 修改球队信息
	 * @param userTeam
	 * @return
	 * @throws Exception
	 */
	public User_team updateUserTeam(User_team userTeam) throws Exception;
	
	/**
	 * 根据编号得到球队信息
	 * @param id
	 * @return
	 */
	public User_team getUserTeamById(int id);
	
	/**
	 * 分页得到球队列表信息
	 * @param userTeam
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<User_team> getPageFindeUserTeam(User_team userTeam, int pageNumber, int pageSize);
}
