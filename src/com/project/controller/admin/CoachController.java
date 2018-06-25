package com.project.controller.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.common.Settings;
import com.project.common.Constants.OperationResult;
import com.project.controller.BaseController;
import com.project.pojo.Apply;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Coach_teach_person;
import com.project.pojo.Coaching_experience;
import com.project.pojo.DateAssist;
import com.project.pojo.Manager;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.Qualification_certificate;
import com.project.pojo.Region;
import com.project.pojo.Space_time_price;
import com.project.pojo.StationMessage;
import com.project.pojo.User_count;
import com.project.pojo.Weuser;
import com.project.service.IApplyService;
import com.project.service.ICertificateService;
import com.project.service.ICoachReserveService;
import com.project.service.ICoachService;
import com.project.service.ICoach_set_timeService;
import com.project.service.ICoach_teach_personService;
import com.project.service.ICoaching_experienceService;
import com.project.service.IDictionariesService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IPlaygroundService;
import com.project.service.IRegionService;
import com.project.service.ISpace_time_priceService;
import com.project.service.IStationMessageService;
import com.project.service.ISubsidiesService;
import com.project.service.ISystemConfigService;
import com.project.service.IUser_countService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;
import com.project.util.SMSUtil;
import com.project.util.SecurityUtil;

@Controller("admincoachController")
@RequestMapping(value = "/pgm")
public class CoachController extends BaseController {
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CoachController.class); 
	@Resource
	private ICoaching_experienceService coaching_experienceService; 
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private ICoachService coachService;
	@Resource
	private IWeuserService weuserService;
	@Resource
	private IUser_countService user_countService;
	@Resource
	private IRegionService regionService;
	@Resource
	private ICertificateService certificateService;
	@Resource
	private IDictionariesService dictionariesService;
	@Resource
	private ICoach_set_timeService coach_set_timeService;
	@Resource
	private ICoach_teach_personService coach_teach_personService;
	@Resource
	private ISystemConfigService systemConfigService; 
	@Resource
	private ISubsidiesService subsidiesService;
	@Resource
	private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private ICoachReserveService coachReserveService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
	@Resource
	private IApplyService applyService;
	@Resource
	private IStationMessageService stationMessageService;
	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "toaddcoach")
	public String toaddcoach(HttpServletRequest request, ModelMap map){
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		map.put("groundList", playgroundService.getAll());
		List<Region> list = regionService.getProvince();
		map.put("provincelist",list);
		
		map.put("pgm",pgm);
		map.put("ds",dictionariesService.getDictionaries(Constants.SERVICE_COACH));
		return "admin/coach/coach_add";
	}
	
	/**
	 * 增加
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "add_coach")
	public String add_space(HttpServletRequest request,String pass,Coach o) {
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
			Weuser user = weuserService.getUserByPhone(o.getPhone());
			if (user != null) {
				return "redirect:toaddcoach.do";			//电话号码不能重复		add by lxc	2015-11-19
			}
			//添加教练的同时，添加用户
			Weuser we = new Weuser();
			we.setUsername(o.getName());
			we.setAge(o.getAge());
			we.setUphone(o.getPhone());	
			we.setSignature(o.getPersonalitySignature());
			we.setCityid(o.getNow_live_city());
			we.setAreaid(o.getAreaid());
			we.setPass(SecurityUtil.encrypt(pass));
			we.setIs_coach(Constants.IS_COACH1);
			we.setHead_photo(o.getHead_portrait());
			we.setIdcard_no(request.getParameter("idcard_no"));
			we.setSchool(request.getParameter("shcool"));
			weuserService.saveUser(we);
			
			//设置用户统计表
			User_count uCount=new User_count();
			uCount.setWeuser(we);
			user_countService.saveuser_Count(uCount);
			
			o.setUserid(we);
			
			//保存证书信息
			Qualification_certificate qc = new Qualification_certificate();
			Qualification_certificate qc1 = saveCertificate(request,qc);
			o.setCertificate(qc1);
			
			if(o.getPlayground_id()>0){
				Playground play = playgroundService.getPlaygroundById(o.getPlayground_id());
				o.setCoordinateslatitude(play.getCoordinateslatitude());
				o.setCoordinateslongitude(play.getCoordinateslongitude());
			}
			if(o.getCoachType() == Constants.COACHTYPE.INNERCOACH.getStatus()){
				o.setStatus(Constants.O_STATUS.PASS_FOR_CHECK.getStatus());
				o.setVerified(1);
			}
			Coach oa = coachService.saveCoach(o);
			
			
			//循环删除原来的执教经历
			List<Coaching_experience> list = coaching_experienceService.getCoaching_experienceListByCoachId(o.getId());
			if(list != null && list.size() >0 ){
				for (Coaching_experience coaching_experience : list) {
					coaching_experienceService.deleteCoaching_experience(coaching_experience);
				}
			}
			
			//添加执教经历
			String [] begin_time = request.getParameterValues("begin_time");
			String [] end_time =  request.getParameterValues("end_time");
			String [] unitName =  request.getParameterValues("unitName");
			if(unitName!=null)
			for (int i = 0; i < unitName.length; i++) {
				if(begin_time!=null)
				for (int s = 0; s < begin_time.length; s++) {
					if(end_time!=null)
					for (int y = 0; y < end_time.length; y++) {
						if(i == s && s == y){
							if(CommonUtil.NotEmpty(unitName[i]) && CommonUtil.NotEmpty(begin_time[s]) && CommonUtil.NotEmpty(end_time[y])){
								Coaching_experience c = new Coaching_experience();
								c.setBegin_time(begin_time[s]);
								c.setUnitName(unitName[i]);
								c.setEnd_time(end_time[y]);
								c.setCoachId(o.getId());
								coaching_experienceService.saveCoaching_experience(c);
								break;
							}
						}
					}
				}
			}
			
			//添加补贴
//			String money = systemConfigService.getConfigValueByKey(ConfigKey.COACH_SUBSIDY_PROPORTION,"0.01");//取系统配置值  教练补贴比例
//			String coach_type = systemConfigService.getConfigValueByKey(ConfigKey.COACH_SUBSIDY_TYPE,"1");//取系统配置值  教练补贴类型
//			Subsidies subsidies = new Subsidies();
//			subsidies.setMoney(Float.valueOf(money));
//			subsidies.setZhe_id(oa.getId());
//			subsidies.setZhe_type(Constants.SUBSIDIES_COACH);
//			subsidies.setType(Integer.valueOf(coach_type));
//			subsidies.setEnd_time(CommonController.getCurrYearLast());
//			subsidiesService.saveSubsidies(subsidies);
			
			//新增教练带人		edit by lxc	2015-12-11	不管什么教练,后台添加,也需要默认新增
//			if(oa.getCoachType()== 2){
				String p = String.valueOf(o.getMoney());
				float price = (float) o.getPrice();
				Coach_teach_person ct = new Coach_teach_person();
				ct.setCoach_id(oa.getId());
				ct.setPrice(Float.parseFloat(p));
				coach_teach_personService.saveCoach_teach_person(ct);
//			}
		
			//工作时间
			for (int i = Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
				Coach_set_time c = new Coach_set_time();
				c.setCoach_id(oa);
				c.setTime(i);
				c.setTime_type(1);
				c.setFlag(1);
				coach_set_timeService.saveCoachSetTime(c);
			}
			//节假时间
			for (int i = Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
				Coach_set_time c = new Coach_set_time();
				c.setCoach_id(oa);
				c.setTime(i);
				c.setTime_type(2);
				c.setFlag(1);
				coach_set_timeService.saveCoachSetTime(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存教练信息出现错误！错误信息："+e.getMessage());
		}
		 return "redirect:coach_list.do";
	}
	
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "coach_list")
	public String playground_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Coach o) {
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		if(pgm!=null){
			o.setPlaygroundmanager_id(pgm.getId());
			
		}
		o.setStatus(0);
		o.setVerified(-1);
		map.put("data_page",coachService.getPageFindeCoach(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("company_name", o.getName());
		map.put("manager", m);
		return "admin/coach/coach_list";
	}
	
	
	/**
	 * 审核列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "coach_check")
	public String coach_check(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Coach o) {
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		if(pgm!=null){
			o.setPlaygroundmanager_id(pgm.getId());
		}
		o.setStatus(0);
		o.setVerified(2);
		map.put("data_page",coachService.getPageFindeCoach(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("company_name", o.getName());
		return "admin/coach/coach_check_list";
	}
	
	/**
	 * 审核
	 * @return
	 * @throws Exception 
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "edit_status_coach")
	public String edit_status_coach(int oid,int status) throws Exception{
		Coach coach = coachService.getcoachById(oid);
		coach.setStatus(status);
		if(status == 2){
			coach.setVerified(1);
		}else if(status == 3){
			coach.setVerified(0);
		}
		coachService.mergePlayground(coach);
		return "redirect:coach_check.do";
	}
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "toedit_coach")
	public String toedit_coach(HttpServletRequest request, ModelMap map,int oid) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		map.put("check", check);
		map.put("groundList", playgroundService.getAll());
		Coach coach = coachService.getcoachById(oid);
		map.put("o",coach);
		map.put("r",regionService.getRegionById(coach.getNow_live_city()));
		map.put("a",regionService.getRegionById(coach.getAreaid()));
		
		List<Region> list = regionService.getProvince();
		if(coach.getNow_live_city() != 0){
			map.put("sid", regionService.getRegionById(coach.getNow_live_city()).getParent_id());
		}
		map.put("provincelist",list);
		request.setAttribute("ser", coach.getServices());
		map.put("ds",dictionariesService.getDictionaries(Constants.SERVICE_COACH));
		List<Coach_set_time> jtime = coach_set_timeService.getCoach_set_timeByCoachId(coach.getId(), 1,0);
		List<Coach_set_time> htime = coach_set_timeService.getCoach_set_timeByCoachId(coach.getId(), 2,0);
		String jobstime = "";
		for (Coach_set_time coach_set_time : jtime) {
			jobstime += coach_set_time.getTime()+",";
		}
		String holidaytime = "";
		for (Coach_set_time coach_set_time : htime) {
			holidaytime += coach_set_time.getTime()+",";
		}
		map.put("jtime", jobstime);
		map.put("htime", holidaytime);
		map.put("qc", certificateService.getCertificateByTypeAndZheId(Constants.CERTIFICATE_COACH, coach.getCertificate().getId()));
		
		map.put("pgm",pgm);
		
		List<Coaching_experience> clist = coaching_experienceService.getCoaching_experienceListByCoachId(coach.getId());
		map.put("clist", clist);
		return "admin/coach/coach_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "edit_coach")
	public String edit_playground_manager(HttpServletRequest request,int id,int userId,int pcId,Coach o ) {
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
			//修改教练的同时，修改用户
			Weuser we = weuserService.getUserById(userId);
			we.setUsername(o.getName());
			we.setAge(o.getAge());
			we.setUphone(o.getPhone());
			we.setSignature(o.getPersonalitySignature());
			we.setCityid(o.getNow_live_city());
			we.setHead_photo(o.getHead_portrait());
			we.setSchool(request.getParameter("shcool"));
			o.setUserid(we);
			o.setCreate_time(coachService.getcoachById(id).getCreate_time());
			weuserService.mergeAndUpdateTime(we);
			
			//循环删除原来的执教经历
			List<Coaching_experience> list = coaching_experienceService.getCoaching_experienceListByCoachId(o.getId());
			if(list != null && list.size() >0 ){
				for (Coaching_experience coaching_experience : list) {
					coaching_experienceService.deleteCoaching_experience(coaching_experience);
				}
			}
			
			//添加执教经历
			String [] begin_time = request.getParameterValues("begin_time");
			String [] end_time =  request.getParameterValues("end_time");
			String [] unitName =  request.getParameterValues("unitName");
			if(unitName!=null && unitName.length>0)
			for (int i = 0; i < unitName.length; i++) {
				for (int s = 0; s < begin_time.length; s++) {
					for (int y = 0; y < end_time.length; y++) {
						if(i == s && s == y){
							if(CommonUtil.NotEmpty(unitName[i]) && CommonUtil.NotEmpty(begin_time[s]) && CommonUtil.NotEmpty(end_time[y])){
								Coaching_experience c = new Coaching_experience();
								c.setBegin_time(begin_time[s]);
								c.setUnitName(unitName[i]);
								c.setEnd_time(end_time[y]);
								c.setCoachId(o.getId());
								coaching_experienceService.saveCoaching_experience(c);
								break;
							}
						}
					}
				}
			}
			
	    	if(o.getPlayground_id()>0){
		    	Playground play = playgroundService.getPlaygroundById(o.getPlayground_id());
				o.setCoordinateslatitude(play.getCoordinateslatitude());
				o.setCoordinateslongitude(play.getCoordinateslongitude());
	    	}
	    	
	    	Qualification_certificate qc1 = updateCertificate(request,pcId);
	    	o.setCertificate(qc1);
	    	
	    	if(o.getCoachType() == Constants.COACHTYPE.INNERCOACH.getStatus()){
				o.setStatus(Constants.O_STATUS.PASS_FOR_CHECK.getStatus());
				o.setVerified(1);
			}
	    	
	    	Coach oa = coachService.mergePlayground(o);
		} catch (Exception e) {
			logger.error("修改教练信息出现错误！错误信息："+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:coach_list.do";
	}
	
	/**
	 * 修改教练时间
	 * @param request
	 * @param coId
	 * @param hour
	 * @param type
	 * @param tag
	 * @throws Exception
	 */
	@UserRightAuth(menuCode = "coach")
	@RequestMapping(value = "edit_coach_time")
	@ResponseBody
	public void edit_coach_time(HttpServletRequest request,int coId,int hour,int type,int tag) throws Exception {
		if(tag == 1){
			Coach_set_time c = new Coach_set_time();
			c.setCoach_id(new Coach());
			c.getCoach_id().setId(coId);
			c.setTime(hour);
			c.setTime_type(type);
			c.setFlag(1);
			coach_set_timeService.saveCoachSetTime(c);
		}else{
			Coach_set_time co = coach_set_timeService.getCoach_set_timeByTypeAndHour(coId,type, hour);
			if(co != null){
				coach_set_timeService.deleteBycoachId(co);
			}
			
		}
		
		
	}
	
	
	/**
	 * 保存教练资质证书信息
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
		imgFile1 =request.getParameter("ph2");
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
		imgFile1 = request.getParameter("ph9");
		if (CommonUtil.NotEmpty(imgFile1)) {
			o.setPh9(imgFile1);
		}
		o.setType(Constants.CERTIFICATE_COACH);
		Qualification_certificate qc = null;
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
    	qc.setPh9(request.getParameter("z9"));
		qc.setUpdate_time(CommonUtil.getTimeNow());
		Qualification_certificate qc1 = null;
		try {
			qc1 = certificateService.updateCertificate(qc);
		} catch (Exception e) {
			logger.error("修改证书信息出现错误！错误信息："+e.getMessage());
			e.printStackTrace();
		}
		return qc1;
	}
	
	/**
	 * 新增检查电话
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "savecheckCoachPhone")
	@ResponseBody
	public Object savecheckPhone(HttpServletRequest request, ModelMap map,String phone) {
		boolean flag = coachService.checkPhone(phone,0);
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
	 * @param phone
	 * @param id		教练ID	
	 * @return
	 */
	@RequestMapping(value = "updatecheckCoachPhone")
	@ResponseBody
	public Object updatecheckCoachPhone(HttpServletRequest request, ModelMap map,String phone,int id) {
		boolean flag = coachService.checkPhone(phone,id);
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
	 * @param phone
	 * @param id		教练ID	
	 * @return
	 */
	@RequestMapping(value = "checkIdcard_no")
	@ResponseBody
	public Object checkIdcard_no(HttpServletRequest request, ModelMap map,String idcard_no,int id) {
		boolean flag = weuserService.checkIdcard_no(idcard_no,id);
		if(flag){
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 推荐教练
	 * @param request
	 * @param map
	 * @param stick
	 * @param id		教练ID	
	 * @return
	 */
	@RequestMapping(value = "pushcoach")
	@ResponseBody
	public Object pushcoach(HttpServletRequest request, ModelMap map,int stick,int id) {
		Coach c = coachService.getcoachById(id);
		c.setStick(stick);
		try {
			coachService.mergePlayground(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),"",stick);
	}
	
	/**
	 * 自由教练 预定场地
	 * @param request
	 * @param map
	 * @param id		教练ID	
	 * @return
	 */
	@RequestMapping(value = "is_reservecoach")
	@ResponseBody
	public Object is_reserve(HttpServletRequest request, ModelMap map,int id) {
		Coach c = coachService.getcoachById(id);
		if(c.getIs_reserve() == 0){
			c.setIs_reserve(1);
		}else{
			c.setIs_reserve(0);
		}
		try {
			coachService.mergePlayground(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),"","");
	}
	
	/**
	 * 自由教练 用户预定我
	 * @param request
	 * @param map
	 * @param id		教练ID	
	 * @return
	 */
	@RequestMapping(value = "reserve_mecoach")
	@ResponseBody
	public Object reserve_me(HttpServletRequest request, ModelMap map,int id) {
		Coach c = coachService.getcoachById(id);
		if(c.getReserve_me() == 0){
			c.setReserve_me(1);
		}else{
			c.setReserve_me(0);
		}
		try {
			coachService.mergePlayground(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),"","");
	}
	
	
	/**
	 * 教练日程表
	 * @param request
	 * @param map
	 * @param coachId
	 * @return
	 */
	@RequestMapping(value="carrschedule")
	public String carrschedule(HttpServletRequest request,ModelMap map,Integer coachId){
		logger.info("教练日程表....");
		long s_long = System.currentTimeMillis();		//开始计时
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
			da.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
			da.setWeek(getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
			da.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
			if(calendar.get(Calendar.DATE)<10){
				da.setDay("0"+String.valueOf(calendar.get(Calendar.DATE)));
			}else{
				da.setDay(String.valueOf(calendar.get(Calendar.DATE)));
			}
			
			list.add(da);
		}
		
		PlaygroundManager pm=playgroundManagerService.getPlaygroundManagerById(coachId);
		
		Coach c=coachService.getcoachById(pm.getCoachid());
		
		map.put("coach", c);
		map.put("dateList",list);
		
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		return "/admin/playground/carrSchedule";
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
	 * 教练带人列表
	 * @param request
	 * @param map
	 * @param coachId
	 * @return
	 */
	@RequestMapping(value = "coach_teach_list")
	public String coach_teach_list(HttpServletRequest request, ModelMap map,
						int coachId,@RequestParam(defaultValue="1")int pageNumber,
						Coach_teach_person o) {
		o.setCoach_id(coachId);
		map.put("data_page",coach_teach_personService.getPageFindeCoach_teach_person(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("coachId", coachId);
		return "admin/coach/coach_teach_list";
	}
	
	/**
	 * 跳转到添加教练带人页面
	 * @param request
	 * @param map
	 * @param coachId
	 * @return
	 */
	@RequestMapping(value = "toaddcoach_teach")
	public String toaddcoach_teach(HttpServletRequest request, ModelMap map,int coachId) {
		map.put("coachId", coachId);
		return "admin/coach/coach_teach_add";
	}
	
	/**
	 * 添加教练带人
	 * @param request
	 * @param map
	 * @param coachId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "addcoach_teach")
	public String addcoach_teach(HttpServletRequest request, ModelMap map,Coach_teach_person o) throws Exception {
		coach_teach_personService.saveCoach_teach_person(o);
		return "redirect:coach_teach_list.do?coachId="+o.getCoach_id();
	}
	
	/**
	 * 删除教练带人
	 * @param request
	 * @param map
	 * @param coachId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "coach_teach_del")
	public String coach_teach_del(HttpServletRequest request, ModelMap map,int oid,int coachId) throws Exception {
		coach_teach_personService.deleteById(oid);
		return "redirect:coach_teach_list.do?coachId="+coachId;
	}
	
	/**
	 * 获取对应时间点的驻场教练
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "in_coach")
	@ResponseBody
	public Object getIncoach(HttpServletRequest request,String space_time_priceId,Integer playgroundId){
		logger.info("获取对应时间点的驻场教练....");
		long s_long = System.currentTimeMillis();		//开始计时
		List<Coach> mCoach=coachService.getCoachBygroundAndType(playgroundId, Constants.COACHTYPE.INNERCOACH.getStatus());
		String[] stpId=space_time_priceId.split(",");
		
		String[] nu=new String[0];
	
		
		try {
			
			
			
			for(String i:stpId){
				if(!i.equals("")){
					Space_time_price s=space_time_priceService.findById(Integer.valueOf(i));
					for(int j=0;j<mCoach.size();j++){
						if(coachReserveService.getCoachReserve(s.getDate(), mCoach.get(j).getId(), s.getTime())!=null){
							mCoach.remove(j);
							break; 
						}
						
						//找教练，当这个教练在休息的时候移除他
						if(coach_set_timeService.getCoach_set_timeByTypeAndHour(mCoach.get(j).getId(), s.getDateType(), s.getTime()).getSpace_id()==0){
							mCoach.remove(j);
							break;
						}
					}
				}
			}
			
			for(int i=0;i<mCoach.size();i++){
				mCoach.get(i).setCtp(coach_teach_personService.getCoach_teach_personByCidAndper(mCoach.get(i).getId()));
			}
			
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			if("".equals(stpId[0])){
				return new BusinessResponse(
						Constants.OperationResult.NOSTPID.getStatus(), "数据获取失败", nu);
			}
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", mCoach);
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SYSTEM_ERROR.getStatus(), "错误", nu);
		}
		
	}
	
	/**
	 * 教练申请管理列表
	 * @param request
	 * @param map
	 * @param coachId
	 * @return
	 */						
	@RequestMapping(value = "coach_apply_list")
	public String coach_apply_list(HttpServletRequest request, ModelMap map,
						@RequestParam(defaultValue="1")int pageNumber,
						Apply o) {
		PlaygroundManager pgm = getPlaygroundManager(request);
		Manager m = getManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		o.setPlaygroundmanager_id(pgm.getId());
		map.put("playground",playgroundService.getAll());
		map.put("data_page",applyService.getPageFindeApply(o, pageNumber,  Constants.BACKGROUND_PAGESIZE));
		map.put("ap", o);
		return "admin/coach/coach_apply_list";
	}
	
	/**
	 * 申请 驻场教练
	 * @param request
	 * @param map
	 * @param oid
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value = "coach_apply")
	public String coach_apply_list(HttpServletRequest request, ModelMap map,
						int oid,int status,int pid,int id) throws Exception{
		Apply ap = new Apply();
		Coach co = new Coach();
		co.setId(oid);
		ap.setCoach(co);
		
		String content = "";
		 
		//通过
		Coach o = coachService.getcoachById(oid);
		Playground pd = playgroundService.getPlaygroundById(pid);
		if(status == 1){
			if(o.getCoachType() == Constants.COACHTYPE.FREECOACH.getStatus()){  //教练为自由教练
				o.setCoachType(Constants.COACHTYPE.INNERCOACH.getStatus());
				o.setPlayground_id(pid);
				Playground p = playgroundService.getPlaygroundById(pid);
				o.setPlaygroundmanager_id(p.getPlaygroundmanager_id());
				o.setCoordinateslatitude(p.getCoordinateslatitude());
				o.setCoordinateslongitude(p.getCoordinateslongitude());
				coachService.mergePlayground(o);
				
				Apply aply = applyService.getApplyById(id);
				if(aply != null){
					aply.setStatus(3);
					applyService.updateApply(aply);
				}
				content = o.getName()+"先生您好,您在"+pd.getName()+"场馆申请已经通过,恭喜!";
//				SMSUtil.sendSmsMsg(o.getPhone(), o.getName()+"先生您好,您在"+Settings.PROJECT_NAME+"APP中的"+pd.getName()+"场馆申请已经通过,恭喜!");
			}
		}else{
			ap.setPlayground_id(pid);
			List<Apply> listApply = applyService.getApplyByObj(ap);
			//删除此教练申请当前场馆的申请记录
			if(listApply != null){
				for (Apply apply : listApply) {
					applyService.deleteApply(apply);
				}
			}
			content = o.getName()+"先生您好,您在"+pd.getName()+"场馆申请失败了,抱歉!";
//			SMSUtil.sendSmsMsg(o.getPhone(), o.getName()+"先生您好,您在"+Settings.PROJECT_NAME+"APP中的"+pd.getName()+"场馆申请失败了,抱歉!");
		}
	
		StationMessage s = new StationMessage();
		s.setContent(content);
		s.setTitle("成为驻场教练审核通知");
		s.setSummary("成为驻场教练审核通知");
		s.setWeuser_id(o.getUserid().getId());
		System.err.println(o.getUserid().getId()+"保存到消息表中");
		stationMessageService.saveStationMessage(s);
		
		return "redirect:coach_apply_list.do";
	}
	
	/**
	 * 场馆运营者查看自己场馆下的所有教练
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "managerCoachList")
	public String managerCoachList(HttpServletRequest request, ModelMap map,@RequestParam(defaultValue="1")int pageNumber,Coach o){
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		
		
		o.setPlaygroundmanager_id(pgm.getId());
		o.setVerified(1);
		o.setStatus(2);
		
		map.put("data_page",coachService.getPageFindeCoach(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("company_name", o.getName());
		return "admin/coach/coachLeave_list";
	}
	
	/**
	 * 教練離職
	 * @param coachId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "coachLeave")
	public String coachLeave(Integer coachId) throws Exception{
		
		coachL(coachId);
		return "redirect:managerCoachList.do";
	}
	
	public void coachL(Integer coachId){
		Coach c=coachService.getcoachById(coachId);
		
		//删除此教练申请当前场馆的申请记录
		Apply ap = new Apply();
		Coach co = new Coach();
		co.setId(coachId);
		ap.setCoach(co);
		ap.setPlayground_id(c.getPlayground_id());
		List<Apply> listApply = applyService.getApplyByObj(ap);
		if(listApply != null){
			for (Apply apply : listApply) {
				try {
					applyService.deleteApply(apply);
				} catch (Exception e) {
				}
			}
		}
		
		//离职没有驻场服务  lzy
		String service = "";
		if(CommonUtil.NotEmpty( c.getServices())){
			String[] services  = c.getServices().split(",");
			if(c.getServices().contains("3")){
				for (String s : services) {
					if(!s.equals("3")){
						service += s;
					}
				}
			}
		}
		//e

		c.setServices(service);
		c.setCoachType(Constants.COACHTYPE.FREECOACH.getStatus());
		c.setPlayground_id(0);
		c.setPlaygroundmanager_id(0);
		
		
		
		try {
			coachService.mergePlayground(c);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		if(coach_set_timeService.getCoach_set_timeByCoachId(coachId)==null){
			//工作时间
			for (int i = Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
				Coach_set_time cst = new Coach_set_time();
				cst.setCoach_id(c);
				cst.setTime(i);
				cst.setTime_type(1);
				cst.setFlag(Constants.NORMAL_FLAG);
				try {
					coach_set_timeService.saveCoachSetTime(cst);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//节假时间
			for (int i =  Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
				Coach_set_time cst = new Coach_set_time();
				cst.setCoach_id(c);
				cst.setTime(i);
				cst.setTime_type(2);
				cst.setFlag(Constants.NORMAL_FLAG);
				try {
					coach_set_timeService.saveCoachSetTime(cst);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
