package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.OrderMain;
import com.project.pojo.vo.OrderVo;


public interface IOrderMainService {
	
	/**
	 * 保存
	 * @param o
	 * @throws Exception
	 */
	public OrderMain saveOrderMain(OrderMain o) throws Exception;
	
	/**
	 * 更新主订单信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public OrderMain updateOrderMain(OrderMain o) throws Exception;
	
	/**
	 * 根据主订单号查找主订单
	 * @param ordderMainNo
	 * @return
	 */
	public OrderMain getOrderMainByNO(String orderMainNo);
	
	/**
	 * 根据用户ID和支付状态查找订单数
	 * @return
	 */
	public Integer getOrderMainByUserId_payStatus_size(int userId,int payStatus);
	
	/**
	 * 根据用户ID获得主订单和子订单
	 * @param weuserId
	 * @return
	 */
	public PageFinder<OrderMain> getOrderMainByWeuserId(Integer payStatus,int weuserId,int pageNumber, int pageSize);
	
	
	 /** 
     * 未支付1;  部分支付 2;  已支付 3;  执行中4;	已完成5;		退款申请中 6;  部分退款 7;  全额退款 8;  退款失败9;
	 * @param payStatus
	 * @return
	 */
	public List<OrderMain> getOrderMainListBy_payStatus(int payStatus);
	
	/**
	 * 查找 已支付 3;  执行中4;少于当前时间的订单
	 * @return
	 */
	public List<OrderMain> getOrderMainListByCondition();	
	
	/**
	 * 根据用户ID和主订单ID查询数据，并在用户金额减值，修改订单的状态
	 * @param mainId
	 * @param userId
	 * @param activeId 场馆ID,教练ID,活动ID,培训ID
	 * @return
	 */
	public boolean pay(Integer mainId,Integer userId,Integer playgroundId,Integer caochId,Integer pay_type);
	
	/**
	 * 根据ID获取数据
	 * @param id
	 * @return
	 */
	public OrderMain getById(Integer id);
	
	/**
	 * 根据用户ID获得订单信息
	 * @param weuserId
	 * @return
	 */
	public PageFinder<OrderMain> getOrderMain(int type,OrderMain o,int weuserId,int pageNumber, int pageSize);
	
	/**
	 * 根据主订单id删除信息
	 * @param orderMainid
	 * @param userId
	 * @return
	 */
	public OrderMain cancelOrder(Integer orderMainid, Integer userId);
	
	/**
	 *  根据用户ID获得订单数量
	 * @param type
	 * @param o
	 * @param weuserId
	 * @return
	 */
	public List<OrderMain> getOrderMainList(int type,int weuserId);
	
	
	/**
	 * 根据用户ID和状态获得订单数量
	 * @param type
	 * @param o
	 * @param weuserId
	 * @return
	 */
	public List<OrderMain> getOrderMainByStatus(int status,int weuserId);
	
	/**
	 * 根据用户ID和状态获得订单（分页）
	 * @param type
	 * @param o
	 * @param weuserId
	 * @return
	 */
	public PageFinder<OrderMain> getPageOrderMainByStatus(int status,OrderMain o,int weuserId,int pageNumber, int pageSize);
	
	/**
	 * 查询课时 
	 * @param type 0.全部  1.本周 2.本月 
	 * @param coachId
	 * @return
	 */
	public List<OrderMain> getOrderMainListByActiveID(int type,int activeId);
	
	
	/**
	 * 按周期查询和用户编号查询 
	 * @return
	 */
	public List<OrderMain> getOrderMainListByCycle(Map<?,?> map);
	
	/**
	 * 取消订单，将订单状态改为作废
	 * @param orderMainNo
	 */
	public void cancelOrderMain(String orderMainNo);
	
	/**
	 * 取消订单，返回用户用的钱
	 * @param orderMainNo
	 */
	public boolean cancelOrderUserMoeny(String orderMainNo);
	
	/**
	 * 按OrderMainVo查询主订单 
	 * @return
	 */
	public List<OrderMain> getOrderMainListByOrderMainVo(OrderVo o);
	
	/**
	 * 根据主订单id改订单为现场支付
	 * @param om
	 * @return
	 */
	public OrderMain livePayOrder(OrderMain om);
}
