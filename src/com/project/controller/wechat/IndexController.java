package com.project.controller.wechat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.ConfigKey;
import com.project.common.Constants;
import com.project.pojo.Advertise;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach;
import com.project.pojo.Course;
import com.project.pojo.Notice;
import com.project.pojo.Playground;
import com.project.pojo.Region;
import com.project.service.IAdvertiseService;
import com.project.service.ICoachService;
import com.project.service.IPlaygroundService;
import com.project.service.IRegionService;
import com.project.util.CommonUtil;

@Controller("indexWebWechatController")
@RequestMapping(value="/")
public class IndexController {
	@Resource
	private IRegionService regionService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private ICoachService coachService;
	@Resource
	private IAdvertiseService advertiseService;
	/**
	 * 首页数据
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("wechatIndex")
	public String wechatIndex(HttpServletRequest request,ModelMap map,String cityName) {
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
		List<Playground> playgroundList = playgroundService.getPlaygroundList(city_show_id,Constants.STICK1,"");	//场馆
		List<Coach> coachList = coachService.getCoachList(city_show_id,Constants.STICK1,"");						//教练
		List<Advertise> advertiseList = advertiseService.getAdvertiseByType(7);	
		map.put("advertise", advertiseList);
		map.put("playground", playgroundList);
		map.put("coach", coachList);
		return "/weixin/index";
	}
}
