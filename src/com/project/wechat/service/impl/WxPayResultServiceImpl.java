package com.project.wechat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.wechat.dao.IWxPayResultDao;
import com.project.wechat.model.pojo.WxPayResult;
import com.project.wechat.service.IWxPayResultService;

@Service
public class WxPayResultServiceImpl implements IWxPayResultService{

	@Resource
	private IWxPayResultDao wxPayResultDao;

	@Override
	@Transactional
	public WxPayResult savaWxPayResult(WxPayResult wx) {
		try {
			return wxPayResultDao.saveObject(wx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
