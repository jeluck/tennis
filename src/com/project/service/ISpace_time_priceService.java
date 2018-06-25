package com.project.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.project.pojo.Space_time_price;

public interface ISpace_time_priceService {
	
	/**
	 * 保存数据
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public Space_time_price saveSpace_time_price(Space_time_price s) throws Exception;
	
	/**
	 * 根据对象删除数据
	 * @param day
	 */
	public void deleteStp(Space_time_price stp)throws Exception;
	
	/**
	 * 根据场地ID和时间得到数据
	 * @param strat
	 * @param end
	 * @return
	 */
	public List<Space_time_price> findByDate(int spaceId,String date);
	
	/**
	 * 根据ID获取数据
	 * @param Id
	 * @return
	 */
	public Space_time_price findById(Integer Id);
	
	/**
	 * 修改场地价格时间的数据
	 * @param s
	 * @return
	 */
	public Space_time_price updatespace_time_price(Space_time_price s);
	
	/**
	 * 根据场地ID和时间和时间点得到数据
	 * @param strat
	 * @param end
	 * @return
	 */
	public Space_time_price findByDate(int spaceId,String date,Integer timepoint);
	
	public List<Space_time_price> findBySpaceId(int spaceId);
	
	
	/**
	 * 根据场地ID和时间点得到数据
	 * @param strat
	 * @param end
	 * @return
	 */
	public List<Space_time_price> findByTime(int spaceId,int time,int dataType);
}
