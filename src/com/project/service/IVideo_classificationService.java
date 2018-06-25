package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Video;
import com.project.pojo.Video_classification;

public interface IVideo_classificationService {
	/**
	 * 保存视频分类信息
	 * @param a
	 * @return
	 */
	public Video_classification saveVideo_classification(Video_classification e) throws Exception;
	
	/**
	 * 分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Video_classification> getPageFindeVideo_classification(Video_classification o, int pageNumber, int pageSize);
	
	/**
	 * 更新视频分类信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Video_classification updateVideo_classification(Video_classification o) throws Exception;
	
	/**
     * 根据ID获得视频分类数据
     * @param oid
     * @return
     */
    public Video_classification getVideo_classificationById(int oid);
    
    /**
     * 获得所有视频分类的信心
     * @return
     */
    public List<Video_classification> getAll();
}
