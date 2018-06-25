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
import com.project.pojo.Red_bag;
import com.project.service.IRedBagService;
import com.project.util.CommonUtil;

@Controller("adminredbagController")
@RequestMapping("/admin")
public class RedBagController extends BaseController{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CoachController.class); 
	
	@Resource
	private IRedBagService redBagService;
	
	/**
	 * 添加红包页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "redbag")	
	@RequestMapping(value = "toaddredbag")
	public String toaddRedBag(HttpServletRequest request, ModelMap map){
		CommonUtil.printHTTP(request);
		return "admin/redbag/redbag_add";
	}
	
	/**
	 * 添加红包
	 * @param request
	 * @param map
	 * @param red
	 * @return
	 */
	@UserRightAuth(menuCode = "redbag")
	@RequestMapping(value = "addredbag")
	public String addRedBag(HttpServletRequest request, ModelMap map,Red_bag red) {
		CommonUtil.printHTTP(request);
		try {
			red.setStatus(Constants.NORMAL_FLAG);
			redBagService.saveRedBag(red);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:redbag_list.do";
	}
	
	
	/**
	 * 列表数据
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param red
	 * @return
	 */
	@UserRightAuth(menuCode = "redbag")
	@RequestMapping(value = "redbag_list")
	public String redbag_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Red_bag red) {
		CommonUtil.printHTTP(request);
		map.put("reward_type",red.getReward_type());
		map.put("data_page",redBagService.getPageFindeRedBag(red, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/redbag/redbag_list";
	}
	
	/**
	 * 跳转查看或者修改页面
	 * @param request
	 * @param map
	 * @param oid
	 * @return
	 */
	@UserRightAuth(menuCode = "redbag")
	@RequestMapping(value = "toredbag_edit")
	public String toredbag_edit(HttpServletRequest request, ModelMap map,int oid) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", redBagService.getRedBagById(oid));
		return "admin/redbag/redbag_edit";
	}
	
	/**
	 * 修改红包数据
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "redbag")
	@RequestMapping(value = "redbag_edit")
	public String edit_playground_manager(HttpServletRequest request, ModelMap map,Red_bag red) {
		CommonUtil.printHTTP(request);
		try {
			Red_bag o = redBagService.getRedBagById(red.getId());
			red.setCreate_time(o.getCreate_time());
			redBagService.updateRedBag(red);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:redbag_list.do";
	}
}
