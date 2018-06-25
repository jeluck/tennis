package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IAdvertiseDao;
import com.project.dao.IFriendlyLinkDao;
import com.project.dao.INoticeDao;
import com.project.dao.IPlatformInfoDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Advertise;
import com.project.pojo.FriendlyLink;
import com.project.pojo.Notice;
import com.project.pojo.PlatformInfo;
import com.project.pojo.SEOSetting;
import com.project.service.IPropagandaService;
import com.project.util.CommonUtil;

@Service("advertiseServiceImpl")
public class PropagandaServiceImpl implements IPropagandaService {

	@Resource
	private IPlatformInfoDao platformInfoDao;
	@Resource
	private INoticeDao noticeDao;
	@Resource
	private IFriendlyLinkDao friendlyLinkDao;
	@Resource
	private IAdvertiseDao advertiseDao;
	
	@Override
	@Transactional
	public void delAdvertise(int ad_id) throws Exception {
		advertiseDao.removeById(ad_id);
	}

	@Override
	@Transactional
	public void updateAdvertise(Advertise advertise) {
		advertiseDao.merge(advertise);
	}

	@Override
	@Transactional
	public void saveAdvertise(Advertise advertise) throws Exception {
		advertiseDao.save(advertise);
	}

	@Override
	public List<Advertise> getAdlistOnMobile() {
		return advertiseDao.findBy("adtype", 1, "sort_num", false, false);
	}
	
	@Override
	public Advertise getAdvertiseInfo(int ad_id) {
		return advertiseDao.getById(ad_id);
	}

	@Override
	public PageFinder<Notice> getNoticeList(int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = noticeDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.addOrder(Order.desc("id"));
		PageFinder<Notice> l = noticeDao.pagedByCriteria(criteria, pageNumber, pageSize);
		noticeDao.releaseHibernateSession(criteriaAdapter.getSession());
		return l;
	}
	
