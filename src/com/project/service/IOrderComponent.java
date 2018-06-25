package com.project.service;

import java.util.Map;
import java.util.Set;

import com.project.common.OrderEnum.LogType;
import com.project.common.OrderEnum.MethodCode;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.OrderLog;
import com.project.pojo.Orderinfo;
import com.project.pojo.PayInfo_thirdPay_orderNo;
import com.project.pojo.Weuser;

/**
 * @author xieqingang
 * @date 2011-4-21
 */
public interface IOrderComponent {

	/**
	 * 方法描述：TODO 公共日志方法
	 * 
	 * @param operator
	 *            当前登陆用户实体，当前使用LoginName
	 * @param orderNo
	 *            当前订单编号
	 * @param genSub
	 *            生成订单类型ENUM
	 * @param orderType
	 *            生成订单日志类型ENUM
	 * @param remark
	 *            备注信息
	 * @param flag
	 *            成功1,失败2
	 * @throws Exception
	 */
	public void genOrderLogForSave(String operator, String orderNo,
			MethodCode methodCode, LogType logType, String remark, boolean flag);

	/**
	 * 订单支付成功后续处理
	 * 
	 * @param payState
	 *            支付状态
	 * @param payInfoId
	 *            在线支付订单ONLINE_PAY_INFO_ID
	 * @param tradeNo
	 *            子订单号(有可能也是交易号)
	 * @param onlinePayStyleNo
	 *            支付类型号
	 * @param payAmount
	 *            支付金额
	 * @throws Exception
	 */
	public Map paySuccessDeal(int payState, String payInfoId,
			String tradeNo, int onlinePayStyleNo, double payAmount)
			throws Exception;

	/**
	 * 
	 * 方法描述： 生成正常子订单日志与拆分子订单日志
	 * 
	 * @param user
	 * @param orderSubVo
	 * @throws Exception
	 *             void
	 * @throws
	 */
	public void genOrderLog(Weuser w, Orderinfo o) throws Exception;
	
	/***
	 * 订单日志(带分页)
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<OrderLog> getPageFindeOrderLog(OrderLog o , int pageNumber, int pageSize);
	
	/***
	 * 保存支付信息,支付订单号
	 * @param orderMainNo		主订单号
	 * @param amount			金额
	 * @param pay_type			支付方式:	1微信,2支付宝
	 */
	public String savePayInfo_thirdPay_orderNo(String orderMainNo,double amount,int pay_type);

	/***
	 * 查询支付信息,支付订单号
	 * @param payOrderNo		订单付款信息订单号
	 */
	public PayInfo_thirdPay_orderNo getPayInfo_thirdPay_orderNo(String payOrderNo);
	
}

