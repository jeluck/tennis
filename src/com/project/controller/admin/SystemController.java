package com.project.controller.admin;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.auth.UserRightAuth;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.pojo.InternetInfo;
import com.project.pojo.SEOSetting;
import com.project.pojo.SysSetting;
import com.project.service.ISystemService;
import com.project.util.SMSClient;
import com.project.util.SMSUtil;
import com.project.util.SMSClient.SMSRegResultType;


@Controller("adminSystemController")
@RequestMapping(value="/admin")
public class SystemController extends BaseController{
	
	@Resource
	private ISystemService systemService;
	
		
	/**
	 * 短信邮箱信息
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode="sys_setting")
	@RequestMapping(value="sys_setting.do", method = RequestMethod.GET)
	public String sys_setting(ModelMap map){
		map.put("setting", systemService.getSysSetting());
		return "admin/system/sys_setting";
	}
	
	/**
	 * 系统邮箱设置
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@UserRightAuth(menuCode="sys_setting")
	@RequestMapping(value="update_sys_email.do", method = RequestMethod.POST)
	@ResponseBody
	public Object update_sys_setting(SysSetting setting){
		SysSetting o = systemService.getSysSettingById(setting.getId());
		setting.setSms_cd_key(o.getSms_cd_key());//设置数据
		setting.setSms_key(o.getSms_key());//设置数据
		setting.setSms_password(o.getSms_password());//设置数据
		setting.setSms_signature(o.getSms_signature());	//设置数据
		systemService.updateSysEMail(setting);
		Settings.EML_ACCOUNT = setting.getEml_account();
		Settings.EML_PASSWORD = setting.getEml_password();
		Settings.EML_HOST = setting.getEml_host();
		Settings.EML_PORT = setting.getEml_port();
		return pushmsg("系统邮箱设置已生效.", true);
	}
	
	/**
	 * 系统短信设置
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode="sys_setting")
	@RequestMapping(value="update_sys_sms.do", method = RequestMethod.POST)
	@ResponseBody
	public Object update_sys_sms(SysSetting setting){
		SysSetting o = systemService.getSysSettingById(setting.getId());
		setting.setEml_account(o.getEml_account());//设置数据
		setting.setEml_host(o.getEml_host());//设置数据
		setting.setEml_password(o.getEml_password());//设置数据
		setting.setEml_port(o.getEml_port());	//设置数据
		
		if(setting.getSms_cd_key().equals(SMSUtil.CD_KEY)){//如果新的cd_key和原来的cd_key相等就只更新签名字段
			setting.setSms_key(SMSUtil.SMS_KEY);
			setting.setSms_password(SMSUtil.PASSWORD);
			SMSUtil.SMS_SIGNATURE = setting.getSms_signature();
			System.err.println("-------------"+SMSUtil.SMS_SIGNATURE);
			systemService.updateSysSms(setting);
			return pushmsg("系统短信设置已生效.", true);
		}else{//如果新的cdkey原来的cdkey不一样，则需要把cdkey注册一下，注册成功后方可使用
			int smsreg = SMSClient.getClient(setting.getSms_cd_key(), setting.getSms_key()).registEx(setting.getSms_password());
			System.err.println("smsreg"+smsreg+" cd_key:"+setting.getSms_cd_key()+" sms_key:"+setting.getSms_key()+" paw:"+setting.getSms_password());
			if((smsreg+"").equals(SMSRegResultType.SUCESS.getType())){
				systemService.updateSysSms(setting);
				SMSUtil.SMS_SIGNATURE = setting.getSms_signature();
				SMSUtil.CD_KEY = setting.getSms_cd_key();
				SMSUtil.PASSWORD = setting.getSms_password();
				SMSUtil.SMS_KEY = setting.getSms_key();
				return pushmsg("系统短信设置已生效.", true);
			}else{
				return pushmsg(SMSRegResultType.typeOf(smsreg+"").getDesc(), false);
			}
		}
	}
	
	/**
	 * 得到站点信息
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode="internet_info")
	@RequestMapping(value="internet_info.do", method = RequestMethod.GET)
	public String internet_info(ModelMap map,HttpServletRequest request){
		InternetInfo internetInfo = systemService.getInternetInfo();
		if(internetInfo==null){//不存在则创建
			internetInfo = new InternetInfo();
			try {
				internetInfo.setId(systemService.saveInternetInfo(internetInfo).getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("internet_info", internetInfo);
		return "admin/system/internet_info";
	}

	/**
	 * 更新站点信息
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode="internet_info")
	@RequestMapping(value="internet_update.do", method = RequestMethod.POST)
	@ResponseBody
	public Object internet_update(InternetInfo internetInfo,HttpServletRequest request){
		systemService.updateInternetInfo(internetInfo);
		request.getServletContext().setAttribute("company",internetInfo);
		return pushmsg("站点信息设置已生效.", true);
	}
	
	/**
	 * 得到站点SEO信息
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode="seosetting_info")
	@RequestMapping(value="seosetting_info.do", method = RequestMethod.GET)
	public String seosetting_info(ModelMap map,HttpServletRequest request){
		SEOSetting setting = systemService.getSeoSettingInfo();
		if(setting==null){//不存在则创建
			setting = new SEOSetting();
			try {
				setting.setId(systemService.saveSeoSettingInfo(setting).getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("setting", setting);
		return "admin/system/seo_setting";
	}

	/**
	 * 更新站点SEO信息
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode="seosetting_info")
	@RequestMapping(value="seosetting_update.do", method = RequestMethod.POST)
	@ResponseBody
	public Object seosetting_update(SEOSetting setting,HttpServletRequest request){
		systemService.updateSeoSettingInfo(setting);
		request.getServletContext().setAttribute("internet",setting);
		return pushmsg("SEO设置信息已生效.", true);
	}
	
	/**
	 * 更新首页为静态页
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode="seosetting_info")
	@RequestMapping(value="buildIndexHtml.do", method = RequestMethod.POST)
	@ResponseBody
	public Object buildIndexHtml(HttpServletRequest request){

		try {
			systemService.buildIndexHtml(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return pushmsg("更新首页为静态页失败.", false);
		}
		return pushmsg("更新首页为静态页已生效.", true);
	}
	

	
}