	@Override
	public PageFinder<Notice> getNoticeListWithNoticeType(int pageNumber, int pageSize, String noticeType)
	{
		CriteriaAdapter criteriaAdapter = noticeDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("notice_type", noticeType));
		PageFinder<Notice> l = noticeDao.pagedByCriteria(criteria, pageNumber, pageSize);
		noticeDao.releaseHibernateSession(criteriaAdapter.getSession());
		return l;
	}
	
	@Override
	public PageFinder<Notice> getNoticeListForMobile(int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = noticeDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("type", 2));
		criteria.addOrder(Order.desc("create_time"));
		PageFinder<Notice> l = noticeDao.pagedByCriteria(criteria, pageNumber, pageSize);
		noticeDao.releaseHibernateSession(criteriaAdapter.getSession());
		return l;
	}

	@Override
	public Notice getNoticeById(int id) {
		return noticeDao.getById(id);
	}
	
	@Override
	@Transactional
	public boolean saveNotice(Notice notice){
		notice.setCreate_time(CommonUtil.getTimeNow());
		try {
			noticeDao.save(notice);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public PageFinder<FriendlyLink> getFriendlyLinkList(int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = friendlyLinkDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.addOrder(Order.asc("sort"));
		PageFinder<FriendlyLink> l = friendlyLinkDao.pagedByCriteria(criteria, pageNumber, pageSize);
		friendlyLinkDao.releaseHibernateSession(criteriaAdapter.getSession());
		return l;
	}

	@Override
	@Transactional
	public FriendlyLink saveFriendlyLink(FriendlyLink link) throws Exception {
		link.setCreate_time(CommonUtil.getTimeNow());
		return friendlyLinkDao.saveObject(link);
	}

	@Override
	public PlatformInfo getPlatformInfo() {
		CriteriaAdapter criteriaAdapter = platformInfoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		PlatformInfo o =  platformInfoDao.queryObjectByCriteria(criteria);
		platformInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return o;
	}

	@Override
	public List<Advertise> getAllAdvertises() {
		return advertiseDao.getAll("sort_num", false);
	}

	@Override
	public List<FriendlyLink> getAllFriendlyLinks() {
		return friendlyLinkDao.getAll("sort",false);
	}
	
	@Override
	public List<FriendlyLink> getFriendLinksInTypes(String linkType)
	{
		return friendlyLinkDao.findBy("link_type", linkType, "sort", false, false);
	}

	@Override
	@Transactional
	public void delFriendlink(int id) throws Exception {
		friendlyLinkDao.removeById(id);
	}

	
	@Override
	@Transactional
	public void delNotice(int id) throws Exception {
		noticeDao.removeById(id);
	}

	@Override
	@Transactional
	public void updateNotice(Notice notice) {
		Notice n = getNoticeById(notice.getId());
		notice.setCreate_time(n.getCreate_time());
		notice.setCreater_uid(n.getCreater_uid());
		
		
		notice.setUpdate_time(CommonUtil.getTimeNow());
		noticeDao.merge(notice);
	}

	@Override
	public FriendlyLink getFriendlyLinkById(int id) {
		return friendlyLinkDao.getById(id);
	}

	@Override
	@Transactional
	public boolean updateFriendlyLink(FriendlyLink friendlyLink) {
		FriendlyLink f = getFriendlyLinkById(friendlyLink.getId());
		friendlyLink.setCreate_time(f.getCreate_time());
		
		friendlyLink.setUpdate_time(CommonUtil.getTimeNow());
		friendlyLinkDao.merge(friendlyLink);
		return true;
	}

	@Override
	public List<Advertise> getAdvertiseByType(int ad_type) {
		return advertiseDao.findBy("adtype", ad_type, "sort_num", false, false);
	}

	@Override
	public PageFinder<Advertise> getAdvertisePageBean(Advertise o,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = advertiseDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.addOrder(Order.desc("create_time"));
		PageFinder<Advertise> l = advertiseDao.pagedByCriteria(criteria, pageNumber, pageSize);
		advertiseDao.releaseHibernateSession(criteriaAdapter.getSession());
		return l;
	}

	@Override
	@Transactional
	public boolean updatePlatformInfoForCompany_info(PlatformInfo pi) {
		PlatformInfo p = getPlatformInfo();
//		pi.setContact_us(p.getContact_us());
//		pi.setAgreement(p.getAgreement());
//		pi.setCreate_time(p.getCreate_time());
//		pi.setUpdate_time(CommonUtil.getTimeNow());
		p.setCompany_info(pi.getCompany_info());
		platformInfoDao.merge(p);
		return true;
	}
	
	@Override
	@Transactional
	public boolean updatePlatformInfoForContact_us(PlatformInfo pi) {
		PlatformInfo p = getPlatformInfo();
//		pi.setCompany_info(p.getCompany_info());
//		pi.setAgreement(p.getAgreement());
//		pi.setCreate_time(p.getCreate_time());
//		pi.setUpdate_time(CommonUtil.getTimeNow());
		p.setContact_us(pi.getContact_us());
		platformInfoDao.merge(p);
		return true;
	}

	@Override
	public List<Notice> getNoticeLists(int city_show_id,int province_id) {
		CriteriaAdapter criteriaAdapter = noticeDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
    	criteria.add(Restrictions.eq("type", 2));
    	if(city_show_id>0){
        	Disjunction dis=Restrictions.disjunction();  
        	dis.add(Restrictions.eq("city_show_id",city_show_id));
 			dis.add(Restrictions.eq("province_id", 0));
 			criteria.add(dis);
        }
    	criteria.setFirstResult(0);
    	criteria.setMaxResults(5);
    	criteria.addOrder(Order.desc("create_time"));
        List<Notice> list = criteria.list();
        noticeDao.releaseHibernateSession(criteriaAdapter.getSession());
        
        if(province_id>0){
	        CriteriaAdapter criteriaAdapter1 = noticeDao.createCriteriaAdapter();
	        Criteria criteria1 = criteriaAdapter1.getCriteria();
	    	criteria1.add(Restrictions.eq("type", 2));
	    	criteria1.add(Restrictions.eq("province_id",province_id)).add(Restrictions.eq("city_show_id",0));
	    	criteria1.setFirstResult(0);
	    	criteria1.setMaxResults(5);
	    	criteria1.addOrder(Order.desc("create_time"));
	        List<Notice> list1 = criteria1.list();
	        noticeDao.releaseHibernateSession(criteriaAdapter1.getSession());
	        list.addAll(list1);
        }
       
        return list;
	}

	@Override
	public boolean updatePlatformInfoAgreement(PlatformInfo pi) {
		PlatformInfo p = getPlatformInfo();
//		pi.setCompany_info(p.getCompany_info());
//		pi.setContact_us(p.getContact_us());
//		pi.setCreate_time(p.getCreate_time());
//		pi.setUpdate_time(CommonUtil.getTimeNow());
		p.setAgreement(pi.getAgreement());
		platformInfoDao.merge(p);
		return false;
	}

	@Override
	public boolean updatePlatformInfoLevel_explain(PlatformInfo pi) {
		PlatformInfo p = getPlatformInfo();
		p.setLevel_explain(pi.getLevel_explain());
		platformInfoDao.merge(p);
		return true;
	}
	
}
