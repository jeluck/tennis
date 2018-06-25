package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IPlaygroundUserDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundUser;
import com.project.pojo.Weuser;
import com.project.service.IPlaygroundUserService;
import com.project.util.CommonUtil;

@Service
public class PlaygroundUserServiceImpl implements IPlaygroundUserService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PlaygroundUserServiceImpl.class);

    @Resource
    private IPlaygroundUserDao playgroundUserDao;

    @Override
    public List<PlaygroundUser> getAllPlaygroundUser()
    {
        CriteriaAdapter criteriaAdapter = playgroundUserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        List<PlaygroundUser> list = criteria.list();
        playgroundUserDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
    }

	@Override
	@Transactional
	public PlaygroundUser savePlaygroundUser(PlaygroundUser o) {
    	try {
    		o.setCreate_time(CommonUtil.getTimeNow());
    		o.setUpdate_time(CommonUtil.getTimeNow());
			return playgroundUserDao.saveObject(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
	}

	@Override
	public PageFinder<PlaygroundUser> getPlaygroundUserList(PlaygroundUser o, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = playgroundUserDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("user", "user",CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("playground", "playground",CriteriaSpecification.LEFT_JOIN);
		if (CommonUtil.NotEmptyObject(o.getPlayground()) && o.getPlayground().getId()>0) {
			criteria.add(Restrictions.eq("playground.id", o.getPlayground().getId()));
		}
		if (CommonUtil.NotEmptyObject(o.getUser()) && o.getUser().getId()>0) {
			criteria.add(Restrictions.eq("user.id",o.getUser().getId()));
		}
		
		if (CommonUtil.NotEmptyObject(o.getUser()) && CommonUtil.NotEmptyObject(o.getUser().getUphone())) {
			criteria.add(Restrictions.like("user.uphone", o.getUser().getUphone(), MatchMode.ANYWHERE));
		}
		
		if (CommonUtil.NotEmptyObject(o.getUser()) && CommonUtil.NotEmptyObject(o.getUser().getUsername())) {
			criteria.add(Restrictions.like("user.username",o.getUser().getUsername(),MatchMode.ANYWHERE));
		}
		
		if (CommonUtil.NotEmptyObject(o.getUser()) && CommonUtil.NotEmptyObject(o.getUser().getIdcard_no())) {
			criteria.add(Restrictions.like("user.idcard_no",o.getUser().getIdcard_no(),MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.desc("id"));
		PageFinder<PlaygroundUser> list = playgroundUserDao.pagedByCriteria(criteria, pageNumber, pageSize);
		playgroundUserDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public List<PlaygroundUser> getPlaygroundUserByUserId(int userId) {
		CriteriaAdapter criteriaAdapter = playgroundUserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("user", "user",CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("playground", "playground",CriteriaSpecification.LEFT_JOIN);
    	if(userId>0){
        	criteria.add(Restrictions.eq("user.id", userId));
        }
    	criteria.addOrder(Order.desc("id"));
        List<PlaygroundUser> list = criteria.list();
        playgroundUserDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
		
	}

	@Override
	public PlaygroundUser getPlaygroundUser(int userId, int playgroundId) {
		CriteriaAdapter criteriaAdapter = playgroundUserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
//		criteria.createAlias("user", "user",CriteriaSpecification.LEFT_JOIN);
//		criteria.createAlias("playground", "playground",CriteriaSpecification.LEFT_JOIN);
       	criteria.add(Restrictions.eq("user.id", userId));
       	criteria.add(Restrictions.eq("playground.id", playgroundId));
    	PlaygroundUser pu = (PlaygroundUser) criteria.uniqueResult();
        playgroundUserDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pu;
	}

	@Override
	public List<PlaygroundUser> getPlaygroundUserByPlaygroundId(int playgroundId) {
		CriteriaAdapter criteriaAdapter = playgroundUserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("user", "user",CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("playground", "playground",CriteriaSpecification.LEFT_JOIN);
    	if(playgroundId>0){
        	criteria.add(Restrictions.eq("playground.id", playgroundId));
        }
    	criteria.addOrder(Order.desc("id"));
        List<PlaygroundUser> list = criteria.list();
        playgroundUserDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
	}

	@Override
	@Transactional
	public PlaygroundUser getOrSavePlaygroundUser(Weuser user,Playground playground) {
		logger.info("查找用户id为:"+user.getId()+"  是否场馆名id为:"+playground.getId()+"  的会员");
		PlaygroundUser pu = getPlaygroundUser(user.getId(), playground.getId());
		if(pu==null){
			logger.info("恭喜用户名为:"+user.getUsername()+"  成为场馆名为:"+playground.getName()+"  的会员");
			pu = new PlaygroundUser();
			pu.setUser(user);
			pu.setPlayground(playground);
			pu = savePlaygroundUser(pu);
		}
		return pu;
	}
}
