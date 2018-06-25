package com.project.controller.wechat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.pojo.School;
import com.project.service.IGalleryService;
import com.project.service.ISchoolService;

@Controller("schoolWebController")
@RequestMapping(value="/")
public class SchoolController {

	@Resource
	private IGalleryService galleryService;
	@Resource
	private ISchoolService schoolService;
	
	
	/**
	 * 学校首页
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "school")
	public String school(HttpServletRequest request,ModelMap map){
		School o = schoolService.getSchool();
		map.put("o",o);
		map.put("galleryImgList", galleryService.getGalleryByCidAndType(2, o.getId()));
		return "/weixin/school";
	}
}
