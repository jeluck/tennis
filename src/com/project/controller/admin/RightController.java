package com.project.controller.admin;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.AdminMenu;
import com.project.pojo.AdminRole;
import com.project.pojo.Manager;
import com.project.service.IManagerService;
import com.project.service.IRightService;
import com.project.service.IUserService;
import com.project.util.CommonUtil;
import com.project.util.SecurityUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限管理控制器
 * @author daybreak
 *
 */
@Controller("rightController")
@RequestMapping(value = "/admin")
public class RightController extends BaseController {

	@Resource
	private IUserService userService;

	@Resource
	private IRightService rightService;
	
	@Resource
	private IManagerService managerService;
	
	
	//管理员列表
	@UserRightAuth(menuCode="managerlist")
	@RequestMapping(value = "managerlist.do", method = RequestMethod.GET)
	public String managerlist(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request,ModelMap map) {
		PageFinder<Manager> ms = managerService.getManagerList(pageNumber, Constants.BACKGROUND_PAGESIZE);
		map.put("news_page", ms);
		return "admin/manager_list";
	}
	
	//保存管理员信息
	@UserRightAuth(menuCode="managerlist")
	@RequestMapping(value = "save_manager.do", method = RequestMethod.POST)
	@ResponseBody
	public Object save_manager(Manager manager) {
		if(manager.getId()==0){
			manager.setPassword(SecurityUtil.encrypt(manager.getPassword()));
			Manager managertemp = managerService.saveManager(manager);
			if(managertemp==null){
				return pushmsg("添加管理员信息失败", false);
			}
			return 	pushmsg("添加管理员信息成功", true);
		}else{
			Manager m = managerService.getManagerById(manager.getId());
			manager.setCreate_time(m.getCreate_time());
			manager.setPassword(m.getPassword());
			if(managerService.updateManager(manager)){
				return pushmsg("修改管理员信息成功", true);
			}
			return pushmsg("修改管理员信息失败", false);
		}
	}
	
	//得到管理员信息
	@UserRightAuth(menuCode="managerlist")
	@ResponseBody
	@RequestMapping(value="managerinfo.do",method = RequestMethod.GET)
	public Object medianews_info(int id){
		return managerService.getManagerById(id);
	}
	
	//对管理员进行锁定与激活操作
	@UserRightAuth(menuCode="managerlist")
	@ResponseBody
	@RequestMapping(value="lock_manager.do",method = RequestMethod.GET)
	public Object lock_manager(int id,int active){
		Manager m = managerService.getManagerById(id);
		m.setIs_active(active);
		if(managerService.updateManager(m)){
			return pushmsg("已经成功对该管理员进行状态操作.", true);
		}
		return pushmsg("操作失败，请重试.", false);
	}

	// 得到菜单列表
	@UserRightAuth(menuCode="menu_list")
	@RequestMapping(value = "menu_list.do", method = RequestMethod.GET)
	public String menu_list(ModelMap map) {
		map.put("menu_list", rightService.getAllAdminMenu());
		return "admin/admin_menu";
	}

	// 保存菜单信息
	@UserRightAuth(menuCode="menu_list")
	@RequestMapping(value = "save_menu_info.do", method = RequestMethod.POST)
	public String save_menu_info(AdminMenu menu) {
		if(menu.getId()==0){
			rightService.saveAdminMenu(menu);
		}else{
			AdminMenu o = rightService.getAdminMenuById(menu.getId());
			menu.setCreate_time(o.getCreate_time());
			menu.setMenu_icon(o.getMenu_icon());
			rightService.updateAdminMenu(menu);
		}
		return "redirect:menu_list.do";
	}
	
