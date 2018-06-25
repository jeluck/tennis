package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Subsidies;

public interface ISubsidiesService {
	
	/**
	 * 保存
	 * @param o
	 * @return
	 */
	public Subsidies saveSubsidies(Subsidies o) throws Exception;
	
	/**
	 * 根据编号查询
	 * @param id
	 * @return
	 */
	public Subsidies getSubsidiesById(int id);
	
	
	/**
	 * 根据场馆编号或者教练编号查询
	 * @param id
	 * @return
	 */
	public Subsidies getSubsidiesByPdId(int id,int zhe_type);
	
	
	/**
	 * 修改
	 * @param o
	 * @return
	 */
	public Subsidies updateSubsidies(Subsidies o);
	
	
	/**
	 * 查询补贴
	 * @param o
	 * @return
	 */
	public List<Subsidies> getSubsidiesByObj(Subsidies o);
	
	/**
	 * 删除
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public  void deleteSubsidies(Subsidies o) throws Exception;
	
	
	public PageFinder<Subsidies> getPageFindeSubsidies(Subsidies o,
			int pageNumber, int pageSize);
	
	
	/**
	 * 根据map和obj设置条件查询补贴
	 * @param o
	 * @param map
	 * @return
	 */
	public List<Subsidies> getSubsidiesByMapAndObj(Subsidies o,Map<?,?> map);
}
