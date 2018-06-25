package com.project.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.project.pojo.InternetInfo;
import com.project.pojo.SEOSetting;
import com.project.pojo.SysSetting;


/**
 * 系统管理
 * @author daybreak
 *
 */
public interface ISystemService {
	
	/**
	 * 得到站点信息
	 * @return
	 */
	public InternetInfo getInternetInfo();
	/**
	 * 保存站点信息
	 * @param internetInfo
	 * @return
	 */
	public InternetInfo saveInternetInfo(InternetInfo internetInfo) throws Exception ;
	/**
	 * 更新站点信息
	 * @param internetInfo
	 */
	public void updateInternetInfo(InternetInfo internetInfo);
	/**
	 * 得到SEO设置信息
	 * @return
	 */
	public SEOSetting getSeoSettingInfo();
	/**
	 * 保存SEO设置信息
	 * @param setting
	 * @return
	 */
	public SEOSetting saveSeoSettingInfo(SEOSetting setting)  throws Exception ;
	/**
	 * 更新SEO设置信息
	 * @param setting
	 */
	public void updateSeoSettingInfo(SEOSetting setting);
	
	//得到系统设置
	public SysSetting getSysSetting();
	
	//根据ID得到系统设置
	public SysSetting getSysSettingById(int oid);
	
	//更新系统设置
	public boolean updateSysEMail(SysSetting setting);
	//更新系统设置
	public boolean updateSysSms(SysSetting setting);
	
		/***
	 * 更新首页为静态页
	 * @param request
	 */
	public void buildIndexHtml(HttpServletRequest request)  throws IOException ;	
	/***
	 * 更新首页为静态页
	 * @param request
	 */
	public void buildIndexHtml(String filePath,String path,String basePath)  throws IOException ;
}
