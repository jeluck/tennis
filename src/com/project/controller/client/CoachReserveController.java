package com.project.controller.client;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.Constants;
import com.project.pojo.BusinessResponse;
import com.project.pojo.CoachReserve;
import com.project.service.ICoachReserveService;

@Controller("clientcoachReserveController")
@RequestMapping("/")
public class CoachReserveController {
	
	@Resource
	private ICoachReserveService coachreserveService;
	
	/**
	 * 获取教练预约的单个数据
	 * @param request
	 * @param cityName
	 * @return
	 */
	@RequestMapping("coachReserve")
	@ResponseBody
	public Object getCoachReserve(HttpServletRequest request,String date,Integer timepoint,Integer coachId){
		CoachReserve c=coachreserveService.getCoachReserve(date, coachId, timepoint);
		
		return new BusinessResponse(
				Constants.OperationResult.SUCCESS.getStatus(), "成功", c);
	}
}
