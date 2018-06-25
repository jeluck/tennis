package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.common.Constants;
import com.project.dao.IUser_countDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Orderinfo;
import com.project.pojo.User_count;
import com.project.service.IOrderInfoService;
import com.project.service.IUser_countService;

@Service("user_countServiceImpl")
public class User_countServiceImpl implements IUser_countService {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(User_countServiceImpl.class);
	
	@Resource
	private IUser_countDao  user_countDao;
	@Resource
	private IOrderInfoService orderInfoService; 

	@Override
	public User_count saveuser_Count(User_count o) throws Exception {
		return user_countDao.saveObject(o);
	}

	@Override
	public User_count getUser_countById(int id) {
		CriteriaAdapter criteriaAdapter = user_countDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("weuser.id", id));
        List<User_count> list = criteria.list();
        user_countDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list != null && list.size()>0){
        	return list.get(0);
        }else{
        	return null;
        }
		
	}

	@Override
	public User_count updateUser_count(User_count u) {
		return user_countDao.merge(u);
	}

	@Override
	public User_count getUser_countByUserId(Integer userId) {
		return user_countDao.findUniqueBy("weuser.id", userId, true);
	}

	@Override
	public void saveUser_countByUserIdAndCoachId(int userId, int coachId) throws Exception {
		Orderinfo o = orderInfoService.getOrderinfoByUserIdAndActiveID(userId, String.valueOf(coachId), Constants.DATATYPE.CoachType.getStatus());
		if(o==null){
			User_count c = getUser_countById(userId);
			if(c!=null){
				c.setTraineecount(c.getTraineecount()+1);
				updateUser_count(c);
			}else{
				c.setTraineecount(1);
				saveuser_Count(c);
			}
			
		}
	}
}
