package com.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.log.Log;
import com.project.common.Constants;
import com.project.common.Settings;
import com.project.dao.ICoachDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.service.ICoachService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;

@Service
public class CoachServiceImpl implements ICoachService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CoachServiceImpl.class);

    @Resource
    private ICoachDao coachDao;
    @Resource
    private IWeuserService weuserService;
    
    @Transactional
	@Override
	public Coach saveCoach(Coach o) throws Exception {
		return coachDao.saveObject(o);
	}
//
//	@Override
//	public Integer getCoachByUserId_payStatus_size(int userId, int payStatus) {
//		org.hibernate.Query query=null;
//		org.hibernate.Session sessionTemp = orderMainDao.getHibernateSession();
//		String sql = "SELECT count(*) FROM order_main om WHERE om.weuser_id ="+userId+" and om.PAY_STATUS="+payStatus;
//		query= sessionTemp.createSQLQuery(sql);//.setParameter(0, userId).setParameter(1, payStatus);
//		Object ob = query.uniqueResult();
//		if(ob==null)ob=0;
//		Integer count = Integer.parseInt(ob.toString());
//		sessionTemp.close();			
//		//end
//		return count;
//	}


	@Override
	public PageFinder<Coach> getPageFindeCoach(Coach o, int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if (CommonUtil.NotEmpty(o.getName())) {
			criteria.add(Restrictions.like("name", o.getName(),MatchMode.ANYWHERE));
		}
		if(o.getPlaygroundmanager_id()>0){
			criteria.add(Restrictions.eq("playgroundmanager_id", o.getPlaygroundmanager_id()));
		}
		
		if(o.getStatus()>0){
			criteria.add(Restrictions.eq("status", o.getStatus()));
		}
		if(o.getVerified()>=0){
			criteria.add(Restrictions.eq("verified", o.getVerified()));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Coach> pageFinder = coachDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		coachDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Coach getcoachById(int oid) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("userid", "userid",
				CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("certificate", "certificate",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("id", oid));
        List<Coach> list = criteria.list();
        coachDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null && list.size()>0){
        	return list.get(0);
        }else{
        	logger.error("此教练ID不存在"+oid);
        	return null;
        }
		
	}

	@Override
	public Coach mergePlayground(Coach o) throws Exception {
		o.setUpdate_time(CommonUtil.getTimeNow());
		return coachDao.merge(o);
	}

	@Override
	public List<Coach> getcoachByPlayId(int oid) {
		List<Coach> coachList =  coachDao.findBy("playground_id", oid, false);
		return coachList;
	}


	@Override
	public Coach getcoachByIdCacheObject(int oid,int coachType) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("userid", "userid",
				CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("certificate", "certificate",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("id", oid));
        if(coachType>0){
        	criteria.add(Restrictions.eq("coachType", coachType));
        }
        List<Coach> list = criteria.list();
        coachDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list.get(0);
	}


	@Override
	public List<Coach> getCoachList(int now_live_city,int stick,String name) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        if(now_live_city>0){
        	 criteria.add(Restrictions.eq("now_live_city", now_live_city));
        }
        if(stick>=0){
       	 criteria.add(Restrictions.eq("stick",stick));
        }
        
        if(CommonUtil.NotEmpty(name)){
        	criteria.add(Restrictions.like("name",name,MatchMode.ANYWHERE));
        }
        List<Coach> list = criteria.list();
        coachDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}


	@Override
	public PageFinder<Coach> getPageCoachListByMap(Map<?, ?> map,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("reserve_me",1));   //显示用户可以预定我(1可以)的
		criteria.add(Restrictions.eq("verified",1));
		criteria.add(Restrictions.ne("money",0.0));//最低价不等于零的教练
		if(CommonUtil.NotEmptyObject(map.get("coachName"))){ //教练名称
        	criteria.add(Restrictions.like("name",map.get("coachName").toString(),MatchMode.ANYWHERE));
        }
		if(CommonUtil.NotEmptyObject(map.get("cityid"))){   //城市
	        criteria.add(Restrictions.eq("now_live_city",Integer.valueOf(map.get("cityid").toString())));
        }
        if(CommonUtil.NotEmptyObject(map.get("areaid"))){	//全城（区县）
        	criteria.add(Restrictions.eq("areaid",Integer.valueOf(map.get("areaid").toString())));
        }
        if(CommonUtil.NotEmptyObject(map.get("services"))){ //服务
        	criteria.add(Restrictions.like("services",map.get("services").toString(),MatchMode.ANYWHERE));
        }
        if(CommonUtil.NotEmptyObject(map.get("screening"))){   //筛选
        	String[] sg = map.get("screening").toString().split(",");
        	for (int i = 0; i < sg.length; i++) {
				if(sg[i].equals("男") || sg[i].equals("女")){
					 criteria.add(Restrictions.eq("sex",sg[i]));
				}
				if(sg[i].equals("中教") || sg[i].equals("外教")){
					 criteria.add(Restrictions.eq("nationality",sg[i]));
				}
				if(sg[i].equals("3")){
					criteria.add(Restrictions.lt("teaching",Integer.valueOf(sg[i])));
				}
				if(sg[i].equals("3_5")){
					String n1 = sg[i].substring(0, sg[i].indexOf("_"));
					String n2 = sg[i].substring(sg[i].indexOf("_")+1);
					criteria.add(Restrictions.ge("teaching",Integer.valueOf(n1)))
							.add(Restrictions.le("teaching",Integer.valueOf(n2)));
				}
				if(sg[i].equals("5")){
					criteria.add(Restrictions.gt("teaching",Integer.valueOf(sg[i])));
				}
				if(sg[i].equals("少儿(业余)") || sg[i].equals("成人(业余)") || sg[i].equals("全部(业余)")
						|| sg[i].equals("职业")){
					criteria.add(Restrictions.eq("professional",sg[i]));
				}
				if(sg[i].equals("ph1") || sg[i].equals("ph2") || sg[i].equals("ph3") || 
				   sg[i].equals("ph4") || sg[i].equals("ph5") || sg[i].equals("ph6")){
					criteria.createAlias("certificate", "certificate",
							CriteriaSpecification.LEFT_JOIN);
					criteria.add(Restrictions.not(Restrictions.eq("certificate."+sg[i],"")));
				}
			}
        }
        if(CommonUtil.NotEmptyObject(map.get("sort"))){	//排序
        	if(map.get("sort").equals("price_lowToHigh")){ 		//价格从低到高
        		criteria.addOrder(Order.asc("price"));
        	}
        	if(map.get("sort").equals("evaluation_highToLow")){ //评价从高到低
        		criteria.addOrder(Order.desc("evaluate_score"));
        	}
        	if(map.get("sort").equals("Seniority_highToLow")){ //教龄从高到低
        		criteria.addOrder(Order.desc("teaching"));
        	}
        	if(map.get("sort").equals("Seniority_lowToHigh")){ //教龄从低到高
        		criteria.addOrder(Order.asc("teaching"));
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
        	if(map.get("sort").equals("price_highToLow")){ //价格从高到低
        		criteria.addOrder(Order.desc("price"));
        	}
        	if(map.get("sort").equals("evaluation_lowToHigh")){ //评价从低到高
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
        
		PageFinder<Coach> pageFinder = coachDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		coachDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}


	@Override
	public boolean checkPhone(String phone,int id) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("phone", phone));
        if(id>0){
        	 criteria.add(Restrictions.not(Restrictions.eq("id", id)));
        }
        List<Coach> list = criteria.list();
        coachDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()<=0){
        	return true;
		}else{
			return false;
		}
	}
	
	@Override
	public Coach getLonginByHttpServletRequest_Id(HttpServletRequest request,
			Coach o, Class c) {
		
		String id = request.getParameter("id");
    	System.err.println("从request获得的id: "+id+"------------------------------------,调用的class:"+c.getName());
    	if(o!=null){
    		System.err.println("从登录者获得的id: "+o.getId()+"------------------------------------,调用的class:"+c.getName());
    		if(CommonUtil.NotEmpty(id) && o.getId()!=Integer.parseInt(id)){
    			try{
    	    		o = getcoachById(Integer.parseInt(id));
    	    		o.setUserid(weuserService.getUserById(o.getUserid().getId()));
    	    	}catch(Exception e){
    	    		
    	    	}
    	    	HttpSession session = request.getSession();
    			session.setAttribute(Settings.USER_SESSION, o);
    			session.setMaxInactiveInterval(1 * 60);
    		}
    	}
		if(o==null){
	    	try{
	    		o = getcoachById(Integer.parseInt(id));
	    		o.setUserid(weuserService.getUserById(o.getUserid().getId()));
	    	}catch(Exception e){
	    		
	    	}
	    	HttpSession session = request.getSession();
			session.setAttribute(Settings.USER_SESSION, o);
			session.setMaxInactiveInterval(1 * 60);
		}
		return o;
	}


	@Override
	public List<Coach> getCoachAndUserList(int userId,int now_live_city, int stick) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("userid", "userid",
				CriteriaSpecification.LEFT_JOIN);
        if(now_live_city>0){
        	 criteria.add(Restrictions.eq("now_live_city", now_live_city));
        }
        if(stick>=0){
       	 criteria.add(Restrictions.eq("stick",stick));
        }
        if(userId>0){
           criteria.add(Restrictions.ne("userid.id", userId));
        }
        List<Coach> list = criteria.list();
        coachDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}


	@Override
	public Coach getCoachByUserId(int userId) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("userid", "userid",CriteriaSpecification.LEFT_JOIN);
        criteria.createAlias("certificate", "certificate",CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("userid.id",userId));
        Coach o = (Coach) criteria.uniqueResult();
        coachDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(o != null){
        	return o;	
        }else{
        	return null;
        }
		
	}


	@Override
	public List<Coach> getCoachBygroundAndType(Integer playground_id, Integer coachType) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("userid", "userid",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("playground_id", playground_id));
        criteria.add(Restrictions.eq("coachType", coachType));
        criteria.add(Restrictions.eq("verified", 1));
        
        List<Coach> list = criteria.list();
        coachDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}


	@Override
	public Coach getcoachByphone(String phone) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("phone", phone));
        Coach o = (Coach) criteria.uniqueResult();
        coachDao.releaseHibernateSession(criteriaAdapter.getSession());
        return o;
	}


	@Override
	public List<Coach> getCoachByObj(Coach o) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("userid", "userid",
				CriteriaSpecification.LEFT_JOIN);
        
        if(o.getCoachType()>0){
        	 criteria.add(Restrictions.eq("coachType", o.getCoachType()));
        }
        
        if(o.getVerified() >= 0){
        	  criteria.add(Restrictions.eq("verified", o.getVerified()));
        }

        if(o.getNow_live_city()>0){
        	  criteria.add(Restrictions.eq("now_live_city", o.getNow_live_city()));
        }
        List<Coach> list = criteria.list();
        coachDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list != null && list.size()>0){
    		return list;	
        }else{
        	return null; 
        }
	}


	@Override
	public List<Coach> getCoachByPm(Integer pmId) {
		CriteriaAdapter criteriaAdapter = coachDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("playgroundmanager_id", pmId));
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		List<Coach> list = criteria.list();
		coachDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}  

}
