package com.project.controller.palyground;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.project.auth.UserRightAuth;
import com.project.common.ConfigKey;
import com.project.common.Constants;
import com.project.common.Constants.COACHTYPE;
import com.project.common.Settings;
import com.project.common.Constants.OperationResult;
import com.project.controller.BaseController;
import com.project.controller.client.CommonController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.AdminMenu;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Coach_teach_person;
import com.project.pojo.Manager;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.Qualification_certificate;
import com.project.pojo.Region;
import com.project.pojo.StationMessage;
import com.project.pojo.Subsidies;
import com.project.pojo.User_count;
import com.project.pojo.VerifyCode;
import com.project.pojo.Weuser;
import com.project.service.ICertificateService;
import com.project.service.ICoachService;
import com.project.service.ICoach_set_timeService;
import com.project.service.ICoach_teach_personService;
import com.project.service.IDictionariesService;
import com.project.service.IPlaygroundManagerRightService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IPlaygroundService;
import com.project.service.IRegionService;
import com.project.service.IStationMessageService;
import com.project.service.ISubsidiesService;
import com.project.service.ISystemConfigService;
import com.project.service.IUserService;
import com.project.service.IUser_countService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;
import com.project.util.ImageDispose;
import com.project.util.RandomValidateCode;
import com.project.util.SMSUtil;
import com.project.util.SecurityUtil;

@Controller("playgroundManagerWebController")
@RequestMapping(value="/pgm")
public class PlaygroundManagerAdminController extends BaseController {

	@Resource
	private IPlaygroundManagerRightService playgroundManagerRightService;
	@Resource
	private ISystemConfigService systemConfigService;
	
    @Resource
    private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private IUser_countService user_countService;
	@Resource
	private ICoach_teach_personService coach_teach_personService;
	@Resource
	private ISubsidiesService subsidiesService;
	@Resource
	private IWeuserService weuserService;
	@Resource
	private ICertificateService certificateService;
	@Resource
	private ICoachService coachService; 
	@Resource
	private ICoach_set_timeService coach_set_timeService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private IRegionService regionService;
	@Resource
	private IDictionariesService dictionariesService;
	@Resource
	private IUserService userService;
	@Resource
	private IStationMessageService stationMessageService;
	
