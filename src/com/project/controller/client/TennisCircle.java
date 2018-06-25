package com.project.controller.client;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.pojo.Advertise;
import com.project.service.IAdvertiseService;

@Controller("TennisCircleWebController")
@RequestMapping(value="/")
public class TennisCircle {

	@Resource
	private IAdvertiseService advertiseService;
	
	@RequestMapping("tennisCircleList")
	public String TennisCircleList(HttpServletRequest request, ModelMap map) {
		List<Advertise> advertiseList = advertiseService.getAdvertiseByType(3);	
		map.put("advertiseList", advertiseList);
		return "/phone/user/tennisCircle";
	}
}
