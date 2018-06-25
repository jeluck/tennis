package com.project.service;


import java.util.List;
import java.util.Map;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Playground;

public interface IPlaygroundService {
	
	/**
	 * 保存
	 */
	public Playground savePlaygroundService(Playground o)  throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Playground> getPageFindePlayground(Playground o, int pageNumber, int pageSize);
	
	/**
     * 根据ID获得场馆数据
     * @param oid
     * @return
     */
    public Playground getPlaygroundById(int oid);
    
    
    /***
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Playground mergePlayground(Playground o) throws Exception;
	
	/**
	 * 获取到所有的场馆数据
	 * @return
	 */
	public List<Playground> getAll();
	
	/**
	 * 根据城市编号获取到所有的场馆数据
	 * @return
	 */
	public List<Playground> getPlaygroundList(int city_show_id,int stick,String name);
	
	/**
	 * 根据条件获取到所有的场馆数据
	 * @return
	 */
	public PageFinder<Playground> getPlaygroundListByMap(Map<?,?> map,int pageNumber, int pageSize);
	
	/**
	 * 根据电话判断场馆电话是否已存在
	 * @param phone
	 * @return
	 */
	public boolean checkPhone(String phone,int id);
	
	/**
	 * 根据场馆运营者id获得一个场馆
	 * @param managerId
	 * @return
	 */
	public Playground getByPlaygroundManagerId(Integer managerId);
	
	
	/**
	 * 根据场馆运营者id
	 * @return
	 */
	public List<Playground> getPlaygroundListByPlaygroundManagerId(Integer managerId);
	
	/**
	 * 查询
	 * @param o
	 * @return
	 */
	public List<Playground> getPlaygroundListByObj(Playground o);
}