	@RequestMapping(value = "user.do", method = RequestMethod.POST,params = "action=changepswd")
	@ResponseBody
	public Object changepswd(String oldpswd,String newpswd,HttpServletRequest request) {
		PlaygroundManager k = getPlaygroundManager(request);
		if(k.getPassword().equals(SecurityUtil.encrypt(oldpswd))){
			k.setPassword(SecurityUtil.encrypt(newpswd));//SecurityUtil加密
			if(playgroundManagerService.mergePlaygroundManager(k)!=null){
				//edit by lxc	2015-12-30	
				Weuser user = weuserService.getUserByPhone(k.getUsercode());
				if(user!=null){
					user.setPass(k.getPassword());
					user = weuserService.mergeAndUpdateTime(user);
					logger.info("运营者修改密码,修改用户密码成功。。。");
				}
				//E
				return pushmsg("成功修改密码，密码将在下次登录生效！",true);
			}else{
				return pushmsg("修改密码失败，请稍候重试！",false);
			}
		}else{
			return pushmsg("您输入密码和原密码不一致，请重新输入！",false);
		}
	}
	
	
    
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpSession session,ModelMap map) {
		if(session.getAttribute("msg")!=null){
			map.put("msg", session.getAttribute("msg"));
			session.removeAttribute("msg");
		}
		//S 把信息放到application中		add by lxc	2015-07-27
		if(session.getServletContext().getAttribute("server_host")==null){
			session.getServletContext().setAttribute("server_host",Settings.SERVER_HOST);//服务器主机域名
		}
		//E
		return "pgm/login";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(Settings.PLAYGROUNDMANAGER_SESSION);
		return "pgm/login";
	}
	
	/**
	 * 
	 * @param k
	 * @param session
	 * @param valicode
	 * @param request
	 * @param logintype		登录方式(现暂无用)
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "pgmlogin", method = RequestMethod.POST)
	public String pgmlogin(PlaygroundManager k,HttpSession session,String valicode,HttpServletRequest request,String logintype, ModelMap map) {
		logger.info("收到登录请求：uphone："+k.getUsercode()+"-- password:"+k.getPassword());
		String pass = SecurityUtil.encrypt(k.getPassword().trim());
		String usercode = k.getUsercode();
		k.setPassword(pass);
		k = playgroundManagerService.playgroundmanagerlogin(k);
		String randomkey = String.valueOf(session.getAttribute(RandomValidateCode.RANDOMCODEKEY));
		if(randomkey.toUpperCase().equals(valicode.toUpperCase())){//输入正确验证码
			
			//S		edit by lxc	2015-12-21		 在同一个页面有两种账号登录方式	根据logintype判断,登录者是谁,
			//logintype等于1,则为管理员登录
//			if(1==logintype){
//				Manager m = new Manager();
//				m.setUsercode(usercode);
//				m.setPassword(pass);
//				m = userService.managerlogin(m);
//				if(m!=null){
//					if(m.getIs_active()==1){//激活状态
//						session.setAttribute(Settings.MANAGER_SESSION, m);
//						session.setMaxInactiveInterval(60*60);
//						return "redirect:/admin/index.do";
//					}
//					session.setAttribute("msg", "该用户已被锁定，激活请联系管理员!");
//				}else{
//					session.setAttribute("msg", "用户名或密码不正确！");
//				}
//			}
			//E
			
			
			
			if(k!=null){
				if(k.getIs_active()==Constants.NORMAL_FLAG){//激活状态
					session.setAttribute(Settings.PLAYGROUNDMANAGER_SESSION, k);
					session.setMaxInactiveInterval(60*60);
					return "redirect:index.do";
				}
				System.err.println(k.getPassword());
				session.setAttribute("msg", "该用户已被锁定，激活请联系管理员!");
			}else{
				session.setAttribute("msg", "用户名或密码不正确！");
			}
		}else{
			session.setAttribute("msg", "验证码输入有误！");
		}
		return "redirect:login.do";
	}
	
	//首页视图
	@RequestMapping(value = "index")
	public String show_index(HttpServletRequest request,HttpSession session,ModelMap map,RedirectAttributesModelMap redirectMap) {
		PlaygroundManager k = getPlaygroundManager(request);
		if(k==null){
			return "redirect:login.do";
		}
		List<AdminMenu> menulist = playgroundManagerRightService.getUserMenus(k.getId(),k.getPgm_president());
		redirectMap.addAllAttributes(request.getParameterMap());
		session.setAttribute("all_menu_urls", getMenuUrls(playgroundManagerRightService.getAllAdminMenu()));
		session.setAttribute("menu_urls", getMenuUrls(menulist));
		session.setAttribute("menu_codes", getMenuCodes(menulist));
		map.put("menulist", menulist);
		map.put("k", k);
		
		//弹出最新消息		add by lxc	2016-01-04
		PageFinder<StationMessage> list = stationMessageService.getPageStationMessageByUserId(k.getId(), 1, 1,2,"");
		if(list!=null && list.getDataList()!=null && list.getDataList().size()>0){
			StationMessage sm = list.getDataList().get(0);
			if(sm.getReadstatus()==Constants.MessageReadStatus.NOT_READ.getStatus()){
				map.put("sm", sm);
			}
		}//E
		
		return "pgm/index";
	}
	
	private Object getMenuCodes(List<AdminMenu> menulist) {
		StringBuilder menu_codes  = new StringBuilder("");
		for (AdminMenu adminMenu : menulist) {
			menu_codes.append(","+adminMenu.getMenu_code());
		}
		return menu_codes.toString();
	}

	@PostConstruct
	public void saveSysAllMenus(){
		System.setProperty("all_menu_urls", getMenuUrls(playgroundManagerRightService.getAllAdminMenu()));
	}
	
	private String getMenuUrls(List<AdminMenu> menu_list){
		StringBuilder menu_urls  = new StringBuilder("");
		for (AdminMenu adminMenu : menu_list) {
			menu_urls.append(","+adminMenu.getMenu_url());
		}
		return menu_urls.toString();
	}
	
	/**
	 * 输入验证码图片
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "valicode.do", method = RequestMethod.GET, params = "action=valicode")
	public void valiimg(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response);// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 上传文件
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "upload_picture.do")
	@ResponseBody
	public Object upload_header_picture(HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response,String folder,int type) {
		String image_path ="";
		MultipartFile imgFile1 = multipartRequest.getFile("imgName");//获取前台传来的参数
			if (imgFile1.isEmpty()){		//判断上传的文件是否为空
				System.err.println(OperationResult.UNKNOWN_MISTAKE.getStatus()+"空文件");
			}else{
				try{
					
					// 上传文件img1
					String photoPath = Settings.IMAGE_SAVE_FILE_NAME + "/"+folder+"/";		//图片目录路径
					if (imgFile1 != null && imgFile1.getSize() > 0) {
						String imagenamestr = "";
						if(type == 1){
							imagenamestr = Settings.COACHHEAD_IMAGENAMESTR;  //教练头像 
							String image_name = ImageDispose.imageName(imagenamestr, Settings.JPG_PICTUREFORMAT);//图片名称
							image_path=handleImage(imgFile1, request, photoPath, image_name,"",Settings.SMALL_IMAGENAMESTR,"");			//图片上传处理
						}else if(type == 2){
							imagenamestr = Settings.PLAYPROUND_IMAGENAMESTR;  //场馆图片
							String image_name = ImageDispose.imageName(imagenamestr, Settings.JPG_PICTUREFORMAT);//图片名称
							image_path=handleImage(imgFile1, request, photoPath, image_name,"",Settings.SMALL_IMAGENAMESTR,"");			//图片上传处理
						}else if(type == 3){
							imagenamestr = Settings.CERTIFICATE_IMAGENAMESTR;  //证书图片
							String image_name = ImageDispose.imageName(imagenamestr, Settings.JPG_PICTUREFORMAT);//图片名称
							image_path=handleImage(imgFile1, request, photoPath, image_name,"","","");			//图片上传处理
						}else if(type == 4){
							imagenamestr = Settings.EVENTS_IMAGENAMESTR;  //证书图片
							String image_name = ImageDispose.imageName(imagenamestr, Settings.JPG_PICTUREFORMAT);//图片名称
							image_path=handleImage(imgFile1, request, photoPath, image_name,"","","");			//图片上传处理
						}
						System.err.println(image_path + "后台====================上传图片,图片大小:"+(float)imgFile1.getSize()/1024/1024);
					}
					
				}catch(Exception e){
					return new BusinessResponse(OperationResult.UNKNOWN_MISTAKE.getStatus(),"上传失败", "");
				}
			}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),"上传成功", image_path);
	}
	
	
	
	
	@RequestMapping(value = "reg", method = RequestMethod.GET)
	public String reg(HttpSession session,ModelMap map) {
		if(session.getAttribute("msg")!=null){
			map.put("msg", session.getAttribute("msg"));
			session.removeAttribute("msg");
		}
		return "pgm/reg";
	}
	
	
	
	/**
	 * 获取验证码
	 * @param request
	 * @param phone  手机
	 * @param flag	 不带表示注册验证码 ;带了表示忘记密码等....
	 * @return
	 */
	@RequestMapping(value = "send_sms_for_reg")
	@ResponseBody
	public Object send_sms_for_reg(HttpServletRequest request,String phone,String flag) {
		if(!CommonUtil.NotEmpty(flag)){
			PlaygroundManager p = playgroundManagerService.getPGM(phone);
			if (p != null) {
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "您的手机号码已被注册",
						phone);
			}
		}
		Coach c = coachService.getcoachByphone(phone);
		if(c!=null && c.getCoachType()==Constants.COACHTYPE.INNERCOACH.getStatus()){
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "驻场教练不可申请,请先解除与场馆的关系",
					phone);
		}
		if(CommonUtil.isEmpty(phone)){
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "手机号码为空",
					phone);
		}
		try {
			String verifyCodeString = SMSUtil.genVerifyCode(6);
			VerifyCode verifyCode = new VerifyCode();
			verifyCode.setCreatetime(System.currentTimeMillis());
			verifyCode.setVerifycode(verifyCodeString);
			verifyCode.setPhone(phone);

			System.err.println(phone + " -> " + verifyCodeString);
			ServletContext application = request.getSession().getServletContext();
	     	application.setAttribute(Settings.PHONECODE_APPLICATION+phone, verifyCode); 
	     	
	     	String code_time = systemConfigService.getConfigValueByKey(ConfigKey.DEFAULT_MOBILE_VERIFICATION_CODE_TIME,"3");//取系统配置值
	     	String msg = "您在"+Settings.PROJECT_NAME+"获取的验证码为：" + verifyCodeString+ " ，请在"+code_time+"分钟内尽快验证。如非本人操作，请忽略此短信，由此给您带来的不便请谅解！";
	     	int result = SMSUtil.sendSmsMsg(phone, msg);
			if (result == 0) {
				System.err.println(phone + msg);
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "发送成功！",
						verifyCode);
			}
			
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(),"发送失败",phone);
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			logger.error("");

			return new BusinessResponse(
					Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),
					Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}
	
	@ResponseBody
	@RequestMapping("checkCode")
	public Object checkCode(HttpServletRequest request,String phone,String phonecode){
		if(!CommonUtil.NotEmptyObject(phonecode)){
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "验证码为空",
					phone);
		}
		ServletContext application = request.getSession().getServletContext();
		VerifyCode verifyCode = (VerifyCode) application.getAttribute(Settings.PHONECODE_APPLICATION+phone);
		if(!CommonUtil.NotEmptyObject(verifyCode)){
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "请先获取验证码",
					phone);
		}
		String phonecode_verfiy =verifyCode.getVerifycode();
		if(!phonecode.equals(phonecode_verfiy)){
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "验证码错误",
					phone);
		}
		String code_time = systemConfigService.getConfigValueByKey(ConfigKey.DEFAULT_MOBILE_VERIFICATION_CODE_TIME,"3");//取系统配置值
		int time = Integer.valueOf(code_time);
		if(System.currentTimeMillis()-time*60*1000>verifyCode.getCreatetime()){
			application.removeAttribute(Settings.PHONECODE_APPLICATION+phone);
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "验证码过期",
					phone);
		}
		return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), "成功！",verifyCode);
	}
	
	@RequestMapping("savePGM")
	@Transactional
	public String savePGM(HttpServletRequest request,HttpServletResponse response,PlaygroundManager o,HttpSession session){
		o.setPassword(SecurityUtil.encrypt(o.getPassword()));//SecurityUtil加密
		try {
			o.setIs_active(Constants.NORMAL_FLAG);
			o.setUsername(o.getUsercode());
			o.setMobile(o.getUsercode());
			playgroundManagerService.savePlaygroundManager(o);
			
			//根据注册的场馆运营者,分配默认的权限
			playgroundManagerRightService.defaultPGMAdminRoleRight(o);
			
			o.setPgm_president(o.getId());				//由于一个场馆管理者有多个人员可登陆后台，需加上归属某个场馆管理者标示
	    	Coach c =saveCoach(request, o);//保存教练为运营商
	    	o.setCoachid(c.getId());
	    	o.setHeader(c.getHead_portrait());
	    	playgroundManagerService.mergePlaygroundManager(o);
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute(Settings.PLAYGROUNDMANAGER_SESSION, o);
		session.setMaxInactiveInterval(60*60);
		return "redirect:index.do";
	}
	
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
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "gerenxinxiedit_coach")
	public String gerenxinxiedit_coach(HttpServletRequest request,int id,int userId,int pcId,Coach o ) {
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		if(pgm!=null){
			o.setPlaygroundmanager_id(pgm.getId());
		}
		Coach olda = coachService.getcoachById(o.getId());
		String tip="";
		try {
			//修改教练的同时，修改用户
			Weuser we = weuserService.getUserById(userId);
			we.setUsername(o.getName());
			we.setAge(o.getAge());
//			we.setUphone(o.getPhone());
			we.setHead_photo(o.getHead_portrait());
			we.setSignature(o.getPersonalitySignature());
			we.setCityid(o.getNow_live_city());
			we.setAreaid(o.getAreaid());
			we.setSex(o.getSex());
			o.setUserid(we);
			o.setCreate_time(coachService.getcoachById(id).getCreate_time());
			weuserService.mergeAndUpdateTime(we);
			
	    	Qualification_certificate qc1 = updateCertificate(request,pcId);
	    	o.setCertificate(qc1);
	    	
	    	olda.setName(o.getName());
	    	olda.setAge(o.getAge());
	    	olda.setHeight(o.getHeight());
	    	olda.setWeight(o.getWeight());
	    	olda.setPersonalitySignature(o.getPersonalitySignature());
	    	olda.setTeaching(o.getTeaching());
	    	olda.setNow_live_city(o.getNow_live_city());
	    	olda.setAreaid(o.getAreaid());
	    	olda.setTeachtype(o.getTeachtype());
	    	olda.setSex(o.getSex());
	    	olda.setBank(o.getBank());
	    	olda.setZbank(o.getZbank());
	    	olda.setBankZh(o.getBankZh());
	    	olda.setBankName(o.getBankName());
	    	if(o.getReserve_me()==Constants.IS_RESERVE0){
	    		olda.setIs_reserve(o.getIs_reserve());
		    	olda.setReserve_me(o.getReserve_me());
	    	}
	    	
	    	
	    	List<Playground> plist = playgroundService.getPlaygroundListByPlaygroundManagerId(pgm.getId());
	    	boolean b = false;
	    	if(plist.size()<=1){
	    		for (Playground pd : plist) {
					if(pd.getAuditStatus() == Constants.AUDITSTATUS_THROUGH && pd.getIs_reserve() == Constants.IS_RESERVE1){
						b=true;
					}
				}
	    		
	    		if(b){
	    			olda.setIs_reserve(o.getIs_reserve());
			    	olda.setReserve_me(o.getReserve_me());
			    	olda.setVerified(1);  //已认证
	    		}
	    	}else{
	    		tip="?tip=tip";
	    	}
	    	
	    	Coach oa = coachService.mergePlayground(olda);
	    	
	    	PlaygroundManager p = playgroundManagerService.getPlaygroundManagerById(pgm.getId());
	    	p.setUsername(o.getName());
	    	p.setHeader(o.getHead_portrait());
	    	playgroundManagerService.mergePlaygroundManager(p);
	    	if(CommonUtil.NotEmpty(tip)){
	    		tip +="success";
	    	}else{
	    		tip +="?tip=success";
	    	}
	    	//当选择了可以预定我时,但运营商没有场馆需要弹出提示
    		if(!b){
    			if(o.getReserve_me()==Constants.IS_RESERVE1){
    				tip +="no_reserve";
    			}
    		}
		} catch (Exception e) {
			logger.error("修改教练信息出现错误！错误信息："+e.getMessage());
			e.printStackTrace();
			if(CommonUtil.NotEmpty(tip)){
	    		tip +="error";
	    	}else{
	    		tip +="?tip=error";
	    	}
		}
		return "redirect:gerenxinxi.do"+tip;
	}
	
	public Qualification_certificate updateCertificate(HttpServletRequest request,int id){
		Qualification_certificate qc = certificateService.getCertificateById(id);
    	qc.setPh1(request.getParameter("z1"));
    	qc.setPh2(request.getParameter("z2"));
    	qc.setPh3(request.getParameter("z3"));
    	qc.setPh4(request.getParameter("z4"));
    	qc.setPh5(request.getParameter("z5"));
    	qc.setPh6(request.getParameter("z6"));
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
	
	@RequestMapping("gerenxinxi")
	public String gerenxinxi(HttpServletRequest request, ModelMap map){
		CommonUtil.printHTTP(request);
		Manager m = getManager(request);
		PlaygroundManager pgm = getPlaygroundManager(request);
		if(pgm==null && m==null){
			return "redirect:login.do";
		}
		map.put("groundList", playgroundService.getAll());
		Coach coach = coachService.getcoachById(pgm.getCoachid());
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
		
		String tip=request.getParameter("tip");
    	
    	
    	if(CommonUtil.NotEmpty((tip))){
    		map.put("tip", tip);
    	}
		return "/pgm/coach/gerenxinxi";
	}
}

