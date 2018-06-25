package com.project.controller.admin;

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
import com.project.common.Constants.COACHTYPE;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Coach_teach_person;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.Qualification_certificate;
import com.project.pojo.User_count;
import com.project.pojo.Weuser;
import com.project.service.ICertificateService;
import com.project.service.ICoachService;
import com.project.service.ICoach_set_timeService;
import com.project.service.ICoach_teach_personService;
import com.project.service.IPlaygroundManagerRightService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IUser_countService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;
import com.project.util.SecurityUtil;

@Controller("adminplayground_managerWebController")
@RequestMapping(value = "/admin")
public class PlaygroundManagerController extends BaseController {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PlaygroundManagerController.class);
	@Resource
	private IPlaygroundManagerRightService playgroundManagerRightService;
	@Resource
	private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private ICoachService coachService; 
	@Resource
	private IWeuserService weuserService;
	@Resource
	private ICertificateService certificateService;
	@Resource
	private ICoach_set_timeService coach_set_timeService;
	@Resource
	private IUser_countService user_countService;
	@Resource
	private ICoach_teach_personService coach_teach_personService;
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "playground_manager")
	@RequestMapping(value = "playground_manager_list")
	public String playground_manager_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,PlaygroundManager o) {
		CommonUtil.printHTTP(request);
		PlaygroundManager k = getPlaygroundManager(request);
		map.put("manager", k);
		PageFinder<PlaygroundManager> pf = playgroundManagerService.getPageFindePlaygroundManager(o, pageNumber, Constants.BACKGROUND_PAGESIZE);
		Coach c = null;
		if(pf.getDataList()!=null && pf.getDataList().size()>0){
			for (PlaygroundManager p : pf.getDataList()) {
				c = coachService.getcoachById(p.getCoachid());
				p.setCoach(c);
			}
			
		}
		map.put("data_page",pf);
		return "admin/playground_manager/pgm_list";
	}

	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "playground_manager")
	@RequestMapping(value = "toaddplayground_manager")
	public String toaddplayground_manager(HttpServletRequest request, ModelMap map) {
		CommonUtil.printHTTP(request);
	
		return "admin/playground_manager/pgm_add";
	}
	
	/**
	 * 增加
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "playground_manager")
	@RequestMapping(value = "add_playground_manager")
	public String add_playground_manager(HttpServletRequest request, ModelMap map,PlaygroundManager o) {
		CommonUtil.printHTTP(request);
		try {
			o.setPassword(SecurityUtil.encrypt(o.getPassword()));//SecurityUtil加密
			playgroundManagerService.savePlaygroundManager(o);
			
			//根据注册的场馆运营者,分配默认的权限
			playgroundManagerRightService.defaultPGMAdminRoleRight(o);
	    	Coach c =saveCoach(request, o);//保存教练为运营商
	    	o.setCoachid(c.getId());
	    	o.setHeader(c.getHead_portrait());
	    	o.setPgm_president(o.getId());				//由于一个场馆管理者有多个人员可登陆后台，需加上归属某个场馆管理者标示
	    	playgroundManagerService.mergePlaygroundManager(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "redirect:playground_manager_list.do";
	}

	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "playground_manager")
	@RequestMapping(value = "toedit_playground_manager")
	public String toedit_playground_manager(HttpServletRequest request, ModelMap map,int oid) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		PlaygroundManager p = playgroundManagerService.getPlaygroundManagerById(oid);
		String pass = SecurityUtil.decryptString(p.getPassword());
		p.setPassword(pass);
		map.put("check", check);
		map.put("o",p);
		return "admin/playground_manager/pgm_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "playground_manager")
	@RequestMapping(value = "edit_playground_manager")
	public String edit_playground_manager(HttpServletRequest request, ModelMap map,PlaygroundManager o) {
		CommonUtil.printHTTP(request);
		o.setPassword(SecurityUtil.encrypt(o.getPassword()));//SecurityUtil加密
		o.setPgm_president(o.getId());				//由于一个场馆管理者有多个人员可登陆后台，需加上归属某个场馆管理者标示
		playgroundManagerService.mergePlaygroundManager(o);
		return "redirect:playground_manager_list.do";
	}
	
	
	/**
	 * 新增或者修改检查电话
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdateCheckMobile")
	@ResponseBody
	public Object savecheckMobile(HttpServletRequest request, ModelMap map,String mobile,int id) {
		boolean flag = playgroundManagerService.checkMobile(mobile,id);
		if(flag){
			return 1;
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 新增或者修改检查登录名称
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdateCheckName")
	@ResponseBody
	public Object saveOrUpdateCheckName(HttpServletRequest request, ModelMap map,String name,int id) {
		boolean flag = playgroundManagerService.checkName(name,id);
		if(flag){
			return 1;
		}else{
			return 0;
		}
	}
	
	//后台添加管理者 创建教练用户 证书
	@Transactional
	private Coach saveCoach(HttpServletRequest request,PlaygroundManager o) throws Exception{
		Coach c=null;
		c= coachService.getcoachByphone(o.getUsercode());
		if(c==null){
			 c=new Coach();
			c.setName(o.getUsercode());
			c.setPhone(o.getUsercode());		//默认手机号,前台可以改
			c.setCoachType(Constants.COACHTYPE.CARRIEROPERATORCOACH.getStatus());	//教练类型   3，运营商	
			c.setReserve_me(Constants.IS_RESERVE0);		//用户是否可以预定我(0不可以)'
			
			Qualification_certificate qc = new Qualification_certificate();
			qc.setType(Constants.CERTIFICATE_COACH);
			certificateService.saveCertificate(qc);
			c.setCertificate(qc);
			
			Weuser weuser = null;
			weuser = weuserService.getUserByPhone(o.getUsercode());
			if(weuser==null){
				weuser = new Weuser();
				weuser.setWenumber("");
				weuser.setPass(o.getPassword());
				weuser.setUphone(o.getUsercode());
				weuser.setUsername(o.getUsercode());//默认手机号,前台可以改
				weuser.setCreate_time(CommonUtil.getTimeNow());
				weuser.setUpdate_time(CommonUtil.getTimeNow());
				weuser.setLast_login_ip(CommonUtil.getRemoteIpAddr(request));
				weuser.setLast_login_time(CommonUtil.getTimeNow());
				weuser.setFlag(Constants.NORMAL_FLAG);
				weuser.setLogin_count((weuser.getLogin_count() + 1));
				weuser.setIs_coach(Constants.IS_COACH1);
				if (CommonUtil.isEmpty(weuser.getUsername())) {
					weuser.setUsername(weuser.getReal_name());
				}
				
				weuserService.saveNewUser(weuser);
				
				//新增用户时---设置用户统计表			edit by 2015-12-17
				User_count uCount=new User_count();
				uCount.setWeuser(weuser);
				user_countService.saveuser_Count(uCount);
				
				
			}else{
				weuser.setPass(o.getPassword());
				weuser.setIs_coach(Constants.IS_COACH1);
				weuserService.mergeAndUpdateTime(weuser);
			}
			c.setUserid(weuser);
			c = coachService.saveCoach(c);
			
			//添加补贴
//			String money = systemConfigService.getConfigValueByKey(ConfigKey.COACH_SUBSIDY_PROPORTION,"0.01");//取系统配置值  教练补贴比例
//			String coach_type = systemConfigService.getConfigValueByKey(ConfigKey.COACH_SUBSIDY_TYPE,"1");//取系统配置值  教练补贴类型
//			Subsidies subsidies = new Subsidies();
//			subsidies.setMoney(Float.valueOf(money));
//			subsidies.setZhe_id(c.getId());
//			subsidies.setZhe_type(Constants.SUBSIDIES_COACH);
//			subsidies.setType(Integer.valueOf(coach_type));
//			subsidies.setEnd_time(CommonController.getCurrYearLast());
//			subsidiesService.saveSubsidies(subsidies);
			
				if (weuser.getId() > 0) {
					logger.info("注册成功！");
					
					//新增教练带人
					String p = String.valueOf(c.getPrice());
					Coach_teach_person ct = new Coach_teach_person();
					ct.setCoach_id(c.getId());
					ct.setPrice(Float.parseFloat(p));
					coach_teach_personService.saveCoach_teach_person(ct);
				
					//工作时间
					for (int i = Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
						Coach_set_time cst = new Coach_set_time();
						cst.setCoach_id(c);
						cst.setTime(i);
						cst.setTime_type(1);
						cst.setFlag(Constants.NORMAL_FLAG);
						coach_set_timeService.saveCoachSetTime(cst);
					}
					//节假时间
					for (int i = Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
						Coach_set_time cst = new Coach_set_time();
						cst.setCoach_id(c);
						cst.setTime(i);
						cst.setTime_type(2);
						cst.setFlag(Constants.NORMAL_FLAG);
						coach_set_timeService.saveCoachSetTime(cst);
					}
			}
		}else{
			
			//s add lzy
			Weuser weuser  = weuserService.getUserByPhone(o.getUsercode());
			weuser.setPass(o.getPassword());
			weuser.setIs_coach(Constants.IS_COACH1);
			weuserService.mergeAndUpdateTime(weuser);
			//e 
			c.setReserve_me(Constants.IS_RESERVE0);		//用户是否可以预定我(0不可以)'
			c.setCoachType(COACHTYPE.CARRIEROPERATORCOACH.getStatus());
			c=coachService.mergePlayground(c);
		}
		return c;
	}
}
