package com.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.common.Constants;
import com.project.dao.IPlaygroundDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Playground;
import com.project.service.IPlaygroundService;
import com.project.util.CommonUtil;

@Service
public class PlaygroundServiceImpl implements IPlaygroundService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PlaygroundServiceImpl.class);

    @Resource
    private IPlaygroundDao playgroundDao;

	@Override
	@Transactional
	public Playground savePlaygroundService(Playground o) throws Exception {
		return playgroundDao.saveObject(o);
	}

	@Override
	public PageFinder<Playground> getPageFindePlayground(Playground o, int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = playgroundDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(o.getPlaygroundmanager_id()>0){
			criteria.add(Restrictions.eq("playgroundmanager_id", o.getPlaygroundmanager_id()));
		}
		if(o.getAuditStatus()==Constants.AUDITSTATUS_APPLY){
			criteria.add(Restrictions.eq("auditStatus", o.getAuditStatus()));
		}
		if(o.getIs_locked()>=0){
			criteria.add(Restrictions.eq("is_locked", o.getIs_locked()));
		}
		if (CommonUtil.NotEmpty(o.getName())) {
			criteria.add(Restrictions.like("name", o.getName(),MatchMode.ANYWHERE));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Playground> pageFinder = playgroundDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		playgroundDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Playground getPlaygroundById(int oid) {
		CriteriaAdapter criteriaAdapter = playgroundDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("certificate", "certificate",
				CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("pservices", "pservices",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("id", oid));
        List<Playground> list = criteria.list();
        playgroundDao.releaseHibernateSession(criteriaAdapter.getSession());
        
        if(list != null && list.size() > 0 ){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public Playground mergePlayground(Playground o) throws Exception {
		o.setUpdate_time(CommonUtil.getTimeNow());
		return playgroundDao.merge(o);
	}

	@Override
	public List<Playground> getAll() {
		return playgroundDao.getAll();
	}

	@Override
	public List<Playground> getPlaygroundList(int city_show_id,int stick,String name) {
		CriteriaAdapter criteriaAdapter = playgroundDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        if(city_show_id>0){
        	 criteria.add(Restrictions.eq("cityid",city_show_id));
        }
        
        if(stick>=0){
        	 criteria.add(Restrictions.eq("stick",stick));
        }
        if(CommonUtil.NotEmpty(name)){
        	criteria.add(Restrictions.like("name",name,MatchMode.ANYWHERE));
        }
        List<Playground> list = criteria.list();
        playgroundDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public  PageFinder<Playground> getPlaygroundListByMap(Map<?, ?> map,int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = playgroundDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createCriteria("pservices", "pservices", CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("auditStatus",Constants.AUDITSTATUS_THROUGH));
        if(CommonUtil.NotEmptyObject(map.get("pdName"))){ //教练名称
        	criteria.add(Restrictions.like("name",map.get("pdName").toString(),MatchMode.ANYWHERE));
        }
        if(CommonUtil.NotEmptyObject(map.get("cityid"))){
        	criteria.add(Restrictions.eq("cityid",Integer.valueOf(map.get("cityid").toString())));
        }
        if(CommonUtil.NotEmptyObject(map.get("areaid"))){
        	criteria.add(Restrictions.eq("areaid",Integer.valueOf(map.get("areaid").toString())));
        }
        if(CommonUtil.NotEmptyObject(map.get("type"))){
        	String type = map.get("type").toString();
        	String type1 = type.substring(0, type.lastIndexOf("e")+1);
        	String type2 = type.substring(type.lastIndexOf("e")+1);
        	Disjunction dis=Restrictions.disjunction();  
    		dis.add(Restrictions.eq(type1,type2));
    		dis.add(Restrictions.like("spType",type2,MatchMode.ANYWHERE));
    		criteria.add(dis); 
        }
        if(CommonUtil.NotEmptyObject(map.get("services"))){
        	criteria.add(Restrictions.eq("pservices."+map.get("services").toString(),1));
        }
        if(CommonUtil.NotEmptyObject(map.get("sort"))){
        	if(map.get("sort").equals("distance_farToNearly")){ //距离从远到近
        		if(CommonUtil.NotEmptyObject(map.get("lat"))&&CommonUtil.NotEmptyObject(map.get("lng"))){
        			double lat = Double.valueOf(map.get("lat").toString());
        			double lng = Double.valueOf(map.get("lng").toString());
        			String sql="coordinateslatitude > "+lat+" - 100 "+
        					"and coordinateslatitude < "+lat+" + 100 "+
        					"and coordinateslongitude > "+lng+" - 100 "+
        					"and coordinateslongitude < "+lng+" + 100 "+
        					"order by ACOS(SIN(( "+lat+" * 3.1415) / 180 ) * SIN((coordinateslatitude * 3.1415) / 180 ) "+
								"+COS(( "+lat+" * 3.1415) / 180 ) * COS((coordinateslatitude * 3.1415) / 180 ) "+
								"*COS(("+lng+" * 3.1415) / 180 - (coordinateslongitude * 3.1415) / 180 ) ) * 6380 desc";
        			criteria.add(Restrictions.sqlRestriction(sql));
        		}
        	}
        	if(map.get("sort").equals("price_lowToHigh")){ 		//价格从低到高
        		criteria.addOrder(Order.asc("money"));
        	}
        	if(map.get("sort").equals("evaluation_highToLow")){ //评价从高到低
        		criteria.addOrder(Order.desc("evaluate_score"));
        	}
        	if(map.get("sort").equals("On_line")){ //在线预定
        		criteria.add(Restrictions.eq("is_reserve",1));
        	}
        	if(map.get("sort").equals("Telephone")){ //电话预定
        		criteria.add(Restrictions.eq("is_reserve",0));
        	}
        	if(map.get("sort").equals("distance_nearlyToFar")){ //距离从近到远
        		if(CommonUtil.NotEmptyObject(map.get("lat"))&&CommonUtil.NotEmptyObject(map.get("lng"))){
        			double lat = Double.valueOf(map.get("lat").toString());
        			double lng = Double.valueOf(map.get("lng").toString());
        			String sql="coordinateslatitude > "+lat+" - 100 "+
        					"and coordinateslatitude < "+lat+" + 100 "+
        					"and coordinateslongitude > "+lng+" - 100 "+
        					"and coordinateslongitude < "+lng+" + 100 "+
        					"order by ACOS(SIN(( "+lat+" * 3.1415) / 180 ) * SIN((coordinateslatitude * 3.1415) / 180 ) "+
								"+COS(( "+lat+" * 3.1415) / 180 ) * COS((coordinateslatitude * 3.1415) / 180 ) "+
								"*COS(("+lng+" * 3.1415) / 180 - (coordinateslongitude * 3.1415) / 180 ) ) * 6380 asc";
        			criteria.add(Restrictions.sqlRestriction(sql));
        		}
        	}
        	if(map.get("sort").equals("price_highToLow")){ //价格从高到低
        		criteria.addOrder(Order.desc("money"));
        	}
        	if(map.get("sort").equals("evaluation_lowToHigh")){ //评价从低到搞
        		criteria.addOrder(Order.asc("evaluate_score"));
        	}
        }else{
        	//定位成功的按距离，定位不成功按添加时间，时间越早，排列越前
        	if(CommonUtil.NotEmptyObject(map.get("lat"))&&CommonUtil.NotEmptyObject(map.get("lng"))){
    			double lat = Double.valueOf(map.get("lat").toString());
    			double lng = Double.valueOf(map.get("lng").toString());
    			String sql="coordinateslatitude > "+lat+" - 100 "+
    					"and coordinateslatitude < "+lat+" + 100 "+
    					"and coordinateslongitude > "+lng+" - 100 "+
    					"and coordinateslongitude < "+lng+" + 100 "+
    					"order by ACOS(SIN(( "+lat+" * 3.1415) / 180 ) * SIN((coordinateslatitude * 3.1415) / 180 ) "+
							"+COS(( "+lat+" * 3.1415) / 180 ) * COS((coordinateslatitude * 3.1415) / 180 ) "+
							"*COS(("+lng+" * 3.1415) / 180 - (coordinateslongitude * 3.1415) / 180 ) ) * 6380 asc";
    			criteria.add(Restrictions.sqlRestriction(sql));
    		}else{
    			criteria.addOrder(Order.asc("id"));
    		}
        }
        PageFinder<Playground> pageFinder = playgroundDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
        playgroundDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public boolean checkPhone(String phone,int id) {
		CriteriaAdapter criteriaAdapter = playgroundDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("telphone", phone));
        if(id>0){
        	 criteria.add(Restrictions.not(Restrictions.eq("id", id)));
        }
        List<Playground> list = criteria.list();
        playgroundDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()<=0){
        	return true;
		}else{
			return false;
		}
		
	}

	@Override
	public Playground getByPlaygroundManagerId(Integer managerId) {
		CriteriaAdapter criteriaAdapter = playgroundDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("playgroundmanager_id", managerId));
        criteria.add(Restrictions.eq("auditStatus", 2));
        criteria.addOrder(Order.asc("id"));
        List<Playground> list = criteria.list();
        playgroundDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null && list.size()>0){
        	return list.get(0);
        }else{
        	return null;
        }
	}

	@Override
	public List<Playground> getPlaygroundListByPlaygroundManagerId(
			Integer managerId) {
		CriteriaAdapter criteriaAdapter = playgroundDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("playgroundmanager_id", managerId));
        List<Playground> list = criteria.list();
        playgroundDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public List<Playground> getPlaygroundListByObj(Playground o) {

		CriteriaAdapter criteriaAdapter = playgroundDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        if(o.getAuditStatus()>0){
        	 criteria.add(Restrictions.eq("auditStatus", o.getAuditStatus()));
        }
        if(o.getCityid()>0){
       	     criteria.add(Restrictions.eq("cityid", o.getCityid()));
        }
        List<Playground> list = criteria.list();
        playgroundDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!= null && list.size()>0){
        	return list;
        }else{
        	return null;
        }
		
	}


}
