package com.project.controller.palyground;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.PlaygroundManagerAdminRole;
import com.project.pojo.Manager;
import com.project.pojo.StationMessage;
import com.project.pojo.TerraceMessage;
import com.project.service.IPlaygroundManagerRightService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IStationMessageService;
import com.project.service.ITerraceMessageService;
import com.project.service.IUserService;
import com.project.util.OrderUtils;
import com.project.util.SecurityUtil;

/**
 * 权限管理控制器
 * @author daybreak
 *
 */
@Controller("playgroundManagerRightController")
@RequestMapping(value = "/pgm")
public class PlaygroundManagerRightController extends BaseController {

	@Resource
	private IUserService userService;

	@Resource
	private IPlaygroundManagerRightService kindergartenRightService;
    @Resource
    private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private IStationMessageService stationMessageService;
	
	@Resource
	private ITerraceMessageService terraceMessageService;
	
	//管理员列表
//	@UserRightAuth(menuCode="kinderlist")
	@RequestMapping(value = "pgmlist.do", method = RequestMethod.GET)
	public String pgmlist(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request,ModelMap map,PlaygroundManager c) {
		PlaygroundManager k = getPlaygroundManager(request);
		Manager manager = getManager(request);
		if(k==null && manager==null){
			return "redirect:login.do";
		}
		if(k!=null){
			c.setPgm_president(k.getPgm_president());
			map.put("show", "show");
		}
		PageFinder<PlaygroundManager> ms =playgroundManagerService.getPageFindePlaygroundManager(c, pageNumber, Constants.BACKGROUND_PAGESIZE);
		map.put("news_page", ms);
//		map.put("getKinderStatus", OrderUtils.getKinderStatus());
		
		return "pgm/manager_list";
	}
	
	//保存管理员信息
	@RequestMapping(value = "save_pgm.do", method = RequestMethod.POST)
	@ResponseBody
	public Object save_pgm(HttpServletRequest request,PlaygroundManager o) {
		PlaygroundManager k = getPlaygroundManager(request);
		if(k==null){
			return "redirect:login.do";
		}
		o.setPgm_president(k.getPgm_president());
		if(o.getId()==0){
			o.setPassword(SecurityUtil.encrypt(o.getPassword()));
			PlaygroundManager otemp=null;
			try {
				otemp = playgroundManagerService.savePlaygroundManager(o);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(otemp==null){
				return pushmsg("添加管理员信息失败", false);
			}
			return 	pushmsg("添加管理员信息成功", true);
		}else{
			PlaygroundManager m = playgroundManagerService.getPlaygroundManagerById(o.getId());
			m.setUsercode(o.getUsercode());
//			m.setPg_manager_type(o.getPg_manager_type());
			m.setIs_active(o.getIs_active());
			if(playgroundManagerService.mergePlaygroundManager(m)!=null){
				return pushmsg("修改管理员信息成功", true);
			}
			return pushmsg("修改管理员信息失败", false);
		}
	}
	
	//得到管理员信息
//	@UserRightAuth(menuCode="kinderlist")
	@ResponseBody
	@RequestMapping(value="pgminfo.do",method = RequestMethod.GET)
	public Object pgminfo(int id){
		return playgroundManagerService.getPlaygroundManagerById(id);
	}
	
	//对管理员进行锁定与激活操作
//	@UserRightAuth(menuCode="kinderlist")
	@ResponseBody
	@RequestMapping(value="lock_pgm.do",method = RequestMethod.GET)
	public Object lock_pgm(int id,int active){
		PlaygroundManager m = playgroundManagerService.getPlaygroundManagerById(id);
		m.setIs_active(active);
		if(playgroundManagerService.mergePlaygroundManager(m)!=null){
			return pushmsg("已经成功对该管理员进行状态操作.", true);
		}
		return pushmsg("操作失败，请重试.", false);
	}

	// 得到菜单列表
//	@UserRightAuth(menuCode="menu_list")
	@RequestMapping(value = "menu_list.do", method = RequestMethod.GET)
	public String menu_list(ModelMap map) {
		map.put("menu_list", kindergartenRightService.getAllAdminMenu());
		return "kinder/admin_menu";
	}

	// 保存菜单信息
//	@UserRightAuth(menuCode="menu_list")
//	@RequestMapping(value = "save_menu_info.do", method = RequestMethod.POST)
//	public String save_menu_info(AdminMenu menu) {
//		if(menu.getId()==0){
//			kindergartenRightService.saveAdminMenu(menu);
//		}else{
//			AdminMenu o = kindergartenRightService.getAdminMenuById(menu.getId());
//			menu.setCreate_time(o.getCreate_time());
//			menu.setMenu_icon(o.getMenu_icon());
//			kindergartenRightService.updateAdminMenu(menu);
//		}
//		return "redirect:menu_list.do";
//	}
	
	//根据Code得到菜单信息
//	@UserRightAuth(menuCode="menu_list")
	@RequestMapping(value = "get_menu_info.do", method = RequestMethod.GET)
	@ResponseBody
	public Object save_menu_info(String menu_code) {
		return kindergartenRightService.getAdminMenuByCode(menu_code);
	}

	// 得到角色列表
	/**
	 * 此方法，平台也会用到，需要为幼儿园的院长分配角色
	 * @param map
	 * @param request
	 * @return
	 */
	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "role_list.do", method = RequestMethod.GET)
	public String role_list(ModelMap map,HttpServletRequest request) {
		PlaygroundManager k = getPlaygroundManager(request);
		Manager manager = getManager(request);
		if(k!=null && manager==null){
			map.put("role_list", kindergartenRightService.getAllPlaygroundManagerAdminRoleAdminRole(k.getId()));
			//map.put("kindergartens", playgroundManagerService.getPlaygroundManagerListByPlaygroundManager_president(k.getPlaygroundManager_president()));
		}else{
			if(manager==null){
				return "redirect:login.do";
			}
			map.put("kindergartens", playgroundManagerService.getPlaygroundManagerListBy_pg_manager_type_Flag());
			map.put("role_list", kindergartenRightService.getAllPlaygroundManagerAdminRoleAdminRole(0));
		}
		return "pgm/admin_role";
	}

