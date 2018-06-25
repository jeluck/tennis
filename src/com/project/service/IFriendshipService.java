package com.project.service;

import com.project.pojo.Friendship;

public interface IFriendshipService {

	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Friendship saveFriendship(Friendship o) throws Exception;
	
	/**
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Friendship updateFriendship(Friendship o) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteFriendship(int id)  throws Exception;
	
	
	/**
	 * 根据编号查询
	 * @param id
	 * @return
	 */
	public Friendship getFriendshipById(int id);
	
	/**
	 * 根据用户编号 和好友编号查询
	 * @param userId
	 * @param friend_user_id
	 * @return
	 */
	public Friendship getFriendshipByUserIdAndFuserId(int userId,int friend_user_id);
	
	/**
	 * 根据对象删除
	 * @param o
	 * @throws Exception
	 */
	public void deleteFriendshipByObject(Friendship o) throws Exception;
	
}
