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
import com.project.pojo.Information;
import com.project.pojo.Red_bag;
import com.project.pojo.Red_bag_record;
import com.project.service.ICategoryInfoService;
import com.project.service.IInformationService;
import com.project.util.CommonUtil;

/**
 * 新闻资讯controller
 * @author Administrator
 *
 */
@Controller("informationController")
@RequestMapping(value = "/pgm")
public class InformationController extends BaseController {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CoachController.class); 
	
	@Resource
	private IInformationService informationService;
	@Resource
	private ICategoryInfoService categoryInfoService;
	
	
	/**
	 * 添加新闻资讯页面
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "category")
	@RequestMapping(value = "toaddInformation")
	public String toaddInformation(HttpServletRequest request, ModelMap map) {
		CommonUtil.printHTTP(request);
		map.put("category", categoryInfoService.getAllCategory());
		return "admin/category/news_information_add";
	}
	
	
	/**
	 * 添加新闻资讯
	 * @param request
	 * @param map
	 * @param red
	 * @return
	 */
	@UserRightAuth(menuCode = "category")
	@RequestMapping(value = "addInformation")
	public String addInformation(HttpServletRequest request, ModelMap map,Information o) {
		CommonUtil.printHTTP(request);
		try {
			informationService.updateInformation(o);
		} catch (Exception e) {
			logger.error("添加新闻资讯信息出现错误！错误信息：" + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:news_information_list.do";
	}
	
	/**
	 * 新闻资讯列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "category")
	@RequestMapping(value = "news_information_list")
	public String news_information_list(
			@RequestParam(defaultValue = "1") int pageNumber,
			HttpServletRequest request, ModelMap map,Information news) {
		CommonUtil.printHTTP(request);
		map.put("title", news.getTitle());
		if(news.getInformation_categoryinfo_id()!=null){
			map.put("info_id", news.getInformation_categoryinfo_id().getId());
		}
		map.put("category", categoryInfoService.getAllCategory());
		map.put("data_page",informationService.getPageFindeInformation(news, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/category/news_information_list";
	}
	
	/**
	 * 跳转查看或者修改页面
	 * @param request
	 * @param map
	 * @param oid
	 * @return
	 */
	@UserRightAuth(menuCode = "category")
	@RequestMapping(value = "tonews_information_edit")
	public String tonews_information_edit(HttpServletRequest request, ModelMap map,int oid) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("category", categoryInfoService.getAllCategory());
		map.put("news", informationService.getInformationById(oid));
		return "admin/category/news_information_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "category")
	@RequestMapping(value = "news_information_edit")
	public String news_information_edit(HttpServletRequest request, ModelMap map,Information news) {
		CommonUtil.printHTTP(request);
		try {
			Information o = informationService.getInformationById(news.getId());
			news.setCreate_time(o.getCreate_time());
			informationService.updateInformation(news);
		} catch (Exception e) {
			logger.error("修改新闻资讯信息出现错误！错误信息：" + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:news_information_list.do";
	}
}
