package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.RechargeEvents;
import com.project.pojo.Red_bag;

public interface IRechargeEventsService {

	
	/**
	 * 得到所有正常的充值活动数据
	 * @return
	 */
	public List<RechargeEvents> getAllRechargeEventsList();
	
	/**
	 * 保存
	 * @param o
	 * @return
	 */
	public RechargeEvents saveRechargeEvents(RechargeEvents o) throws Exception;
	
	/**
	 * 修改
	 * @param o
	 * @return
	 */
	public RechargeEvents uppdateRechargeEvents(RechargeEvents o) throws Exception;
	
	/**
	 * 删除
	 * @param o
	 */
	public void deleteRechargeEvents(RechargeEvents o) throws Exception;
	
	
	/**
	 * 根据编号查询数据
	 * @param id
	 * @return
	 */
	public RechargeEvents getRechargeEventsById(int id);
	
	
	/**
	 * 分页数据
	 * @param re
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<RechargeEvents> getPageFindeRechargeEvents(RechargeEvents re, int pageNumber, int pageSize);
}
