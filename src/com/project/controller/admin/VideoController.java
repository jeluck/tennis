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
import com.project.pojo.Events;
import com.project.pojo.Manager;
import com.project.pojo.Video;
import com.project.service.IVideoService;
import com.project.service.IVideo_classificationService;
import com.project.util.CommonUtil;

@Controller("adminvideoController")
@RequestMapping(value="/admin")
public class VideoController extends BaseController{
	@Resource
	private IVideoService videoService;
	@Resource
	private IVideo_classificationService video_classificationService;
	
	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "video")
	@RequestMapping(value = "toaddvideo")
	public String toAddvideo(HttpServletRequest request, ModelMap map){
		map.put("videoType", video_classificationService.getAll());
		return "admin/video/video_add";
	}
	
	/**
	 * 添加操作
	 * @param request
	 * @param map
	 * @param a
	 * @return
	 */
	@UserRightAuth(menuCode = "video")
	@RequestMapping(value = "addvideo")
	public String addvideo(HttpServletRequest request, ModelMap map,Video a){
		try {
			videoService.saveVideo(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:video_list.do";
	}
	
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "video")
	@RequestMapping(value = "video_list")
	public String video_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Video o) {
		CommonUtil.printHTTP(request);
		map.put("data_page",videoService.getPageFindeVideo(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/video/video_list";
	}
	
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "video")
	@RequestMapping(value = "toedit_video")
	public String toEditvideo(HttpServletRequest request, ModelMap map,int oid){
		String check = request.getParameter("check");
		map.put("videoType", video_classificationService.getAll());
		map.put("check", check);
		map.put("o", videoService.getVideoById(oid));
		
		return "admin/video/video_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "video")
	@RequestMapping(value = "edit_video")
	public String editvideo(HttpServletRequest request, ModelMap map,Video o){
		try {
			videoService.updateVideo(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:video_list.do";
	}
}
