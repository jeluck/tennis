package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Events;
import com.project.pojo.Video;

public interface IVideoService {
	/**
	 * 保存视频信息
	 * @param a
	 * @return
	 */
	public Video saveVideo(Video e) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Video> getPageFindeVideo(Video o, int pageNumber, int pageSize);
	
	/**
	 * 更新视频信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Video updateVideo(Video o) throws Exception;
	
	/**
     * 根据ID获得视频数据
     * @param oid
     * @return
     */
    public Video getVideoById(int oid);
}
