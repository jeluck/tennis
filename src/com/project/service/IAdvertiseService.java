package com.project.service;

import java.util.List;

import com.project.pojo.Advertise;

public interface IAdvertiseService {

	/**
	 * 按类型获取广告数据 1首页,2场馆列表,3网球圈,4赛事,5首页中间悬浮广告
	 * @param type
	 * @return
	 */
	public List<Advertise> getAdvertiseByType(int type);
}
