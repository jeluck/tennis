package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Red_bag;

/**
 * 红包 Service
 * @author Administrator
 *
 */
public interface IRedBagService {

	/**
	 * 保存红包信息
	 * @param red
	 * @return
	 * @throws Exception
	 */
	public Red_bag saveRedBag(Red_bag red) throws Exception;
	
	/**
	 * 更新红包信息
	 * @param red
	 * @return
	 * @throws Exception
	 */
	public Red_bag updateRedBag(Red_bag red) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Red_bag> getPageFindeRedBag(Red_bag red, int pageNumber, int pageSize);
	
	/**
	 * 全部红包信息
	 * @return
	 */
	public List<Red_bag> getAllRedBag();
	
	/**
     * 根据ID获得红包信息
     * @param oid
     * @return
     */
    public Red_bag getRedBagById(int redId);
}
