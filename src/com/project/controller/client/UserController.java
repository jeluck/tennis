package com.project.controller.client;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.common.ConfigKey;
import com.project.common.Constants;
import com.project.common.Constants.OperationResult;
import com.project.common.Constants.ResponseCode;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach;
import com.project.pojo.Friendship;
import com.project.pojo.Invite;
import com.project.pojo.PlatformInfo;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.Region;
import com.project.pojo.User_count;
import com.project.pojo.User_level;
import com.project.pojo.VerifyCode;
import com.project.pojo.Weuser;
import com.project.service.IBankService;
import com.project.service.ICoachService;
import com.project.service.IFriendshipService;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IPlaygroundService;
import com.project.service.IPropagandaService;
import com.project.service.IRedBagRecordService;
import com.project.service.IRegionService;
import com.project.service.IStationMessageService;
import com.project.service.ISystemConfigService;
import com.project.service.IUser_countService;
import com.project.service.IUser_levelService;
import com.project.service.IUser_vipService;
import com.project.service.IWeuserService;
import com.project.service.InviteService;
import com.project.util.CommonUtil;
import com.project.util.FileUploadUtil;
import com.project.util.FileUtil;
import com.project.util.ImageDispose;
import com.project.util.JsonInfoToHttpPost;
import com.project.util.SMSUtil;
import com.project.util.SecurityUtil;

@Controller("userController")
@RequestMapping(value = "/")
public class UserController extends BaseController {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(UserController.class);

