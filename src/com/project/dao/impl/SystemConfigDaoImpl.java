package com.project.dao.impl;

import com.project.common.Constants;
import com.project.dao.ISystemConfigDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.SystemConfig;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 类的实现描述：
 */
@Repository
public class SystemConfigDaoImpl extends HibernateEntityDao<SystemConfig> implements ISystemConfigDao {

	@Override
	public SystemConfig findSystemConfigByKey(String key) {
		
		//换成CriteriaAdapter方式取数据			
//		Session session = getHibernateSession();
//		String hql =  "select sc from SystemConfig sc where sc.deleteFlag = ? and sc.key = ?";
//		Query query = session.createQuery(hql).setParameter(0, "1").setParameter(1, key);
		
		CriteriaAdapter criteriaAdapter = createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("deleteFlag", Constants.NORMAL_FLAG));
		criteria.add(Restrictions.eq("key", key));
		
		List<SystemConfig> scs = null;
		try {
			scs = criteria.list();
			releaseHibernateSession(criteriaAdapter.getSession());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally{
		}
		if(scs!=null && scs.size()>0){
			return scs.get(0);
		}else{
			return null;
		}
	}
    
}
