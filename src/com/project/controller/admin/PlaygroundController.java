package com.project.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.auth.UserRightAuth;
import com.project.common.ConfigKey;
import com.project.common.Constants;
import com.project.common.Settings;
import com.project.common.Constants.OperationResult;
import com.project.controller.BaseController;
import com.project.controller.client.CommonController;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Business_time;
import com.project.pojo.Coach;
import com.project.pojo.DateAssist;
import com.project.pojo.Manager;
import com.project.pojo.Peripheral_services;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundImg;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.Qualification_certificate;
import com.project.pojo.Region;
import com.project.pojo.ReturnReason;
import com.project.pojo.Space;
import com.project.pojo.Space_time_price;
import com.project.pojo.Subsidies;
import com.project.pojo.TimeMoeny;
import com.project.pojo.Weuser;
import com.project.service.IBusiness_timeService;
import com.project.service.ICertificateService;
import com.project.service.ICoachService;
import com.project.service.IPeripheral_servicesService;
import com.project.service.IPlaygroundImgService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IPlaygroundService;
import com.project.service.IRegionService;
import com.project.service.IReturnReasonService;
import com.project.service.ISpaceService;
import com.project.service.ISpace_time_priceService;
import com.project.service.ISubsidiesService;
import com.project.service.ISystemConfigService;
import com.project.service.ITimeMoenyService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;

