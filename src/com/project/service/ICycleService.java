package com.project.service;

import java.util.List;

import com.project.pojo.Cycle;
import com.project.pojo.Space_time_price;

public interface ICycleService {
	
	/**
	 * 保存周期预订的信息
	 * @param c
	 * @return
	 */
	public Cycle saveCycle(Cycle c) throws Exception;
	
	/**
	 * 修改周期预订信息
	 * @param c
	 * @return
	 */
	public Cycle updateCycle(Cycle c) throws Exception;
	
	/**
	 * 获得所有周期预订的数据,传递场地时间价格数据进来，判断该场地是否被预订
	 * @return
	 */
	public boolean getAll(Space_time_price s);
	
	public void deleteByOrderMainNo(Cycle c);
	
	/**
	 * 根据订单号去数据
	 * @param orderMainNo
	 * @return
	 */
	public List<Cycle> getOrderMainNoCycle(String orderMainNo);
}
