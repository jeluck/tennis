package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Space;
import com.project.pojo.TerraceMessage;

public interface ITerraceMessageService {
	/**
	 * 保存平台要发送的信息
	 * @param t
	 * @return
	 */
	public TerraceMessage saveTerraceMessage(TerraceMessage t)throws Exception;
	
	/**
	 * 修改平台信息，提供软删除
	 * @param t
	 * @return
	 */
	public TerraceMessage updateTerraceMessage(TerraceMessage t)throws Exception;
	
	/**
	 * 彻底删除数据
	 * @param t
	 */
	public void deleteTerraceMessage(Integer id)throws Exception;
	
	/**
	 * 分页列表
	 * @param t
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<TerraceMessage> getPageFindeTerraceMessage(TerraceMessage t, int pageNumber, int pageSize);
	
	/**
	 * 根据ID取得数据
	 * @param id
	 * @return
	 */
	public TerraceMessage getById(Integer id);
}