@Controller("adminplaygroundWebController")
@RequestMapping(value = "/pgm")
public class PlaygroundController extends BaseController {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PlaygroundController.class); 
	
	@Resource
	private IPlaygroundService playgroundService;
    @Resource
    private IRegionService regionService;
    @Resource
    private IBusiness_timeService business_timeService;
    @Resource
	private ICertificateService certificateService;
    @Resource
    private IPeripheral_servicesService peripheral_services;
    @Resource
    private IPlaygroundImgService playgroundImgService;
    @Resource
    private IWeuserService weuserService;
    @Resource
    private ISpaceService spaceService;
    @Resource
    private ISpace_time_priceService space_time_priceService;
    @Resource
    private ITimeMoenyService timeMoenyService;
	@Resource
	private ISystemConfigService systemConfigService; 
	@Resource
	private ISubsidiesService subsidiesService;
	@Resource
	private IReturnReasonService returnReasonService;
	@Resource
	private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private ICoachService coachService;
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "playground_list")
	public String playground_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Playground o) {
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		if(pgm!=null){
			o.setPlaygroundmanager_id(pgm.getId());
		}
		o.setAuditStatus(0);
		o.setIs_locked(-1);
		map.put("k", pgm);
		map.put("update", request.getParameter("update"));
		map.put("data_page",playgroundService.getPageFindePlayground(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("company_name", o.getName());
		return "admin/playground/pg_list";
	}
	
	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "toaddplayground")
	public String toaddplayground(HttpServletRequest request, ModelMap map){
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		List<Region> list = regionService.getProvince();
		map.put("provincelist",list);
		return "admin/playground/pg_add";
	}
	
	/**
	 * 增加
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "add_playground")
	public String add_playground(HttpServletRequest request, ModelMap map,Playground o) {
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		if(pgm!=null){
			o.setPlaygroundmanager_id(pgm.getId());
		}
		
		try {
			

			
			Qualification_certificate qc = new Qualification_certificate();
			Qualification_certificate qc1 = saveCertificate(request,qc);
			o.setCertificate(qc1);
			o.setPservices(saveOrUpdatePs(request,0));
			o = playgroundService.savePlaygroundService(o);
			
			//添加场馆详情图片
		    PlaygroundImg pimg = new PlaygroundImg();
		    pimg.setPdId(o.getId());
		    String imgurl = o.getPdImg();
			int length = imgurl.lastIndexOf("/")+1;
			String imgurl_mulu = imgurl.substring(0, length);
			String imgUrl = imgurl.substring(imgurl.lastIndexOf("/")+1);
			imgUrl = imgUrl.substring(imgUrl.indexOf("_")+1);
		    pimg.setImg(imgurl_mulu+imgUrl);
		    playgroundImgService.savePlaygroundImg(pimg);
			
			//添加补贴
//			String money = systemConfigService.getConfigValueByKey(ConfigKey.PLAYGROUND_SUBSIDY_PROPORTION,"0.01");//取系统配置值  场馆补贴比例
//			String playground_type = systemConfigService.getConfigValueByKey(ConfigKey.PLAYGROUND_SUBSIDY_TYPE,"1");//取系统配置值  场馆补贴类型
//			Subsidies subsidies = new Subsidies();
//			subsidies.setMoney(Float.valueOf(money));
//			subsidies.setZhe_id(o.getId());
//			subsidies.setZhe_type(Constants.SUBSIDIES_PlAYGROUND);
//			subsidies.setType(Integer.valueOf(playground_type));
//			subsidies.setEnd_time(CommonController.getCurrYearLast());
//			subsidiesService.saveSubsidies(subsidies);
			
			
			//工作营业时间对象
			Business_time gb=new Business_time();
			gb.setDateType(1);
			gb.setEnd_time(Integer.valueOf(request.getParameter("gendtime")));
			gb.setStart_time(Integer.valueOf(request.getParameter("gopentime")));
			gb.setPlayground(new Playground());
			gb.getPlayground().setId(o.getId());
			
			//节假日营业时间对象
			Business_time jb=new Business_time();
			jb.setDateType(2);
			jb.setEnd_time(Integer.valueOf(request.getParameter("jendtime")));
			jb.setStart_time(Integer.valueOf(request.getParameter("jopentime")));
			jb.setPlayground(new Playground());
			jb.getPlayground().setId(o.getId());
			
			business_timeService.saveBusiness_time(gb);
			business_timeService.saveBusiness_time(jb);
			
			//当运营商用户新增场馆时,需要把银行账户赋给运营商教练	edit by lxc	2015-12-24		
			if(pgm!=null){
				Coach c = coachService.getcoachById(pgm.getCoachid());
				List<Playground> ps = playgroundService.getPlaygroundListByPlaygroundManagerId(pgm.getPgm_president());
				if(ps==null || ps.size()==1){
					
					if(!CommonUtil.NotEmpty(c.getBank()))c.setBank(o.getBank());   //开户银行
					if(!CommonUtil.NotEmpty(c.getZbank()))c.setZbank(o.getZbank());	//支行
					if(!CommonUtil.NotEmpty(c.getBankName()))c.setBankName(o.getBankName()); //户名
					if(!CommonUtil.NotEmpty(c.getBankZh()))c.setBankZh(o.getBankZh());		//银行帐号
					coachService.mergePlayground(c);
					Qualification_certificate qcc =  certificateService.getCertificateById(c.getCertificate().getId());	//教练的证书数据
					if(qcc!=null){
						if(!CommonUtil.NotEmpty(qcc.getPh7()))qcc.setPh7(qc1.getPh1());
						if(!CommonUtil.NotEmpty(qcc.getPh8()))qcc.setPh8(qc1.getPh2());
						if(!CommonUtil.NotEmpty(qcc.getPh9()))qcc.setPh9(qc1.getPh3());
						certificateService.updateCertificate(qcc);
					}
					
					
				}
			}//E
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:playground_list.do";
	}
	
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "toedit_playground")
	public String toedit_playground(HttpServletRequest request, ModelMap map,int oid,String isaud) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		Manager m = getManager(request);
		PlaygroundManager k = getPlaygroundManager(request);
		
		if(k==null && m==null){
			return "redirect:login.do";
		}
		map.put("k", k);
		map.put("check", check);
		map.put("is_aud", isaud);
		Playground playground = playgroundService.getPlaygroundById(oid);
		map.put("o",playground);
		map.put("gopentime", business_timeService.findByplaygroundId(oid,1));
		map.put("jopentime", business_timeService.findByplaygroundId(oid,2));
		String s=String.valueOf(playgroundService.getPlaygroundById(oid).getCityid());
		List<Region> list = regionService.getArea(s);
		List<Region> pList=regionService.getProvince();
		
		map.put("sid", regionService.getRegionById(playground.getCityid()).getParent_id());
		map.put("provincelist",pList);
		map.put("areaList", list);
		map.put("r",regionService.getRegionById(playground.getCityid()));
		map.put("ps", peripheral_services.getServices(playground.getPservices().getId()));
		map.put("qc", certificateService.getCertificateByTypeAndZheId(Constants.CERTIFICATE_VENUE, playground.getCertificate().getId()));
		ReturnReason o = new ReturnReason();
		o.setType(1);
		o.setS_id(oid);
		map.put("reasonList", returnReasonService.getReturnReasonlistByObj(o));
		if(CommonUtil.NotEmpty(isaud)){
			return "admin/playground/pg_check";
		}else{
			return "admin/playground/pg_edit";
		}
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "edit_playground")
	public String edit_playground_manager(HttpServletRequest request, ModelMap map,int pcId,Playground o) {
		Playground p = playgroundService.getPlaygroundById(o.getId());
		o.setEvaluate_score(p.getEvaluate_score());
		o.setCertificateValidityPeriod(p.getCertificateValidityPeriod());
		o.setProfit(p.getProfit());
		o.setAudCount(p.getAudCount());
		o.setEffective_time(p.getEffective_time());
		o.setStick(p.getStick());
		o.setScoreCount(p.getScoreCount());
		o.setSpType(p.getSpType());
		
		CommonUtil.printHTTP(request);
		int psId = 0;
		if(CommonUtil.NotEmpty(request.getParameter("psId"))){
			psId = Integer.valueOf(request.getParameter("psId"));
		}
		Manager manager = getManager(request);
		PlaygroundManager k = getPlaygroundManager(request);
		
		if(k==null && manager==null){
			return "redirect:login.do";
		}
		int flag = 0;
		try {
	    	
	    	Qualification_certificate qc = updateCertificate(request,pcId);
			o.setCertificate(qc);
			o.setPservices(saveOrUpdatePs(request,psId));
			if(CommonUtil.NotEmptyObject(manager)){ //是否是平台
				flag = 1;
			}
			if(CommonUtil.NotEmptyObject(k)){ //是否是场馆管理者
				if(o.getAuditStatus() == Constants.AUDITSTATUS_REFUSE){ //拒绝
					o.setAuditStatus(Constants.AUDITSTATUS_APPLY); 	//改为申请中
				}
			}
			
			playgroundService.mergePlayground(o);
			//工作营业时间对象
			Business_time gb=new Business_time();
//			gb.setId(Integer.valueOf(request.getParameter("gid")));
//			gb.setDateType(1);
//			gb.setEnd_time(Integer.valueOf(request.getParameter("gendtime")));
//			gb.setStart_time(Integer.valueOf(request.getParameter("gopentime")));
//			gb.setPlayground(new Playground());
//			gb.getPlayground().setId(o.getId());
			
			//节假日营业时间对象
			Business_time jb=new Business_time();
//			jb.setId(Integer.valueOf(request.getParameter("jid")));
//			jb.setDateType(2);
//			jb.setEnd_time(Integer.valueOf(request.getParameter("jendtime")));
//			jb.setStart_time(Integer.valueOf(request.getParameter("jopentime")));
//			jb.setPlayground(new Playground());
//			jb.getPlayground().setId(o.getId());
			
			gb=business_timeService.findById(Integer.valueOf(request.getParameter("gid")));
			jb=business_timeService.findById(Integer.valueOf(request.getParameter("jid")));
			
			//工作时间设置
			if(gb.getStart_time()==Integer.valueOf(request.getParameter("gopentime")) && gb.getEnd_time()==Integer.valueOf(request.getParameter("gendtime"))){
				
			}else{
				
				//都是大于的设置
				if(gb.getStart_time()>=Integer.valueOf(request.getParameter("gopentime")) && gb.getEnd_time()>=Integer.valueOf(request.getParameter("gendtime"))){
					List<Space> spaceList=spaceService.getPlaygroundSpaceBy_PGid(o.getId(), 0);
					//上午时间的设置
					for(int start=Integer.valueOf(request.getParameter("gopentime"));start<gb.getStart_time();start++){
						for(Space space:spaceList){
							TimeMoeny t=new TimeMoeny();
							t.setHour(start);
							t.setSpace(space);
							t.setPlayground_id(space.getPlayground_id().getId());
							t.setTime_type(1);
							t.setPrice(space.getPrice());
							t.setHelp_filed(2);
							
							timeMoenyService.saveTimeMoeny(t);
							for(int m=0;m<7;m++){
								Date date=new Date();//取时间
								Calendar calendar = new GregorianCalendar();
								calendar.setTime(date);
								calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
								int week=calendar.get(Calendar.DAY_OF_WEEK);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String dateStr = sdf.format(calendar.getTime());
								Space_time_price s=new Space_time_price();
								s.setDate(dateStr);
								s.setPrice(o.getPrice());
								s.setTime(start);
								s.setSpace_id(space);
								s.setMust_coach(2);
								try {
									space_time_priceService.saveSpace_time_price(s);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					
					//下午时间的设置
					for(int end=Integer.valueOf(request.getParameter("gendtime"))+1;end<=gb.getEnd_time();end++){
						for(Space space:spaceList){
							for(Space_time_price stp:space_time_priceService.findByTime(space.getId(), end,1)){
								timeMoenyService.deleteTimeMoeny(timeMoenyService.getTimeMoenyBySpaHourType(space.getId(), end, 1));
								
								
								space_time_priceService.deleteStp(stp);
								
							}
						}
					}
				}
				
				
				//都是小于的设置
				if(gb.getStart_time()<=Integer.valueOf(request.getParameter("gopentime")) && gb.getEnd_time()<=Integer.valueOf(request.getParameter("gendtime"))){
					List<Space> spaceList=spaceService.getPlaygroundSpaceBy_PGid(o.getId(), 0);
					//下午时间的设置
					for(int end=gb.getEnd_time()+1;end<=Integer.valueOf(request.getParameter("gendtime"));end++){
						for(Space space:spaceList){
							TimeMoeny t=new TimeMoeny();
							t.setHour(end);
							t.setSpace(space);
							t.setPlayground_id(space.getPlayground_id().getId());
							t.setTime_type(1);
							t.setPrice(space.getPrice());
							t.setHelp_filed(2);
							
							
							timeMoenyService.saveTimeMoeny(t);
							for(int m=0;m<7;m++){
								Date date=new Date();//取时间
								Calendar calendar = new GregorianCalendar();
								calendar.setTime(date);
								calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
								int week=calendar.get(Calendar.DAY_OF_WEEK);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String dateStr = sdf.format(calendar.getTime());
								Space_time_price s=new Space_time_price();
								s.setDate(dateStr);
								s.setPrice(o.getPrice());
								s.setTime(end);
								s.setSpace_id(space);
								s.setMust_coach(2);
								try {
									space_time_priceService.saveSpace_time_price(s);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					
					//上午时间的设置
					for(int strat=gb.getStart_time();strat<Integer.valueOf(request.getParameter("gopentime"));strat++){
						for(Space space:spaceList){
							for(Space_time_price stp:space_time_priceService.findByTime(space.getId(), strat,1)){
								
								timeMoenyService.deleteTimeMoeny(timeMoenyService.getTimeMoenyBySpaHourType(space.getId(), strat, 1));
								space_time_priceService.deleteStp(stp);
								
							}
						}
					}
				}
				
				//都是一小一大的设置
				if(gb.getStart_time()<=Integer.valueOf(request.getParameter("gopentime"))
					&& 
					gb.getEnd_time()>=Integer.valueOf(request.getParameter("gendtime"))){
					
					List<Space> spaceList=spaceService.getPlaygroundSpaceBy_PGid(o.getId(), 0);
					//下午时间的设置
					for(int end=Integer.valueOf(request.getParameter("gendtime"))+1;end<=gb.getEnd_time();end++){
						for(Space space:spaceList){
							for(Space_time_price stp:space_time_priceService.findByTime(space.getId(), end,1)){
								timeMoenyService.deleteTimeMoeny(timeMoenyService.getTimeMoenyBySpaHourType(space.getId(), end, 1));
								space_time_priceService.deleteStp(stp);
								
							}
						}
					}
					
					//上午时间的设置
					for(int strat=gb.getStart_time();strat<Integer.valueOf(request.getParameter("gopentime"));strat++){
						for(Space space:spaceList){
							for(Space_time_price stp:space_time_priceService.findByTime(space.getId(), strat,1)){
								timeMoenyService.deleteTimeMoeny(timeMoenyService.getTimeMoenyBySpaHourType(space.getId(), strat, 1));
								space_time_priceService.deleteStp(stp);
								
							}
						}
					}
				}
				
				//都是一大一小的设置
				if(gb.getStart_time()>=Integer.valueOf(request.getParameter("gopentime")) && gb.getEnd_time()<=Integer.valueOf(request.getParameter("gendtime"))){
					List<Space> spaceList=spaceService.getPlaygroundSpaceBy_PGid(o.getId(), 0);
					//上午时间的设置
					for(int end=gb.getEnd_time()+1;end<=Integer.valueOf(request.getParameter("gendtime"));end++){
						for(Space space:spaceList){
							TimeMoeny t=new TimeMoeny();
							t.setHour(end);
							t.setSpace(space);
							t.setPlayground_id(space.getPlayground_id().getId());
							t.setTime_type(1);
							t.setPrice(space.getPrice());
							t.setHelp_filed(2);
							
							
							timeMoenyService.saveTimeMoeny(t);
							
							for(int m=0;m<7;m++){
								Date date=new Date();//取时间
								Calendar calendar = new GregorianCalendar();
								calendar.setTime(date);
								calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
								int week=calendar.get(Calendar.DAY_OF_WEEK);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String dateStr = sdf.format(calendar.getTime());
								Space_time_price s=new Space_time_price();
								s.setDate(dateStr);
								s.setPrice(o.getPrice());
								s.setTime(end);
								s.setSpace_id(space);
								s.setMust_coach(2);
								try {
									space_time_priceService.saveSpace_time_price(s);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					
					//下午时间的设置
					for(int strat=Integer.valueOf(request.getParameter("gopentime"))+1;strat<gb.getStart_time();strat++){
						for(Space space:spaceList){
							TimeMoeny t=new TimeMoeny();
							t.setHour(strat);
							t.setSpace(space);
							t.setPlayground_id(space.getPlayground_id().getId());
							t.setTime_type(1);
							t.setPrice(space.getPrice());
							t.setHelp_filed(2);
							
							
							timeMoenyService.saveTimeMoeny(t);
							
							for(int m=0;m<7;m++){
								Date date=new Date();//取时间
								Calendar calendar = new GregorianCalendar();
								calendar.setTime(date);
								calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
								int week=calendar.get(Calendar.DAY_OF_WEEK);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String dateStr = sdf.format(calendar.getTime());
								Space_time_price s=new Space_time_price();
								s.setDate(dateStr);
								s.setPrice(o.getPrice());
								s.setTime(strat);
								s.setSpace_id(space);
								s.setMust_coach(2);
								try {
									space_time_priceService.saveSpace_time_price(s);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				gb.setStart_time(Integer.valueOf(request.getParameter("gopentime")));
				gb.setEnd_time(Integer.valueOf(request.getParameter("gendtime")));
				
				business_timeService.updateBusiness_time(gb);
			}
			
			
			//节假日时间设置
			if(jb.getStart_time()==Integer.valueOf(request.getParameter("jopentime")) && jb.getEnd_time()==Integer.valueOf(request.getParameter("jendtime"))){
				
			}else{
				
				//都是大于的设置
				if(jb.getStart_time()>=Integer.valueOf(request.getParameter("jopentime")) && jb.getEnd_time()>=Integer.valueOf(request.getParameter("jendtime"))){
					List<Space> spaceList=spaceService.getPlaygroundSpaceBy_PGid(o.getId(), 0);
					//上午时间的设置
					for(int start=Integer.valueOf(request.getParameter("jopentime"))+1;start<jb.getStart_time();start++){
						for(Space space:spaceList){
							TimeMoeny t=new TimeMoeny();
							t.setHour(start);
							t.setSpace(space);
							t.setPlayground_id(space.getPlayground_id().getId());
							t.setTime_type(2);
							t.setPrice(space.getPrice());
							t.setHelp_filed(2);
							
							
							timeMoenyService.saveTimeMoeny(t);
							
							for(int m=0;m<7;m++){
								Date date=new Date();//取时间
								Calendar calendar = new GregorianCalendar();
								calendar.setTime(date);
								calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
								int week=calendar.get(Calendar.DAY_OF_WEEK);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String dateStr = sdf.format(calendar.getTime());
								Space_time_price s=new Space_time_price();
								s.setDate(dateStr);
								s.setPrice(o.getPrice());
								s.setTime(start);
								s.setSpace_id(space);
								s.setMust_coach(2);
								try {
									space_time_priceService.saveSpace_time_price(s);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					
					//下午时间的设置
					for(int end=Integer.valueOf(request.getParameter("jendtime"))+1;end<=jb.getEnd_time();end++){
						for(Space space:spaceList){
							for(Space_time_price stp:space_time_priceService.findByTime(space.getId(), end,2)){
								timeMoenyService.deleteTimeMoeny(timeMoenyService.getTimeMoenyBySpaHourType(space.getId(), end, 2));
								space_time_priceService.deleteStp(stp);
								
								
							}
						}
					}
					
				}
				
				
				//都是小于的设置
				if(jb.getStart_time()<=Integer.valueOf(request.getParameter("jopentime")) && jb.getEnd_time()<=Integer.valueOf(request.getParameter("jendtime"))){
					List<Space> spaceList=spaceService.getPlaygroundSpaceBy_PGid(o.getId(), 0);
					//下午时间的设置
					for(int end=jb.getEnd_time()+1;end<=Integer.valueOf(request.getParameter("jendtime"));end++){
						for(Space space:spaceList){
							TimeMoeny t=new TimeMoeny();
							t.setHour(end);
							t.setSpace(space);
							t.setPlayground_id(space.getPlayground_id().getId());
							t.setTime_type(2);
							t.setPrice(space.getPrice());
							t.setHelp_filed(2);
							
							
							timeMoenyService.saveTimeMoeny(t);
							for(int m=0;m<7;m++){
								Date date=new Date();//取时间
								Calendar calendar = new GregorianCalendar();
								calendar.setTime(date);
								calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
								int week=calendar.get(Calendar.DAY_OF_WEEK);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String dateStr = sdf.format(calendar.getTime());
								Space_time_price s=new Space_time_price();
								s.setDate(dateStr);
								s.setPrice(o.getPrice());
								s.setTime(end);
								s.setSpace_id(space);
								s.setMust_coach(2);
								try {
									space_time_priceService.saveSpace_time_price(s);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					
					//上午时间的设置
					for(int strat=jb.getStart_time();strat<Integer.valueOf(request.getParameter("jopentime"));strat++){
						for(Space space:spaceList){
							for(Space_time_price stp:space_time_priceService.findByTime(space.getId(), strat,2)){
								timeMoenyService.deleteTimeMoeny(timeMoenyService.getTimeMoenyBySpaHourType(space.getId(), strat, 2));
								space_time_priceService.deleteStp(stp);
								
							}
						}
					}
				}
				
				//都是一小一大的设置
				if(jb.getStart_time()<=Integer.valueOf(request.getParameter("jopentime"))
					&& 
					jb.getEnd_time()>=Integer.valueOf(request.getParameter("jendtime"))){
					
					List<Space> spaceList=spaceService.getPlaygroundSpaceBy_PGid(o.getId(), 0);
					//下午时间的设置
					for(int end=Integer.valueOf(request.getParameter("jendtime"))+1;end<=jb.getEnd_time();end++){
						for(Space space:spaceList){
							for(Space_time_price stp:space_time_priceService.findByTime(space.getId(), end,2)){
								timeMoenyService.deleteTimeMoeny(timeMoenyService.getTimeMoenyBySpaHourType(space.getId(), end, 2));
								space_time_priceService.deleteStp(stp);
							
							}
						}
					}
					
					//上午时间的设置
					for(int strat=jb.getStart_time();strat<Integer.valueOf(request.getParameter("jopentime"));strat++){
						for(Space space:spaceList){
							for(Space_time_price stp:space_time_priceService.findByTime(space.getId(), strat,2)){
								timeMoenyService.deleteTimeMoeny(timeMoenyService.getTimeMoenyBySpaHourType(space.getId(), strat, 2));
								space_time_priceService.deleteStp(stp);
								
							}
						}
					}
				}
				
				//都是一大一小的设置
				if(jb.getStart_time()>=Integer.valueOf(request.getParameter("jopentime")) && jb.getEnd_time()<=Integer.valueOf(request.getParameter("jendtime"))){
					List<Space> spaceList=spaceService.getPlaygroundSpaceBy_PGid(o.getId(), 0);
					//上午时间的设置
					for(int end=jb.getEnd_time()+1;end<=Integer.valueOf(request.getParameter("jendtime"));end++){
						for(Space space:spaceList){
							TimeMoeny t=new TimeMoeny();
							t.setHour(end);
							t.setSpace(space);
							t.setPlayground_id(space.getPlayground_id().getId());
							t.setTime_type(2);
							t.setPrice(space.getPrice());
							t.setHelp_filed(2);
							
							
							timeMoenyService.saveTimeMoeny(t);
							for(int m=0;m<7;m++){
								Date date=new Date();//取时间
								Calendar calendar = new GregorianCalendar();
								calendar.setTime(date);
								calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
								int week=calendar.get(Calendar.DAY_OF_WEEK);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String dateStr = sdf.format(calendar.getTime());
								Space_time_price s=new Space_time_price();
								s.setDate(dateStr);
								s.setPrice(o.getPrice());
								s.setTime(end);
								s.setSpace_id(space);
								s.setMust_coach(2);
								try {
									space_time_priceService.saveSpace_time_price(s);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					
					//下午时间的设置
					for(int strat=Integer.valueOf(request.getParameter("jopentime"));strat<jb.getStart_time();strat++){
						for(Space space:spaceList){
							TimeMoeny t=new TimeMoeny();
							t.setHour(strat);
							t.setSpace(space);
							t.setPlayground_id(space.getPlayground_id().getId());
							t.setTime_type(2);
							t.setPrice(space.getPrice());
							t.setHelp_filed(2);
							
							
							timeMoenyService.saveTimeMoeny(t);
							for(int m=0;m<7;m++){
								Date date=new Date();//取时间
								Calendar calendar = new GregorianCalendar();
								calendar.setTime(date);
								calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
								int week=calendar.get(Calendar.DAY_OF_WEEK);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String dateStr = sdf.format(calendar.getTime());
								Space_time_price s=new Space_time_price();
								s.setDate(dateStr);
								s.setPrice(o.getPrice());
								s.setTime(strat);
								s.setSpace_id(space);
								s.setMust_coach(2);
								try {
									space_time_priceService.saveSpace_time_price(s);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				
				
				jb.setStart_time(Integer.valueOf(request.getParameter("jopentime")));
				jb.setEnd_time(Integer.valueOf(request.getParameter("jendtime")));
				
				business_timeService.updateBusiness_time(jb);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		if(flag == 1){
//			return "redirect:audPlayground.do";
//		}
		return "redirect:playground_list.do";
	}
	
	/**
	 * 场馆添加图片页面
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "toaddPlaygroundImg")
	public String toaddPlaygroundImg(HttpServletRequest request, ModelMap map,int oid){
		request.setAttribute("pdId", oid);
		return "admin/playground/pg_img_add";
	}
	
	
	/**
	 * 保存场馆证书信息
	 * @param request
	 * @param o
	 * @param id
	 */
	public Qualification_certificate saveCertificate(HttpServletRequest request,Qualification_certificate o){
		// 上传文件img1
		String imgFile1 = request.getParameter("ph1");
		if (CommonUtil.NotEmpty(imgFile1)) {
			o.setPh1(imgFile1);
		}
		imgFile1 = request.getParameter("ph2");
		if (CommonUtil.NotEmpty(imgFile1)) {
			o.setPh2(imgFile1);
		}
		imgFile1 = request.getParameter("ph3");
		if (CommonUtil.NotEmpty(imgFile1)) {
			o.setPh3(imgFile1);
		}
		imgFile1 = request.getParameter("ph4");
		if (CommonUtil.NotEmpty(imgFile1)) {
			o.setPh4(imgFile1);
		}
		imgFile1 = request.getParameter("ph5");
		if (CommonUtil.NotEmpty(imgFile1)) {
			o.setPh5(imgFile1);
		}
		imgFile1 = request.getParameter("ph6");
		if (CommonUtil.NotEmpty(imgFile1)) {
			o.setPh6(imgFile1);
		}
		imgFile1 = request.getParameter("ph7");
		if (CommonUtil.NotEmpty(imgFile1)) {
			o.setPh7(imgFile1);
		}
		imgFile1 = request.getParameter("ph8");
		if (CommonUtil.NotEmpty(imgFile1)) {
			o.setPh8(imgFile1);
		}
		Qualification_certificate qc = null;
		o.setType(Constants.CERTIFICATE_VENUE);
		try {
			qc = certificateService.saveCertificate(o);
		} catch (Exception e) {
			logger.error("保存证书信息出现错误！错误信息："+e.getMessage());
			e.printStackTrace();
		}
		return qc;
	}
	
	public Qualification_certificate updateCertificate(HttpServletRequest request,int id){
		Qualification_certificate qc = certificateService.getCertificateById(id);
    	qc.setPh1(request.getParameter("z1"));
    	qc.setPh2(request.getParameter("z2"));
    	qc.setPh3(request.getParameter("z3"));
    	qc.setPh4(request.getParameter("z4"));
    	qc.setPh5(request.getParameter("z5"));
    	qc.setPh6(request.getParameter("z6"));
    	qc.setPh7(request.getParameter("z7"));
    	qc.setPh8(request.getParameter("z8"));
    	Qualification_certificate qc1 = null;
		qc.setUpdate_time(CommonUtil.getTimeNow());
		try {
			qc1 = certificateService.updateCertificate(qc);
		} catch (Exception e) {
			logger.error("修改证书信息出现错误！错误信息："+e.getMessage());
			e.printStackTrace();
		}
		return qc1;
	}
	
	/**
	 * 保存或者修改服务
	 * @param request
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public Peripheral_services saveOrUpdatePs(HttpServletRequest request,int psId) throws Exception{
		
		Peripheral_services os = null;
		if(psId != 0){
			os = peripheral_services.getServices(psId);
		}else{
			os = new Peripheral_services();
		}
		if(CommonUtil.NotEmpty(request.getParameter("wifi"))){
			os.setWifi(Integer.valueOf(request.getParameter("wifi")));
		}else{
			os.setWifi(0);
		}
		if(CommonUtil.NotEmpty(request.getParameter("equipment"))){
			os.setEquipment(Integer.valueOf(request.getParameter("equipment")));
		}else{
			os.setEquipment(0);
		}
		if(CommonUtil.NotEmpty(request.getParameter("equipment_shop"))){
			os.setEquipment_shop(Integer.valueOf(request.getParameter("equipment_shop")));
		}else{
			os.setEquipment_shop(0);
		}
		if(CommonUtil.NotEmpty(request.getParameter("locker_room"))){
			os.setLocker_room(Integer.valueOf(request.getParameter("locker_room")));
		}else{
			os.setLocker_room(0);
		}
		if(CommonUtil.NotEmpty(request.getParameter("lockers"))){
			os.setLockers(Integer.valueOf(request.getParameter("lockers")));
		}else{
			os.setLockers(0);
		}
		if(CommonUtil.NotEmpty(request.getParameter("parking_lot"))){
			os.setParking_lot(Integer.valueOf(request.getParameter("parking_lot")));
		}else{
			os.setParking_lot(0);
		}
		if(CommonUtil.NotEmpty(request.getParameter("shower"))){
			os.setShower(Integer.valueOf(request.getParameter("shower")));
		}else{
			os.setShower(0);
		}
		if(CommonUtil.NotEmpty(request.getParameter("vip_room"))){
			os.setVip_room(Integer.valueOf(request.getParameter("vip_room")));
		}else{
			os.setVip_room(0);
		}
		if(CommonUtil.NotEmpty(request.getParameter("food"))){
			os.setFood(Integer.valueOf(request.getParameter("food")));
		}else{
			os.setFood(0);
		}
		if(psId != 0){
			peripheral_services.updateServices(os);
		}else {
			peripheral_services.saveServices(os);
		}
		return os;
	}
	
	/**
	 * 新增检查电话
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "savecheckPlayPhone")
	@ResponseBody
	public Object savecheckPhone(HttpServletRequest request, ModelMap map,String phone) {
		boolean flag = playgroundService.checkPhone(phone,0);
		if(flag){
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 修改检查电话
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "updatecheckPlayPhone")
	@ResponseBody
	public Object updatecheckPlayPhone(HttpServletRequest request, ModelMap map,String phone,int id) {
		boolean flag = playgroundService.checkPhone(phone,id);
		if(flag){
			return 1;
		}else{
			return 0;
		}
	}

	/**
	 * 审核场馆列表页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "audPlayground")
	public String auPlayground(HttpServletRequest request, ModelMap map,@RequestParam(defaultValue="1")int pageNumber,Playground o){
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		if(m==null){
			return "redirect:login.do";
		}
		o.setAuditStatus(1);
		o.setIs_locked(0);
		map.put("data_page",playgroundService.getPageFindePlayground(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/playground/aud_pg_list";
	}
	
	
	/***
	 * 跳到场馆详情页
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "backplaygrounddetail")
	public String playgrounddetail(ModelMap map, HttpServletRequest request,Integer playgroundId) {
		map.put("playground", playgroundService.getPlaygroundById(playgroundId));
		map.put("img", playgroundImgService.getPlaygoundImgListByPdId(playgroundId));
		
		
		//获取到7天的日期表格
		List<DateAssist> list=new ArrayList<DateAssist>();
		for(int m=0;m<7;m++){
			DateAssist da=new DateAssist();
			Date date=new Date();//取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
			if((calendar.get(Calendar.DAY_OF_WEEK)-1)==0){
				da.setWeek("星期天");
			}
			da.setWeek(getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
			da.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
			if(calendar.get(Calendar.DATE)<10){
				da.setDay("0"+String.valueOf(calendar.get(Calendar.DATE)));
			}else{
				da.setDay(String.valueOf(calendar.get(Calendar.DATE)));
			}
			da.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
			
			list.add(da);
		}
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		  
		Calendar c = new GregorianCalendar();
		
		//两个月的时间
		c.setTime(new Date());
		c.add(Calendar.MONTH, 2);
		String two=f.format(c.getTime());
		
		//三个月的时间
		c.setTime(new Date());
		c.add(Calendar.MONTH, 3);
		String three=f.format(c.getTime());
		
		//六个月的时间
		c.setTime(new Date());
		c.add(Calendar.MONTH, 6);
		String six=f.format(c.getTime());
		
		//十二个月的时间
		c.setTime(new Date());
		c.add(Calendar.MONTH, 12);
		String twelve=f.format(c.getTime());
		
		Map<String,String> cyMap=new HashMap<String,String>();
		
		cyMap.put("近2个月", two);
		cyMap.put("3个月", three);
		cyMap.put("6个月", six);
		cyMap.put("1年", twelve);
		
		map.put("users", weuserService.getAllUser());
		map.put("type", 1);
		map.put("cyMap", cyMap);
		map.put("dateList", list);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		map.put("stratTime", format.format(new Date()));
		
		map.put("activeId", playgroundService.getPlaygroundById(playgroundId).getId());
		
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		return "/admin/playground/pg_book";
	}
	
	/**
	 * 锁定场馆列表
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "lockedPlayground")
	public String lockedPlayground(HttpServletRequest request, ModelMap map,@RequestParam(defaultValue="1")int pageNumber,Playground o){
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		if(m==null){
			return "redirect:login.do";
		}
		o.setAuditStatus(0);
		o.setIs_locked(1);
		map.put("data_page",playgroundService.getPageFindePlayground(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/playground/locked_pg_list";
	}
	
	/**
	 * 解锁场馆
	 * @param request
	 * @param oid
	 * @param audStaus
	 * @return
	 */
	@RequestMapping(value="unlockPlayground_aud")
	public String unlockPlayground_aud(HttpServletRequest request,Integer oid){
		Playground p=playgroundService.getPlaygroundById(oid);
		p.setAuditStatus(Constants.AUDITSTATUS_REFUSE);
		p.setIs_locked(0);
//		p.setReturn_count(0);
		try {
			playgroundService.mergePlayground(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:lockedPlayground.do";
	}
	
	
	/**
	 * 审核场馆
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="pdCheck")
	public String pdCheck(HttpServletRequest request, ModelMap map,Playground o) throws Exception{
		int auditStatus = o.getAuditStatus();
		String return_reason = o.getReturn_reason();
		String time = o.getEffective_time();
		o = playgroundService.getPlaygroundById(o.getId());
		o.setAuditStatus(auditStatus);
		o.setReturn_reason(return_reason);
		o.setEffective_time(time);
		if(o.getAuditStatus() == Constants.AUDITSTATUS_REFUSE && CommonUtil.NotEmpty(o.getReturn_reason())){ //拒绝
			o.setReturn_count(o.getReturn_count()+1);
			if(o.getReturn_count() % 3 == 0){  //如果有三次就锁定
				o.setIs_locked(1);
			}
		}
		o = playgroundService.mergePlayground(o);
		if(o.getPlaygroundmanager_id()!=0){//当场馆管理者的场馆审核通过后,教练也表示审核通过
			PlaygroundManager manager = playgroundManagerService.getPlaygroundManagerById(o.getPlaygroundmanager_id());
			Coach co = coachService.getcoachById(manager.getCoachid());
			co.setStatus(Constants.O_STATUS.PASS_FOR_CHECK.getStatus());
			coachService.mergePlayground(co);
		}
		if(CommonUtil.NotEmpty(return_reason)){
			ReturnReason ro = new ReturnReason();
			ro.setReason(return_reason);
			ro.setType(1);
			ro.setS_id(o.getId());
			returnReasonService.saveReturnReason(ro);
		}
		return "redirect:audPlayground.do";
	}
	
	public String getWeek(int week){
		if(week==1){
			return "星期天";
		}else if(week ==2){
			return "星期一";
		}else if(week ==3){
			return "星期二";
		}else if(week ==4){
			return "星期三";
		}else if(week ==5){
			return "星期四";
		}else if(week ==6){
			return "星期五";
		}else{
			return "星期六";
		}
	}
	
	/**
	 * 推荐场馆
	 * @param request
	 * @param map
	 * @param stick
	 * @param id		场馆ID	
	 * @return
	 */
	@RequestMapping(value = "pushplayground")
	@ResponseBody
	public Object pushplayground(HttpServletRequest request, ModelMap map,int stick,int id) {
		Playground c = playgroundService.getPlaygroundById(id);
		c.setStick(stick);
		try {
			playgroundService.mergePlayground(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),"",stick);
	}
	
	@RequestMapping(value = "searchPhone")
	@ResponseBody
	public Object searchPhone(HttpServletRequest request,String Phone){
		Weuser user=weuserService.getUserByPhone(Phone);
		
		if(user!=null){
			return new BusinessResponse(OperationResult.SUCCESS.getStatus(),"检索用户成功",user);
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),"检索用户失败","0");
	}
	
	@RequestMapping(value = "searchId")
	@ResponseBody
	public Object searchId(HttpServletRequest request,Integer id){
		Weuser user=weuserService.getUserById(id);
		
		if(user!=null){
			return new BusinessResponse(OperationResult.SUCCESS.getStatus(),"检索用户成功",user);
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),"检索用户失败","0");
	}
}
