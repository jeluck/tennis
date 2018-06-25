package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IFriendshipDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Coach;
import com.project.pojo.Friendship;
import com.project.service.IFriendshipService;

@Service
public class FriendshipServiceImpl implements IFriendshipService {
	
	@Resource
	private IFriendshipDao friendshipDao;

	@Override
	@Transactional
	public Friendship saveFriendship(Friendship o) throws Exception {
		return friendshipDao.saveObject(o);
	}

	@Override
	public Friendship updateFriendship(Friendship o) throws Exception {
		return friendshipDao.merge(o);
	}

	@Override
	public void deleteFriendship(int id) throws Exception {
		friendshipDao.removeById(id);
	}

	@Override
	public Friendship getFriendshipById(int id) {
		CriteriaAdapter criteriaAdapter = friendshipDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("id", id));
        List<Friendship> list = criteria.list();
        friendshipDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list.get(0);
	}

	@Override
	public Friendship getFriendshipByUserIdAndFuserId(int userId,
			int friend_user_id) {
		CriteriaAdapter criteriaAdapter = friendshipDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("user_id.id", userId));
        criteria.add(Restrictions.eq("friend_user_id.id", friend_user_id));
        List<Friendship> list = criteria.list();
        friendshipDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }else{
        	return null;
        }
		
	}

	@Override
	public void deleteFriendshipByObject(Friendship o) throws Exception {
		friendshipDao.remove(o);
	}

}
