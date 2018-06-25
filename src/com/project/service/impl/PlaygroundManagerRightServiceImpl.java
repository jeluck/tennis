package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.common.Settings;
import com.project.dao.IAdminMenuDao;
import com.project.dao.IAdminRoleRightDao;
import com.project.dao.IPlaygroundManagerAdminRoleDao;
import com.project.dao.IPlaygroundManagerDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.AdminMenu;
import com.project.pojo.AdminRoleRight;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.PlaygroundManagerAdminRole;
import com.project.service.IPlaygroundManagerRightService;
import com.project.util.CommonUtil;
import com.project.util.RandomUtils;

@Service("kindergartenRightServiceImpl")
public class PlaygroundManagerRightServiceImpl implements IPlaygroundManagerRightService {
	private Logger logger = Logger.getLogger(this.getClass());
    @Resource 
    private IAdminMenuDao                adminMenuDao;

    @Resource 
    private IPlaygroundManagerDao kindergartenDao; 
    
    @Resource
    private IPlaygroundManagerAdminRoleDao kindergartenAdminRoleDao;
    
    @Resource 
    private IAdminRoleRightDao                adminRoleRightDao;
    
	@Resource 
	private SessionFactory sessionFactory;
    
	@Override
	public boolean saveAdminMenu(AdminMenu menu) {
		menu.setMenu_belong_to_role(Settings.MENU_BELONG_TO_ROLE_COOPERATIVE_PARTNER);
		menu.setCreate_time(CommonUtil.getTimeNow());
		try {
			adminMenuDao.saveObject(menu);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public AdminMenu getAdminMenuById(int id){
		return adminMenuDao.getById(id);
	}

	@Override
	public boolean updateAdminMenu(AdminMenu menu) {
		menu.setUpdate_time(CommonUtil.getTimeNow());
		adminMenuDao.merge(menu);
		return true;
	}

	@Override
	public List<AdminMenu> getAllAdminMenu() {
		CriteriaAdapter criteriaAdapter = adminMenuDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("menu_belong_to_role", Settings.MENU_BELONG_TO_ROLE_COOPERATIVE_PARTNER));
		List<AdminMenu> list = criteria.list();
		adminMenuDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public List<PlaygroundManagerAdminRole> getAllPlaygroundManagerAdminRoleAdminRole(int cooperative_partner_id) {
		CriteriaAdapter criteriaAdapter = kindergartenAdminRoleDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(cooperative_partner_id>0){
			criteria.add(Restrictions.eq("cooperative_partner_id", cooperative_partner_id));
		}
		criteria.addOrder(Order.asc("create_time"));
		List<PlaygroundManagerAdminRole> list = criteria.list();
		kindergartenAdminRoleDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public boolean savePlaygroundManagerAdminRole(PlaygroundManagerAdminRole role) {
		try {
			role.setCreate_time(CommonUtil.getTimeNow());
			role.setRole_type(2);// 2：用户自定义角色
			kindergartenAdminRoleDao.save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean updatePlaygroundManagerAdminRole(PlaygroundManagerAdminRole role) {
		role.setUpdate_time(CommonUtil.getTimeNow());
		kindergartenAdminRoleDao.merge(role);
		return false;
	}


	@Override
	@Transactional
	public boolean saveRoleRights(String menu_ids, String role_code) {
		if(CommonUtil.NotEmpty(menu_ids)){
			//先清除
			List<AdminRoleRight> l = getAdminRoleRightByRole_code(role_code);
			for (AdminRoleRight o : l) {
				if(o!=null){
					try {
						adminRoleRightDao.removeById(o.getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			String[] menu_id = menu_ids.split(",");
			
			//添加
			for (String m_id : menu_id) {
				AdminRoleRight a = new AdminRoleRight();
				a.setMenu_code(m_id);
				a.setRole_code(role_code);
				try {
					adminRoleRightDao.saveObject(a);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	@Override
	public List<PlaygroundManager> getPlaygroundManagerRoleUsers(String role_code, boolean eq,int cooperative_partner_id) {
		List o = kindergartenDao.createSQLQuery("SELECT playgroundmanager_id FROM pm_admin_role_user WHERE role_code=? and cooperative_partner_id=?", role_code,cooperative_partner_id).list();
		CriteriaAdapter criteriaAdapter = kindergartenDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(eq){
			if(o.size()>0){
				criteria.add(Restrictions.in("id", o));
			}
			else{
				kindergartenDao.releaseHibernateSession(criteriaAdapter.getSession());
				kindergartenDao.clear();
				return new ArrayList<>() ;
			}
		}else{
			if(o.size()>0){
				criteria.add(Restrictions.not(Restrictions.in("id", o)));
			}
				criteria.add(Restrictions.eq("pgm_president", cooperative_partner_id));
			
			
		}
		List<PlaygroundManager> list = criteria.list();
		kindergartenDao.releaseHibernateSession(criteriaAdapter.getSession());kindergartenDao.clear();
		return list;
	}

	@Override
	public boolean savePlaygroundManagerRoleUsers(String role_code, String users,int cooperative_partner_id) {
		//S		删除角色前,如果表中有值,则清除,	add by lxc	2015-05-22
		Integer i = 0;
		try{
			List o = kindergartenDao.createSQLQuery("select id from pm_admin_role_user where role_code=? and cooperative_partner_id=?", role_code,cooperative_partner_id).list();
			i = o.size(); 
			if(CommonUtil.isEmpty(users)&&i!=0){
				//先清除
				kindergartenDao.createSQLQuery("delete from pm_admin_role_user where role_code =? and cooperative_partner_id=?", role_code,cooperative_partner_id).executeUpdate();
			}
		}catch(Exception e){
			i=0;
		}//E
		if(CommonUtil.NotEmpty(users)){
			//先清除
			kindergartenDao.createSQLQuery("delete from pm_admin_role_user where role_code =? and cooperative_partner_id=?", role_code,cooperative_partner_id).executeUpdate();
			String[] user_ids = users.split(",");
			//添加
			for (String userId : user_ids) {
				//update("insert into admin_role_user(uid,role_code) values(:uid,:role_code)", "uid",userId,"role_code",role_code);
				kindergartenDao.createSQLQuery("insert into pm_admin_role_user(playgroundmanager_id,role_code,cooperative_partner_id) values(?,?,?)", userId,role_code,cooperative_partner_id).executeUpdate();
			}
		}
		kindergartenDao.clear();
		return true;
	}

	@Override
	public AdminMenu getAdminMenuByCode(String menu_code) {
		CriteriaAdapter criteriaAdapter = adminMenuDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("menu_code",menu_code));
		AdminMenu a =null;
		try{
			a = (AdminMenu) criteria.uniqueResult();
			adminMenuDao.releaseHibernateSession(criteriaAdapter.getSession());
		}catch(Exception e){
		}
		return a;
	}
	
	@Override
	public PlaygroundManagerAdminRole getPlaygroundManagerAdminRoleById(int id) {
		return kindergartenAdminRoleDao.getById(id);
	}

	@Override
	public PlaygroundManagerAdminRole getPlaygroundManagerAdminRoleByCode(String role_code) {
		CriteriaAdapter criteriaAdapter = kindergartenAdminRoleDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("role_code",role_code));
		PlaygroundManagerAdminRole a =null;
		try{
			a = (PlaygroundManagerAdminRole) criteria.uniqueResult();
			kindergartenAdminRoleDao.releaseHibernateSession(criteriaAdapter.getSession());
		}catch(Exception e){
			return null;
		}
		return a;
	}
	
	@Override
	public List<AdminRoleRight> getAdminRoleRightByRole_code(String role_code) {
		
		CriteriaAdapter criteriaAdapter = adminRoleRightDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("role_code", role_code));
		List<AdminRoleRight> list =criteria.list();
		adminRoleRightDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	

	@Override
	public List<AdminMenu> getUserMenus(int playgroundmanager_id,int cooperative_partner_id) {
		
		List<AdminMenu> list =  adminMenuDao.createSQLQuery("SELECT id,menu_name,menu_code,parent_menu_code,menu_url,menu_icon,weight FROM admin_menu WHERE menu_code IN " +
				"(SELECT DISTINCT menu_code FROM admin_role_right WHERE role_code IN  (SELECT role_code FROM pm_admin_role_user WHERE playgroundmanager_id=? and cooperative_partner_id=?))" +
				" ORDER BY weight", playgroundmanager_id,cooperative_partner_id)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("menu_name", Hibernate.STRING)
				.addScalar("menu_code", Hibernate.STRING)
				.addScalar("parent_menu_code", Hibernate.STRING)
				.addScalar("menu_url", Hibernate.STRING)
				.addScalar("menu_icon", Hibernate.STRING)
				.addScalar("weight", Hibernate.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(AdminMenu.class)).list();
		adminMenuDao.clear();
		return list;
	}

	
	@Override
	public List<AdminMenu> getRoleRights(String role_code) {
		List<AdminMenu> list =  adminMenuDao.createSQLQuery("SELECT am.* FROM admin_menu am "
				+"LEFT JOIN admin_role_right arr ON am.menu_code=arr.menu_code WHERE arr.role_code=? ORDER BY am.weight" ,role_code)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("menu_name", Hibernate.STRING)
				.addScalar("menu_code", Hibernate.STRING)
				.addScalar("parent_menu_code", Hibernate.STRING)
				.addScalar("menu_url", Hibernate.STRING)
				.addScalar("menu_icon", Hibernate.STRING)
				.addScalar("weight", Hibernate.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(AdminMenu.class)).list();
		adminMenuDao.clear();
		
		return list;
	}

	@Override
	@Transactional
	public void defaultPGMAdminRoleRight(PlaygroundManager o) {
		//创建角色信息
		PlaygroundManagerAdminRole role = new PlaygroundManagerAdminRole();
		role.setRole_name(o.getUsercode());
		role.setRole_desc(o.getUsercode());
		role.setCooperative_partner_id(o.getId());
		role.setRole_type(2);// 2：用户自定义角色
		String role_code = RandomUtils.getRole_code(12);
		role.setRole_code(role_code);			//随机生成12位码
		savePlaygroundManagerAdminRole(role);
		logger.info("创建角色信息成功!...");
		
		//获得所有带menu_belong_to_role所有菜单项信息
		List<AdminMenu> amlist = getAllAdminMenu();
		//取出所有menu_code
		StringBuffer menu_ids = new StringBuffer();
		for (AdminMenu adminMenu : amlist) {
			menu_ids.append(adminMenu.getMenu_code());
			menu_ids.append(",");
		}
		//保存角色权限信息
		saveRoleRights(menu_ids.toString(),role_code);
		logger.info("保存角色权限信息成功!...");
		
		//保存角色用户信息
		String users = String.valueOf(o.getId());
		savePlaygroundManagerRoleUsers(role_code,users,o.getId());
		logger.info("保存角色用户信息成功!...");
	}
}
