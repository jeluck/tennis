package com.project.service;


import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.PlaygroundManager;

public interface IPlaygroundManagerService {
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<PlaygroundManager> getPageFindePlaygroundManager(PlaygroundManager o, int pageNumber, int pageSize);
	
	/**
	 * 保存
	 * @return 
	 */
	public PlaygroundManager savePlaygroundManager(PlaygroundManager o)  throws Exception;
	
	/***
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public PlaygroundManager mergePlaygroundManager(PlaygroundManager o) ;
	
    /**
     * 登录
     * @param o
     * @return
     */
	public PlaygroundManager playgroundmanagerlogin(PlaygroundManager o) ;
	
    /**
     * 根据ID获得场馆管理者数据
     * @param oid
     * @return
     */
    public PlaygroundManager getPlaygroundManagerById(int oid);
    
    /**
     * 获得所有可用场馆管理者数据的列表
     */
    public List<PlaygroundManager> getPlaygroundManagerListBy_pg_manager_type_Flag();
    
    /**
     * 根据手机和编号判断手机是否存在
     * @param mobile
     * @param id
     * @return
     */
    public boolean checkMobile(String mobile,int id);
    
    /**
     * 根据手机找数据
     * @param mobile
     * @param id
     * @return
     */
    public PlaygroundManager getPGM(String mobile);
    
    /**
     * 根据用户名和编号判断用户名是否存在
     * @param name
     * @param id
     * @return
     */
    public boolean checkName(String name,int id);
    
    /**
     * 获得所有场馆管理者
     * @return
     */
    public List<PlaygroundManager> getAll();
    
    /**
     * 根据ID获得场馆管理者数据
     * @param oid
     * @return
     */
    public PlaygroundManager getPlaygroundManagerByCoachId(int coachId);
    
}
