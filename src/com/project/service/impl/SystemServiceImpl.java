package com.project.service.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.common.Settings;
import com.project.dao.IInternetInfoDao;
import com.project.dao.ISEOSettingDao;
import com.project.dao.ISysSettingDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.InternetInfo;
import com.project.pojo.SEOSetting;
import com.project.pojo.SysSetting;
import com.project.service.ISystemService;
import com.project.util.CommonUtil;
import com.project.util.MakeHTML;

@Service("systemServiceImpl")
public class SystemServiceImpl  implements ISystemService {

	@Resource
	private IInternetInfoDao internetInfoDao;
	@Resource
	private ISEOSettingDao seoSettingDao;
	@Resource
	private ISysSettingDao sysSettingDao;
	
	@Override
	public InternetInfo getInternetInfo() {
		CriteriaAdapter criteriaAdapter = internetInfoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		InternetInfo i =  internetInfoDao.queryObjectByCriteria(criteria);
		internetInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return i;
		
	}

	@Override
	public InternetInfo saveInternetInfo(InternetInfo internetInfo) throws Exception {
		internetInfo.setCreate_time(CommonUtil.getTimeNow());
		return internetInfoDao.saveObject(internetInfo);
	}

	@Override
	public void updateInternetInfo(InternetInfo internetInfo) {
		internetInfo.setUpdate_time(CommonUtil.getTimeNow());
		internetInfoDao.merge(internetInfo);
	}

	@Override
	public SEOSetting getSeoSettingInfo() {
		CriteriaAdapter criteriaAdapter = seoSettingDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		SEOSetting o =  seoSettingDao.queryObjectByCriteria(criteria);
		seoSettingDao.releaseHibernateSession(criteriaAdapter.getSession());
		return o;
	}

	@Override
	public SEOSetting saveSeoSettingInfo(SEOSetting o)  throws Exception {
		o.setCreate_time(CommonUtil.getTimeNow());
		return seoSettingDao.saveObject(o);
	}

	@Override
	public void updateSeoSettingInfo(SEOSetting setting) {
		setting.setUpdate_time(CommonUtil.getTimeNow());
		seoSettingDao.merge(setting);
	}
	
	public SysSetting getSysSetting() {
		CriteriaAdapter criteriaAdapter = sysSettingDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		SysSetting o =  sysSettingDao.queryObjectByCriteria(criteria);
		sysSettingDao.releaseHibernateSession(criteriaAdapter.getSession());
		return o;
	}
	
	@Override
	public SysSetting getSysSettingById(int oid) {
		CriteriaAdapter criteriaAdapter = sysSettingDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("id",oid));
		SysSetting o =  sysSettingDao.queryObjectByCriteria(criteria);
		sysSettingDao.releaseHibernateSession(criteriaAdapter.getSession());
		return o;
	}

	@Override
	public boolean updateSysEMail(SysSetting setting) {
		setting.setUpdate_time(CommonUtil.getTimeNow());
		sysSettingDao.merge(setting);
		//		return update_bool(setting, "sys_setting", false,"sms_signature","sms_cd_key","sms_password");
		return true;
	}

	@Override
	public boolean updateSysSms(SysSetting setting) {
		setting.setUpdate_time(CommonUtil.getTimeNow());
		//return update_bool(setting, "sys_setting", true,"sms_signature","sms_cd_key","sms_password","update_time","sms_key");
		sysSettingDao.merge(setting);
		return true;
	}

	@Override
	public void buildIndexHtml(HttpServletRequest request) throws IOException {
		MakeHTML ru = new MakeHTML();
		String filePath=request.getSession().getServletContext().getRealPath("/");		// 取得项目根目录
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";			//java获取根域名
		
		if(filePath!=null && filePath.indexOf("tomcat")>-1){
			String filePath2 = filePath.substring(0,filePath.indexOf("tomcat"));
			filePath2+=Settings.STATIC_TOMCAT_PATH+"\\webapps\\ROOT";
			System.err.println(filePath2);
		}
		
		ru.convert2Html(basePath+"index.do", "UTF-8", filePath + "/", "index.html");
		System.err.println(filePath);
		
	}

	@Override
	public void buildIndexHtml(String filePath, String path, String basePath)
			throws IOException {
		MakeHTML ru = new MakeHTML();
		if(filePath!=null && filePath.indexOf("tomcat")>-1){
			String filePath2 = filePath.substring(0,filePath.indexOf("tomcat"));
			filePath2+=Settings.STATIC_TOMCAT_PATH+"\\webapps\\ROOT";
			System.err.println(filePath2);
		}
		
		ru.convert2Html(basePath+"index.do", "UTF-8", filePath + "/", "index.html");
		System.err.println(filePath);
		System.err.println(path);
		System.err.println(basePath);
		
	}

}