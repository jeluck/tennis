package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Module;

public interface IModuleService {

	/**
	 * 保存
	 * @param o
	 * @return
	 */
	public Module saveModule(Module o) throws Exception;
	
	
	/**
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Module upadateModule(Module o) throws Exception;
	
	/**
	 * 根据编号查询
	 * @param id
	 * @return
	 */
	public Module getModuleById(int id);
	
	
	/**
	 * 按状态条件的全部数据
	 * @param o
	 * @return
	 */
	public List<Module> getModuleListByStatus(int status);
	
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Module> getPageFindeModule(Module o,int status,int pageNumber, int pageSize);


}
