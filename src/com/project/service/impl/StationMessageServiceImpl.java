package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.common.Constants;
import com.project.common.Settings;
import com.project.dao.IStationMessageDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.StationMessage;
import com.project.service.IStationMessageService;

@Service
public class StationMessageServiceImpl implements IStationMessageService{

	@Resource
	private IStationMessageDao stationMessageDao;
	
	@Override
	public StationMessage saveStationMessage(StationMessage s)throws Exception {
		return stationMessageDao.saveObject(s);
	}

	@Override
	public void deleteStationMessage(Integer id)throws Exception {
		
		stationMessageDao.removeById(id);
	}

	@Override
	public List<StationMessage> getStationMessageByWeuserId(Integer weuserId) {
		CriteriaAdapter criteriaAdapter = stationMessageDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("weuser_id", weuserId));
        List<StationMessage> list = criteria.list();
        stationMessageDao.releaseHibernateSession(criteriaAdapter.getSession());

        return list;
	}

	@Override
	public StationMessage updateStationMessage(StationMessage s)throws Exception {
		return stationMessageDao.merge(s);
	}

	@Override
	public PageFinder<StationMessage> getPageStationMessageByUserId(
			Integer weuserId,int pageNumber, int pageSize,int send_type,String appsyetem) {
		CriteriaAdapter criteriaAdapter = stationMessageDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("send_type", send_type));
		criteria.add(Restrictions.eq("weuser_id", weuserId));
		if(Settings.IOS.equals(appsyetem)){
			Disjunction dis=Restrictions.disjunction();  
			dis.add(Restrictions.like("appsyetem",appsyetem,MatchMode.ANYWHERE));
			dis.add(Restrictions.eq("appsyetem", ""));
			criteria.add(dis);  
		}else{
			Disjunction dis=Restrictions.disjunction();  
			dis.add(Restrictions.like("appsyetem",Settings.ANDROID,MatchMode.ANYWHERE));
			dis.add(Restrictions.eq("appsyetem", ""));
			criteria.add(dis);  
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<StationMessage> pageFinder = stationMessageDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		stationMessageDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public List<StationMessage> getStationMessageByWeuserIdAndStatus(
			Integer weuserId,String appsyetem) {
		CriteriaAdapter criteriaAdapter = stationMessageDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("weuser_id", weuserId));
        criteria.add(Restrictions.eq("status", 0));
        criteria.add(Restrictions.eq("readstatus", 0));
        criteria.add(Restrictions.eq("send_type", 1));
        if(Settings.IOS.equals(appsyetem)){
			Disjunction dis=Restrictions.disjunction();  
			dis.add(Restrictions.like("appsyetem",appsyetem,MatchMode.ANYWHERE));
			dis.add(Restrictions.eq("appsyetem", ""));
			criteria.add(dis);  
		}else{
			Disjunction dis=Restrictions.disjunction();  
			dis.add(Restrictions.like("appsyetem",Settings.ANDROID,MatchMode.ANYWHERE));
			dis.add(Restrictions.eq("appsyetem", ""));
			criteria.add(dis);  
		}
        List<StationMessage> list = criteria.list();
        stationMessageDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list != null){
			return list;
		}else{
			return new ArrayList<StationMessage>();
		}
	}

	@Override
	public StationMessage getStationMessageById(int id) {
		return stationMessageDao.getById(id);
	}

	@Override
	public StationMessage getOneStationMessageByWeuserId(Integer weuserId,String appsyetem) {
		CriteriaAdapter criteriaAdapter = stationMessageDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("weuser_id", weuserId));
        
        if(Settings.IOS.equals(appsyetem)){
			Disjunction dis=Restrictions.disjunction();  
			dis.add(Restrictions.like("appsyetem",appsyetem,MatchMode.ANYWHERE));
			dis.add(Restrictions.eq("appsyetem", ""));
			criteria.add(dis);  
		}else{
			Disjunction dis=Restrictions.disjunction();  
			dis.add(Restrictions.like("appsyetem",Settings.ANDROID,MatchMode.ANYWHERE));
			dis.add(Restrictions.eq("appsyetem", ""));
			criteria.add(dis);  
		}
        
        criteria.setProjection(Projections.max("id"));
        List<Integer> list = criteria.list();
        stationMessageDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.get(0)!=null){
        	StationMessage sm = stationMessageDao.getById(list.get(0));
            if(sm != null){
    			return sm;
    		}else{
    			return null;
    		}
        }
        else{
			return null;
		}
	}

}
