package com.project.service;

import java.util.List;

import com.project.pojo.Dictionaries;

public interface IDictionariesService {

	/**
	 * 按类型查询
	 * @param type
	 * @return
	 */
	public List<Dictionaries> getDictionaries(int type);
	
	/**
	 * 按类型查询的数量
	 * @param type
	 * @return
	 */
	public int getDictionariesCount(int type);
	
	/**
	 * 保存
	 * @param o
	 * @return
	 */
	public Dictionaries saveDictionaries(Dictionaries o) throws Exception;
	
	
	
}
