package com.project.controller.client;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.Constants;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Region;
import com.project.service.IRegionService;

@Controller("regionWebController")
@RequestMapping(value="/")
public class RegionController {

    @Resource
    private IRegionService regionService;


    @RequestMapping(value = "province")
    @ResponseBody
    public Object getProvince(HttpServletRequest request,ModelMap map)
    {
        try {
            List<Region> list = regionService.getProvince();
            return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), list);
        } catch (Exception e) {
            e.printStackTrace();
            return new BusinessResponse(Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
        }
    }

    @RequestMapping(value = "city")
    @ResponseBody
    public Object getCity(HttpServletRequest request)
    {
        String provinceid = request.getParameter("province");
        try {
            List<Region> list = regionService.getCity(provinceid);
            return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), list);
        }
        catch (NumberFormatException e)
        {
            return new BusinessResponse(Constants.OperationResult.INVALID_PARAMETER.getStatus(),Constants.OperationResult.INVALID_PARAMETER.getMsg(), "");
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BusinessResponse(Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
        }
    }

    @RequestMapping(value = "area")
    @ResponseBody
    public Object getArea(HttpServletRequest request)
    {
        String cityid = request.getParameter("city");
        try {
            List<Region> list = regionService.getArea(cityid);
            return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), list);
        }
        catch (NumberFormatException e)
        {
            return new BusinessResponse(Constants.OperationResult.INVALID_PARAMETER.getStatus(),Constants.OperationResult.INVALID_PARAMETER.getMsg(), "");
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BusinessResponse(Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
        }
    }
    
    /**
     * 热门城市接口
     * @param request
     * @return
     */
    @RequestMapping(value = "hotRegion")
    @ResponseBody
    public Object hotRegion(HttpServletRequest request)
    {
    	List<Region> regionList = regionService.getRegionByStatus();
    	if(regionList.size()>0){
    		return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), "成功", regionList);
    	}else{
    		return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
    	}
    }

//    @RequestMapping(value = "allregion")
//    @ResponseBody
//    public Object allRegion(HttpServletRequest request)
//    {
//        List<RegionProvince> regionProvinceList = new ArrayList<RegionProvince>();
//        List<Region> plist = regionService.getProvince();
//        for(Region r : plist)
//        {
//            RegionProvince regionProvince = new RegionProvince();
//            regionProvince.setProvincename(r.getREGION_NAME());
//
//            List<Region> clist = regionService.getCity("" + r.getREGION_ID());
//            for(Region c : clist)
//            {
//                RegionCity regionCity = new RegionCity();
//                regionCity.setCityname(c.getREGION_NAME());
//
//                List<Region> alist = regionService.getArea("" + c.getREGION_ID());
//                for(Region a : alist)
//                {
//                    RegionArea regionArea = new RegionArea();
//                    regionArea.setAreaname(a.getREGION_NAME());
//
//                    regionCity.getList().add(regionArea);
//                }
//
//                regionProvince.getList().add(regionCity);
//            }
//
//            regionProvinceList.add(regionProvince);
//        }
//
//        return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), regionProvinceList);
//    }
}
