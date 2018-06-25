package com.project.controller.admin;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.auth.UserRightAuth;
import com.project.pojo.Space;
import com.project.pojo.Space_time_price;
import com.project.pojo.TimeMoeny;
import com.project.service.ISpace_time_priceService;
import com.project.service.ITimeMoenyService;

@Controller("adminspace_time_priceController")
@RequestMapping(value="/pgm")
public class Space_time_priceController {
	
	@Resource
	private ITimeMoenyService timeMoenyService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
	
	/**
	 * 去修改时间页面页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "space_time_price")
	@RequestMapping(value = "toeditspace_time_price")
	public String toeditspace_time_price(HttpServletRequest request, ModelMap map,int spaceId,int playgroundId){
		map.put("tiemMoenyList", timeMoenyService.getTimeMoenyBySpaceId(spaceId));
		map.put("oid", playgroundId);
		map.put("spaceId", spaceId);
		Integer size=space_time_priceService.findBySpaceId(spaceId).size();
		if(size!=0){
			map.put("is_edit", "is_edit");
		}
		return "admin/space/space_time_price_edit";
	}
	
	/**
	 * 第一次保存时间
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "space_time_price")
	@RequestMapping(value = "add_space_time_price")
	public String addspace_time_price(HttpServletRequest request, ModelMap map,int spaceId,int playgroundId){
		String[] gprice=request.getParameterValues("gprice");
		String[] jprice=request.getParameterValues("jprice");
		String[] gid=request.getParameterValues("gid");
		String[] jid=request.getParameterValues("jid");
		String[] gmust=request.getParameterValues("gmust");
		String[] jmust=request.getParameterValues("jmust");
		int i=0;
		for(String id:gid){
			System.err.println(id);
			TimeMoeny timeMoeny=new TimeMoeny();
			timeMoeny = timeMoenyService.getTimeMoenyById(Integer.valueOf(id));
			System.err.println(timeMoeny);
			timeMoeny.setHelp_filed(Integer.valueOf(gmust[i]));
			timeMoeny.setPlayground_id(playgroundId);
			timeMoeny.setPrice(Double.valueOf(gprice[i]));
			timeMoenyService.updateTimeMoeny(timeMoeny);
			i++;
		}
		int j=0;
		for(String id:jid){
			TimeMoeny timeMoeny=new TimeMoeny();
			timeMoeny = timeMoenyService.getTimeMoenyById(Integer.valueOf(id));
			timeMoeny.setPrice(Double.valueOf(jprice[j]));
			timeMoeny.setPlayground_id(playgroundId);
			timeMoeny.setHelp_filed(Integer.valueOf(jmust[j]));
//			timeMoeny.setId(Integer.valueOf(id));
//			timeMoeny.setTime_type(2);
			timeMoenyService.updateTimeMoeny(timeMoeny);
			j++;
		}
		for(int m=0;m<7;m++){
			Date date=new Date();//取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
			int week=calendar.get(Calendar.DAY_OF_WEEK);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = sdf.format(calendar.getTime());
			if(week==1 || week==7){
				for(TimeMoeny t:timeMoenyService.getTimeMoenyBySpaceId(spaceId)){
					if(t.getTime_type()==2){
						Space_time_price s=new Space_time_price();
						s.setDate(dateStr);
						s.setPrice(t.getPrice());
						s.setTime(t.getHour());
						s.setSpace_id(new Space());
						s.getSpace_id().setId(spaceId);
						s.setMust_coach(t.getHelp_filed());
						s.setDateType(t.getTime_type());
						s.setMust_coach(t.getHelp_filed());
						try {
							space_time_priceService.saveSpace_time_price(s);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}else{
				for(TimeMoeny t:timeMoenyService.getTimeMoenyBySpaceId(spaceId)){
					if(t.getTime_type()==1){
						Space_time_price s=new Space_time_price();
						s.setDate(dateStr);
						s.setPrice(t.getPrice());
						s.setTime(t.getHour());
						s.setMust_coach(t.getHelp_filed());
						s.setSpace_id(new Space());
						s.getSpace_id().setId(spaceId);
						s.setDateType(t.getTime_type());
						s.setMust_coach(t.getHelp_filed());
						try {
							space_time_priceService.saveSpace_time_price(s);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return "redirect:space_list.do?oid="+playgroundId;
	}
	
	/**
	 * 之后的修改时间保存时间
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "space_time_price")
	@RequestMapping(value = "edit_space_time_price")
	public String editspace_time_price(HttpServletRequest request, ModelMap map,int spaceId,int playgroundId){
		String[] gprice=request.getParameterValues("gprice");
		String[] jprice=request.getParameterValues("jprice");
		String[] gid=request.getParameterValues("gid");
		String[] jid=request.getParameterValues("jid");
		String[] gmust=request.getParameterValues("gmust");
		String[] jmust=request.getParameterValues("jmust");
		int i=0;
		for(String id:gid){
			TimeMoeny timeMoeny=new TimeMoeny();
			timeMoeny = timeMoenyService.getTimeMoenyById(Integer.valueOf(id));
			System.err.println(timeMoeny);
			timeMoeny.setHelp_filed(Integer.valueOf(gmust[i]));
			timeMoeny.setPlayground_id(playgroundId);
			timeMoeny.setPrice(Double.valueOf(gprice[i]));
			timeMoenyService.updateTimeMoeny(timeMoeny);
			i++;
		}
		int j=0;
		for(String id:jid){
			TimeMoeny timeMoeny=new TimeMoeny();
			timeMoeny = timeMoenyService.getTimeMoenyById(Integer.valueOf(id));
			timeMoeny.setPrice(Double.valueOf(jprice[j]));
			timeMoeny.setPlayground_id(playgroundId);
			timeMoeny.setHelp_filed(Integer.valueOf(jmust[j]));
//			timeMoeny.setId(Integer.valueOf(id));
//			timeMoeny.setTime_type(2);
			timeMoenyService.updateTimeMoeny(timeMoeny);
			j++;
		}
		
		List<Space_time_price> stpList=space_time_priceService.findBySpaceId(spaceId);
		List<TimeMoeny> tmlist = timeMoenyService.getTimeMoenyBySpaceId(spaceId);
		for(TimeMoeny t:tmlist){
				
			for(Space_time_price stp:stpList){
				System.err.println(stp.getDateType());
				if(stp.getDateType()==t.getTime_type() && stp.getTime() == t.getHour()){
					stp.setPrice(t.getPrice());
					stp.setMust_coach(t.getHelp_filed());
					space_time_priceService.updatespace_time_price(stp);
				}
			}
		}
			
		
		return "redirect:space_list.do?oid="+playgroundId;
	}
}
