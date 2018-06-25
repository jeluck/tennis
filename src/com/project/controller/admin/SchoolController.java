package com.project.controller.admin;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.pojo.ClubIndex;
import com.project.pojo.Gallery;
import com.project.pojo.PlaygroundImg;
import com.project.pojo.School;
import com.project.service.IGalleryService;
import com.project.service.ISchoolService;
import com.project.util.CommonUtil;

@Controller("schoolController")
@RequestMapping(value = "/admin")
public class SchoolController extends BaseController {

	@Resource
	private ISchoolService schoolService; 
	@Resource
	private IGalleryService galleryService;
	
	/**
	 * 学校管理
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "school_manger")
	public String school_manger(HttpServletRequest request, ModelMap map,ClubIndex o){
		map.put("o", schoolService.getSchool());
		return "admin/school/school_manger";
	}
	
	
	/**
	 * 添加或者修改俱乐部首页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addOrUpdateSchool")
	public String addOrUpdateSchool(HttpServletRequest request) throws Exception{
		School o = CommonUtil.SerializableObj(request.getParameterMap(),
				School.class);
		MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
		// 上传文件img1
		MultipartFile imgFile1 = mhs.getFile("img");
		if (imgFile1 != null && imgFile1.getSize() > 0) {
			String image_name = "banner" + System.currentTimeMillis()
					+ ".jpg";
			o.setImg(uploadFile(imgFile1,request,
					Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,""));
		}else{
			o.setImg(request.getParameter("imgSrc"));
		}
		schoolService.mergeSchool(o);
		return "redirect:school_manger.do";
	}
	
	/**
	 * 学校活动集锦列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "school_gallery_list")
	public String school_gallery_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Gallery o) {
		CommonUtil.printHTTP(request);
		o.setType(2);
		School school = schoolService.getSchool();
		if(school!=null){
			o.setAcId(school.getId());
			map.put("name",school.getName());
			map.put("schoolId", school.getId());
		}else{
			o.setAcId(-1);
		}
		map.put("data_page",galleryService.getPageFindeClubGallery(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/school/school_gallery_list";
	}
	
	
	/**
	 * 查看或者添加学校活动集锦页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "toeditSchoolGallery")
	public String toeditSchoolGallery(HttpServletRequest request, ModelMap map,int oid){
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		if(oid>0){
			map.put("o", galleryService.getGalleryById(oid));
		}
		return "admin/school/school_gallery_edit";
	}
	
	/**
	 * 查看或者修改学校活动集锦
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "editSchoolGallery")
	public String editSchoolGallery(HttpServletRequest request) throws Exception{
		Gallery o = CommonUtil.SerializableObj(request.getParameterMap(),
				Gallery.class);
		o.setType(1);
		MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
		// 上传文件img1
		MultipartFile imgFile1 = mhs.getFile("img");
		if (imgFile1 != null && imgFile1.getSize() > 0) {
			String image_name = "banner" + System.currentTimeMillis()
					+ ".jpg";
			o.setImg(uploadFile(imgFile1,request,
					Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,""));
		}else{
			o.setImg(request.getParameter("imgSrc"));
		}
		String oid = request.getParameter("clubId");
		o.setAcId(Integer.valueOf(oid));
		galleryService.mergeGallery(o);
		return "redirect:school_gallery_list.do";
	}
	
	/**
	 * 删除学校活动集锦
	 * @param request
	 * @param id
	 * @param clubId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delSchoolGallery")
	public String delClubGallery(HttpServletRequest request,int id) throws Exception{
		galleryService.deleteGalleryById(id);
		return "redirect:school_gallery_list.do";
	}
	
	/**
	 * 学校添加图片页面
	 * @return
	 */
	@RequestMapping(value = "toaddSchoolImg")
	public String toaddPlaygroundImg(HttpServletRequest request, ModelMap map,int schoolId){
		request.setAttribute("schoolId", schoolId);
		return "admin/school/school_gallery_img_add";
	}
	
	/**
	 * 学校添加图片
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "schooladdimg")
	@ResponseBody
	public void uploadFile(HttpServletRequest request,HttpServletResponse response) throws Exception {  
		int schoolId = Integer.valueOf(request.getParameter("schoolId"));
		 String image_name = "banner" + System.currentTimeMillis()
					+ ".jpg";
		request.setCharacterEncoding("utf-8");
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();
                    System.out.println(myFileName);
                    
                    Gallery o = new Gallery();
                    o.setAcId(schoolId);
                    o.setType(2);
                    o.setImg(uploadFile(file,request,Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,""));
                    try {
                    	galleryService.seveClubImg(o);
                    } catch (Exception e) {
						e.printStackTrace();
					}
                }  
            }
        }
    }  
}