	/**
	 * 检查menu_code
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "checkMenu_code")
	@ResponseBody
	public Object checkMenu_code(HttpServletRequest request, ModelMap map,String menu_code,int id) {
		boolean flag = rightService.checkMenuCode(menu_code,id);
		if(flag){
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 检查Role_code
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "checkRole_code")
	@ResponseBody
	public Object checkRole_code(HttpServletRequest request, ModelMap map,String role_code,int id) {
		boolean flag1 = rightService.checkPmRoleCode(role_code,id);
		boolean flag2 = rightService.checkRoleCode(role_code,id);
		if(flag1 && flag2){
			return 1;
		}else{
			return 0;
		}
	}
	
	//根据Code得到菜单信息
	@UserRightAuth(menuCode="menu_list")
	@RequestMapping(value = "get_menu_info.do", method = RequestMethod.GET)
	@ResponseBody
	public Object save_menu_info(String menu_code) {
		return rightService.getAdminMenuByCode(menu_code);
	}

	// 得到角色列表
	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "role_list.do", method = RequestMethod.GET)
	public String role_list(ModelMap map) {
		map.put("role_list", rightService.getAllAdminRole());
		return "admin/admin_role";
	}

	// 保存角色信息
	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "save_role_info.do", method = RequestMethod.POST)
	public String save_role_info(AdminRole role) {
		if(role.getId()==0){
			role.setRole_type(2);// 2：用户自定义角色
			rightService.saveAdminRole(role);
		}else{
			AdminRole a = rightService.getAdminRoleById(role.getId());
			role.setCreate_time(a.getCreate_time());
			if(a.getRole_type()==1){
				role.setRole_type(a.getRole_type());
			}else{
				role.setRole_type(2);// 2：用户自定义角色
			}
			rightService.updateAdminRole(role);
		}
		return "redirect:role_list.do";
	}
	
	//根据Code得到角色信息
	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "get_role_info.do", method = RequestMethod.GET)
	@ResponseBody
	public Object get_role_info(String role_code) {
		return rightService.getAdminRoleByCode(role_code);
	}
	
	//得到角色权限信息
	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "get_role_rights.do", method = RequestMethod.GET)
	@ResponseBody
	public Object get_role_rights(String role_code) {
		Map<String, Object> data = new HashMap<String, Object>(2);
		data.put("menulist", rightService.getAllAdminMenu());
		data.put("rolerights", rightService.getRoleRights(role_code));
		return data;
	}
	
	//得到角色用户信息
	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "get_role_users.do", method = RequestMethod.GET)
	@ResponseBody
	public Object get_role_users(String role_code) {
		Map<String, Object> data = new HashMap<String, Object>(2);
		List<Manager> su = rightService.getRoleUsers(role_code,true);
		List<Manager> nsu = rightService.getRoleUsers(role_code,false);
		data.put("select_users", su);
		data.put("not_select_users", nsu);
		return data;
	}
	
	//保存角色权限信息
	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "save_role_rights.do", method = RequestMethod.POST)
	@ResponseBody
	public Object save_role_rights(HttpServletRequest request) {
		String menu_ids = request.getParameter("menu_id");
		String role_code = request.getParameter("role_code");
		if(rightService.saveRoleRights(menu_ids,role_code)){
			return pushmsg("保存角色权限信息成功", true);
		}
		return pushmsg("保存角色权限信息失败，请重试.", false);
	}
	
	//保存角色用户信息
	@UserRightAuth(menuCode="role_list")
	@RequestMapping(value = "save_role_users.do", method = RequestMethod.POST)
	@ResponseBody
	public Object save_role_users(HttpServletRequest request,String role_code,String users) {
		if("superadmin".equals(role_code) && users == ""){
			return pushmsg("必须保留一个超级管理员.", true);
		}
		if(rightService.saveRoleUsers(role_code,users)){
			return pushmsg("保存角色用户信息成功", true);
		}
		return pushmsg("保存角色用户信息失败，请重试.", false);
	}
	
	
	
	/**
	 * 修改检查电话
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "checkManagerPhone")
	@ResponseBody
	public Object updatecheckPlayPhone(HttpServletRequest request, ModelMap map,String phone,int id) {
		boolean flag = managerService.checkPhone(phone,id);
		if(flag){
			return 1;
		}else{
			return 0;
		}
	}
}
