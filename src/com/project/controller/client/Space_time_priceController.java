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
import com.project.pojo.Space;
import com.project.pojo.Space_time_price;
import com.project.service.ISpaceService;
import com.project.service.ISpace_time_priceService;

@Controller("clentspace_time_priceController")
@RequestMapping("/")
public class Space_time_priceController {
	
	@Resource
	private ISpace_time_priceService space_time_priceService;
	@Resource
	private ISpaceService spaceService;

	@RequestMapping(value = "getspace_time_price")
	@ResponseBody
	public Object getSpace(ModelMap map, HttpServletRequest request,Integer spaceId,String date,Integer playgroundId,Integer status){
		
		
		List<Space_time_price> list = space_time_priceService.findByDate(spaceId, date);
        return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), list);
	}
	
	@RequestMapping(value = "getspace_time_priceByid")
	@ResponseBody
	public Object getSpaceByid(ModelMap map, HttpServletRequest request,Integer Id){
		Space_time_price list = space_time_priceService.findById(Id);
        return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), list);
	}
	
	@RequestMapping(value = "getspaceBystpid")
	@ResponseBody
	public Object getspaceBystpid(HttpServletRequest request,Integer Id){
		Space s=spaceService.getSpace(space_time_priceService.findById(Id).getSpace_id().getId());
		
		return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), s);
	}
	
	
}
