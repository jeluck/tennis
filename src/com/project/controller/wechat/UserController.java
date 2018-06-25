package com.project.controller.wechat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.common.ConfigKey;
import com.project.common.Constants;
import com.project.common.Settings;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.StationMessage;
import com.project.pojo.User_count;
import com.project.pojo.Weuser;
import com.project.service.IStationMessageService;
import com.project.service.ISystemConfigService;
import com.project.service.IUser_countService;
import com.project.service.IWeuserService;

@Controller("clubWebWechatUserController")
@RequestMapping(value="/")
public class UserController {
	@Resource
	private IStationMessageService stationMessageService;
	@Resource
	private ISystemConfigService systemConfigService;
	@Resource
	private IWeuserService userService;
	@Resource
	private IUser_countService user_countService;
	
	/**
	 * 消息列表
	 * @param request
	 * @param map
	 * @param userId
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("wechatMessageList")
	public String wechatMessageList(HttpServletRequest request,ModelMap map,int userId,@RequestParam(defaultValue="1")int pageNumber) {
		PageFinder<StationMessage> list = stationMessageService.getPageStationMessageByUserId(userId, pageNumber, 5,1,null);
		map.put("list", list);
		map.put("userId", userId);
		return "/weixin/message_list";
	}
	
	/**
	 * 消息详情
	 * @param request
	 * @param map
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getWechatMessage")
	public String getWechatMessage(HttpServletRequest request,ModelMap map,int id) throws Exception {
		StationMessage s = stationMessageService.getStationMessageById(id);
		s.setReadstatus(Constants.MessageReadStatus.HAVE_READ.getStatus());
		stationMessageService.updateStationMessage(s);
		map.put("o",s);
		return "/weixin/message";
	}
	
	/**
	 * 更多
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("more")
	public String more(HttpServletRequest request,ModelMap map){
		return "/weixin/more";
	}
	
	/**
	 * 下载
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("download")
	public String download(HttpServletRequest request,ModelMap map){
		map.put("coach", Settings.SERVER_HOST+systemConfigService.getConfigValueByKey(ConfigKey.ANDROIDCOACHVERSIONURL));
		map.put("user", Settings.SERVER_HOST+systemConfigService.getConfigValueByKey(ConfigKey.ANDROIDUSERVERSIONURL));
		return "/weixin/download";
	}
	
	/**
	 * 我的
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("userMine")
	public String mine(HttpServletRequest request,ModelMap map,int userId){
		User_count p = user_countService.getUser_countById(userId);
		map.put("o", p);
		return "/weixin/user_mine";
	}
	
}
