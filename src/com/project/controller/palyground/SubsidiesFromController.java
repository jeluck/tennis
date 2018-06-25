package com.project.controller.palyground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.common.ConfigKey;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.pojo.vo.OrderVo;
import com.project.pojo.vo.SubsidiesFrom;
import com.project.service.ISubsidiesFromService;
import com.project.service.ISystemConfigService;
import com.project.util.CommonUtil;

@Controller("adminsubsidiesFromController")
@RequestMapping(value = "/pgm")
public class SubsidiesFromController extends BaseController{
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SubsidiesFromController.class);
	
	@Resource
	private ISubsidiesFromService subsidiesFromService;
	@Resource
	private ISystemConfigService systemConfigService;
	/**
	 * 补贴结算表
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "checkSubsidiesFrom")
	public String checkSubsidiesFrom(HttpServletRequest request,ModelMap map,OrderVo o){
		
		List<SubsidiesFrom> list = subsidiesFromService.getSubsidiesFrom(o);
		map.put("dataList",list);
		map.put("start_time",o.getStart_time());
		map.put("end_time",o.getEnd_time());
		
		/***
		 * 补贴发放时间设置(单位:天)
		 */
		String subsidies_grant_time=systemConfigService.getConfigValueByKey(ConfigKey.SUBSIDIES_GRANT_TIME,"5");//取系统配置值;
		/***
		 * 交易结算时间设置(单位:天)
		 */
		String trade_balance_time=systemConfigService.getConfigValueByKey(ConfigKey.TRADE_BALANCE_TIME,"5");//取系统配置值;
		map.put("subsidies_grant_time",subsidies_grant_time);
		map.put("trade_balance_time",trade_balance_time);
		
		
		return "admin/subsidiesfrom/subsidiesfrom_list";
	}
	
	
	/**
	 * 补贴结算表(财务用)
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "checkSubsidiesFrom_forFinance")
	public String finance(HttpServletRequest request,ModelMap map,OrderVo o){
		
		List<SubsidiesFrom> list = subsidiesFromService.getSubsidiesFrom(o);
		map.put("dataList",list);
		map.put("start_time",o.getStart_time());
		map.put("end_time",o.getEnd_time());
		
		/***
		 * 补贴发放时间设置(单位:天)
		 */
		String subsidies_grant_time=systemConfigService.getConfigValueByKey(ConfigKey.SUBSIDIES_GRANT_TIME,"5");//取系统配置值;
		/***
		 * 交易结算时间设置(单位:天)
		 */
		String trade_balance_time=systemConfigService.getConfigValueByKey(ConfigKey.TRADE_BALANCE_TIME,"5");//取系统配置值;
		map.put("subsidies_grant_time",subsidies_grant_time);
		map.put("trade_balance_time",trade_balance_time);
		
		
		return "admin/subsidiesfrom/subsidiesfrom_list_finance";
	}
	
	/**
	 * 修改交易结算状态
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "trade_balance")
	public String editTrade_balance_status(HttpServletRequest request,ModelMap map,OrderVo o){
		subsidiesFromService.editTrade_balance_status(o.getOrderSubNo());
		
//		List<SubsidiesFrom> list = subsidiesFromService.getSubsidiesFrom(o);
//		map.put("dataList",list);
//		map.put("start_time",o.getStart_time());
//		map.put("end_time",o.getEnd_time());
//		return "admin/subsidiesfrom/subsidiesfrom_list";
		return "redirect:/pgm/checkSubsidiesFrom_forFinance.do?start_time="+o.getStart_time()+"&end_time="+o.getEnd_time();
	}
	
	
	/**
	 * 修改补贴发放状态
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "subsidies_grant")
	public String editSubsidies_grant_status(HttpServletRequest request,ModelMap map,OrderVo o){
		subsidiesFromService.editSubsidies_grant_status(o.getOrderSubNo());
		
//		List<SubsidiesFrom> list = subsidiesFromService.getSubsidiesFrom(o);
//		map.put("dataList",list);
//		map.put("start_time",o.getStart_time());
//		map.put("end_time",o.getEnd_time());
//		return "admin/subsidiesfrom/subsidiesfrom_list";
		
		return "redirect:/pgm/checkSubsidiesFrom_forFinance.do?start_time="+o.getStart_time()+"&end_time="+o.getEnd_time();
	}
	
	
	
	/**
	 * 导出列表
	 * @throws Exception 
	 * */
	@RequestMapping(value="exportreport")
	public String exportreport(HttpServletRequest request,HttpServletResponse response,OrderVo o) throws Exception{

		long startTime = System.currentTimeMillis();
    	logger.info("查询日期:"+o.getStart_time()+" ,  "+o.getEnd_time());
    	List<SubsidiesFrom> l = subsidiesFromService.getSubsidiesFrom(o);
		//导出报表
		List<Map<String,Object>> dataList=createExcelRecord(l,"manager");
		String columnNames[]={"下单日期","订单编号","订单状态","所在场馆","场地","打球日期","打球时间","所属教练","用户","总金额","交易结算状态","交易结算时间","补贴比例","补贴额","补贴发放状态","补贴发放时间"};//列名
        String keys[]    =  {"order_time","orderSubNo","status","playground","space","play_date","play_time","coach","user","total_amount","trade_balance_status","trade_balance_time","subsidies_proportion","subsidies_money","subsidies_grant_status","subsidies_grant_time"};//map中的key
		String name = "补贴结算明细表";
		
        this.exportExcel(request, response, name, keys, columnNames, dataList);
        return null;
	}
	
	
	/**
	 * 导出列表 (财务用)
	 * @throws Exception 
	 * */
	@RequestMapping(value="exportreport_finance")
	public String exportreport_finance(HttpServletRequest request,HttpServletResponse response,OrderVo o) throws Exception{

		long startTime = System.currentTimeMillis();
    	logger.info("查询日期:"+o.getStart_time()+" ,  "+o.getEnd_time());
    	List<SubsidiesFrom> l = subsidiesFromService.getSubsidiesFrom(o);
		//导出报表
		List<Map<String,Object>> dataList=createExcelRecord(l,"");
		String columnNames[]={"下单日期","订单编号","订单状态","总金额","交易结算状态","交易结算时间","补贴比例","补贴额","补贴发放状态","补贴发放时间"};//列名
        String keys[]    =  {"order_time","orderSubNo","status","total_amount","trade_balance_status","trade_balance_time","subsidies_proportion","subsidies_money","subsidies_grant_status","subsidies_grant_time"};//map中的key
		String name = "补贴结算明细表";
		
        this.exportExcel(request, response, name, keys, columnNames, dataList);
        return null;
	}
	
	
	private List<Map<String,Object>> createExcelRecord(List<SubsidiesFrom> rf_s,String whouse){
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "明细表");
        listmap.add(map);
        for(SubsidiesFrom o : rf_s){
        	String trade_balance ="未结算";			
        	String subsidies_grant ="未发放";
        	Map<String, Object> mapValue = new HashMap<String, Object>();
        	//"classroomName","studentName","studentCardNo","parentNickName","uphone","address"
//        	mapValue.put("topsaler", o.getTopsaler());
        	mapValue.put("order_time", o.getOrder_time());
        	mapValue.put("orderSubNo", o.getOrderSubNo());
        	mapValue.put("status", o.getStatus_str());
        	if(CommonUtil.NotEmpty(whouse)){
	        	mapValue.put("playground", o.getPlayground());
	        	mapValue.put("space", o.getSpace());
	        	mapValue.put("play_date", o.getPlay_date());
	        	mapValue.put("play_time", o.getPlay_time());
	        	mapValue.put("coach", o.getCoach());
	        	mapValue.put("user", o.getUser().getUsername());
        	}
        	mapValue.put("total_amount", o.getTotal_amount());
        	if(Constants.OKTODO == o.getTrade_balance_status()){
        		trade_balance ="已结算";
        	}
        	mapValue.put("trade_balance_status", trade_balance);
        	mapValue.put("trade_balance_time", o.getTrade_balance_time());
        	mapValue.put("subsidies_proportion", o.getSubsidies_proportion());
        	mapValue.put("subsidies_money", o.getSubsidies_money());
        	
        	if(Constants.OKTODO == o.getSubsidies_grant_status()){
        		subsidies_grant ="已发放";
        	}
        	mapValue.put("subsidies_grant_status", subsidies_grant);
        	mapValue.put("subsidies_grant_time", o.getSubsidies_grant_time());
        	listmap.add(mapValue);
        }
		return listmap;
	}
}
