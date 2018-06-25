package com.project.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.auth.UserRightAuth;
import com.project.controller.BaseController;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Space;
import com.project.pojo.Space_time_price;
import com.project.service.ICoach_set_timeService;
import com.project.service.ISpaceService;
import com.project.service.ISpace_time_priceService;
import com.project.service.ITimeMoenyService;

/**
 * 教练场地时间修改
 * 
 * @author Administrator
 *
 */
@Controller("adminCoachsettimeController")
@RequestMapping(value = "/pgm")
public class Coach_set_timeController extends BaseController {

	@Resource
	private ICoach_set_timeService coach_set_timeService;
	@Resource
	private ITimeMoenyService timeMoenyService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
	@Resource
	private ISpaceService spaceService;

	/**
	 * 去修改时间页面页
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "toeditcoachspace_time_price")
	public String toeditspace_time_price(HttpServletRequest request,
			ModelMap map, int spaceId, int coachId) {
		map.put("coachId", coachId);
		map.put("tiemMoenyList",coach_set_timeService.getCoach_set_timeByCoachId(coachId, 0,0));
		map.put("spaceId", spaceId);
		
		Integer size=space_time_priceService.findBySpaceId(spaceId).size();
		if(size!=0){
			map.put("is_edit", "is_edit");
		}
		
		return "admin/space/coach_space_time_price_edit";
	}

	/**
	 * 修改时间
	 * 
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "editcoachspace_time_price")
	public String editcoachspace_time_price(HttpServletRequest request,
			int spaceId, ModelMap map) throws Exception {
		int coachId = Integer.valueOf(request.getParameter("coachId"));
		String[] gprice = request.getParameterValues("gprice");
		String[] jprice = request.getParameterValues("jprice");
		String[] gid = request.getParameterValues("gid");
		String[] jid = request.getParameterValues("jid");
		for (int i = 0; i < jid.length; i++) {
			for (int j = 0; j < jprice.length; j++) {
				if (i == j) {
					Coach_set_time o = coach_set_timeService
							.getCoach_set_timeById(Integer.valueOf(jid[i]));
					o.setPrice(Double.valueOf(jprice[j]));
					coach_set_timeService.updateCoach_set_time(o);
					break;
				}
			}
		}
		for (int i = 0; i < gid.length; i++) {
			for (int j = 0; j < gprice.length; j++) {
				if (i == j) {
					Coach_set_time o = coach_set_timeService
							.getCoach_set_timeById(Integer.valueOf(gid[i]));
					o.setPrice(Double.valueOf(gprice[j]));
					coach_set_timeService.updateCoach_set_time(o);
					break;
				}
			}
		}

		for (int m = 0; m < 7; m++) {
			Date date = new Date();// 取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, m);// 把日期往后增加一天.整数往后推,负数往前移动
			int week = calendar.get(Calendar.DAY_OF_WEEK);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = sdf.format(calendar.getTime());
			if (week == 1 || week == 7) {
				for (Coach_set_time t : coach_set_timeService.getCoach_set_timeByCoachId(coachId, 0,0)) {
					if (t.getTime_type() == 2) {
						Space_time_price s = new Space_time_price();
						s.setDate(dateStr);
						s.setPrice(t.getPrice());
						s.setTime(t.getTime());
						s.setSpace_id(new Space());
						s.getSpace_id().setId(spaceId);
						try {
							space_time_priceService.saveSpace_time_price(s);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				for (Coach_set_time t : coach_set_timeService.getCoach_set_timeByCoachId(coachId, 0,0)) {
					if (t.getTime_type() == 1) {
						Space_time_price s = new Space_time_price();
						s.setDate(dateStr);
						s.setPrice(t.getPrice());
						s.setTime(t.getTime());
						s.setSpace_id(new Space());
						s.getSpace_id().setId(spaceId);
						try {
							space_time_priceService.saveSpace_time_price(s);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return "redirect:coach_space_list.do?oid=" + coachId;
	}

	
	/**
	 * 驻场教练去修改时间页面页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "toeditspace_time_money")
	public String toeditspace_time_money(HttpServletRequest request,
			ModelMap map,  int coachId,int pdId) {
		map.put("coachId", coachId);
		map.put("tiemMoenyList",coach_set_timeService.getCoach_set_timeByCoachId(coachId, 0,0));
		List<Space> spaceList = spaceService.getPlaygroundSpaceBy_PGid(pdId, 0);
		map.put("spaceList", spaceList);
		return "admin/coach/coach_space_time_price_edit";
	}
	
	
	/**
	 * 驻场教练修改时间
	 * 
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "editcoachspace_time_money")
	public String editcoachspace_time_money(HttpServletRequest request,
			 ModelMap map) throws Exception {
		String[] gprice = request.getParameterValues("gprice");  //工作日价格
		String[] jprice = request.getParameterValues("jprice");  //节假日价格
		String[] gid = request.getParameterValues("gid");	//工作日ID
		String[] jid = request.getParameterValues("jid");	//节假日ID
		String[] gspaceId = request.getParameterValues("gspaceId");   //工作日时间段是否工作
		String[] jspaceId = request.getParameterValues("jspaceId");	  //节假日时间段是否工作	
		for (int i = 0; i < jid.length; i++) {
			for (int j = 0; j < jprice.length; j++) {
				for (int t = 0; t < jspaceId.length; t++) {
					if (i == t && i ==j) {
						Coach_set_time o = coach_set_timeService.getCoach_set_timeById(Integer.valueOf(jid[i]));
						o.setPrice(Double.valueOf(jprice[j]));
						o.setSpace_id(Integer.valueOf(jspaceId[t]));
						coach_set_timeService.updateCoach_set_time(o);
						break;
					}
				}
			}
		}
		for (int i = 0; i < gid.length; i++) {
			for (int j = 0; j < gprice.length; j++) {
				for (int t = 0; t < gspaceId.length; t++) {
					if ( i == t && i ==j) {
						Coach_set_time o = coach_set_timeService.getCoach_set_timeById(Integer.valueOf(gid[i]));
						o.setPrice(Double.valueOf(gprice[j]));
						o.setSpace_id(Integer.valueOf(gspaceId[t]));
						coach_set_timeService.updateCoach_set_time(o);
						break;
					}
				}
			}
		}
		
		return "redirect:coach_list.do";
	}
}
