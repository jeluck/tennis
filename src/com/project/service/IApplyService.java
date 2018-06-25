package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Apply;

public interface IApplyService {
	
	/**
	 * 按条件查询
	 * @param o
	 * @return
	 */
	public List<Apply> getApplyByObj(Apply o);
	
	/**
	 * 保存
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Apply saveApply(Apply o) throws Exception;
	
	/**
	 * 删除
	 * @param o
	 * @throws Exception
	 */
	public void deleteApply(Apply o)throws Exception;
	
	
	public PageFinder<Apply> getPageFindeApply(Apply o, int pageNumber, int pageSize);
	
	public Apply getApplyById(int id);
	
	public Apply updateApply(Apply o) throws Exception;
}
