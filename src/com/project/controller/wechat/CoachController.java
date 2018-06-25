package com.project.controller.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.common.Constants;
import com.project.common.FinalList;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.Course;
import com.project.pojo.Region;
import com.project.service.ICoachService;
import com.project.service.ICoach_teach_personService;
import com.project.service.ICourseService;
import com.project.service.IDictionariesService;
import com.project.service.IRegionService;
import com.project.util.CommonUtil;
import com.project.util.LatitudeUtil;

@Controller("clubWebWechatcoachController")
@RequestMapping(value="/")
public class CoachController extends BaseController{
	@Resource
	private ICoachService coachService;
	@Resource
	private IRegionService regionService;
	@Resource
	private IDictionariesService dictionariesService;
	@Resource
	private ICourseService courseService;
	@Resource
	private ICoach_teach_personService coach_teach_personService;
	
	@RequestMapping("tocoachData")
	public String wechatMessageList(String areaid,String screening,String services,String sort,
									String cityName,HttpServletRequest request,ModelMap map,
									@RequestParam(defaultValue="1")int pageNumber,
									String lat,String lng,String coachName) {
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
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("cityid", city_show_id);
		paraMap.put("areaid", areaid);
		paraMap.put("screening", screening);
		paraMap.put("services", services);
		paraMap.put("sort", sort);
		paraMap.put("lat", lat);
		paraMap.put("lng", lng);
		paraMap.put("coachName", coachName);
		PageFinder<Coach> list = coachService.getPageCoachListByMap(paraMap, pageNumber, Constants.FOREGROUND_PAGESIZE);
		List<Coach> dataList = new ArrayList<Coach>();
		if(list != null && list.getDataList()!=null && list.getDataList().size()>0){
			for (Coach co : list.getDataList()) {
				//距离
				if(CommonUtil.NotEmpty(lat) && CommonUtil.NotEmpty(lng)){
					double distance = LatitudeUtil.Distance(Double.valueOf(lng.toString()),Double.valueOf(lat.toString()), 
							co.getCoordinateslongitude(), co.getCoordinateslatitude());
					String s1 = String.valueOf(distance);
					String s2 = s1.substring(0,s1.indexOf("."));
					int i = Integer.valueOf(s2); 
					co.setDistanceMeters(i);
				}else{
					co.setDistanceMeters(0);
				}

				//课程
				Course course = courseService.getCourseByUserId(co.getUserid().getId());
				if(course!=null){
					co.setCourseTitle(course.getTitle());
				}else{
					co.setCourseTitle("");
				}
				
				//用作于判断，当自己教练没有在前台设置价格时不添加到数据显示
				if(co.getCoachType()==1 && coach_teach_personService.getCoach_teach_personByCidAndper(co.getId())!=null){
					dataList.add(co);
				}else if(co.getCoachType()==2){
					dataList.add(co);
				}else if(co.getCoachType()==3 && co.getReserve_me()==1){
					dataList.add(co);
				}
				
			}
		}
		map.put("list", dataList);
		map.put("Area", FinalList.getArea(cityName, regionService));
		map.put("Sort", FinalList.getCoachSort());
		map.put("Services", FinalList.getCourseServices(dictionariesService));
		map.put("user", getUser(request));
		return "/weixin/coach_list";
	}

}
