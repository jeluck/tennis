package com.project.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;

public interface ICoachService {
	
	/**
	 * 保存
	 * @param o
	 * @throws Exception
	 */
	public Coach saveCoach(Coach o) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Coach> getPageFindeCoach(Coach o, int pageNumber, int pageSize);
	
	/**
     * 根据ID获得教练数据
     * @param oid
     * @return
     */
    public Coach getcoachById(int oid);
    
    /***
	 * 修改
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Coach mergePlayground(Coach o) throws Exception;
	
	/**
     * 根据场馆ID获得教练数据
     * @param oid 场馆ID
     * @return
     */
    public List<Coach> getcoachByPlayId(int oid);
    
    /**
     * 根据ID获得教练数据（此方法能缓存对象）
     * @param oid
     * @return
     */
    public Coach getcoachByIdCacheObject(int oid,int coachType);
    
    /**
     * 获取全部教练
     * @return
     */
    public List<Coach> getCoachList(int now_live_city,int stick,String name);
    
    
    /**
     * 按条件分页查询教练
     * @param map
     * @return
     */
    public PageFinder<Coach> getPageCoachListByMap(Map<?,?> map,int pageNumber, int pageSize);
    
    /**
	 * 根据电话判断教练电话是否已存在
	 * @param phone
	 * @return
	 */
	public boolean checkPhone(String phone,int id);
	
	
	/**
     * 根据前端传的ID,找当前登录者,并且保存到session
     * @param request
     * @param o
     * @return
     */
	public Coach getLonginByHttpServletRequest_Id(HttpServletRequest request,Coach o,Class c);
	
	/**
     * 获取全部教练和用户
     * @return
     */
    public List<Coach> getCoachAndUserList(int userId,int now_live_city,int stick);
    
    /***
     * 根据用户ID,找教练
     * @param userId
     * @return
     */
    public Coach getCoachByUserId(int userId);
    
    /**
     * 根据场馆ID和教练类型查询数据
     * @param playground_id
     * @param coachType
     * @return
     */
    public List<Coach> getCoachBygroundAndType(Integer playground_id,Integer coachType);
    
    
    public List<Coach> getCoachByObj(Coach coach);
    
    /**
     * 根据phone获得教练数据
     * @param oid
     * @return
     */
    public Coach getcoachByphone(String phone);
    
    /**
     * 根据场馆运营者ID查询教练
     * @param pmId
     * @return
     */
    public List<Coach> getCoachByPm(Integer pmId);
}
