package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundUser;
import com.project.pojo.Weuser;

public interface IPlaygroundUserService {

	/**
	 * 获得所有场馆用户表
	 * @return
	 */
	 public List<PlaygroundUser> getAllPlaygroundUser();

	 /***
     * 查找场馆用户表
     * @param userId
     * @param playgroundId
     * @return
     */
    public PlaygroundUser getPlaygroundUser(int userId,int playgroundId);
	 
	 /***
     * 增加场馆用户表
     * @param c
     * @return
     */
    public PlaygroundUser savePlaygroundUser(PlaygroundUser o);
    
    /**
     * 根据playgroundid获得场馆用户表用户
     * @param id
     * @return
     */
    public   List<PlaygroundUser>  getPlaygroundUserByPlaygroundId(int playgroundId);
    
    /**
     * 获得场馆用户表列表(带分页)
     * @param c
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public  PageFinder<PlaygroundUser> getPlaygroundUserList(PlaygroundUser o, int pageNumber, int pageSize );
    
    /**
     * 根据用户编号获得场馆用户表
     * @param userId
     * @return
     */
    public  List<PlaygroundUser> getPlaygroundUserByUserId(int userId);
    
    /**
     * 先查找有无对应的数据,没有则插入场馆用户表
     * @param userId
     * @return
     */
    public PlaygroundUser getOrSavePlaygroundUser(Weuser user,Playground playground);
    
}
