package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.vo.OrderVo;

public interface IOrderInfoService {
	
	/***
	 * 保存
	 * @param o
	 * @throws Exception
	 */
	public void saveOrderInfoService(Orderinfo o) throws Exception ;
	
	/***
	 * 修改订单
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Orderinfo mergeOrderInfo(Orderinfo o) throws Exception;
	
	/**
	 * 根据子订单ID确认订单
	 * @param id			子订单ID
	 * @throws Exception
	 */
    public void acceptOrder(int id) throws Exception;

    
    /**
     * 根据ID获得子订单(不带级联)
     * @param oid
     * @return
     */
    public Orderinfo getOrderById(int oid);
    
    /**
     * 根据子订单号获得子订单(不带级联)
     * @param orderSubNo
     * @return
     */
    public Orderinfo getOrderByorderSubNo(String orderSubNo);
    
    
    /***
     * 根据条件查找子订单(带分页)
     * @param tradeno
     * @param startdate
     * @param enddate
     * @param baseStatus
     * @param payStatus
     * @param deliveryStatus
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageFinder<Orderinfo> orderList(String tradeno, String startdate, String enddate, int baseStatus, int payStatus, int deliveryStatus, int userId, int pageNumber, int pageSize,int playgroundId,int playgroundManagerId,int is_back);
    
    /**
     * 根据主订单id查找子订单数据
     * @param orderMainId		主订单id
     * @return
     */
    public List<Orderinfo> getOrderInfoListByOrderMainId(String orderMainId);
    
    /**
     * 根据时间年月日和时间点查询数据
     * @param create_date		创建日期(格式2015-01-01)
     * @param today_time		当天时间(格式9,10,11,12)
     * @param orderStauts		未支付1;  部分支付 2;  已支付 3;  执行中4;	已完成5;		退款申请中 6;  部分退款 7;  全额退款 8;  退款失败9;  作废10;
     * @return
     */
    public List<Orderinfo> getOrderInfoByTime(String create_date,String today_time,Integer orderStauts);
    
    /**
     * 执行订单的退款
     * @param orderInfoId
     * @param userId
     * @return
     */
    public Orderinfo cancelOrder(Integer orderInfoId,Integer userId);
    
    /**
     * 根据用户编号 类型 类型编号 查询              
     * @param userId
     * @param activeID
     * @param orderType
     * @return
     */
    public Orderinfo getOrderinfoByUserIdAndActiveID(int userId,String activeID,int orderType);
    
    public List<Orderinfo> getOrderInfoByCreate(String create_date,int palygroundMangerId);
    
    public List<Orderinfo> getOrderinfoByObj(Orderinfo o,Map<?,?> map);
    
    /**
	 * 按OrderVo查询主订单 
	 * @return
	 */
	public List<Orderinfo> getOrderinfoListByOrderVo(OrderVo o);
	 
	public List<Orderinfo> getOrderinfoByObj(Orderinfo o);

	/**
	 * 按当天查询 
	 * @return
	 */
	public List<Orderinfo> getOrderinfoListByToday(int userId);
	
	/**
	 * 按当天查询 
	 * @return
	 */
	public List<Orderinfo> getOrderinfoListByTodayactiveID(String activeID);
}
