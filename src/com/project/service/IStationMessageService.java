package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.StationMessage;

public interface IStationMessageService {
	
	/**
	 * 保存数据
	 * @param s
	 * @return
	 */
	public StationMessage saveStationMessage(StationMessage s) throws Exception;
	
	/**
	 * 删除数据，根据ID删除
	 * @param id
	 */
	public void deleteStationMessage(Integer id)throws Exception;

	/**
	 * 根据用户ID去查询数据
	 * @param weuserId
	 * @return
	 */
	public List<StationMessage> getStationMessageByWeuserId(Integer weuserId);
	
	/**
	 * 修改数据，主要修改两个状态
	 * @param s
	 * @return
	 */
	public StationMessage updateStationMessage(StationMessage s)throws Exception; 
	
	
	/**
	 * 根据用户ID去查询分页数据
	 * @param weuserId
	 * @return
	 */
	public PageFinder<StationMessage> getPageStationMessageByUserId(Integer weuserId,int pageNumber, int pageSize,int send_type,String appsyetem);
	
	/**
	 * 根据用户ID去查询未删除和未读的消息数据
	 * @param weuserId
	 * @return
	 */
	public List<StationMessage> getStationMessageByWeuserIdAndStatus(Integer weuserId,String appsyetem);
	
	/**
	 * 根据编号查询
	 * @param id
	 * @return
	 */
	public StationMessage getStationMessageById(int id);
	
	/**
	 * 根据用户ID去查询未删除和未读的消息数据
	 * @param weuserId
	 * @return
	 */
	public StationMessage getOneStationMessageByWeuserId(Integer weuserId,String appsyetem);
}
