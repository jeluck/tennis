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
import com.project.pojo.Red_bag_record;
import com.project.pojo.Weuser;
import com.project.service.IRedBagRecordService;
import com.project.service.IRedBagService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;

@Controller("redBagRecordController")
@RequestMapping("/admin")
public class RedBagRecordController extends BaseController{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CoachController.class); 
	
	@Resource
	private IRedBagRecordService redBagRecordService;
	@Resource
	private IWeuserService weuserService;
	@Resource
	private IRedBagService redBagService;
	
	/**
	 * 添加红包记录页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "redbagrecord")	
	@RequestMapping(value = "toaddredbagrecord")
	public String toaddRedBagRecord(HttpServletRequest request, ModelMap map){
		CommonUtil.printHTTP(request);
		map.put("weuUser", weuserService.getAllUser());
		map.put("redBag", redBagService.getAllRedBag());
		return "admin/redbagrecord/redbagrecord_add";
	}
	
	/**
	 * 添加红包记录
	 * @param request
	 * @param map
	 * @param red
	 * @return
	 */
	@UserRightAuth(menuCode = "redbagrecord")
	@RequestMapping(value = "addredbagrecord")
	public String addRedBagRecord(HttpServletRequest request, ModelMap map,int redbagid,int weuserid) {
		CommonUtil.printHTTP(request);
		try {
			Red_bag redBag = redBagService.getRedBagById(redbagid);
			Weuser user =weuserService.getUserById(weuserid);
			Red_bag_record red = new Red_bag_record();
			red.setCreate_time(redBag.getCreate_time());
			red.setEnd_time(redBag.getEnd_time());
			red.setStart_time(redBag.getStart_time());
			red.setUpdate_time(redBag.getUpdate_time());
			red.setPurpose(redBag.getPurpose());
			red.setQuantity(redBag.getQuantity());
			red.setReward_type(redBag.getReward_type());
			red.setWeuser(user);
			red.setStatus(Constants.NORMAL_FLAG);
			redBagRecordService.saveRedBagRecord(red);
			
			redBag.setStatus(Constants.DETELE_FLAG);
			redBagService.updateRedBag(redBag);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:redbagrecord_list.do";
	}
	
	
	/**
	 * 列表数据
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param red
	 * @return
	 */
	@UserRightAuth(menuCode = "redbagrecord")
	@RequestMapping(value = "redbagrecord_list")
	public String redbagRecord_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Red_bag_record red) {
		CommonUtil.printHTTP(request);
		map.put("reward_type",red.getReward_type());
		map.put("data_page",redBagRecordService.getPageFindeRedBagRecord(red, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/redbagrecord/redbagrecord_list";
	}
	
	/**
	 * 跳转查看或者修改页面
	 * @param request
	 * @param map
	 * @param oid
	 * @return
	 */
	@UserRightAuth(menuCode = "redbagrecord")
	@RequestMapping(value = "toredbagrecord_edit")
	public String toredbagRecord_edit(HttpServletRequest request, ModelMap map,int oid) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", redBagRecordService.getRedBagRecordById(oid));
		return "admin/redbagrecord/redbagrecord_edit";
	}
	
}
