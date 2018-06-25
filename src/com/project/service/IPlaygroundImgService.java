package com.project.service;


import java.util.List;

import com.project.pojo.PlaygroundImg;

public interface IPlaygroundImgService {

	/**
	 * 保存场馆图片
	 * @param o
	 * @return
	 */
	public PlaygroundImg savePlaygroundImg(PlaygroundImg o) throws Exception;
	
	/**
	 * 根据场馆编号查询场馆图片数据
	 * @param pdId
	 * @return
	 */
	public PlaygroundImg getPlaygroundImgByPdIb(int pdId);
	
	/**
	 * 根据ID获取所有场馆图片的信息
	 * @param pdId
	 * @return
	 */
	public List<PlaygroundImg> getPlaygoundImgListByPdId(int pdId);
}
