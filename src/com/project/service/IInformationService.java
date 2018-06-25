package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Information;

public interface IInformationService {
	
	/**
	 * 保存新闻资讯
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Information saveInformation(Information o) throws Exception;
	
	/**
	 * 删除新闻资讯
	 * @param id
	 * @throws Exception
	 */
	public void deleteInformationById(int id) throws Exception;
	
	/**
	 * 修改新闻资讯
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public Information updateInformation(Information o) throws Exception;
	
	/**
	 * 得到新闻资讯
	 * @param id
	 * @return
	 */
	public Information getInformationById(int id);
	
	/**
	 * 得到新闻资讯分页列表
	 * @param o
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Information> getPageFindeInformation(Information o, int pageNumber, int pageSize);
}