	@Resource
	private IWeuserService weuserService;
	@Resource
	private IPropagandaService propagandaService;
	@Resource
	private IUser_countService user_countService;
	@Resource
	private IBankService bankService;
	@Resource
	private IStationMessageService stationMessageService;
	@Resource
	private IFriendshipService friendshipService;
	@Resource
	private IUser_levelService user_levelService;
	@Resource
	private ISystemConfigService systemConfigService;
	@Resource
	private IRegionService regionService;
	@Resource
	private ICoachService coachService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private IOrderMainService orderMainService;
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private IRedBagRecordService redBagRecordService; 
	@Resource
	private InviteService inviteService;
    @Resource
    private IPlaygroundManagerService playgroundManagerService;
    @Resource
    private IUser_vipService user_vipService;
	/**
	 * 检查手机号码是否可用
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "check_user_phone")
	@ResponseBody
	public Object checkUserPhone(HttpServletRequest request) {
		String userPhone = request.getParameter("userphone");

		try {
			Weuser user = weuserService.getUserByPhone(userPhone);

			if (user == null) {
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(),
						"手机号码可用", "");
			} else {
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "手机号码不可用",
						userPhone);
			}

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

	/**
	 * 获取验证码
	 * @param request
	 * @param phone  手机
	 * @param flag	 不带表示注册验证码 ;带了表示忘记密码等....
	 * @param appType 如果值为空,则是用户端注册,如果等于1,则是教练端注册
	 * @return
	 */
	@RequestMapping(value = "send_sms_for_register")
	@ResponseBody
	public Object sendSMSForRegister(HttpServletRequest request,String phone,String flag,String appType) {
		if(CommonUtil.isEmpty(phone)){
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "手机号码为空",
					phone);
		}
		if(!CommonUtil.NotEmpty(flag)){ //注册
			//由于两个端都是调一个注册方法,需要判断当前注册的手机端类型(一些用户先注册成为会员后,还可以注册为教练)	
			//edit by lxc	2015-12-15
			if(CommonUtil.NotEmptyObject(appType)){
				Coach c = coachService.getcoachByphone(phone);
				if(c!=null){
					return new BusinessResponse(
							OperationResult.USED_USER_PHONE.getStatus(), "您的手机号码已被注册",
							phone);
				} 
			}else{
				Weuser user = weuserService.getUserByPhone(phone);
				if (user != null) {
					return new BusinessResponse(
							OperationResult.USED_USER_PHONE.getStatus(), "您的手机号码已被注册",
							phone);
				}
			}
		}else{ //忘记密码
			if(CommonUtil.NotEmptyObject(appType)){
				Coach c = coachService.getcoachByphone(phone);
				if(c == null){
					return new BusinessResponse(
							Constants.ResponseCode.ERROR_MSG.getType(), "您的手机号码未注册",
							phone);
				}
			}else{
				Weuser user = weuserService.getUserByPhone(phone);
				if (user == null) {
					return new BusinessResponse(
							Constants.ResponseCode.ERROR_MSG.getType(), "您的手机号码未注册",
							phone);
				}
			}
		}
//		用户不存在
		
		
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
	     	int result = SMSUtil.sendSmsMsg(phone, "您在"+Settings.PROJECT_NAME+"获取的验证码为：" + verifyCodeString
					+ " ，请在"+code_time+"分钟内尽快验证。如非本人操作，请忽略此短信，由此给您带来的不便请谅解！");
			if (result == 0) {
				System.err.println(phone + ", 您在"+Settings.PROJECT_NAME+"获取的验证码为："
						+ verifyCodeString
						+ " ，请在"+code_time+"分钟内尽快验证。如非本人操作，请忽略此短信，由此给您带来的不便请谅解！");
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

	/***
	 * 跳到注册页
	 * 
	 * @param map
	 * @param invite_code
	 *            邀请码
	 * @return
	 */
	@RequestMapping(value = "toregister")
	public String toregister(ModelMap map, String invite_code) {
		map.put("invite_code", invite_code);
		return "/mobile/register";
		// return "/test/register";
	}

	/***
	 * 跳到注册下一步
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "tonextRegister")
	public String tonextRegister(ModelMap map, String invitedCode,
			String uphone, String pass, String username) {
		Weuser w = weuserService.getWeuserByinvite_code(invitedCode); // 邀请者信息
		if (w == null) {
			map.put("errormsg", "邀请码不存在");
			return "/mobile/nextRegister";
		}

		Weuser user = weuserService.getUserByPhone(uphone);
		if (user != null) {
			map.put("errormsg", "手机号码不可用");
			return "/mobile/nextRegister";
		}

		map.put("token", JsonInfoToHttpPost.getToken());
		map.put("friendphone", w.getUphone());
		map.put("invite_code", invitedCode);
		map.put("uphone", uphone);
		map.put("pass", pass);
		map.put("username", username);
		return "/mobile/nextRegister";
	}

	/**
	 * 添加新用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add_new_user")
	@ResponseBody
	public Object addNewUser(HttpServletRequest request) {
		logger.info("用户注册。。。。");		long s_long = System.currentTimeMillis();		//开始计时
		String phonecode = request.getParameter("phone_code");
		String uphone = request.getParameter("phone");
		String pass = request.getParameter("password");
		String username = request.getParameter("nickname");
		String real_name = request.getParameter("real_name");
		try {
			Weuser user = weuserService.getUserByPhone(uphone);
			if (user != null) {
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "您的手机号码已被注册",
						uphone);
			}
			ServletContext application = request.getSession().getServletContext();
			VerifyCode verifyCode = (VerifyCode) application.getAttribute(Settings.PHONECODE_APPLICATION+uphone);
			String phonecode_verfiy =verifyCode.getVerifycode();
			if(!CommonUtil.NotEmptyObject(verifyCode)){
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "请先获取验证码",
						uphone);
			}
			if(!phonecode.equals(phonecode_verfiy)){
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "验证码错误",
						uphone);
			}
			
			
			String code_time = systemConfigService.getConfigValueByKey(ConfigKey.DEFAULT_MOBILE_VERIFICATION_CODE_TIME,"3");//取系统配置值
			int time = Integer.valueOf(code_time);
			if(System.currentTimeMillis()-time*60*1000>verifyCode.getCreatetime()){
				application.removeAttribute(Settings.PHONECODE_APPLICATION+uphone);
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "验证码过期",
						uphone);
			}
			
			
			Weuser weuser = new Weuser();
			weuser.setWenumber("");
			weuser.setPass(SecurityUtil.encrypt(pass));
			weuser.setUphone(uphone);
			weuser.setUsername(username);
			weuser.setReal_name(real_name);
			weuser.setCreate_time(CommonUtil.getTimeNow());
			weuser.setUpdate_time(CommonUtil.getTimeNow());
			weuser.setLast_login_ip(CommonUtil.getRemoteIpAddr(request));
			weuser.setLast_login_time(CommonUtil.getTimeNow());
			weuser.setFlag(Constants.NORMAL_FLAG);
			
			if (CommonUtil.isEmpty(weuser.getUsername())) {
				weuser.setUsername(weuser.getReal_name());
			}
			weuserService.saveNewUser(weuser);
			
			//设置用户统计表
			User_count uCount=new User_count();
			uCount.setWeuser(weuser);
			user_countService.saveuser_Count(uCount);
			
			if (weuser.getId() > 0) {
				logger.info("注册成功！");
				application.removeAttribute(Settings.PHONECODE_APPLICATION+uphone);
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "注册成功",
						weuser);
			} else {
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "注册失败", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),
					Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}

	/***
	 * 跳到登录页
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "touserlogin")
	public String touserlogin(ModelMap map) {
		// return "/mobile/register";
		return "/mobile/user/login";
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "user_login")
	@ResponseBody
	public Object userLogin(HttpServletRequest request) {
		logger.info("用户登录.....");long s_long = System.currentTimeMillis();		//开始计时
		String userPhone = request.getParameter("tel");
		String password = request.getParameter("password");
		try {
			
			Weuser user = weuserService.getUserByPhone(userPhone);
			if (user == null) {
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "用户不存在",
						userPhone);
			}

			String pass = SecurityUtil.encrypt(password);
			if (!pass.equals(user.getPass())) {
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "密码不正确",
						password);
			}
			user.setLogin_count((user.getLogin_count() + 1));
			weuserService.updateUser(user);
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			setWeuserSession(request, user);
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "登录成功", user);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("登录出错");
			return new BusinessResponse(
					Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),
					Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}

	/***
	 * 跳到我的页
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "touser")
	public String touser(ModelMap map, HttpServletRequest request) {
		String userPhone = request.getParameter("phonetologin_id");
		String password = request.getParameter("phonetologin_key");
		if (CommonUtil.NotEmpty(userPhone) && CommonUtil.NotEmpty(password)) {
			System.err.println(userPhone);
			userPhone = SecurityUtil.decryptString(userPhone);
			Weuser user = weuserService.userlogin(userPhone, password);
			setWeuserSession(request, user);
		}
		Weuser w = getUser(request);
		if (w == null) {
			return "redirect:touserlogin.do";
		}
		map.put("w", w);
		
		return "/mobile/user/user";
	}

	/***
	 * 跳到个人中心页
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "myInform")
	public String myInform(ModelMap map, HttpServletRequest request) {
		Weuser w = getUser(request);
		if (w == null) {
			return "redirect:touserlogin.do";
		}
		w = weuserService.getUserById(w.getId());
		setWeuserSession(request, w);
		map.put("w", w);
		return "/mobile/user/myInform";
	}

	/***
	 * 跳到找回密码页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "forgetPwd")
	public String forgetPwd(ModelMap map) {
		return "/mobile/forgetPwd";
	}
	
	/**
	 * 设置新密码，发送手机验证码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "set_pass_verifycode")
	@ResponseBody
	public Object set_pass_verifycode(HttpServletRequest request,int id) {
		Weuser w = weuserService.getUserById(id);
		if(w==null){
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "用户不存在",
					id);
		}
		try {
			String verifyCodeString = SMSUtil.genVerifyCode(6);
			String phone = w.getUphone();
			VerifyCode verifyCode = new VerifyCode();
			verifyCode.setCreatetime(System.currentTimeMillis());
			verifyCode.setVerifycode(verifyCodeString);
			verifyCode.setPhone(phone);

			System.err.println(phone + " -> " + verifyCodeString);
			ServletContext application = request.getSession().getServletContext();
			
			
	     	application.setAttribute(Settings.PHONECODE_APPLICATION+phone, verifyCode); 
	    	int result = SMSUtil.sendSmsMsg(phone, "您在"+Settings.PROJECT_NAME+"修改密码的验证码为：" + verifyCodeString
					+ " ，请尽快验证。如非本人操作，请忽略此短信，由此给您带来的不便请谅解！");
			if (result == 0) {
				System.err.println(phone + ", 您在"+Settings.PROJECT_NAME+"设置新密码的验证码为："
						+ verifyCodeString
						+ " ，请尽快验证。如非本人操作，请忽略此短信，由此给您带来的不便请谅解！");
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "发送成功！",
						verifyCode);
			}
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(),"发送失败",phone);
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),
					Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}

	/***
	 * 重新设置密码，并对比验证码
	 * @param map
	 * @param request		
	 * @param id			用户ID
	 * @param phonecode		验证码
	 * @param pass			密码
	 * @return
	 */
	@RequestMapping(value = "toforgetPwd")
	@ResponseBody
	public Object toforgetPwd(ModelMap map,int id, HttpServletRequest request,String phonecode,String pass) {
		Weuser w = weuserService.getUserById(id);
		w=weuserService.getLonginByHttpServletRequest_Id(request, w, this.getClass());	//根据前端传的ID,找登录者,并且保存到session
		if (w == null) {
			return new BusinessResponse(OperationResult.NOT_LOGIN.getStatus(),
					OperationResult.NOT_LOGIN.getMsg(), "");
		}

		String uphone = w.getUphone();
		ServletContext application = request.getSession().getServletContext();
		
		VerifyCode verifyCode = (VerifyCode) application.getAttribute(Settings.PHONECODE_APPLICATION+uphone);
		
		String code_time = systemConfigService.getConfigValueByKey(ConfigKey.DEFAULT_MOBILE_VERIFICATION_CODE_TIME,"3");//取系统配置值
		int time = Integer.valueOf(code_time);
		if(System.currentTimeMillis()-time*60*1000>verifyCode.getCreatetime()){
			application.removeAttribute(Settings.PHONECODE_APPLICATION+uphone);
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "验证码过期",
					uphone);
		}
		if(!phonecode.equals(verifyCode.getVerifycode())){
			return new BusinessResponse(
					Constants.OperationResult.INVALID_CODE.getStatus(), Constants.OperationResult.INVALID_CODE.getMsg(),uphone);
		}
		w.setPass(SecurityUtil.encrypt(pass));
		weuserService.mergeAndUpdateTime(w);
		logger.info("用户修改密码成功。。。");
		//edit by lxc	2015-12-30	
		PlaygroundManager pgm = playgroundManagerService.getPGM(uphone);
		if(pgm!=null){
			pgm.setPassword(w.getPass());
			playgroundManagerService.mergePlaygroundManager(pgm);
			logger.info("用户修改密码并且运营者密码成功。。。");
		}//E
		application.removeAttribute(Settings.PHONECODE_APPLICATION+uphone);
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),ResponseCode.SUCCESS.getName(), "");
	}

	/***
	 * 跳到设置新密码页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "setNewPwd")
	public String setNewPwd(ModelMap map, String idcard_no) {
		Weuser user = weuserService.getUserByIdcardNo(idcard_no);
		if (user == null) {
			map.put("idcard_no", "");
		} else {
			map.put("idcard_no", idcard_no);
		}
		return "/mobile/setNewPwd";
	}


	/***
	 * 跳到修改密码
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "changePasswd")
	public String changePasswd(ModelMap map, HttpServletRequest request) {
		Weuser w = getUser(request);
		if (w == null) {
			return "redirect:touserlogin.do";
		}
		map.put("w", w);
		return "/mobile/user/changePasswd";
	}

	/**
	 * 修改我的密码
	 * @param map
	 * @param request
	 * @param pass			旧密码
	 * @param newpass		新密码
	 * @param renewpass		确认新密码
	 * @return
	 */
	@RequestMapping(value = "tochangePasswd")
	@ResponseBody
	public Object tochangePasswd(ModelMap map, HttpServletRequest request,
			String pass, String newpass, String renewpass) {
		Weuser w = getUser(request);
		w=weuserService.getLonginByHttpServletRequest_Id(request, w, this.getClass());	//根据前端传的ID,找登录者,并且保存到session
		if (w == null) {
			return new BusinessResponse(OperationResult.NOT_LOGIN.getStatus(),
					OperationResult.NOT_LOGIN.getMsg(), "");
		}

		String encryptpass = SecurityUtil.encrypt(pass);
		if (!encryptpass.equals(w.getPass())) {
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "密码不正确", pass);
		}

		if (!newpass.equals(renewpass)) {
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "新密码和确认新密码不一致",
					pass);
		}

		String encryptnewpass = SecurityUtil.encrypt(newpass);
		w.setPass(encryptnewpass);
		weuserService.mergeAndUpdateTime(w);
		//edit by lxc	2015-12-30	
		PlaygroundManager pgm = playgroundManagerService.getPGM(w.getUphone());
		if(pgm!=null){
			pgm.setPassword(w.getPass());
			playgroundManagerService.mergePlaygroundManager(pgm);
			logger.info("用户修改密码并且运营者密码成功。。。");
		}//E
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),
				OperationResult.SUCCESS.getMsg(), "");
	}
	
	
	/**
	 * 忘记密码重置密码
	 * @param map
	 * @param request
	 * @param phone_code
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "setPasswd")
	@ResponseBody
	public Object toSetPasswd(ModelMap map, HttpServletRequest request,String phone_code,String phone,String password){
		
		ServletContext application = request.getSession().getServletContext();
		VerifyCode verifyCode = (VerifyCode) application.getAttribute(Settings.PHONECODE_APPLICATION+phone);
		String phonecode_verfiy =verifyCode.getVerifycode();
		if(!CommonUtil.NotEmptyObject(verifyCode)){
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "请先获取验证码",
					phone);
		}
		if(!phone_code.equals(phonecode_verfiy)){
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
		Weuser user = weuserService.getUserByPhone(phone);
		user.setPass(SecurityUtil.encrypt(password));
		user = weuserService.mergeAndUpdateTime(user);
		//edit by lxc	2015-12-30	
		PlaygroundManager pgm = playgroundManagerService.getPGM(phone);
		if(pgm!=null){
			pgm.setPassword(user.getPass());
			playgroundManagerService.mergePlaygroundManager(pgm);
			logger.info("用户修改密码并且运营者密码成功。。。");
		}//E
		if(user!=null){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", user);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "失败", "");
		}
	}
	

//	/***
//	 * 资金管理
//	 * 
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping(value = "countdetail")
//	public String countdetail(ModelMap map, HttpServletRequest request,
//			@RequestParam(defaultValue = "1") int pageNumber) {
//		Weuser w = getUser(request);
//		if (w == null) {
//			return "redirect:touserlogin.do";
//		}
//		map.put("w", w);
//		return "/mobile/user/countDetali";
//	}

	/***
	 * 我要提现
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "getmoney")
	public String togetpay(ModelMap map, HttpServletRequest request) {
		Weuser w = getUser(request);
		if (w == null) {
			return "redirect:touserlogin.do";
		}
		map.put("w", w);
		return "/mobile/user/getmoney";
	}

//	/**
//	 * 跳到获得账号页
//	 * 
//	 * @param request
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping("getuserpay")
//	public String getuserpay(HttpServletRequest request, ModelMap map) {
//		Weuser w = getUser(request);
//		if (w == null) {
//			return "redirect:touserlogin.do";
//		}
//		map.put("w", w);
//		return "/mobile/user/getuserpay";
//	}

//	/**
//	 * 跳到myCardPay
//	 * 
//	 * @param request
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping("user_myCardPay")
//	public String user_myCardPay(HttpServletRequest request, ModelMap map) {
//		Weuser w = getUser(request);
//		if (w == null) {
//			return "redirect:touserlogin.do";
//		}
//		map.put("w", w);
//		map.put("bank_list", bankService.getAllBankInfos());
//		return "/mobile/user/myCardPay";
//	}

//	/**
//	 * 绑定银行卡
//	 * @param request
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping("user_addmyCardPay")
//	public String user_addmyCardPay(HttpSession session,
//			HttpServletRequest request, ModelMap map, Weuser nw) {
//		Weuser w = getUser(request);
//		if (w == null) {
//			return "redirect:touserlogin.do";
//		}
//		w.setBankcard_realname(nw.getBankcard_realname());
//		w.setBankinfoid(nw.getBankinfoid());
//		w.setCardnumber(nw.getCardnumber());
//		weuserService.updateUser(w);
//		setWeuserSession(request, w);
//		map.put("w", w);
//		return "redirect:getuserpay.do";
//	}

//	/**
//	 * 跳到myAliPay
//	 * 
//	 * @param request
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping("user_myAliPay")
//	public String user_myAliPay(HttpServletRequest request, ModelMap map) {
//		Weuser w = getUser(request);
//		if (w == null) {
//			return "redirect:touserlogin.do";
//		}
//		map.put("w", w);
//
//		return "/mobile/user/myAliPay";
//	}

//	/**
//	 * 绑定支付宝
//	 * 
//	 * @param request
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping("user_addmyAliPay")
//	public String user_addmyAliPay(HttpSession session,
//			HttpServletRequest request, ModelMap map, Weuser nc) {
//		Weuser w = getUser(request);
//		if (w == null) {
//			return "redirect:touserlogin.do";
//		}
//		map.put("w", w);
//		w.setAlipay_account(nc.getAlipay_account());
//		w.setAlipay_realname(nc.getAlipay_realname());
//		weuserService.updateUser(w);
//		setWeuserSession(request, w);
//		map.put("w", w);
//		return "redirect:getuserpay.do";
//	}


//	/***
//	 * 设置
//	 * 
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping(value = "set")
//	public String set(ModelMap map, HttpServletRequest request) {
//		Weuser w = getUser(request);
//		w=weuserService.getLonginByHttpServletRequest_Id(request, w, this.getClass());	//根据前端传的ID,找登录者,并且保存到session
//		if (w == null) {
//			return "redirect:touserlogin.do";
//		}
//		map.put("w", w);
//		return "/mobile/user/set";
//	}

	/***
	 * 退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "userlogout", method = RequestMethod.GET)
	public String userlogout(HttpServletRequest request) {
		request.getSession().removeAttribute(Settings.USER_SESSION);
		return "/mobile/user/login";
	}

	/**
	 * 跳到关于我们和服务协议
	 * 1.关于我们   2.服务协议    3.联系我们
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("about_user")
	public String toabout_us(HttpServletRequest request, ModelMap map,int type) {
		map.put("company", propagandaService.getPlatformInfo());
		map.put("type", type);
		return "/phone/user/about_us";
	}
	
	/**
	 * 跳到关于我们,联系我们,公司协议接口
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("about_us")
	@ResponseBody
	public Object about_us(HttpServletRequest request, ModelMap map) {
		PlatformInfo p = propagandaService.getPlatformInfo();
		if(p!=null){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", p);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
		
	}
	
	/***
	 * 上传头像
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("user_upload_head")
	@ResponseBody
	public String user_upload_head(HttpSession session,
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, ModelMap map, String imgName) {
		Weuser w = getUser(request);
		w=weuserService.getLonginByHttpServletRequest_Id(request, w, this.getClass());	//根据前端传的ID,找登录者,并且保存到session
		if (w == null) {
			return "redirect:touserlogin.do";
		}
		String url = "";
		MultipartFile file = multipartRequest.getFile(imgName);
		if (FileUtil.checkFile(file.getOriginalFilename(), ".jpg", ".jpeg",
				".png", ".gif")) {
			String newfileName = "" + System.currentTimeMillis();// 上传后的目录和文件名
			String catalogue = "upload/commodity/picture"; // 文件要保存的路径。相对于Root
			String realPath = request.getSession().getServletContext()
					.getRealPath("");
			url = FileUploadUtil.getInstance().saveFileUpload(file, catalogue,
					newfileName, realPath);
			w.setHead_photo(url);
			System.err.println(url + "====================上传头像");
			weuserService.updateUser(w);
			setWeuserSession(request, w);
		} else {
			return "请上传格式为(*.JPG | *.JPEG | *.PNG)的认证图片";
		}
		return url;
	}

	/**
	 * 对接手机端,上传头像文件(安卓端使用)
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "user_header_picture.do")
	@ResponseBody
	public Object user_header_picture(HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response, String userId) {
		logger.info("对接手机端,上传头像文件.....");long s_long = System.currentTimeMillis();		//开始计时
		String photoPath = Settings.IMAGE_SAVE_FILE_NAME + "/a_head_photo/";// 目录名
		Weuser w = null;
		try {
			w = weuserService.getUserById(Integer.parseInt(userId));
		} catch (Exception e) {
			return new BusinessResponse(
					OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败", "");
		}
		MultipartFile files = multipartRequest.getFile("imgName");// 获取前台传来的参数
		String oldphoto = w.getHead_photo();		//原头像地址
		String image_name = ImageDispose.imageName(Settings.HEAD_IMAGENAMESTR, Settings.JPG_PICTUREFORMAT);//图片名称
		if (files.isEmpty()) { // 判断上传的文件是否为空
			System.err.println(OperationResult.UNKNOWN_MISTAKE.getStatus()
					+ "空文件");
		} else {
			try {
				if(files.getSize() > Settings.IMAGESIZE){  
					return new BusinessResponse(
							OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败,您上传的图片过大,上传图片不能超过4m", "");
				}
				image_name = handleImage(files, request, photoPath, image_name,oldphoto,Settings.SMALL_IMAGENAMESTR,"x");//图片上传处理
				w.setHead_photo(image_name);
				System.err.println(image_name + "安卓====================上传头像");
				weuserService.updateUser(w);
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			} catch (Exception e) {
				e.printStackTrace();
				return new BusinessResponse(
						OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败", "");
			}
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),
				"上传成功",image_name);
	}

	/**
	 * 我的信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "myInfo")
	@ResponseBody
	public Object myInfo(HttpServletRequest request,int userId,String appsyetem){
		logger.info("我的信息....");
		long s_long = System.currentTimeMillis();		//开始计时
		Map<String,Object> map = new HashMap<String,Object>();
		User_count p = user_countService.getUser_countById(userId);
		int all = orderMainService.getOrderMainByStatus(0, userId).size();//全部
		int not = orderMainService.getOrderMainByStatus(1, userId).size();//未付款
		int wait = orderMainService.getOrderMainByStatus(3, userId).size();//待消费
		int evaluation = orderMainService.getOrderMainByStatus(5, userId).size();//待评价
		int vouchers = redBagRecordService.getRed_bag_recordByUserId(userId,Integer.valueOf(Constants.RewardType.CASHCOUPON.getStatus())).size();
		int message = stationMessageService.getStationMessageByWeuserIdAndStatus(userId,appsyetem).size();//消息
		Weuser w = p.getWeuser();
		if(w.getCityid() != null){
			w.setAddress(regionService.getRegionById(w.getCityid()).getRegion_name());
			p.setWeuser(w);
		}
		if(w.getVip()!=null){
			w.setVip_img(user_vipService.getUser_vipById(w.getVip().getId()).getImg());
		}
		map.put("user", p);
		map.put("all", all);
		map.put("not", not);
		map.put("wait", wait);
		map.put("vouchers", vouchers);   //代金卷
		map.put("evaluation", evaluation);
		map.put("message", message);
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		
		if(map!=null && map.size()>0){
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", map);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
		
	}
	
	/**
	 * 关注
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "attention")
	@ResponseBody
	public Object attention(HttpServletRequest request,String userId,String friendUserId) throws Exception{
		Friendship p2 = friendshipService.getFriendshipByUserIdAndFuserId(Integer.valueOf(userId), Integer.valueOf(friendUserId));
		if(p2!=null){
			return cancelAttention(request, userId, friendUserId);
		}else{
			Friendship o = new Friendship();
			Weuser u1 = weuserService.getUserById(Integer.valueOf(userId));
			Weuser u2 = weuserService.getUserById(Integer.valueOf(friendUserId));
			o.setUser_id(u1);
			o.setFriend_user_id(u2);
			Friendship p = friendshipService.saveFriendship(o);
			
			User_count u = user_countService.getUser_countByUserId(Integer.valueOf(userId));   //关注人
			u.setAttentecount(u.getAttentecount()+1); 
			u = user_countService.updateUser_count(u);
			
			User_count uc = user_countService.getUser_countByUserId(Integer.valueOf(friendUserId));		//被关注人
			uc.setBeconcernedcount(uc.getBeconcernedcount()+1);
			uc = user_countService.updateUser_count(uc);
			if(p!=null&&u!=null&&uc!=null){
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "成功", "");
			}else{
				return new BusinessResponse(
						Constants.ResponseCode.EMPTY_DATA.getType(), "失败", "");
			}
		}
		
	}
	
	/**
	 * 取消关注
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "cancelAttention")
	@ResponseBody
	public Object cancelAttention(HttpServletRequest request,String userId,String friendUserId) throws Exception{
		Friendship p = friendshipService.getFriendshipByUserIdAndFuserId(Integer.valueOf(userId), Integer.valueOf(friendUserId));
		friendshipService.deleteFriendshipByObject(p);
		
		User_count u = user_countService.getUser_countByUserId(Integer.valueOf(userId));
		u.setAttentecount(u.getAttentecount()-1); 
		u = user_countService.updateUser_count(u);
		
		User_count uc = user_countService.getUser_countByUserId(Integer.valueOf(friendUserId));
		uc.setBeconcernedcount(uc.getBeconcernedcount()-1);
		uc = user_countService.updateUser_count(uc);
		if(u!=null&&uc!=null){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "");
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "失败", "");
		}
		
	}
	
	/**
	 * 我的邀请信息接口
	 * @param request
	 * @param cityName
	 * @return
	 */
	@RequestMapping("invite")
	@ResponseBody
	public Object pridads(HttpServletRequest request,String cityName,String userId) {
		logger.info("我的邀请信息接口....");
		long s_long = System.currentTimeMillis();		//开始计时
		int city_show_id = 0;
		if(CommonUtil.NotEmpty(cityName)){
			String cityNameCl = cityName + "市";
			Region region = regionService.getRegionByCityName(cityNameCl);
			city_show_id = region.getRegion_id();
		}else{
			city_show_id = regionService.getRegionByCityName("深圳市").getRegion_id();
		}
		List<Coach> coachList = coachService.getCoachAndUserList(Integer.valueOf(userId),city_show_id,Constants.STICK1);
		List<Coach> list = new ArrayList<Coach>();
		if(coachList!=null&&coachList.size()>0){
			for (Coach coach : coachList) {
				coach.setCity(regionService.getRegionById(coach.getNow_live_city()).getRegion_name());
				coach.setArea(regionService.getRegionById(coach.getAreaid()).getRegion_name());
				coach.setAttention(user_countService.getUser_countByUserId(coach.getUserid().getId()).getBeconcernedcount());
				coach.setUid(coach.getUserid().getId());
				Friendship p = friendshipService.getFriendshipByUserIdAndFuserId(Integer.valueOf(userId), coach.getUserid().getId());
				if(p!=null){
					coach.setAreConcerned(1);
				}else{
					coach.setAreConcerned(0);
				}
				list.add(coach);
			}
		}else{
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	
		if(list!=null&&list.size()>0){
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", list);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
	
	
	/**
	 * 跳转个人中心
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "toSaveUser")
	public String toSaveUser(HttpServletRequest request,ModelMap map,int userId){
		logger.info("跳转到个人中心....");
		long s_long = System.currentTimeMillis();		//开始计时
		Weuser o = weuserService.getUserById(userId);
		map.put("o", o);
		List<User_level>  levelList = user_levelService.getUserLevleList();
		for (User_level level : levelList) {
			level.setName(level.getName().replaceAll("\r\n","<br/>"));
		}
		map.put("level", levelList);
		if(o != null){
			if(o.getCityid()!=null && o.getCityid()>0){
				Region r = regionService.getRegionById(o.getCityid());
				
				map.put("rname",regionService.getRegionById(r.getParent_id()).getRegion_name()+" "+r.getRegion_name()+" "+regionService.getRegionById(o.getAreaid()).getRegion_name());
				if(o.getHometown()!=null && o.getHometown()>0){
					Region r1 = regionService.getRegionById(o.getHometown());
					map.put("jname", regionService.getRegionById(r1.getParent_id()).getRegion_name()+" "+r1.getRegion_name());
					
				}else{
					map.put("jname","");
				}
			}else{
				map.put("rname","");
				if(o.getHometown()!=null && o.getHometown()>0){
					Region r1 = regionService.getRegionById(o.getHometown());
					map.put("jname", regionService.getRegionById(r1.getParent_id()).getRegion_name()+" "+r1.getRegion_name());
				}else{
					map.put("jname","");
				}
			}
		}else{
			map.put("rname","");
			map.put("jname","");
		}
		Invite invite = inviteService.getInviteByfriend_user_id(userId);
		map.put("invite", invite);
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		return "/phone/user/user";
	}
	
	/**
	 * APP 个人中心 保存用户
	 * @param userId 		           用户编号		
	 * @param username			姓名
	 * @param sex				性别
	 * @param birthday			生日
	 * @param signature			签名
	 * @param tennis_level		网球等级
	 * @param cityid			所在城市
	 * @param hometown			籍贯
	 * @param school			学校
	 * @param employer			工作单位
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveUserInfo")
	@ResponseBody
	public Object saveUserInfo(int userId,String username,String sex,
								String birthday,String signature,
								String tennis_level,int cityid,
								int areaid,int hometown,
								String school, String employer,
								String address,String invite_id) throws Exception{
		logger.info("APP 个人中心 保存用户....");
		long s_long = System.currentTimeMillis();		//开始计时
		Weuser user = weuserService.getUserById(userId);
		user.setUsername(username);
		user.setSex(sex);
		user.setBirthday(birthday);
		
		//根据生日计算年龄
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); //本年
		String birthdayT = birthday.substring(0, birthday.indexOf("-"));  
		int age = year - Integer.valueOf(birthdayT);
		user.setAge(age);
		
		user.setSignature(signature);
		user.setTennis_level(tennis_level);
		user.setCityid(cityid);
		user.setHometown(hometown);
		user.setSchool(school);
		user.setEmployer(employer);
		user.setAddress(address);
		user.setAreaid(areaid);
		//s lzy  修改用户个人信息同时修改教练信息
		Coach o = coachService.getCoachByUserId(userId);
		if(o != null){
			o.setNow_live_city(cityid);				//所在城市
			o.setAreaid(areaid);
			o.setPersonalitySignature(signature);	//签名
			o.setSex(sex); 
			o.setAge(age);
			o.setName(username);
			coachService.mergePlayground(o);
		}
		//e
		
		//如果没有则保存邀请人信息
		Invite invite = inviteService.getInviteByfriend_user_id(userId);
		if(invite == null){
			Weuser user_id = weuserService.getUserByPhone(invite_id);
			Weuser friend_user_id = weuserService.getUserById(userId);
			if(user_id != null){
				//保存邀请信息
				Invite i = new Invite();
				i.setUser_id(user_id);
				i.setFriend_user_id(friend_user_id);
				inviteService.saveInvite(i);
				
				//增加邀请人的邀请数量
				User_count user_count = user_countService.getUser_countByUserId(user_id.getId());
				user_count.setInvitecount(user_count.getInvitecount()+1);
				user_countService.updateUser_count(user_count);
			}
		}
		
		user = weuserService.mergeAndUpdateTime(user);
		logger.info("保存用户信息....");
		if(user!=null){
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", user);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "失败", "");
		}
	}
	
	
	
	/**
	 * 用户查看大图片
	 * @param imgurl
	 * @return
	 */
	@RequestMapping(value = "getUserImgUrl")
	@ResponseBody
	public Object getUserImgUrl(int userId){
		String imgurl = weuserService.getUserById(userId).getHead_photo();
		int length = imgurl.lastIndexOf("/")+1;
		String imgurl_mulu = imgurl.substring(0, length);
		String imgUrl = imgurl.substring(imgurl.lastIndexOf("/")+1);
		imgUrl = imgUrl.substring(imgUrl.indexOf("_")+1);
		System.err.println(imgurl_mulu+imgUrl);
		return new BusinessResponse(
				Constants.OperationResult.SUCCESS.getStatus(), "成功", imgurl_mulu+imgUrl);
	}
	
	
	/**
	 * 去身份认证页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "auth_tion")
	public String auth_tion(HttpServletRequest request,ModelMap map,int userId){
		logger.info("身份认证页面....");
		long s_long = System.currentTimeMillis();		//开始计时
		Weuser o = weuserService.getUserById(userId);
		map.put("o", o);
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		return "/phone/user/Authentication";
	}
	
	/**
	 * 身份认证上传证书
	 * @param request
	 * @param multipartRequest
	 * @param response
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "auth_tion_img.do")
	@ResponseBody
	public Object auth(HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response, String userId,int type) {
		logger.info("身份认证上传证书.....");long s_long = System.currentTimeMillis();		//开始计时
		String photoPath = "upload/certificate/";// 目录名
		Weuser o  = null;
		try {
			o = weuserService.getUserById(Integer.parseInt(userId));
		} catch (Exception e) {
			return new BusinessResponse(
					OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败", "");
		}
		String image_path = "";
		MultipartFile files = multipartRequest.getFile("imgName");// 获取前台传来的参数
		String image_name = ImageDispose.imageName(Settings.CERTIFICATE_IMAGENAMESTR, Settings.JPG_PICTUREFORMAT);//图片名称
		if (files.isEmpty()) { // 判断上传的文件是否为空
			System.err.println(OperationResult.UNKNOWN_MISTAKE.getStatus()
					+ "空文件");
		} else {
			try {
				if(files.getSize() > Settings.IMAGESIZE){  
					return new BusinessResponse(
							OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败,您上传的图片过大,上传图片不能超过4m", "");
				}
				image_name = handleImage(files, request, photoPath, image_name,"",Settings.SMALL_IMAGENAMESTR,"");//图片上传处理
				if(type == 1){
					o.setIdcard_photo_positive(image_name);
				}else if(type == 2){
					o.setIdcard_photo_anti(image_name);
				}
				System.err.println(image_path + "安卓====================上传身份证照");
				weuserService.mergeAndUpdateTime(o);
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			} catch (Exception e) {
				return new BusinessResponse(
						OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败", "");
			}
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),
				"上传成功", image_name);
	}
	
	/**
	 * 身份认证
	 * @param userId
	 * @param idcard_no
	 * @return
	 */
	@RequestMapping(value = "saveAuth_tion")
	@ResponseBody
	public Object saveAuth_tion(int userId,String idcard_no){
		
		logger.info("身份认证....");
		long s_long = System.currentTimeMillis();		//开始计时
		Weuser o = weuserService.getUserById(userId);
//		o.setIdcard_no(idcard_no);
//		o.setIdcard_status(1);
//		o = weuserService.mergeAndUpdateTime(o);
		if(o!=null ){
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", orderInfoService.getOrderinfoListByToday(userId));
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "失败", "");
		}
	}
	
	/**
	 * 邀请检查手机
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "checkfindPhone")
	@ResponseBody
	public Object checkfindPhone(String phone){
		Weuser u = weuserService.getUserByPhone(phone);
		if(u != null){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "");
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "失败", "");
		}
	}
	
	/**
	 * 邀请注册
	 * @param inviteCode
	 * @return
	 */
	@RequestMapping(value = "invitation_reg.do")
	public String invitation_reg(ModelMap map,String inviteCode){
		inviteCode = inviteCode.substring(13, inviteCode.length());
		int id = Integer.valueOf(inviteCode);
		Weuser u = weuserService.getUserById(id);
		String phone = u.getUphone().substring(0,3)+"****"+u.getUphone().subSequence(7, 11);
		map.put("o", u);
		map.put("phone", phone);
		return "/phone/user/invitation_reg";
	}
	
	/**
	 * 已经注册页面
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "invitation_alreadyReg.do")
	public String invitation_alreadyReg(ModelMap map){
		map.put("coach", Settings.SERVER_HOST+systemConfigService.getConfigValueByKey(ConfigKey.ANDROIDCOACHVERSIONURL));
		map.put("user", Settings.SERVER_HOST+systemConfigService.getConfigValueByKey(ConfigKey.ANDROIDUSERVERSIONURL));
		return "/phone/user/invitation_alreadyReg";
	}
	
	/**
	 * 注册成功页面
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "invitation_success.do")
	public String invitation_success(ModelMap map){
		map.put("coach", Settings.SERVER_HOST+systemConfigService.getConfigValueByKey(ConfigKey.ANDROIDCOACHVERSIONURL));
		map.put("user", Settings.SERVER_HOST+systemConfigService.getConfigValueByKey(ConfigKey.ANDROIDUSERVERSIONURL));
		return "/phone/user/invitation_success";
	}
	
	
	/**
	 * 邀请添加新用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addUser")
	@ResponseBody
	public Object addUser(HttpServletRequest request) {
		logger.info("用户注册。。。。");		long s_long = System.currentTimeMillis();		//开始计时
		String phonecode = request.getParameter("phone_code");
		String uphone = request.getParameter("phone");
		String pass = request.getParameter("password");
		String username = request.getParameter("nickname");
		String userId = request.getParameter("userId");
		try {
			Weuser user = weuserService.getUserByPhone(uphone);
			if (user != null) {
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "您的手机号码已被注册",
						uphone);
			}
			ServletContext application = request.getSession().getServletContext();
			VerifyCode verifyCode = (VerifyCode) application.getAttribute(Settings.PHONECODE_APPLICATION+uphone);
			if(!CommonUtil.NotEmptyObject(verifyCode)){
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "请先获取验证码",
						uphone);
			}
			String phonecode_verfiy =verifyCode.getVerifycode();
			if(!phonecode.equals(phonecode_verfiy)){
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "验证码错误",
						uphone);
			}
			
			
			String code_time = systemConfigService.getConfigValueByKey(ConfigKey.DEFAULT_MOBILE_VERIFICATION_CODE_TIME,"3");//取系统配置值
			int time = Integer.valueOf(code_time);
			if(System.currentTimeMillis()-time*60*1000>verifyCode.getCreatetime()){
				application.removeAttribute(Settings.PHONECODE_APPLICATION+uphone);
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "验证码过期",
						uphone);
			}
			
			
			Weuser weuser = new Weuser();
			weuser.setWenumber("");
			weuser.setPass(SecurityUtil.encrypt(pass));
			weuser.setUphone(uphone);
			weuser.setUsername(username);
			weuser.setCreate_time(CommonUtil.getTimeNow());
			weuser.setUpdate_time(CommonUtil.getTimeNow());
			weuser.setLast_login_ip(CommonUtil.getRemoteIpAddr(request));
			weuser.setLast_login_time(CommonUtil.getTimeNow());
			weuser.setFlag(Constants.NORMAL_FLAG);
			if (CommonUtil.isEmpty(weuser.getUsername())) {
				weuser.setUsername(weuser.getReal_name());
			}
			
			weuser = weuserService.saveNewUser(weuser);
			
			Weuser user_id = weuserService.getUserById(Integer.valueOf(userId));
			//保存邀请信息
			Invite i = new Invite();
			i.setUser_id(user_id);
			i.setFriend_user_id(weuser);
			inviteService.saveInvite(i);
			
			//增加邀请人的邀请数量
			User_count user_count = user_countService.getUser_countByUserId(user_id.getId());
			user_count.setInvitecount(user_count.getInvitecount()+1);
			user_countService.updateUser_count(user_count);
			
			//设置用户统计表
			User_count uCount=new User_count();
			uCount.setWeuser(weuser);
			user_countService.saveuser_Count(uCount);
			
			if (weuser.getId() > 0) {
				logger.info("注册成功！");
				application.removeAttribute(Settings.PHONECODE_APPLICATION+uphone);
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "注册成功",
						weuser);
			} else {
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "注册失败", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),
					Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}
	
	/**
	 * 用户入会vip
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "vip.do")
	public String user_vip(ModelMap map,int userId){
		map.put("o", weuserService.getUserById(userId));
		map.put("vip",user_vipService.getUser_vipById(1));
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		return "/phone/user/vip";
	}
	
}
