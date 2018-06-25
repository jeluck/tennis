package com.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.pojo.Activity;
import com.project.pojo.Region;
import com.project.service.IRegionService;
import com.project.util.CommonUtil;

@Controller("adminregionController")
@RequestMapping(value="/admin")
public class RegionController {
	
	@Resource
	private IRegionService regionService;
	
	/**
	 * 去省份页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "region")
	@RequestMapping(value = "region_province_list")
	public String toprovince_list(HttpServletRequest request, ModelMap map,@RequestParam(defaultValue="1")int pageNumber){
		map.put("provinceList", regionService.getProvince(pageNumber, Constants.BACKGROUND_PAGESIZE));
		
		return "admin/region/region_province_list";
	}
	
	/**
	 * 去市
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "region")
	@RequestMapping(value = "region_city_list")
	public String tocity_list(HttpServletRequest request, ModelMap map,int cityid,@RequestParam(defaultValue="1")int pageNumber){
		map.put("cityList", regionService.getCity(cityid,pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("cityid", cityid);
		return "admin/region/region_city_list";
	}
	
	/**
	 * 去区
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "region")
	@RequestMapping(value = "region_area_list")
	public String toarea_list(HttpServletRequest request, ModelMap map,int cityid,@RequestParam(defaultValue="1")int pageNumber){
		map.put("cityid", cityid);
		map.put("areaList", regionService.getArea(cityid,pageNumber, Constants.BACKGROUND_PAGESIZE));
		
		return "admin/region/region_area_list";
	}
	
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "region")
	@RequestMapping(value = "toregion_area_edit")
	public String toEditActivity(HttpServletRequest request, ModelMap map,int oid,Integer cityid,String edit,String backurl){
		String check = request.getParameter("check");
		if(cityid!=null){
			map.put("cityid", cityid);
		}
		map.put("check", check);
		map.put("o", regionService.getRegionById(oid));
		map.put("edit", edit);
		map.put("backurl", backurl);
		return "admin/region/region_area_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "region")
	@RequestMapping(value = "edit_region_area")
	public String editActivity(HttpServletRequest request, ModelMap map,Region o,Integer cityid){
		try {
			if(cityid==null){
				o.setParent_id(1);
			}else{
				o.setParent_id(cityid);
			}
			regionService.updateRegion(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(cityid==null){
			return "redirect:region_province_list.do";
		}
		return "redirect:region_area_list.do?cityid="+cityid;
	}
	
	
	/**
	 * add by lxc	2015-11-28
	 * 增加省或市或区
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "region")
	@RequestMapping(value = "to_add_region_area")
	public String to_add_region_area(HttpServletRequest request, ModelMap map,String cityid,String add,String backurl){
		if(CommonUtil.NotEmpty(cityid)){
			map.put("o", regionService.getRegionById(Integer.parseInt(cityid)));
		}else{
			map.put("o", regionService.getRegionById(1));
		}
		map.put("cityid", cityid);
		if(backurl!=null && backurl.indexOf("pageNumber")<0){
			String pageNumber = request.getParameter("pageNumber");
			backurl+="&pageNumber="+pageNumber;
			System.err.println(backurl);
		}
		map.put("add", add);
		map.put("backurl", backurl);
		return "admin/region/to_add_region_area";
	}
	
	/**
	 * add by lxc	2015-11-28
	 * 增加省或市或区
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "region")
	@RequestMapping(value = "add_region_area")
	public String to_add_region_area(HttpServletRequest request, ModelMap map,Region r,String backurl){
		Region newr = regionService.saveRegion(r);
		System.err.println(backurl);
		return  "redirect:"+backurl;
	}
}
