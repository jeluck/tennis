package com.project.service;

import java.util.List;

import com.project.pojo.ReturnReason;

public interface IReturnReasonService {

	/**
	 * 查询集合
	 * @param o
	 * @return
	 */
	public List<ReturnReason> getReturnReasonlistByObj(ReturnReason o);
	
	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ReturnReason saveReturnReason(ReturnReason o)throws Exception;
	
}
