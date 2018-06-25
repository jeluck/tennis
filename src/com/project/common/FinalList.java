package com.project.common;

import java.util.ArrayList;
import java.util.List;

import com.project.pojo.Dictionaries;
import com.project.pojo.Region;
import com.project.pojo.vo.Search_terms;
import com.project.service.IDictionariesService;
import com.project.service.IRegionService;
import com.project.util.CommonUtil;

public class FinalList {
	
	/**
	 * 场馆周边服务
	 * @return
	 */
	public static List<Search_terms> getPlaygroundServices(){
		List<Search_terms> list = new ArrayList<Search_terms>();
		Search_terms st9 =new Search_terms();
		st9.setKey("");
		st9.setValue("默认");
		list.add(st9);
		Search_terms st =new Search_terms();
		st.setKey("wifi");
		st.setValue("wifi");
		list.add(st);
		Search_terms st1 =new Search_terms();
		st1.setKey("equipment");
		st1.setValue("器材");
		list.add(st1);
		Search_terms st2 =new Search_terms();
		st2.setKey("locker_room");
		st2.setValue("更衣室");
		list.add(st2);
		Search_terms st3 =new Search_terms();
		st3.setKey("lockers");
		st3.setValue("储物柜");
		list.add(st3);
		Search_terms st4 =new Search_terms();
		st4.setKey("shower");
		st4.setValue("淋浴");
		list.add(st4);
		Search_terms st5 =new Search_terms();
		st5.setKey("vip_room");
		st5.setValue("贵宾室");
		list.add(st5);
		Search_terms st6 =new Search_terms();
		st6.setKey("equipment_shop");
		st6.setValue("装备店");
		list.add(st6);
		Search_terms st7 =new Search_terms();
		st7.setKey("food");
		st7.setValue("食品");
		list.add(st7);
		Search_terms st8 =new Search_terms();
		st8.setKey("parking_lot");
		st8.setValue("停车场");
		list.add(st8);
		return list;
	}
	
	/**
	 * 全城
	 * @param cityName
	 * @param regionService
	 * @return
	 */
	public static List<Search_terms> getArea(String cityName,IRegionService regionService){
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
		List<Region> regionList = regionService.getArea(String.valueOf(city_show_id));
		List<Search_terms> alist = new ArrayList<Search_terms>();
		Search_terms st8 =new Search_terms();
		st8.setKey("");
		st8.setValue("默认");
		alist.add(st8);
		if(regionList.size()>0){
			for (Region region : regionList) {
				Search_terms area =new Search_terms();
				area.setKey(String.valueOf(region.getRegion_id()));
				area.setValue(region.getRegion_name());
				alist.add(area);
			}
		}
		return alist;
	}
	
