package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Activity;
import com.project.pojo.Region;

/**
 * 
 * @author daybreak
 *
 */
public interface IRegionService {
	
	/**
	 * 获得省份
	 * @return
	 */
	public List<Region> getProvince();
	
	/**
	 * 获得省份分页
	 * @return
	 */
	public PageFinder<Region> getProvince(int pageNumber, int pageSize);
    
	/**
	 * 获得省份对应下的城市
	 * @param provinceid
	 * @return
	 */
	public List<Region> getCity(String provinceid);
	
	/**
	 * 获得省份对应下的城市分页
	 * @param provinceid
	 * @return
	 */
	public PageFinder<Region> getCity(int provinceid, int pageNumber, int pageSize);

	/***
	 * 获得城市对应下的区
	 * @param cityid
	 * @return
	 */
	public List<Region> getArea(String cityid);
	
	/***
	 * 获得城市对应下的区分页
	 * @param cityid
	 * @return
	 */
	public PageFinder<Region> getArea(int cityid, int pageNumber, int pageSize);
	
	/***
	 * 根据ID获得数据
	 * @param regionId
	 * @return
	 */
	public Region getRegionById(int regionId);
	
	/**
	 * 修改数据
	 * @param r
	 * @return
	 */
	public Region updateRegion(Region r);
	
	public List<Region> getRegionByStatus();
	
	/**
	 * 根据城市名称获得城市编号
	 * @param cityName
	 * @return
	 */
	public Region getRegionByCityName(String cityName);
	
	
	/**
	 * add by lxc	2015-11-28
	 * 保存对象
	 * @param r
	 * @return
	 */
	public Region saveRegion(Region r);
}
