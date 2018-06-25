package com.project.service;

import java.util.List;

import com.project.pojo.SubsidiesSettlement;

public interface ISubsidiesSettlementService {

	/**
	 * 保存
	 * @param o
	 * @return
	 */
	public SubsidiesSettlement saveSubsidiesSettlement(SubsidiesSettlement o) throws Exception;
	
	
	/**
	 * 修改
	 * @param o
	 * @return
	 */
	public SubsidiesSettlement updateSubsidiesSettlement(SubsidiesSettlement o) throws Exception;
	
	
	/**
	 * 保存
	 * @param o
	 * @return
	 */
	public void saveSubsidiesSettlementByPdId(int zhe_id,double money,String orderMainNo,int type) throws Exception;
	
	
	/**
	 * 根据所属编号和类型查询
	 * @param zhe_id
	 * @param type
	 * @return
	 */
	public List<SubsidiesSettlement> subsidiesSettlementList(int zhe_id,String type);
	
}