	/**
	 * 场馆类型
	 * @return
	 */
	public static List<Search_terms> getCourt() {
		List<Search_terms> list = new ArrayList<Search_terms>();
		Search_terms st8 =new Search_terms();
		st8.setKey("");
		st8.setValue("默认");
		list.add(st8);
		Search_terms st =new Search_terms();
		st.setKey("site_type红土");
		st.setValue("红土");
		list.add(st);
		Search_terms st1 =new Search_terms();
		st1.setKey("site_type硬地");
		st1.setValue("硬地");
		list.add(st1);
		Search_terms st2 =new Search_terms();
		st2.setKey("site_type草地");
		st2.setValue("草地");
		list.add(st2);
		Search_terms st3 =new Search_terms();
		st3.setKey("site_type地毯");
		st3.setValue("地毯");
		list.add(st3);
		Search_terms st4 =new Search_terms();
		st4.setKey("court_type普通");
		st4.setValue("普通");
		list.add(st4);
		Search_terms st5 =new Search_terms();
		st5.setKey("court_type智能");
		st5.setValue("智能");
		list.add(st5);
		Search_terms st6 =new Search_terms();
		st6.setKey("space_type室内");
		st6.setValue("室内");
		list.add(st6);
		Search_terms st7 =new Search_terms();
		st7.setKey("space_type室外");
		st7.setValue("室外");
		list.add(st7);
		
		return list;
	}
	
	
	/**
	 * 场馆排序
	 * @return
	 */
	public static List<Search_terms> getPlaygroundSort() {
		List<Search_terms> list = new ArrayList<Search_terms>();
		Search_terms st8 =new Search_terms();
		st8.setKey("");
		st8.setValue("默认");
		st8.setType("");
		list.add(st8);
		Search_terms st =new Search_terms();
		st.setKey("distance_farToNearly");
		st.setValue("距离从远到近");
		st.setType("1");
		list.add(st);
		Search_terms st1 =new Search_terms();
		st1.setKey("distance_nearlyToFar");
		st1.setValue("距离从近到远");
		st1.setType("2");
		list.add(st1);
		Search_terms st2 =new Search_terms();
		st2.setKey("price_lowToHigh");
		st2.setValue("价格从低到高");
		st2.setType("3");
		list.add(st2);
		Search_terms st3 =new Search_terms();
		st3.setKey("price_highToLow");
		st3.setValue("价格从高到低");
		st3.setType("4");
		list.add(st3);
		Search_terms st4 =new Search_terms();
		st4.setKey("evaluation_highToLow");
		st4.setValue("评价从高到低");
		st4.setType("5");
		list.add(st4);
		Search_terms st5 =new Search_terms();
		st5.setKey("evaluation_lowToHigh");
		st5.setValue("评价从低到高");
		st5.setType("6");
		list.add(st5);
		Search_terms st6 =new Search_terms();
		st6.setKey("On_line");
		st6.setValue("在线预定");
		st6.setType("");
		list.add(st6);
		Search_terms st7 =new Search_terms();
		st7.setKey("Telephone");
		st7.setValue("电话预定");
		st7.setType("");
		list.add(st7);
		return list;
	}
	
	/**
	 * 教练排序
	 * @return
	 */
	public static List<Search_terms> getCoachSort() {
		List<Search_terms> list = new ArrayList<Search_terms>();
		Search_terms st8 =new Search_terms();
		st8.setKey("");
		st8.setValue("默认");
		st8.setType("");
		list.add(st8);
		Search_terms st =new Search_terms();
		st.setKey("distance_farToNearly");
		st.setValue("距离从远到近");
		st.setType("1");
		list.add(st);
		Search_terms st1 =new Search_terms();
		st1.setKey("distance_nearlyToFar");
		st1.setValue("距离从近到远");
		st1.setType("2");
		list.add(st1);
		Search_terms st2 =new Search_terms();
		st2.setKey("price_lowToHigh");
		st2.setValue("价格从低到高");
		st2.setType("3");
		list.add(st2);
		Search_terms st3 =new Search_terms();
		st3.setKey("price_highToLow");
		st3.setValue("价格从高到低");
		st3.setType("4");
		list.add(st3);
		Search_terms st4 =new Search_terms();
		st4.setKey("evaluation_highToLow");
		st4.setValue("评价从高到低");
		st4.setType("5");
		list.add(st4);
		Search_terms st5 =new Search_terms();
		st5.setKey("evaluation_lowToHigh");
		st5.setValue("评价从低到高");
		st5.setType("6");
		list.add(st5);
		Search_terms st6 =new Search_terms();
		st6.setKey("Seniority_highToLow");
		st6.setValue("教龄从高到低");
		st6.setType("7");
		list.add(st6);
		Search_terms st7 =new Search_terms();
		st7.setKey("Seniority_lowToHigh");
		st7.setValue("教龄从低到高");
		st7.setType("8");
		list.add(st7);
		return list;
	}
	

	/**
	 * 教练服务
	 * @return
	 */
	public static List<Search_terms> getCourseServices(IDictionariesService o){
		List<Dictionaries> dList = o.getDictionaries(Constants.SERVICE_COACH);
		List<Search_terms> list = new ArrayList<Search_terms>();
		Search_terms st8 =new Search_terms();
		st8.setKey("");
		st8.setValue("默认");
		st8.setType("");
		list.add(st8);
		for (Dictionaries ds : dList) {
			Search_terms st =new Search_terms();
			st.setKey(String.valueOf(ds.getId()));
			st.setValue(ds.getService());
			st.setType("");
			list.add(st);
		}
		return list;
	}
}
