package com.project.controller.wechat;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.pojo.Events;
import com.project.pojo.EventsCooperation;
import com.project.service.IEventsCooperationService;
import com.project.service.IEventsService;
import com.project.util.CommonUtil;

@Controller("eventsWechatController")
@RequestMapping(value="/")
public class EventsController extends BaseController {
	
	@Resource
	private IEventsService eventsService;
	@Resource
	private IEventsCooperationService eventsCooperationService;
	
	/**
	 * 去赛事模板
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "toEventsMode")
	public String toEventsMode(HttpServletRequest request, ModelMap map){
		Events events = eventsService.getNewEvents();
		int id = 0;
		if(events != null && events.getId()>0){
			id = events.getId();
		}
		map.put("o", events);
		map.put("eList", eventsCooperationService.getEventsCooperationByEid(id));
		return "/weixin/events_mode";
	}
	
	
	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "toaddEvents")
	public String toAddEvents(HttpServletRequest request, ModelMap map){
		List<Events> list = eventsService.getEvents();
		map.put("list", list);
		map.put("price", list.get(0).getPrice());
		return "/weixin/events_add";
	}
	
}
