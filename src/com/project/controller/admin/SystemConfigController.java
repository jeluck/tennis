package com.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.auth.NoLoginAuth;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.pojo.SystemConfig;
import com.project.service.ISystemConfigService;

@Controller("systemConfigWebController")
@RequestMapping(value="/admin")
public class SystemConfigController extends BaseController{
	
	@Resource
	private ISystemConfigService systemConfigService;
	
	@NoLoginAuth
	@RequestMapping(value = "systemConfiglist")
	public String systemConfig_list(@RequestParam(defaultValue="1")int pageNumber,SystemConfig sc,HttpServletRequest request,ModelMap map) {
		
		map.put("data_page", systemConfigService.getSystemConfigList(pageNumber, Constants.BACKGROUND_PAGESIZE,sc));
		map.put("configName", sc.getConfigName());
		map.put("key", sc.getKey());
		System.err.println(sc.getConfigName());
		System.err.println(sc.getKey());
		return "admin/systemConfig/systemConfig_list";
	}
	
	
	@RequestMapping(value = "toaddsystemConfig", method = RequestMethod.GET)
	public String toaddsystemConfig(HttpServletRequest request,ModelMap map) {
		return "admin/systemConfig/add_systemConfig";
	}
	
	/***
	 * 添加系统配置
	 * @param request
	 * @param map
	 * @param systemConfig
	 * @return
	 */
	@RequestMapping(value = "addsystemConfig", method = RequestMethod.POST)
	public String addsystemConfig(HttpServletRequest request,ModelMap map,SystemConfig systemConfig) {
		try {
			systemConfigService.addSystemConfig(systemConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:systemConfiglist.do";
	}
	
	@RequestMapping(value = "toeditsystemConfig")
	public String toeditsystemConfig(HttpServletRequest request,ModelMap map,int id) {
		map.put("s", systemConfigService.getSystemConfigById(id));
		return "admin/systemConfig/edit_systemConfig";
	}
	
	@RequestMapping(value = "editsystemConfig", method = RequestMethod.POST)
	public String editsystemConfig(HttpServletRequest request,ModelMap map,SystemConfig systemConfig) {
		try {
			systemConfigService.addSystemConfig(systemConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:systemConfiglist.do";
	}
	
	@RequestMapping(value = "closesystemConfig")
	public String closeSystemConfig(HttpServletRequest request,ModelMap map,int id) {
		try {
			systemConfigService.closeSystemConfig(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:systemConfiglist.do";
	}
	
	@RequestMapping(value = "useConfig")
	public String useConfig(HttpServletRequest request,ModelMap map,int id) {
		try {
			systemConfigService.useConfig(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:systemConfiglist.do";
	}
}

