package com.project.controller.admin;

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

@Controller("admineventsController")
@RequestMapping(value="/admin")
public class EventsController extends BaseController {
	
	@Resource
	private IEventsService eventsService;
	@Resource
	private IEventsCooperationService eventsCooperationService;
	
	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "events")
	@RequestMapping(value = "toaddEvents")
	public String toAddEvents(HttpServletRequest request, ModelMap map){
		return "admin/events/events_add";
	}
	
	/**
	 * 添加操作
	 * @param request
	 * @param map
	 * @param a
	 * @return
	 */
	@UserRightAuth(menuCode = "events")
	@RequestMapping(value = "addEvents")
	public String addEvents(HttpServletRequest request, ModelMap map,Events a){
		
		try {
			a = eventsService.saveEvents(a);
			String[] zhubanImg=request.getParameterValues("zhubanImg");
			String[] zhubanInput=request.getParameterValues("zhubanInput");
			String[] chengbanImg=request.getParameterValues("chengbanImg");
			String[] chengbanInput=request.getParameterValues("chengbanInput");
			String[] xiebanImg=request.getParameterValues("xiebanImg");
			String[] xiebanInput=request.getParameterValues("xiebanInput");
			String[] hezImg=request.getParameterValues("hezImg");
			String[] zanzImg=request.getParameterValues("zanzImg");
			for (int i = 0; i < zhubanImg.length; i++) {
				for (int y = 0; y < zhubanInput.length; y++) {
					if(i == y){
						EventsCooperation o = new EventsCooperation(zhubanImg[i],zhubanInput[i],"zhuban",a.getId());
						eventsCooperationService.saveEventsCooperation(o);
					}
				}
			}
			for (int i = 0; i < chengbanImg.length; i++) {
				for (int y = 0; y < chengbanInput.length; y++) {
					if(i == y){
						EventsCooperation o = new EventsCooperation(chengbanImg[i],chengbanInput[i],"chengban",a.getId());
						eventsCooperationService.saveEventsCooperation(o);
					}
				}
			}
			for (int i = 0; i < xiebanImg.length; i++) {
				for (int y = 0; y < xiebanInput.length; y++) {
					if(i == y){
						EventsCooperation o = new EventsCooperation(xiebanImg[i],xiebanInput[i],"xieban",a.getId());
						eventsCooperationService.saveEventsCooperation(o);
					}
				}
			}
			for (int i = 0; i < hezImg.length; i++) {
				EventsCooperation o = new EventsCooperation(hezImg[i],"","hez",a.getId());
				eventsCooperationService.saveEventsCooperation(o);
			}
			for (int i = 0; i < zanzImg.length; i++) {
				EventsCooperation o = new EventsCooperation(zanzImg[i],"","zanz",a.getId());
				eventsCooperationService.saveEventsCooperation(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:events_list.do";
	}
	
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "events")
	@RequestMapping(value = "events_list")
	public String events_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Events o) {
		CommonUtil.printHTTP(request);
		map.put("data_page",eventsService.getPageFindeEvents(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("company_name", o.getTitle());
		return "admin/events/events_list";
	}
	
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "events")
	@RequestMapping(value = "toedit_events")
	public String toEditActivity(HttpServletRequest request, ModelMap map,int oid){
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", eventsService.getEventsById(oid));
		map.put("eList", eventsCooperationService.getEventsCooperationByEid(oid));
		return "admin/events/events_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "events")
	@RequestMapping(value = "edit_events")
	public String editActivity(HttpServletRequest request, ModelMap map,Events a){
		try {
			eventsService.updateEvents(a);
			
			//查询全部 再删除
			List<EventsCooperation> list = eventsCooperationService.getEventsCooperationByEid(a.getId());
			for (EventsCooperation e : list) {
				eventsCooperationService.deleteEventsCooperation(e);
			}
			
			//添加
			String[] zhubanImg=request.getParameterValues("zhubanImg");
			String[] zhubanInput=request.getParameterValues("zhubanInput");
			String[] chengbanImg=request.getParameterValues("chengbanImg");
			String[] chengbanInput=request.getParameterValues("chengbanInput");
			String[] xiebanImg=request.getParameterValues("xiebanImg");
			String[] xiebanInput=request.getParameterValues("xiebanInput");
			String[] hezImg=request.getParameterValues("hezImg");
			String[] zanzImg=request.getParameterValues("zanzImg");
			for (int i = 0; i < zhubanImg.length; i++) {
				for (int y = 0; y < zhubanInput.length; y++) {
					if(i == y){
						EventsCooperation o = new EventsCooperation(zhubanImg[i],zhubanInput[i],"zhuban",a.getId());
						eventsCooperationService.saveEventsCooperation(o);
					}
				}
			}
			for (int i = 0; i < chengbanImg.length; i++) {
				for (int y = 0; y < chengbanInput.length; y++) {
					if(i == y){
						EventsCooperation o = new EventsCooperation(chengbanImg[i],chengbanInput[i],"chengban",a.getId());
						eventsCooperationService.saveEventsCooperation(o);
					}
				}
			}
			for (int i = 0; i < xiebanImg.length; i++) {
				for (int y = 0; y < xiebanInput.length; y++) {
					if(i == y){
						EventsCooperation o = new EventsCooperation(xiebanImg[i],xiebanInput[i],"xieban",a.getId());
						eventsCooperationService.saveEventsCooperation(o);
					}
				}
			}
			for (int i = 0; i < hezImg.length; i++) {
				EventsCooperation o = new EventsCooperation(hezImg[i],"","hez",a.getId());
				eventsCooperationService.saveEventsCooperation(o);
			}
			for (int i = 0; i < zanzImg.length; i++) {
				EventsCooperation o = new EventsCooperation(zanzImg[i],"","zanz",a.getId());
				eventsCooperationService.saveEventsCooperation(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:events_list.do";
	}
}
