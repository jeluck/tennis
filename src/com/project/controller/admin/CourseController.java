package com.project.controller.admin;

import java.util.List;

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
import com.project.pojo.Course;
import com.project.pojo.Manager;
import com.project.pojo.Region;
import com.project.service.ICourseService;
import com.project.service.IRegionService;
import com.project.util.CommonUtil;

@Controller("courseController")
@RequestMapping(value = "/admin")
public class CourseController extends BaseController {

	@Resource
	private ICourseService courseService;
	@Resource
	private IRegionService regionService;

	/**
	 * 添加课程页面
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "course")
	@RequestMapping(value = "toaddcourse")
	public String toAddCourses(HttpServletRequest request, ModelMap map) {
		CommonUtil.printHTTP(request);
		List<Region> list = regionService.getProvince();
		map.put("provincelist", list);
		return "admin/course/course_add";
	}

	/**
	 * 添加课程
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "course")
	@RequestMapping(value = "addcourse")
	public String addCourse(MultipartHttpServletRequest request) {
		// 保存
		Course course = CommonUtil.SerializableObj(request.getParameterMap(),
				Course.class);
		try {
			MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
			// 上传文件img1
			MultipartFile imgFile1 = mhs.getFile("imgFile1");
			if (imgFile1 != null && imgFile1.getSize() > 0) {
				String image_name = "banner" + System.currentTimeMillis()
						+ ".jpg";
				course.setImage1(uploadFile(
						imgFile1,
						request,
						Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,
						""));
			}
			// 上传文件img2
			MultipartFile imgFile2 = mhs.getFile("imgFile2");
			if (imgFile2 != null && imgFile2.getSize() > 0) {
				String image_name = "banner" + System.currentTimeMillis()
						+ ".jpg";
				course.setImage2(uploadFile(
						imgFile2,
						request,
						Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,
						""));
			}
			// 上传文件img3
			MultipartFile imgFile3 = mhs.getFile("imgFile3");
			if (imgFile3 != null && imgFile3.getSize() > 0) {
				String image_name = "banner" + System.currentTimeMillis()
						+ ".jpg";
				course.setImage3(uploadFile(
						imgFile3,
						request,
						Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,
						""));
			}
			Manager m = getManager(request);
			course.setManager_id(m.getId());
			course.setAuthod_type(Constants.AUTHOD_TYPE.MANAGER.getStatus());
			courseService.saveCourse(course);
		} catch (Exception e) {
			logger.error("添加课程信息出现错误！错误信息：" + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:course_list.do";
	}

	/**
	 * 课程列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param course
	 * @return
	 */
	@UserRightAuth(menuCode = "course")
	@RequestMapping(value = "course_list")
	public String course_list(@RequestParam(defaultValue = "1") int pageNumber,
			HttpServletRequest request, ModelMap map, Course course) {
		CommonUtil.printHTTP(request);
		map.put("data_page",courseService.getCourseList(course, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/course/course_list";
	}
	
	
	/**
	 * 跳转查看或者修改页面
	 * @param request
	 * @param map
	 * @param oid
	 * @return
	 */
	@UserRightAuth(menuCode = "course")
	@RequestMapping(value = "tocourse_edit")
	public String tocourse_edit(HttpServletRequest request, ModelMap map,int oid) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		Course course = courseService.getCourseById(oid);
		map.put("o", course);
		map.put("r",regionService.getRegionById(course.getCity_show_id()));
		List<Region> list = regionService.getProvince();
		map.put("provincelist",list);
		return "admin/course/course_edit";
	}
	
	/**
	 * 修改课程数据
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "course")
	@RequestMapping(value = "course_edit")
	public String course_edit(MultipartHttpServletRequest request) {
		Course course =  CommonUtil.SerializableObj(request.getParameterMap(), Course.class);
        //保存
        try {
        	MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
        	MultipartFile imgFile1 = mhs.getFile("imgFile1");
        	if(imgFile1!=null && imgFile1.getSize()>0){
        		String image_name="banner"+System.currentTimeMillis()+".jpg";
        		course.setImage1(uploadFile(imgFile1,request,Settings.IMAGE_SAVE_FILE_NAME+"/banner/"+image_name,""));
        	}else{
        		course.setImage1(request.getParameter("imgUrl1"));
        	}
        	MultipartFile imgFile2 = mhs.getFile("imgFile2");
        	if(imgFile2!=null && imgFile2.getSize()>0){
        		String image_name="banner"+System.currentTimeMillis()+".jpg";
        		course.setImage2(uploadFile(imgFile2,request,Settings.IMAGE_SAVE_FILE_NAME+"/banner/"+image_name,""));
        	}else{
        		course.setImage2(request.getParameter("imgUrl2"));
        	}
        	MultipartFile imgFile3 = mhs.getFile("imgFile3");
        	if(imgFile3!=null && imgFile3.getSize()>0){
        		String image_name="banner"+System.currentTimeMillis()+".jpg";
        		course.setImage3(uploadFile(imgFile3,request,Settings.IMAGE_SAVE_FILE_NAME+"/banner/"+image_name,""));
        	}else{
        		course.setImage3(request.getParameter("imgUrl3"));
        	}
        	Course c = courseService.getCourseById(course.getId());
        	course.setAuthod_type(Constants.AUTHOD_TYPE.MANAGER.getStatus());
        	course.setCreate_time(c.getCreate_time());
        	Manager m = getManager(request);
			course.setManager_id(m.getId());
        	courseService.updateCourse(course);
        } catch (Exception e) {
        	logger.error("修改课程信息出现错误！错误信息："+e.getMessage());
            e.printStackTrace();
        }
		return "redirect:course_list.do?action=advertise_list&pageNumber=1";
	}

}
