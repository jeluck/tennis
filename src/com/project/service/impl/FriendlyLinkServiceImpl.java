package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IFriendlyLinkDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.FriendlyLink;
import com.project.service.IFriendlyLinkService;

@Service
public class FriendlyLinkServiceImpl implements IFriendlyLinkService {
	
	@Resource
	private IFriendlyLinkDao friendlyLinkDao;

	@Transactional
	@Override
	public FriendlyLink saveFriendlyLink(FriendlyLink o) throws Exception {
		return friendlyLinkDao.saveObject(o);
	}

	@Transactional
	@Override
	public void deleteFriendlyLink(int id) throws Exception {
		friendlyLinkDao.removeById(id);
	}

	@Transactional
	@Override
	public FriendlyLink updateFriendlyLink(FriendlyLink o) throws Exception {
		return friendlyLinkDao.merge(o);
	}

	@Override
	public FriendlyLink getFriendlyLinkById(int id) {
		return friendlyLinkDao.getById(id);
	}

	@Override
	public PageFinder<FriendlyLink> getPageFindeFriendlyLink(FriendlyLink o,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = friendlyLinkDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
//		if (CommonUtil.NotEmpty(o.getOrderNo())) {
//			criteria.add(Restrictions.like("orderNo", o.getOrderNo(),MatchMode.ANYWHERE));
//		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<FriendlyLink> pageFinder = friendlyLinkDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		friendlyLinkDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

}
