package com.project.controller.admin;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.pojo.Coach;
import com.project.pojo.Orderinfo;
import com.project.pojo.Playground;
import com.project.pojo.Subsidies;
import com.project.service.ICoachService;
import com.project.service.IOrderInfoService;
import com.project.service.IPlaygroundService;
import com.project.service.ISubsidiesService;
import com.project.util.CommonUtil;


@Controller("adminSubsidiesController")
@RequestMapping(value="/admin")
public class SubsidiesController {
	
	@Resource
	private ISubsidiesService subsidiesService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private ICoachService coachService;
	@Resource
	private IOrderInfoService orderInfoService;
	
	/**
	 * 场馆补贴去增加或者修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "toadd_subsidies")
	public String toAddEvents(HttpServletRequest request, ModelMap map,int oid,int id){
		map.put("oid", oid);
		Playground p = new Playground();
		p.setAuditStatus(Constants.AUDITSTATUS_THROUGH);
		List<Playground> listp =playgroundService.getPlaygroundListByObj(p);
		map.put("plist", listp);
		Subsidies o = subsidiesService.getSubsidiesById(id);
		map.put("o", o);
		return "admin/playground/subsidies_addOrUpdate";
	}
	
	
	
	/**
	 * 场馆补贴列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "subsidiesList")
	public String playground_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Playground o) {
		CommonUtil.printHTTP(request);
		o.setAuditStatus(-1);
		map.put("data_page",playgroundService.getPageFindePlayground(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/playground/subsidies";
	}
	
	/**
	 * 场馆    增加或修改
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "addOrUpdate_subsidies")
	public String addOrUpdate_subsidies(HttpServletRequest request, ModelMap map,Subsidies o) throws Exception{
		Subsidies sub = subsidiesService.getSubsidiesById(o.getId());
		sub.setMoney(o.getMoney()/100);
		Orderinfo order = new Orderinfo();
		order.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
		order.setActiveID(String.valueOf(sub.getZhe_id()));
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("status", "1");
		map1.put("year", sub.getYear());
		map1.put("month", sub.getMonth());
		List<Orderinfo> listo = orderInfoService.getOrderinfoByObj(order, map1);
		double jmoney = 0.0;			//有效交易额
		double subsidies_money = 0.0;   //补贴额
		if(listo != null){
			for (Orderinfo orderinfo : listo) {
				jmoney += orderinfo.getOrderPayTotalAmont();
			}
			subsidies_money = jmoney * sub.getMoney();
		}
		sub.setJmoney(jmoney);
		sub.setSubsidies_money(subsidies_money);
		subsidiesService.updateSubsidies(sub);
		int oid = Integer.valueOf(request.getParameter("oid"));
		return "redirect:sub_play_list.do?oid="+oid;
	}
	
	
	
	/**
	 * 教练   去增加或者修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "toadd_coach_subsidies")
	public String toadd_coach_subsidies(HttpServletRequest request, ModelMap map,int oid,int id){
		map.put("oid", oid);
		Subsidies o = subsidiesService.getSubsidiesById(id);
		map.put("o", o);
		Coach coach = new Coach();
		coach.setCoachType(0);
		coach.setVerified(1);
		List<Coach> clist = coachService.getCoachByObj(coach);
		map.put("list", clist);
		return "admin/coach/subsidies_addOrUpdate";
	}
	
	
	
	/**
	 * 教练 补贴  列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "coachsubsidiesList")
	public String coachsubsidiesList(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Coach o) {
		CommonUtil.printHTTP(request);
		o.setStatus(2);
		o.setVerified(1);
		map.put("data_page",coachService.getPageFindeCoach(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("company_name", o.getName());
		return "admin/coach/subsidies";
	}
	
	/**
	 * 教练补贴增加或修改
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "addOrUpdate_coach_subsidies")
	public String addOrUpdate_coach_subsidies(HttpServletRequest request, ModelMap map,Subsidies o) throws Exception{
		Subsidies sub = subsidiesService.getSubsidiesById(o.getId());
		sub.setMoney(o.getMoney()/100);
		
		Orderinfo order = new Orderinfo();
		order.setOrderType(Constants.DATATYPE.CoachType.getStatus());
		order.setActiveID(String.valueOf(sub.getZhe_id()));
		
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("status", "1");
		map1.put("year", sub.getYear());
		map1.put("month", sub.getMonth());
		
		List<Orderinfo> listo = orderInfoService.getOrderinfoByObj(order, map1);
		double jmoney = 0.0;			//有效交易额
		double subsidies_money = 0.0;   //补贴额
		if(listo != null){
			for (Orderinfo orderinfo : listo) {
				jmoney += orderinfo.getOrderPayTotalAmont();
			}
			subsidies_money = jmoney * sub.getMoney();
		}
		sub.setJmoney(jmoney);
		sub.setSubsidies_money(subsidies_money);
		subsidiesService.updateSubsidies(sub);
		int oid = Integer.valueOf(request.getParameter("oid"));
		return "redirect:sub_coach_list.do?oid="+oid;
	}
	
	
	
	/**
	 * 场馆批量添加补贴页面
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "tobatch_add_playSub")
	public String tobatch_add_playSub(HttpServletRequest request, ModelMap map,Subsidies o) throws Exception{
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		map.put("year", year);
		return "admin/playground/bathch_add_playSub";
	}
	
	/**
	 * 场馆批量添加补贴
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "batch_add_playSub")
	public String batch_add_playSub(HttpServletRequest request, ModelMap map,Subsidies o) throws Exception{
		
		//查询同年份和同月份的场馆补贴 如果存在则全部删除
		o.setZhe_type(2);
		List<Subsidies>  list = subsidiesService.getSubsidiesByObj(o);
		if(list != null){ 
			for (Subsidies subsidies : list) {
				subsidiesService.deleteSubsidies(subsidies);
			}
		}
		String year = o.getYear();
		String month = o.getMonth();
		float money = o.getMoney();
		//查询全部审核通过的场馆
		Playground p = new Playground();
		p.setAuditStatus(Constants.AUDITSTATUS_THROUGH);
		List<Playground> listp =playgroundService.getPlaygroundListByObj(p);
		if(listp != null){ 
			for (Playground playground : listp) {
				o = new Subsidies();
				o.setZhe_type(2);
				o.setYear(year);
				o.setMoney(money/100);
				o.setMonth(month);
				o.setZhe_id(playground.getId());
				o.setType( Constants.SUBSIDIES_APRIL);
				Orderinfo order = new Orderinfo();
				order.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
				order.setActiveID(String.valueOf(playground.getId()));
				Map<String,String> map1 = new HashMap<String,String>();
				map1.put("status", "1");
				map1.put("year", year);
				map1.put("month", month);
				List<Orderinfo> listo = orderInfoService.getOrderinfoByObj(order, map1);
				double jmoney = 0.0;			//有效交易额
				double subsidies_money = 0.0;   //补贴额
				if(listo != null){
					for (Orderinfo orderinfo : listo) {
						jmoney += orderinfo.getOrderPayTotalAmont();
					}
					subsidies_money = jmoney * o.getMoney();
				}
				o.setJmoney(jmoney);
				o.setSubsidies_money(subsidies_money);
				subsidiesService.saveSubsidies(o);
			}
		}
		return "redirect:subsidiesList.do";
	}
	
	
	/**
	 * 场馆补贴列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "sub_play_list")
	public String sub_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Subsidies o,int oid) {
		CommonUtil.printHTTP(request);
		o.setZhe_id(oid);
		o.setZhe_type(2);
		map.put("oid", oid);
		//查询全部审核通过的场馆
		Playground p = new Playground();
		p.setAuditStatus(Constants.AUDITSTATUS_THROUGH);
		List<Playground> listp =playgroundService.getPlaygroundListByObj(p);
		map.put("plist", listp);
		map.put("data_page",subsidiesService.getPageFindeSubsidies(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/playground/sub_list";
	}
	
	
	
	/**
	 * 教练批量添加补贴页面
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "tobatch_add_coachSub")
	public String tobatch_add_coachSub(HttpServletRequest request, ModelMap map,Subsidies o) throws Exception{
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		map.put("year", year);
		return "admin/coach/bathch_add_coachSub";
	}
	
	
	/**
	 * 教练批量添加补贴
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "batch_add_coachSub")
	public String batch_add_coachSub(HttpServletRequest request, ModelMap map,Subsidies o,int coachType) throws Exception{
		Coach coach = new Coach();
		coach.setCoachType(coachType);
		coach.setVerified(1);
		o.setZhe_type(1);
		//查询已审核的教练
		List<Coach> clist = coachService.getCoachByObj(coach);
		//查询同年份和同月份的教练补贴
		List<Subsidies>  list = subsidiesService.getSubsidiesByObj(o);
		if(list != null && clist != null){ 
			for (Subsidies subsidies : list) { 
				for (Coach c : clist) { 
					if(subsidies.getZhe_id() == c.getId()){  //对比所属编号和教练编号是否一致  一致则删除
						subsidiesService.deleteSubsidies(subsidies);
					}
				}
			}
		}
		String year = o.getYear();
		String month = o.getMonth();
		float money = o.getMoney();
		if(clist != null){ 
			for (Coach c : clist) {  //已审核的教练全部添加补贴
				o = new Subsidies();
				o.setZhe_type(1);
				o.setYear(year);
				o.setMoney(money/100);
				o.setMonth(month);
				o.setZhe_id(c.getId());
				o.setType( Constants.SUBSIDIES_APRIL);
				
				Orderinfo order = new Orderinfo();
				order.setOrderType(Constants.DATATYPE.CoachType.getStatus());
				order.setActiveID(String.valueOf(c.getId()));
				
				Map<String,String> map1 = new HashMap<String,String>();
				map1.put("status", "1");
				map1.put("year", year);
				map1.put("month", month);
				List<Orderinfo> listo = orderInfoService.getOrderinfoByObj(order, map1);
				double jmoney = 0.0;			//有效交易额
				double subsidies_money = 0.0;   //补贴额
				if(listo != null){
					for (Orderinfo orderinfo : listo) {
						jmoney += orderinfo.getOrderPayTotalAmont();
					}
					subsidies_money = jmoney * o.getMoney();
				}
				o.setJmoney(jmoney);
				o.setSubsidies_money(subsidies_money);
				subsidiesService.saveSubsidies(o);
			}
		}
		
		return "redirect:coachsubsidiesList.do";
	}
	
	
	/**
	 * 教练补贴列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "sub_coach_list")
	public String sub_coach_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Subsidies o,int oid) {
		CommonUtil.printHTTP(request);
		o.setZhe_id(oid);
		o.setZhe_type(1);
		map.put("oid", oid);
		//查询已审核的教练
		Coach coach = new Coach();
		coach.setCoachType(0);
		coach.setVerified(1);
		List<Coach> clist = coachService.getCoachByObj(coach);
		map.put("list", clist);
		map.put("data_page",subsidiesService.getPageFindeSubsidies(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/coach/sub_list";
	}
}
