package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.SpaceManager;

public  interface ISpaceManagerService{

	/***
	 * 根据货品ID查找货品信息
	 * @param commodityTypeID	货品ID
	 * @return
	 */
    public SpaceManager getSpaceManagerByID(int commodityTypeID);
    
	/***
	 * 根据货品编号查找货品信息
	 * @param commodityTypeNo	货品编号
	 * @return
	 */
    public SpaceManager getSpaceManagerByNo(String commodityTypeNo);
    
    /***
     * 获得所有货品规格表的数据
     * @return
     */
    public List<SpaceManager> getSpaceManagerList();
    
	/**
	 * 获得货品规格信息(带分页)
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<SpaceManager> getSpaceManagerList(SpaceManager o, int pageNumber, int pageSize );
	
	 /***
     * 增加货品规格信息
     * @param c
     * @return
     */
    public SpaceManager saveSpaceManager(SpaceManager o);
    
    /***
     * 更新货品规格信息
     * @param c
     * @return
     */
    public SpaceManager updateSpaceManager(SpaceManager o);
    
    /**
     * 根据id获得货品规格信息
     * @param id
     * @return
     */
    public SpaceManager getSpaceManagerById(int id);
    
    /**
     * 删除
     * @param o
     */
    public void removeSpaceManager(SpaceManager o) throws Exception ;
}
