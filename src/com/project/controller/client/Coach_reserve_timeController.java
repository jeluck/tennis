package com.project.controller.client;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.Constants;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach_reserve_time;
import com.project.service.ICoach_reserve_timeService;

@Controller("coach_reserve_timeWebController")
@RequestMapping(value="/")
public class Coach_reserve_timeController {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private ICoach_reserve_timeService coach_reserve_timeService;
	
	@RequestMapping(value = "crt_list" )
	@ResponseBody
	public Object getCrtList(HttpServletRequest request,String date){
		try {
			List<Coach_reserve_time> crtList = coach_reserve_timeService.getByTime(date);
			
			return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(),crtList);
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}
}