	// 保存角色信息
//	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "save_role_info.do", method = RequestMethod.POST)
	public String save_role_info(PlaygroundManagerAdminRole role,HttpServletRequest request) {
		PlaygroundManager k = getPlaygroundManager(request);
		if(k!=null){
			role.setCooperative_partner_id(k.getId());
		}
		if(role.getId()==0){
			role.setRole_type(2);// 2：用户自定义角色
			kindergartenRightService.savePlaygroundManagerAdminRole(role);
		}else{
			PlaygroundManagerAdminRole a = kindergartenRightService.getPlaygroundManagerAdminRoleById(role.getId());
			role.setCreate_time(a.getCreate_time());
			
			if(a.getRole_type()==1){
				role.setRole_type(a.getRole_type());
			}else{
				role.setRole_type(2);// 2：用户自定义角色
				role.setCooperative_partner_id(a.getCooperative_partner_id());
			}
			kindergartenRightService.updatePlaygroundManagerAdminRole(role);
		}
		return "redirect:role_list.do";
	}
	
	//根据Code得到角色信息
//	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "get_role_info.do", method = RequestMethod.GET)
	@ResponseBody
	public Object get_role_info(String role_code) {
		return kindergartenRightService.getPlaygroundManagerAdminRoleByCode(role_code);
	}
	
	//得到角色权限信息
//	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "get_role_rights.do", method = RequestMethod.GET)
	@ResponseBody
	public Object get_role_rights(String role_code) {
		Map<String, Object> data = new HashMap<String, Object>(2);
		data.put("menulist", kindergartenRightService.getAllAdminMenu());
		data.put("rolerights", kindergartenRightService.getRoleRights(role_code));
		return data;
	}
	
	//得到角色用户信息
//	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "get_role_users.do", method = RequestMethod.GET)
	@ResponseBody
	public Object get_role_users(String role_code,HttpServletRequest request,int cooperative_partner_id) {
		Map<String, Object> data = new HashMap<String, Object>(2);
		PlaygroundManager k = getPlaygroundManager(request);
		List<PlaygroundManager> su = null;
		List<PlaygroundManager> nsu = null;
		if(k!=null){
			su = kindergartenRightService.getPlaygroundManagerRoleUsers(role_code,true,k.getId());//已绑定该角色用户
			nsu = kindergartenRightService.getPlaygroundManagerRoleUsers(role_code,false,k.getId());//未绑定该角色用户
		}else{
			su = kindergartenRightService.getPlaygroundManagerRoleUsers(role_code,true,cooperative_partner_id);//已绑定该角色用户
			nsu = kindergartenRightService.getPlaygroundManagerRoleUsers(role_code,false,cooperative_partner_id);//未绑定该角色用户
		}
		
		data.put("select_users", su);
		data.put("not_select_users", nsu);
		return data;
	}
	
	//保存角色权限信息
//	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "save_role_rights.do", method = RequestMethod.POST)
	@ResponseBody
	public Object save_role_rights(HttpServletRequest request) {
		String menu_ids = request.getParameter("menu_id");
		String role_code = request.getParameter("role_code");
		if(kindergartenRightService.saveRoleRights(menu_ids,role_code)){
			return pushmsg("保存角色权限信息成功", true);
		}
		return pushmsg("保存角色权限信息失败，请重试.", false);
	}
	
	//保存角色用户信息
//	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "save_role_users.do", method = RequestMethod.POST)
	@ResponseBody
	public Object save_role_users(HttpServletRequest request,String role_code,String users,int cooperative_partner_id) {
		PlaygroundManager k = getPlaygroundManager(request);
		if(k!=null){
			if(kindergartenRightService.savePlaygroundManagerRoleUsers(role_code,users,k.getId())){
				return pushmsg("保存角色用户信息成功", true);
			}
		}else{
			if(kindergartenRightService.savePlaygroundManagerRoleUsers(role_code,users,cooperative_partner_id)){
				return pushmsg("保存角色用户信息成功", true);
			}
		}
		return pushmsg("保存角色用户信息失败，请重试.", false);
	}
	
	/**
	 * 运营者进入消息中心
	 */
	@RequestMapping(value = "tomessage")
	public String tomessage(HttpServletRequest request,@RequestParam(defaultValue="1")int pageNumber,ModelMap map){
		PlaygroundManager k = getPlaygroundManager(request);
		PageFinder<StationMessage> list = null;
		if(k!=null ){
			list = stationMessageService.getPageStationMessageByUserId(k.getId(), pageNumber, 10,2,"");
			map.put("data_page", list);
		}
		return "admin/terraceMessage/receiveMessage_list";
	}
	
	/**
	 * 去查看页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "terraceMessage")
	@RequestMapping(value = "check_terraceMessage")
	public String toEditterraceMessage(HttpServletRequest request, ModelMap map,int oid){
		PlaygroundManager k = getPlaygroundManager(request);
		if(k==null){
			return "redirect:login.do";
		}
		map.put("k", k);
		String check = request.getParameter("check");
		map.put("check", check);
		
		StationMessage s=stationMessageService.getStationMessageById(oid);
		s.setReadstatus(Constants.MessageReadStatus.HAVE_READ.getStatus());
		try {
			stationMessageService.updateStationMessage(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("o", s);
		
		return "admin/terraceMessage/managerTerraceMessage_edit";
	}
}
