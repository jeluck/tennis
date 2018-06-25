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
import com.project.pojo.Activity;
import com.project.pojo.Coach;
import com.project.pojo.Manager;
import com.project.pojo.PlaygroundManager;
import com.project.service.IActivityService;
import com.project.util.CommonUtil;

@Controller("adminactivityController")
@RequestMapping(value="/pgm")
public class ActivityController extends BaseController {
	
	@Resource
	private IActivityService activityService;
	
	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "activity")
	@RequestMapping(value = "toaddActivity")
	public String toAddActivity(HttpServletRequest request, ModelMap map){
		return "admin/activity/activity_add";
	}
	
	/**
	 * 添加操作
	 * @param request
	 * @param map
	 * @param a
	 * @return
	 */
	@UserRightAuth(menuCode = "activity")
	@RequestMapping(value = "addActivity")
	public String addActivity(HttpServletRequest request, ModelMap map,Activity a){
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		if(pgm!=null){
			a.setPlaygroundmanager_id(pgm.getId());
			a.setAuthod_type(Constants.AUTHOD_TYPE.PLAYGROUNDMANAGER.getStatus());
		}
		try {
			if(m!=null){
				a.setManager_id(m.getId());
				a.setAuthod_type(Constants.AUTHOD_TYPE.MANAGER.getStatus());
			}
			
			activityService.saveActivity(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:activity_list.do";
	}
	
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "activity")
	@RequestMapping(value = "activity_list")
	public String playground_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Activity o) {
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		if(pgm!=null){
			o.setPlaygroundmanager_id(pgm.getId());
		}
		map.put("data_page",activityService.getPageFindeActivity(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/activity/activity_list";
	}
	
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "activity")
	@RequestMapping(value = "toedit_activity")
	public String toEditActivity(HttpServletRequest request, ModelMap map,int oid){
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", activityService.getActivityById(oid));
		
		return "admin/activity/activity_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "activity")
	@RequestMapping(value = "edit_activity")
	public String editActivity(HttpServletRequest request, ModelMap map,Activity o){
		Activity a = activityService.getActivityById(o.getId());
		try {
			o.setPlayground_id(a.getPlayground_id());
			o.setPlaygroundManager(a.getPlaygroundManager());
			o.setPlaygroundmanager_id(a.getPlaygroundmanager_id());
			o.setAuthod_type(a.getAuthod_type());
			activityService.updateActivity(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:activity_list.do";
	}
}