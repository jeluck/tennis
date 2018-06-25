package com.project.service;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Suggestion;


public interface ISuggestionService {
	/**
	 * 获取建议列表
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Suggestion> getSuggestionList(int pageNumber, int pageSize);

	/**
	 * 新增建议信息
	 * @param s
	 * @return
	 */
	public Suggestion saveSuggestion(Suggestion s);

	/**
	 * 根据id获取建议信息
	 * @param id
	 * @return
	 */
	public Suggestion getSuggestionById(int id);

	/**
	 * 根据id更新建议的数据
	 * @param s
	 * @return
	 */
	public boolean updateSuggestionByid(Suggestion s);

	
	public void testDB();
	
	/***
	 * 删除建议
	 * @param id
	 * @return
	 */
	public boolean deleteSuggestionById(int id);
}
