package com.project.service;

import java.util.List;

import com.project.pojo.Trade_recode;

/**
 * 交易记录service
 * @author Administrator
 *
 */
public interface ITrade_recodeService {
	
	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Trade_recode saveTrade_recode(Trade_recode o) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteTrade_recode(int id) throws Exception;
	
	/**
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Trade_recode updateTrade_recode(Trade_recode o) throws Exception;
	
	/**
	 * 根据编号查询
	 * @param id
	 * @return
	 */
	public Trade_recode getTrade_recodeById(int id);
	
	/**
	 * 根据主订单编号查询交易记录
	 * @param orderMainId
	 * @return
	 */
	public Trade_recode getTrade_recodeByOrderMian(String orderMainId);
	
	
	/**
	 * 根据用户编号查询交易记录
	 * @param userId
	 * @return
	 */
	public List<Trade_recode> getTrade_recodeByUserId(int userId,String type);
	
}
