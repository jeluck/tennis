package com.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.pojo.Video;
import com.project.pojo.Video_classification;
import com.project.service.IVideo_classificationService;
import com.project.util.CommonUtil;

@Controller("adminvideo_classificationController")
@RequestMapping(value="/admin")
public class Video_classificationController {
	@Resource
	private IVideo_classificationService video_classificationService;
	
	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "video_classification")
	@RequestMapping(value = "toaddvideo_classification")
	public String toAddvideo_classification(HttpServletRequest request, ModelMap map){
		return "admin/video_classification/video_classification_add";
	}
	
	/**
	 * 添加操作
	 * @param request
	 * @param map
	 * @param a
	 * @return
	 */
	@UserRightAuth(menuCode = "video_classification")
	@RequestMapping(value = "addvideo_classification")
	public String addvideo_classification(HttpServletRequest request, ModelMap map,Video_classification a){
		
		try {
			
			video_classificationService.saveVideo_classification(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:video_classification_list.do";
	}
	
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "video_classification")
	@RequestMapping(value = "video_classification_list")
	public String video_classification_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Video_classification o) {
		CommonUtil.printHTTP(request);
		map.put("data_page",video_classificationService.getPageFindeVideo_classification(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/video_classification/video_classification_list";
	}
	
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "video_classification")
	@RequestMapping(value = "toedit_video_classification")
	public String toEditvideo_classification(HttpServletRequest request, ModelMap map,int oid){
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", video_classificationService.getVideo_classificationById(oid));
		
		return "admin/video_classification/video_classification_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "video")
	@RequestMapping(value = "edit_video_classification")
	public String editvideo_classification(HttpServletRequest request, ModelMap map,Video_classification o){
		try {
			video_classificationService.updateVideo_classification(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:video_classification_list.do";
	}
}
