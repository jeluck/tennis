package com.project.service;

import com.project.pojo.ClubIndex;

public interface IClubIndexService {

	
	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ClubIndex saveClubIndex(ClubIndex o) throws Exception;
	
	/**
	 * 查询
	 * @return
	 */
	public ClubIndex getClubIndex();
	
	/**
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ClubIndex updateClubIndex(ClubIndex o) throws Exception;
	
}
