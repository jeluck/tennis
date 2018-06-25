package com.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.pojo.Events_endInfo;
import com.project.service.IEvents_endInfoService;
import com.project.util.CommonUtil;

@Controller("adminevents_endInfoController")
@RequestMapping(value="/pgm")
public class Events_endInfoController extends BaseController{
	@Resource
	private IEvents_endInfoService events_endInfoService;
	
	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "events_endInfo")
	@RequestMapping(value = "toaddEvents_endInfo")
	public String toAddEvents_endInfo(HttpServletRequest request, ModelMap map,int eventsId){
		map.put("eventsId", eventsId);
		
		return "admin/events_endInfo/events_endInfo_add";
	}
	
	/**
	 * 添加操作
	 * @param request
	 * @param map
	 * @param a
	 * @return
	 */
	@UserRightAuth(menuCode = "events_endInfo")
	@RequestMapping(value = "addEvents_endInfo")
	public String addevents_endInfo(HttpServletRequest request, ModelMap map,Events_endInfo a){
		
		try {
			
			events_endInfoService.saveEvents_endInfo(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:events_endInfo_list.do?oid="+a.getEvents().getId();
	}
	
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "events_endInfo")
	@RequestMapping(value = "events_endInfo_list")
	public String events_endInfo_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Events_endInfo o,int oid) {
		CommonUtil.printHTTP(request);
		map.put("eventsId", oid);
		map.put("data_page",events_endInfoService.getPageFindeEvents_endInfo(o, pageNumber, Constants.BACKGROUND_PAGESIZE,oid));
		return "admin/events_endInfo/events_endInfo_list";
	}
	
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "events_endInfo")
	@RequestMapping(value = "toedit_events_endInfo")
	public String toEditevents_endInfo(HttpServletRequest request, ModelMap map,int oid,int eventsId){
		String check = request.getParameter("check");
		map.put("eventsId", eventsId);
		map.put("check", check);
		map.put("o", events_endInfoService.getEvents_endInfoById(oid));
		
		return "admin/events_endInfo/events_endInfo_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "events_endInfo")
	@RequestMapping(value = "edit_events_endInfo")
	public String editevents_endInfo(HttpServletRequest request, ModelMap map,Events_endInfo o){
		try {
			events_endInfoService.updateEvents_endInfo(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:events_endInfo_list.do?oid="+o.getEvents().getId();
	}
}
