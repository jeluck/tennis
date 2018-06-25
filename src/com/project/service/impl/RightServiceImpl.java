package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IAdminMenuDao;
import com.project.dao.IAdminRoleDao;
import com.project.dao.IAdminRoleRightDao;
import com.project.dao.IManagerDao;
import com.project.dao.IPlaygroundManagerAdminRoleDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.AdminMenu;
import com.project.pojo.AdminRole;
import com.project.pojo.AdminRoleRight;
import com.project.pojo.Manager;
import com.project.service.IRightService;
import com.project.util.CommonUtil;

@Service("rightServiceImpl")
public class RightServiceImpl implements IRightService {

    @Resource 
    private IAdminMenuDao                adminMenuDao;

    @Resource 
    private IManagerDao managerDao;
    
    @Resource 
    private IAdminRoleDao                adminRoleDao; //
    
    @Resource 
    private IAdminRoleRightDao                adminRoleRightDao;
    
    @Resource
    private IPlaygroundManagerAdminRoleDao playgroundManagerAdminRoleDao; 
    
	@Resource 
	private SessionFactory sessionFactory;
    
	@Override
	public boolean saveAdminMenu(AdminMenu menu) {
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
		List<AdminMenu> list = criteria.list();
		adminMenuDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public List<AdminRole> getAllAdminRole() {
		CriteriaAdapter criteriaAdapter = adminRoleDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.addOrder(Order.asc("create_time"));
		List<AdminRole> list = criteria.list();
		adminRoleDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public boolean saveAdminRole(AdminRole role) {
		try {
			role.setCreate_time(CommonUtil.getTimeNow());
			role.setRole_type(2);// 2：用户自定义角色
			adminRoleDao.save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean updateAdminRole(AdminRole role) {
		role.setUpdate_time(CommonUtil.getTimeNow());
		adminRoleDao.merge(role);
		return false;
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
	public List<Manager> getRoleUsers(String role_code, boolean eq) {
		List o = managerDao.createSQLQuery("SELECT manager_id FROM admin_role_user WHERE role_code=?", role_code).list();
		CriteriaAdapter criteriaAdapter = managerDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(eq){
			if(o.size()>0)
			criteria.add(Restrictions.in("id", o));
			else{
				managerDao.releaseHibernateSession(criteriaAdapter.getSession());
				adminMenuDao.clear();
				return new ArrayList<>() ;
			}
		}else{
			if(o.size()>0)
			criteria.add(Restrictions.not(Restrictions.in("id", o)));
			
		}
		List<Manager> list = criteria.list();
		managerDao.releaseHibernateSession(criteriaAdapter.getSession());adminMenuDao.clear();
		return list;
	}

	@Override
	public boolean saveRoleUsers(String role_code, String users) {
		//S		删除角色前,如果表中有值,则清除,	add by lxc	2015-05-22
		Integer i = 0;
		try{
			List o = managerDao.createSQLQuery("select id from admin_role_user where role_code=?", role_code).list();
			i = o.size(); 
			if(CommonUtil.isEmpty(users)&&i!=0){
				//先清除
				managerDao.createSQLQuery("delete from admin_role_user where role_code =?",role_code).executeUpdate();
			}
		}catch(Exception e){
			i=0;
		}//E
		if(CommonUtil.NotEmpty(users)){
			//先清除
			managerDao.createSQLQuery("delete from admin_role_user where role_code =?",role_code).executeUpdate();
			String[] user_ids = users.split(",");
			//添加
			for (String userId : user_ids) {
				//update("insert into admin_role_user(uid,role_code) values(:uid,:role_code)", "uid",userId,"role_code",role_code);
				managerDao.createSQLQuery("insert into admin_role_user(manager_id,role_code) values(?,?)", userId,role_code).executeUpdate();
			}
		}
		adminMenuDao.clear();
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
	public AdminRole getAdminRoleById(int id) {
		return adminRoleDao.getById(id);
	}

	@Override
	public AdminRole getAdminRoleByCode(String role_code) {
		CriteriaAdapter criteriaAdapter = adminRoleDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("role_code",role_code));
		AdminRole a =null;
		try{
			a = (AdminRole) criteria.uniqueResult();
			adminRoleDao.releaseHibernateSession(criteriaAdapter.getSession());
		}catch(Exception e){
			return null;
		}
		return a;
	}
	

	@Override
	public List<AdminMenu> getUserMenus(int manager_id) {
		
		List<AdminMenu> list =  adminMenuDao.createSQLQuery("SELECT id,menu_name,menu_code,parent_menu_code,menu_url,menu_icon,weight FROM admin_menu WHERE menu_code IN " +
				"(SELECT DISTINCT menu_code FROM admin_role_right WHERE role_code IN  (SELECT role_code FROM admin_role_user WHERE manager_id=?))" +
				" ORDER BY weight", manager_id)
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
	public List<AdminRoleRight> getAdminRoleRightByRole_code(String role_code) {
		
		CriteriaAdapter criteriaAdapter = adminRoleRightDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("role_code", role_code));
		List<AdminRoleRight> list =criteria.list();
		adminRoleRightDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public boolean checkMenuCode(String menu_code, int id) {
		CriteriaAdapter criteriaAdapter = adminMenuDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("menu_code", menu_code));
        if(id>0){
        	 criteria.add(Restrictions.not(Restrictions.eq("id", id)));
        }
        List<AdminMenu> list = criteria.list();
        adminMenuDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()<=0){
        	return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean checkPmRoleCode(String role_code, int id) {
		CriteriaAdapter criteriaAdapter = playgroundManagerAdminRoleDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("role_code", role_code));
        if(id>0){
       	 criteria.add(Restrictions.not(Restrictions.eq("id", id)));
       }
        List<AdminMenu> list = criteria.list();
        playgroundManagerAdminRoleDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()<=0){
        	return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean checkRoleCode(String role_code, int id) {
		CriteriaAdapter criteriaAdapter = adminRoleDao.createCriteriaAdapter();
	    Criteria criteria = criteriaAdapter.getCriteria();
	    criteria.add(Restrictions.eq("role_code", role_code));
	    if(id>0){
       	 criteria.add(Restrictions.not(Restrictions.eq("id", id)));
       }
	    List<AdminMenu> list = criteria.list();
	    adminRoleDao.releaseHibernateSession(criteriaAdapter.getSession());
	    if(list.size()<=0){
	    	return true;
		}else{
			return false;
		}
	}
}
