package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.common.Constants;
import com.project.dao.IPlaygroundManagerDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundManager;
import com.project.service.IPlaygroundManagerService;
import com.project.util.CommonUtil;

@Service
public class PlaygroundManagerServiceImpl implements IPlaygroundManagerService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PlaygroundManagerServiceImpl.class);

    @Resource
    private IPlaygroundManagerDao playgroundManagerDao ;

	@Override
	public PageFinder<PlaygroundManager> getPageFindePlaygroundManager(PlaygroundManager o, int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = playgroundManagerDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
//		if (CommonUtil.NotEmpty(o.getOrderNo())) {
//			criteria.add(Restrictions.like("orderNo", o.getOrderNo(),MatchMode.ANYWHERE));
//		}
		if(CommonUtil.NotEmpty(o.getUsername())){
    		criteria.add(Restrictions.like("username", o.getUsername(), MatchMode.ANYWHERE));
    	}
    	if(o.getPgm_president()!=null){
    		criteria.add(Restrictions.eq("pgm_president", o.getPgm_president()));
    	}else{
    		criteria.add(Restrictions.eq("pg_manager_type",Constants.PLAYGROUND_MANAGER_TYPE1));
    	}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<PlaygroundManager> pageFinder = playgroundManagerDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		playgroundManagerDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}
    
	@Override
	@Transactional
	public PlaygroundManager savePlaygroundManager(PlaygroundManager o) throws Exception {
		playgroundManagerDao.save(o);
		return o;
	}

	@Override
	@Transactional
	public PlaygroundManager mergePlaygroundManager(PlaygroundManager o)
			 {
		o.setUpdate_time(CommonUtil.getTimeNow());
		return playgroundManagerDao.merge(o);
	}

	@Override
	public PlaygroundManager getPlaygroundManagerById(int oid) {
		return playgroundManagerDao.getById(oid);
	}

	@Override
	public PlaygroundManager playgroundmanagerlogin(PlaygroundManager o) {
		CriteriaAdapter criteriaAdapter = playgroundManagerDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("usercode", o.getUsercode()));
		criteria.add(Restrictions.eq("password", o.getPassword()));
		PlaygroundManager k = (PlaygroundManager) criteria.uniqueResult();
		playgroundManagerDao.releaseHibernateSession(criteriaAdapter.getSession());
		return k;
	}

	@Override
	public List<PlaygroundManager> getPlaygroundManagerListBy_pg_manager_type_Flag() {
		CriteriaAdapter criteriaAdapter = playgroundManagerDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("is_active", Constants.NORMAL_FLAG));
		criteria.add(Restrictions.eq("pg_manager_type",Constants.PLAYGROUND_MANAGER_TYPE1));
		List<PlaygroundManager> ks =  criteria.list();
		playgroundManagerDao.releaseHibernateSession(criteriaAdapter.getSession());
		return ks;
	}

	@Override
	public boolean checkMobile(String mobile, int id) {
		CriteriaAdapter criteriaAdapter = playgroundManagerDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("mobile", mobile));
        if(id>0){
        	 criteria.add(Restrictions.not(Restrictions.eq("id", id)));
        }
        List<PlaygroundManager> list = criteria.list();
        playgroundManagerDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()<=0){
        	return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean checkName(String name, int id) {
		CriteriaAdapter criteriaAdapter = playgroundManagerDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("usercode", name));
        if(id>0){
        	 criteria.add(Restrictions.not(Restrictions.eq("id", id)));
        }
        List<PlaygroundManager> list = criteria.list();
        playgroundManagerDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()<=0){
        	return true;
		}else{
			return false;
		}
	}

	@Override
	public PlaygroundManager getPGM(String mobile) {
		CriteriaAdapter criteriaAdapter = playgroundManagerDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("usercode", mobile));
        PlaygroundManager p  = (PlaygroundManager) criteria.uniqueResult();
        playgroundManagerDao.releaseHibernateSession(criteriaAdapter.getSession());
        return p;
	}

	@Override
	public List<PlaygroundManager> getAll() {
		return playgroundManagerDao.getAll();
	}

	@Override
	public PlaygroundManager getPlaygroundManagerByCoachId(int coachId) {
		CriteriaAdapter criteriaAdapter = playgroundManagerDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("coachid", coachId));
		PlaygroundManager k = (PlaygroundManager) criteria.uniqueResult();
		playgroundManagerDao.releaseHibernateSession(criteriaAdapter.getSession());
		return k;
	}

}
