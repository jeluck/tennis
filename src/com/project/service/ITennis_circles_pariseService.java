package com.project.service;

import java.util.List;

import com.project.pojo.Tennis_cricles_parise;

public interface ITennis_circles_pariseService {
	
	/**
	 * 保存点赞
	 * @param tcp
	 * @return
	 */
	public Tennis_cricles_parise saveTennis_cricles_parise(Tennis_cricles_parise tcp) throws Exception;
	
	/**
	 * 根据ID删除数据
	 * @param tennis_cricles_id
	 */
	public void deleteTennis_cricles_parise(int tcpId)throws Exception;
	
	/**
	 * 根据网球圈ID查找所有的点赞人数
	 * @param tcId
	 * @return
	 */
	public List<Tennis_cricles_parise> findTennis_cricles_pariseBytcId(int tcId);
}
