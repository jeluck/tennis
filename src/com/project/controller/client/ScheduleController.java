package com.project.controller.client;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.Constants;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Schedule;
import com.project.pojo.Space_time_price;
import com.project.service.IScheduleService;
import com.project.service.ISpace_time_priceService;

@Controller("clientScheduleController")
@RequestMapping("/")
public class ScheduleController {
	@Resource
	private IScheduleService scheduleService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
	
	@RequestMapping(value="changSche")
	@ResponseBody
	public Object changSche(HttpServletRequest request,Integer timepoint,String week,Integer activeId,Integer stpId,Integer belong){
		Schedule s=scheduleService.getScheduleAll(timepoint, week, activeId, belong);
		
		try {
			if(s==null){
				s=new Schedule();
				s.setActiveId(activeId);
				s.setBelong(belong);
				s.setTimepoint(timepoint);
				s.setWeek(week);
				if(stpId!=0){
					Space_time_price stp= space_time_priceService.findById(stpId);
					stp.setIs_reserve(2);
					space_time_priceService.updatespace_time_price(stp);
				}
				scheduleService.saveSchedule(s);
				
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "成功", "成功");
			}else{
				if(stpId>0){
					Space_time_price stp= space_time_priceService.findById(stpId);
					stp.setIs_reserve(0);
					space_time_priceService.updatespace_time_price(stp);
				}
				scheduleService.deleteSchedule(s);
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "成功", "删除");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "失败");
		}
	}
	
	@RequestMapping(value="getSche")
	@ResponseBody
	public Object getSche(HttpServletRequest request,Integer timepoint,String week,Integer activeId,Integer belong){
		Schedule s=scheduleService.getScheduleAll(timepoint, week, activeId, belong);
		if(s!=null){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "成功");
		}else{
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "失败");
		}
		
	}
}
