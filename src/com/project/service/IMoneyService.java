package com.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Manager;
import com.project.pojo.WithdrawOrder;

public interface IMoneyService {

	/**
	 * 等待提现的审核
	 * @return
	 */
	public Integer getWithdwarCount(int status) ;
	
	/**
	 * 根据Id得到对象(带级联)
	 * @param id
	 * @return
	 */
	public WithdrawOrder getWithdrawOrderById(int id);
	/**
	 * 根据状态得到提现信息
	 * @param pageNumber
	 * @param pageSize
	 * @param status
	 * @param request
	 * @return
	 */
	public PageFinder<WithdrawOrder> getWithdrawByStatus(int pageNumber, int pageSize, int status,HttpServletRequest request);
	/**
	 * 提现审核
	 * @param id
	 * @param stauts
	 * @param remark
	 * @return
	 */
	public boolean updatePassWithdwar(int id, int stauts, String remark) throws Exception;
	
	/**
	 * 根据Id得到对象
	 * @param id
	 * @return
	 */
	public WithdrawOrder getSimpleWithdrawOrderById(int id);
	
	/**
	 * 更新提现记录
	 * @param w
	 * @return
	 * @throws Exception
	 */
	public WithdrawOrder mergeWithdrawOrder(WithdrawOrder w) throws Exception;
	/**
	 * 根据交易批次号得到代付集合
	 * @param req_sn
	 * @return
	 */
	public List<WithdrawOrder> getOrdersByREQ_SN(String req_sn);
	
	/**
	 * 线下手动转账后,点击已转账
	 * @param id
	 * @param stauts
	 * @param remark
	 * @return
	 */
	public boolean updateHandMovementWithdwar(int id, int stauts, String remark,Manager manager)  throws Exception ;
	
	/***
	 * add by lxc	2015-05-22
	 * 根据用户ID查询用户提现金额
	 * @param withdrawer_role		 提现者角色(1用户或者2商家)	
	 * @param id			用户ID
	 * @param wd_status	// 提现状态 1:审核中 2：转账中 3：成功 4：失败
	 * @return
	 */
	public String getAlreadyMentionedAmount(int withdrawer_role,String id,int wd_status);
	
}
