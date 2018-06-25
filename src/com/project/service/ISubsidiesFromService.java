package com.project.service;

import java.util.List;

import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.vo.OrderVo;
import com.project.pojo.vo.SubsidiesFrom;


/**
 * 
 * @author lxc
 *
 */
public interface ISubsidiesFromService {
	
	/**
	 * 按OrderVo查询主订单
	 * @param o
	 * @return
	 */
	public List<OrderMain> getOrderMainListByOrderMainVo(OrderVo o);
	
	/**
	 * 补贴结算表
	 * @param o
	 * @return
	 */
	public List<SubsidiesFrom> getSubsidiesFrom(OrderVo o);
	
	
	/**
	 * 按OrderVo查询主订单
	 * @param o
	 * @return
	 */
	public List<Orderinfo> getOrderinfoListByOrderVo(OrderVo o);
	
	/**
	 * 根据子订单编号修改子订单补贴发放状态
	 * @param orderSubNo
	 * @return
	 */
	public Orderinfo editSubsidies_grant_status(String orderSubNo);
	
	/**
	 * 根据子订单编号修改子订单交易结算状态
	 * @param orderSubNo
	 * @return
	 */
	public Orderinfo editTrade_balance_status(String orderSubNo);
}
