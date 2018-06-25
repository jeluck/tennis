package com.project.service;

import com.project.pojo.Invite;

public interface InviteService {

	/**
	 * 新增
	 * @param o
	 * @return
	 */
	public Invite saveInvite(Invite o)  throws Exception;
	
	/**
	 * 按被邀请人编号查询邀请信息
	 * @param friend_user_id
	 * @return
	 */
	public Invite getInviteByfriend_user_id(int friend_user_id);
	
	/**
	 * 修改
	 * @param o
	 * @return
	 */
	public Invite updateInvite(Invite o)  throws Exception;
	
}
