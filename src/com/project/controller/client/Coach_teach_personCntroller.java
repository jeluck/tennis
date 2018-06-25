package com.project.controller.client;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.auth.ClientLoginAuth;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach;
import com.project.pojo.Coach_teach_person;
import com.project.service.ICoachService;
import com.project.service.ICoach_teach_personService;

@Controller("Coach_teach_personWebController")
@RequestMapping(value="/")
public class Coach_teach_personCntroller extends BaseController{

	private Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private ICoach_teach_personService Coach_teach_personService;
	@Resource
	private ICoachService coachService;
	
	
	/**
	 * 保存教练带人数
	 * @param request
	 * @param cst
	 * @return
	 */
	@RequestMapping(value = "savePerson" )
	@ResponseBody
	public Object saveCoach_teach_person(HttpServletRequest request,Integer coachId,Integer people_num,double price){
		logger.info("保存教练带人数....");
		long s_long = System.currentTimeMillis();		//开始计时
		try {
			Coach_teach_person ctp=new Coach_teach_person();
			ctp.setCoach_id(coachId);
			ctp.setPeople_num(people_num);
			ctp.setPrice(Float.valueOf(String.valueOf(price)));
			
			//s add lzy
			Coach coach = coachService.getcoachById(coachId);
			if(people_num == 1){
				coach.setMoney(price);
				coach.setPrice(price+10);
			}
			if(coach.getMoney()==0 && people_num!=1){
				coach.setMoney(price);
				coach.setPrice(price+10);
			}
				
			coachService.mergePlayground(coach);
			//e
			
			List<Coach_teach_person> ctpList=Coach_teach_personService.getCoach_teach_personByCoachId(coachId);
			
			for(int i=0;i<ctpList.size();i++){
				if(ctpList.get(i).getPrice()==0){
					Coach_teach_personService.deleteById(ctpList.get(i).getId());
				}
			}
			
			Coach_teach_personService.saveCoach_teach_person(ctp);
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SYSTEM_ERROR.getStatus(), "成功", "失败");
		}
	}
	
	/**
	 * 修改时间信息
	 * @param request
	 * @param cst
	 * @return
	 */
	@ClientLoginAuth
	@RequestMapping(value = "update_ctp.do" )
	public String updateCoach_teach_person(HttpServletRequest request,Coach_teach_person ctp){
		try {
			Coach_teach_personService.updateCoach_teach_person(ctp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除信息，根据ID删除
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete_ctp" )
	@ResponseBody
	public Object deleteCoach_teach_person(HttpServletRequest request,int id){
		try {
			Coach_teach_personService.deleteById(id);
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "失败");
		}
	}
	
	@RequestMapping(value = "getPersonByCoachId" )
	@ResponseBody
	public Object getPersonByCoachId(HttpServletRequest request,Integer coachId){
		List<Coach_teach_person> ctpList= Coach_teach_personService.getCoach_teach_personByCoachId(coachId);
		
		return new BusinessResponse(
				Constants.OperationResult.SUCCESS.getStatus(), "成功", ctpList);
	}
	
	@RequestMapping(value = "getPersonByCoachIdToOne" )
	@ResponseBody
	public Object getPersonByCoachIdToOne(HttpServletRequest request,Integer coachId){
		Coach_teach_person ctp=Coach_teach_personService.getCoach_teach_personByCidAndper(coachId);
		return new BusinessResponse(
				Constants.OperationResult.SUCCESS.getStatus(), "成功", ctp);
	}
}
