package com.project.controller.admin;

import com.project.auth.NoLoginAuth;
import com.project.common.Constants;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.pojo.AdminMenu;
import com.project.pojo.Manager;
import com.project.pojo.PlaygroundManager;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IRightService;
import com.project.service.ISystemConfigService;
import com.project.service.IUserService;
import com.project.util.RandomValidateCode;
import com.project.util.SecurityUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("adminWebController")
@RequestMapping(value="/admin")
public class AdminController extends BaseController {
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IRightService rightService;
	@Resource
	private ISystemConfigService systemConfigService;
    @Resource
    private IPlaygroundManagerService playgroundManagerService;
	
	
	@RequestMapping(value = "user.do", method = RequestMethod.POST,params = "action=changepswd")
	@ResponseBody
	public Object changepswd(String oldpswd,String newpswd,HttpServletRequest request) {
		Manager manager = getManager(request);
		if(manager.getPassword().equals(SecurityUtil.encrypt(oldpswd))){
			manager.setPassword(SecurityUtil.encrypt(newpswd));//SecurityUtil加密
			if(userService.updateUserPswd(manager)){
				return pushmsg("成功修改密码，密码将在下次登录生效！",true);
			}else{
				return pushmsg("修改密码失败，请稍候重试！",false);
			}
		}else{
			return pushmsg("您输入密码和原密码不一致，请重新输入！",false);
		}
	}
	
	@NoLoginAuth
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
		if(session.getServletContext().getAttribute(Settings.APPLICATION_PROJECT_NAME)==null){
			session.getServletContext().setAttribute(Settings.APPLICATION_PROJECT_NAME,Settings.PROJECT_NAME);//项目的名称
		}
		//E
		return "admin/login";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(Settings.MANAGER_SESSION);
		return "admin/login";
	}
	
	/**
	 * 
	 * @param manager
	 * @param session
	 * @param valicode
	 * @param request
	 * @param logintype		登录方式(现暂无用)
	 * @param map
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value = "managelogin", method = RequestMethod.POST)
	public String managerLogin(Manager manager,HttpSession session,String valicode,HttpServletRequest request,String logintype, ModelMap map) {
		logger.info("收到登录请求：username："+manager.getUsercode()+"-- password:"+manager.getPassword());
		String pass = SecurityUtil.encrypt(manager.getPassword().trim());
		String usercode = manager.getUsercode();
		manager.setPassword(pass);
		manager = userService.managerlogin(manager);
		String randomkey = String.valueOf(session.getAttribute(RandomValidateCode.RANDOMCODEKEY));
		if(randomkey.toUpperCase().equals(valicode.toUpperCase())){//输入正确验证码
			
			//S		edit by lxc	2015-12-21		 在同一个页面有两种账号登录方式	根据logintype判断,登录者是谁,
			//logintype等于2,则为场馆运营者登录
//			if(2==logintype){
//				PlaygroundManager k = new PlaygroundManager();
//				k.setUsercode(usercode);
//				k.setPassword(pass);
//				k = playgroundManagerService.playgroundmanagerlogin(k);
//				if(k!=null){
//					if(k.getIs_active()==Constants.NORMAL_FLAG){//激活状态
//						session.setAttribute(Settings.PLAYGROUNDMANAGER_SESSION, k);
//						session.setMaxInactiveInterval(60*60);
//						return "redirect:/pgm/index.do";
//					}
//					System.err.println(k.getPassword());
//					session.setAttribute("msg", "该用户已被锁定，激活请联系管理员!");
//					return "redirect:login.do";
//				}else{
//					session.setAttribute("msg", "用户名或密码不正确！");
//					return "redirect:login.do";
//				}
//			}
			//E
			
			
			if(manager!=null){
				if(manager.getIs_active()==1){//激活状态
					session.setAttribute(Settings.MANAGER_SESSION, manager);
					session.setMaxInactiveInterval(60*60);
					return "redirect:index.do";
				}
				System.err.println(manager.getPassword());
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
		if(getManager(request)==null){
			return "redirect:login.do";
		}
		List<AdminMenu> menulist = rightService.getUserMenus(getManager(request).getId());
		redirectMap.addAllAttributes(request.getParameterMap());
		session.setAttribute("all_menu_urls", getMenuUrls(rightService.getAllAdminMenu()));
		session.setAttribute("menu_urls", getMenuUrls(menulist));
		session.setAttribute("menu_codes", getMenuCodes(menulist));
		map.put("menulist", menulist);
		return "admin/index";
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
		System.setProperty("all_menu_urls", getMenuUrls(rightService.getAllAdminMenu()));
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
	@NoLoginAuth
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
}

