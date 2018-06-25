package com.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.pojo.Module;
import com.project.pojo.Playground;
import com.project.service.IModuleService;
import com.project.util.CommonUtil;

/**
 * 首页六个模版控制器
 * @author Administrator
 *
 */
@Controller("moduleController")
@RequestMapping("/admin")
public class ModuleController extends BaseController {
	
	@Resource
	private IModuleService	moduleService;
	
	/**
	 * 模版添加页面
	 * @return
	 */
	@UserRightAuth(menuCode = "module")
	@RequestMapping(value = "toaddIndexMb")
	public String toaddIndexMb(HttpServletRequest request, ModelMap map){
		return "admin/module/module_add";
	}
	
	/**
	 * 增加
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	@UserRightAuth(menuCode = "module")
	@RequestMapping(value = "add_module")
	public String add_module(MultipartHttpServletRequest request, ModelMap map) throws Exception {
		CommonUtil.printHTTP(request);
		Module o = CommonUtil.SerializableObj(request.getParameterMap(),
				Module.class);
		MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
		// 上传文件img1
		MultipartFile imgFile1 = mhs.getFile("img");
		if (imgFile1 != null && imgFile1.getSize() > 0) {
			String image_name = "banner" + System.currentTimeMillis()
					+ ".jpg";
			o.setImg(uploadFile(imgFile1,request,
					Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,""));
		}
		moduleService.saveModule(o);
		return "redirect:indexMbList.do";
	}
	
	/**
	 * 修改或者查看页面
	 * @param request
	 * @param map
	 * @param id
	 * @return
	 */
	@UserRightAuth(menuCode = "module")
	@RequestMapping(value = "toedit_module")
	public String toedit_module(HttpServletRequest request, ModelMap map,int id) {
		CommonUtil.printHTTP(request);
		Module o = moduleService.getModuleById(id);
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", o);
		return "admin/module/module_edit";
	}
	
	
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "module")
	@RequestMapping(value = "indexMbList")
	public String indexMbList(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Module o) {
		CommonUtil.printHTTP(request);
		int status = 0;
		if(CommonUtil.NotEmpty(request.getParameter("status"))){
			status = Integer.valueOf(request.getParameter("status"));
		}
		map.put("data_page",moduleService.getPageFindeModule(o, status, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("status",status);
		return "admin/module/module_list";
	}
	
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@UserRightAuth(menuCode = "module")
	@RequestMapping(value = "edit_module")
	public String edit_module(MultipartHttpServletRequest request, ModelMap map ,int id) throws Exception {
		CommonUtil.printHTTP(request);
		Module o = CommonUtil.SerializableObj(request.getParameterMap(),
				Module.class);
		MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
    	MultipartFile imgFile1 = mhs.getFile("img");
    	if(imgFile1!=null && imgFile1.getSize()>0){
    		String image_name="banner"+System.currentTimeMillis()+".jpg";
    		o.setImg(uploadFile(imgFile1,request,Settings.IMAGE_SAVE_FILE_NAME+"/banner/"+image_name,""));
    	}else{
    		o.setImg(request.getParameter("imgfile"));
    	}
    	moduleService.upadateModule(o);
		return "redirect:indexMbList.do";
	}
}
