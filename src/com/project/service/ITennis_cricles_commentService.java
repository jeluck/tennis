package com.project.service;

import com.project.pojo.Tennis_cricles_comment;

public interface ITennis_cricles_commentService {
	
	/**
	 * 保存评论
	 * @param tcc
	 * @return
	 */
	public Tennis_cricles_comment saveTennis_cricles_comment(Tennis_cricles_comment tcc) throws Exception;
	
	/**
	 * 根据ID删除评论
	 * @param tccId
	 */
	public void deleteTennis_cricles_comment(int tccId) throws Exception;
	
	
}
