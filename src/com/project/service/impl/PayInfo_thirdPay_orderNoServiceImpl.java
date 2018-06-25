package com.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.IPayInfo_thirdPay_orderNoDao;
import com.project.pojo.PayInfo_thirdPay_orderNo;
import com.project.service.IPayInfo_thirdPay_orderNoService;

@Service
public class PayInfo_thirdPay_orderNoServiceImpl implements IPayInfo_thirdPay_orderNoService {

	@Resource
	private IPayInfo_thirdPay_orderNoDao payInfo_thirdPay_orderNoDao; 
	
	@Override
	public PayInfo_thirdPay_orderNo savePayInfo(PayInfo_thirdPay_orderNo o) throws Exception {
		return payInfo_thirdPay_orderNoDao.saveObject(o);
	}

}
