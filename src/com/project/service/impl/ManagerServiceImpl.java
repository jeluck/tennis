package com.project.service.impl;

import java.util.List;

import com.project.dao.IManagerDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.Manager;
import com.project.pojo.Weuser;
import com.project.service.IManagerService;
import com.project.util.CommonUtil;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Transient;

@Service("managerServiceImpl")
public class ManagerServiceImpl implements IManagerService {
	
	@Resource
	private IManagerDao managerDao;
	
	@Override
	public PageFinder<Manager> getManagerList(int pageNumber, int pageSize) {
		//return getPageBeanBySQL("SELECT * FROM manager ORDER BY create_time ASC ",pageBean, Manager.class);
		CriteriaAdapter criteriaAdapter = managerDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.addOrder(Order.asc("create_time"));
		PageFinder<Manager> queryManagerList = managerDao.pagedByCriteria(criteria, pageNumber, pageSize);
		managerDao.releaseHibernateSession(criteriaAdapter.getSession());
		
		return queryManagerList;
	}
	
	@Override
	public Manager saveManager(Manager manager) {
		try {
			manager.setCreate_time(CommonUtil.getTimeNow());
			managerDao.save(manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return manager;
	}
	
	@Override
	@Transactional
	public boolean updateManager(Manager manager) {
		manager.setUpdate_time(CommonUtil.getTimeNow());
		managerDao.merge(manager);
		return true;
	}
	
	@Override
	public Manager getManagerById(int id) {
		return managerDao.getById(id);
	}

	@Override
	public void testDB() {
		final Long begin_time  = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < 1000; j++) {
						//logger.info("当前线程"+Thread.currentThread().getName()+"第"+j+"次执行");
						//queryForObject(Manager.class, "select * from manager where id=:id", "id",1);
						System.out.println("耗时（毫秒）："+(System.currentTimeMillis()-begin_time));
					}
				}
			});
			thread.start();
		}
		
	}

	@Override
	public Manager getManagerByUsercode(String usercode) {
		CriteriaAdapter criteriaAdapter = managerDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("admin_role_user", "admin_role_user",  CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("usercode",usercode));
		Manager m = (Manager) criteria.uniqueResult();
		managerDao.releaseHibernateSession(criteriaAdapter.getSession());
		return m;
	}
	
	@Override
	public Manager  managerlogin(String uphone, String pass) {
        CriteriaAdapter criteriaAdapter = managerDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("usercode",uphone));
        criteria.add(Restrictions.eq("password",pass));
        Manager m = null;
        try {
            m = (Manager) criteria.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        managerDao.releaseHibernateSession(criteriaAdapter.getSession());
        return m;
    }

	@Override
	public boolean checkPhone(String phone, int id) {
		CriteriaAdapter criteriaAdapter = managerDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("mobile", phone));
        if(id>0){
        	 criteria.add(Restrictions.not(Restrictions.eq("id", id)));
        }
        List<Manager> list = criteria.list();
        managerDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()<=0){
        	return true;
		}else{
			return false;
		}
	}
}
