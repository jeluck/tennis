package com.project.controller.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.ConfigKey;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Advertise;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach;
import com.project.pojo.Course;
import com.project.pojo.Module;
import com.project.pojo.Notice;
import com.project.pojo.Playground;
import com.project.pojo.Region;
import com.project.pojo.Weuser;
import com.project.service.IAdvertiseService;
import com.project.service.ICategoryInfoService;
import com.project.service.ICoachService;
import com.project.service.ICourseService;
import com.project.service.IModuleService;
import com.project.service.IPlaygroundService;
import com.project.service.IPropagandaService;
import com.project.service.IRegionService;
import com.project.service.ISystemConfigService;
import com.project.util.CommonUtil;


@Controller("indexWebController")
@RequestMapping(value="/")
public class IndexController extends BaseController {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IPropagandaService propagandaService;
    @Resource
    private ICategoryInfoService categoryService;
    
	@Resource
	private IAdvertiseService advertiseService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private ICoachService coachService;
	@Resource
	private ICourseService courseService;
	@Resource
	private IRegionService regionService;
	@Resource
	private IModuleService	moduleService;
	@Resource
	private ISystemConfigService  systemConfigService;
    /***
     * 跳到商品分类
     * @param request
     * @param cid
     * @param map
     * @return
     */
   @RequestMapping("tocategory")
   public String orderdetail(ModelMap map,HttpServletRequest request){
   	Weuser w = getUser(request);
   	if(w==null){
			return "redirect:touserlogin.do";
		}
   	return "/mobile/category";
   }
   

   /**
    * 点击一级分类ID,进入查看此分类商品
    * @param map
    * @param request
    * @param pageNumber
    * @param categoryId
    * @return
    */
   @RequestMapping("information")
   public String information(ModelMap map,HttpServletRequest request,@RequestParam(defaultValue="1")int pageNumber){
	   PageFinder<Notice> list = propagandaService.getNoticeListForMobile(pageNumber, Constants.BACKGROUND_PAGESIZE);
	   if(list.getDataList()!=null){
		   Notice  n = list.getDataList().get(0);
		   map.put("n", n);
	   }
	   return "/mobile/information";
   }
   
   /**
	 * 首页数据
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("index")
	@ResponseBody
	public Object pridads(HttpServletRequest request,String cityName) {
		int city_show_id = 0;
		if(CommonUtil.NotEmpty(cityName)){
			String cityNameCl = cityName + "市";
			Region region = regionService.getRegionByCityName(cityNameCl);
			if(CommonUtil.NotEmptyObject(region)){
				city_show_id = region.getRegion_id();
			}else{
				city_show_id = regionService.getRegionByCityName("深圳市").getRegion_id();
			}
		}else{
			city_show_id = regionService.getRegionByCityName("深圳市").getRegion_id();
		}
		int province_id = regionService.getRegionById(city_show_id).getParent_id();
		List<Notice> noticeList = propagandaService.getNoticeLists(city_show_id,province_id);								//头条
		List<Playground> playgroundList = playgroundService.getPlaygroundList(city_show_id,Constants.STICK1,"");	//场馆
		List<Coach> coachList = coachService.getCoachList(city_show_id,Constants.STICK1,"");						//教练
		List<Course> courseList = courseService.getAllCourse(city_show_id,Constants.STICK1,"");					//课程
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notice", noticeList);
		map.put("playground", playgroundList);
		map.put("coach", coachList);
		map.put("course", courseList);
		map.put("index_ search", systemConfigService.getConfigValueByKey(ConfigKey.INDEX_SEARCH,""));
		if(noticeList.size()>0 || playgroundList.size()>0 || coachList.size()>0 || courseList.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", map);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
  /**
	 * 首页搜索数据
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("indexs")
	@ResponseBody
	public Object indexs(HttpServletRequest request,String cityName,String name) {
		int city_show_id = 0;
		if(CommonUtil.NotEmpty(cityName)){
			String cityNameCl = cityName + "市";
			Region region = regionService.getRegionByCityName(cityNameCl);
			if(CommonUtil.NotEmptyObject(region)){
				city_show_id = region.getRegion_id();
			}else{
				city_show_id = regionService.getRegionByCityName("深圳市").getRegion_id();
			}
		}else{
			city_show_id = regionService.getRegionByCityName("深圳市").getRegion_id();
		}
		
		List<Playground> playgroundList = playgroundService.getPlaygroundList(city_show_id,-1,name);	//场馆
		List<Coach> coachList = coachService.getCoachList(city_show_id,-1,name);						//教练
		List<Course> courseList = courseService.getAllCourse(city_show_id,-1,name);					//课程
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("playground", playgroundList);
		map.put("coach", coachList);
		map.put("course", courseList);
		if(playgroundList.size()>0 || coachList.size()>0 || courseList.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", map);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
	
	/**
	 * 悬浮广告
	 * @param request
	 * @return
	 */
	@RequestMapping("suspendedAdvertising")
	@ResponseBody
	public Object suspendedAdvertising(HttpServletRequest request) {
		List<Advertise> advertiseList = advertiseService.getAdvertiseByType(Constants.ADTYPE.FLOATING.getStatus());	//悬浮广告
		if(advertiseList.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", advertiseList);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
	/**
	 * 斑斓图 1首页,2场馆列表,3网球圈,4赛事,5首页中间悬浮广告,6教练
	 * @param request
	 * @return
	 */
	@RequestMapping("beautiful_picture")
	@ResponseBody
	public Object Beautiful_picture(HttpServletRequest request,int type) {
		List<Advertise> advertiseList = advertiseService.getAdvertiseByType(type);	
		if(advertiseList.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", advertiseList);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
	/**
	 * 首页中间六个模板接口
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("moduleData")
	@ResponseBody
	public Object moduleList(HttpServletRequest request) {
		List<Module> moduleList = moduleService.getModuleListByStatus(Constants.MODULE_SHOW);
		if(moduleList.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", moduleList);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}

}
