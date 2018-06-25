package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.InviteDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Invite;
import com.project.service.InviteService;

@Service
public class InviteServiceImpl implements InviteService {

	@Resource
	private InviteDao inviteDao;
	
	@Override
	public Invite saveInvite(Invite o) throws Exception {
		return inviteDao.saveObject(o);
	}

	@Override
	public Invite getInviteByfriend_user_id(int friend_user_id) {
		CriteriaAdapter criteriaAdapter = inviteDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("friend_user_id", "friend_user_id",
				CriteriaSpecification.LEFT_JOIN);
        criteria.createAlias("user_id", "user_id",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("friend_user_id.id", friend_user_id));
        List<Invite> list = criteria.list();
        inviteDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()==0){
        	return null;
        }
		return list.get(0);
	}

	@Override
	public Invite updateInvite(Invite o) throws Exception {
		return inviteDao.merge(o);
	}
	
}
