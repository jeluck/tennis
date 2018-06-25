package com.project.wechat.service;

import com.project.wechat.model.pojo.WxPayResult;

public interface IWxPayResultService {

	/**
	 * 保存
	 * @param wx
	 * @return
	 */
	public WxPayResult savaWxPayResult(WxPayResult wx);
}
