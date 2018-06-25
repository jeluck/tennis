package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.common.Constants;
import com.project.dao.IModuleDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Course;
import com.project.pojo.Module;
import com.project.pojo.Red_bag;
import com.project.service.IModuleService;
import com.project.util.CommonUtil;

@Service
public class ModuleServiceImpl implements IModuleService {

	@Resource
	private IModuleDao moduleDao;
	
	@Override
	public Module saveModule(Module o) throws Exception {
		return moduleDao.saveObject(o);
	}

	@Override
	public Module upadateModule(Module o) throws Exception {
		return moduleDao.merge(o);
	}

	@Override
	public Module getModuleById(int id) {
		return moduleDao.getById(id);
	}

	@Override
	public PageFinder<Module> getPageFindeModule(Module o,int status,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = moduleDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if (status>0) {
			criteria.add(Restrictions.eq("status", status));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Module> pageFinder = moduleDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		moduleDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public List<Module> getModuleListByStatus(int status) {
		CriteriaAdapter criteriaAdapter = moduleDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("status", status));
        List<Module> list = criteria.list();
        moduleDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

}
