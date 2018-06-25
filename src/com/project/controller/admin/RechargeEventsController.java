package com.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.pojo.RechargeEvents;
import com.project.pojo.Red_bag;
import com.project.service.IRechargeEventsService;
import com.project.util.CommonUtil;


@Controller("rechargeEventsController")
@RequestMapping(value="/admin")
public class RechargeEventsController {
	
	@Resource
	private IRechargeEventsService rechargeEventsService;
	
	
	/**
	 * 充值活动列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param re
	 * @return
	 */
	@UserRightAuth(menuCode = "rechargeEvents")
	@RequestMapping(value = "rechargeEvents_list")
	public String rechargeEvents_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,RechargeEvents re) {
		CommonUtil.printHTTP(request);
		map.put("data_page",rechargeEventsService.getPageFindeRechargeEvents(re, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("o", re);
		return "admin/rechargeEvents/rechargeEvents_list";
	}
	
	/**
	 * 添加充值活动页面
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "rechargeEvents")	
	@RequestMapping(value = "toadd_rechargeEvents")
	public String toadd_rechargeEvents(HttpServletRequest request, ModelMap map){
		CommonUtil.printHTTP(request);
		return "admin/rechargeEvents/rechargeEvents_add";
	}
	
	/**
	 * 添加充值活动
	 * @param request
	 * @param map
	 * @param red
	 * @return
	 */
	@UserRightAuth(menuCode = "rechargeEvents")
	@RequestMapping(value = "add_rechargeEvents")
	public String addRedBag(HttpServletRequest request, ModelMap map,RechargeEvents re) {
		CommonUtil.printHTTP(request);
		try {
			rechargeEventsService.saveRechargeEvents(re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:rechargeEvents_list.do";
	}
	
	/**
	 * 删除
	 * @param request
	 * @param map
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	@UserRightAuth(menuCode = "rechargeEvents")
	@RequestMapping(value = "delrechargeEvents")
	public String delrechargeEvents(HttpServletRequest request, ModelMap map,int oid) throws Exception {
		CommonUtil.printHTTP(request);
		rechargeEventsService.deleteRechargeEvents(rechargeEventsService.getRechargeEventsById(oid));
		return "redirect:rechargeEvents_list.do";
	}
	
	/**
	 * 跳转查看或者修改页面
	 * @param request
	 * @param map
	 * @param oid
	 * @return
	 */
	@UserRightAuth(menuCode = "rechargeEvents")
	@RequestMapping(value = "torechargeEvents_edit")
	public String torechargeEvents_edit(HttpServletRequest request, ModelMap map,int oid) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", rechargeEventsService.getRechargeEventsById(oid));
		return "admin/rechargeEvents/rechargeEvents_edit";
	}
	
	/**
	 * 修改红包数据
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "rechargeEvents")
	@RequestMapping(value = "rechargeEvents_edit")
	public String rechargeEvents_edit(HttpServletRequest request, ModelMap map,RechargeEvents red) {
		CommonUtil.printHTTP(request);
		try {
			rechargeEventsService.uppdateRechargeEvents(red);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:rechargeEvents_list.do";
	}
}
