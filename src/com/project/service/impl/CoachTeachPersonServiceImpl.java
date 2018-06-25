package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.ICoachTeachPersonDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach_teach_person;
import com.project.service.ICoachTeachPersonService;

@Service
public class CoachTeachPersonServiceImpl implements ICoachTeachPersonService {
	
	@Resource
	private ICoachTeachPersonDao coachTeachPersonDao;

	@Transactional
	@Override
	public Coach_teach_person saveCoachTeachPerson(Coach_teach_person o)
			throws Exception {
		return coachTeachPersonDao.saveObject(o);
	}

	@Transactional
	@Override
	public void deleteCoachTeachPerson(int id) throws Exception {
		coachTeachPersonDao.removeById(id);
	}

	@Transactional
	@Override
	public Coach_teach_person updateCoachTeachPerson(Coach_teach_person o)
			throws Exception {
		return coachTeachPersonDao.merge(o);
	}

	@Override
	public Coach_teach_person getCoachTeachPersonById(int id) {
		return coachTeachPersonDao.getById(id);
	}

	@Override
	public PageFinder<Coach_teach_person> getPageFindeCoachTeachPerson(
			Coach_teach_person o, int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = coachTeachPersonDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
//		if (CommonUtil.NotEmpty(o.getOrderNo())) {
//			criteria.add(Restrictions.like("orderNo", o.getOrderNo(),MatchMode.ANYWHERE));
//		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Coach_teach_person> pageFinder = coachTeachPersonDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		coachTeachPersonDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

}
