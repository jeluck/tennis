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
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Activity;
import com.project.pojo.Playground;
import com.project.pojo.Region;
import com.project.service.IActivityService;
import com.project.service.IPlaygroundService;
import com.project.service.IRegionService;
import com.project.util.CommonUtil;
import com.project.util.LatitudeUtil;

@Controller("webWechatPalygroundController")
@RequestMapping(value="/")
public class PalygroundController {
	@Resource
	private IRegionService regionService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private IActivityService activityService;
	
	@RequestMapping("toPalygroundData")
	public String wechatMessageList(String areaid,String type,String services,String sort,
									String lat,String lng,String pdName,String cityName,
									HttpServletRequest request,ModelMap map,@RequestParam(defaultValue="1")int pageNumber) {
		int city_show_id = 0;
		CommonUtil.printHTTP(request);
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
		paraMap.put("type", type);
		paraMap.put("services", services);
		paraMap.put("sort", sort);
		paraMap.put("lat", lat);
		paraMap.put("lng", lng);
		paraMap.put("pdName", pdName);
		PageFinder<Playground> playgroundList = playgroundService.getPlaygroundListByMap(paraMap,pageNumber,Constants.FOREGROUND_PAGESIZE);	//场馆
		List<Playground> dataList = new ArrayList<Playground>();
		if(playgroundList != null && playgroundList.getDataList()!=null && playgroundList.getDataList().size()>0){
			for (Playground po : playgroundList.getDataList()) {
				
				//距离
				if(CommonUtil.NotEmpty(lat) && CommonUtil.NotEmpty(lng)){
					double distance = LatitudeUtil.Distance(Double.valueOf(lng.toString()),Double.valueOf(lat.toString()), po.getCoordinateslongitude(), po.getCoordinateslatitude());
					String s1 = String.valueOf(distance);
					String s2 = s1.substring(0,s1.indexOf("."));
					int i = Integer.valueOf(s2); 
					po.setDistanceMeters(i);
				}else{
					po.setDistanceMeters(0);
				}
				
				//活动
				Activity activity = activityService.getActivityByPdId(po.getId());
				if(activity!=null){
					po.setHuodong(activity.getTitle());
				}else{
					po.setHuodong("");
				}
				dataList.add(po);
			}
		}
		map.put("list", dataList);
		map.put("Area", FinalList.getArea(cityName, regionService));
		map.put("CourtType", FinalList.getCourt());
		map.put("Services", FinalList.getPlaygroundServices());
		map.put("Sort", FinalList.getPlaygroundSort());
		return "/weixin/palyground_list";
	}	
}	
