package com.project.service;


import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Red_bag_record;

/**
 * 红包记录Service
 * @author Administrator
 *
 */
public interface IRedBagRecordService {

	/**
	 * 保存红包记录信息
	 * @param red
	 * @return
	 * @throws Exception
	 */
	public Red_bag_record saveRedBagRecord(Red_bag_record red) throws Exception;
	
	
	/**
	 * 红包记录分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Red_bag_record> getPageFindeRedBagRecord(Red_bag_record red, int pageNumber, int pageSize);
	
	
	/**
     * 根据ID获得红包记录信息
     * @param oid
     * @return
     */
    public Red_bag_record getRedBagRecordById(int redId);
    
    /**
     * 根据用户和红包类型获得数据
     * @param userId
     * @param type
     * @return
     */
    public List<Red_bag_record> getRed_bag_recordByUserId(Integer userId,Integer type);
}
