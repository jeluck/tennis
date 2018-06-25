package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.common.Constants.MES_CLOUD_TYPE;
import com.project.dao.ITerraceMessageDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Space;
import com.project.pojo.TerraceMessage;
import com.project.service.ITerraceMessageService;
import com.project.util.CommonUtil;

@Service
public class TerraceMessageServiceImpl implements ITerraceMessageService{
	
	@Resource
	private ITerraceMessageDao terraceMessageDao;

	@Override
	public TerraceMessage saveTerraceMessage(TerraceMessage t) throws Exception {
		return terraceMessageDao.saveObject(t);
	}

	@Override
	public TerraceMessage updateTerraceMessage(TerraceMessage t)
			throws Exception {
		return terraceMessageDao.merge(t);
	}

	@Override
	public void deleteTerraceMessage(Integer id) throws Exception {
		terraceMessageDao.removeById(id);
		
	}

	@Override
	public PageFinder<TerraceMessage> getPageFindeTerraceMessage(TerraceMessage o,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = terraceMessageDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(CommonUtil.NotEmpty(o.getTitle())){
			criteria.add(Restrictions.like("title", o.getTitle(),MatchMode.ANYWHERE));
		}
		if(o.getMes_cloud_type()==MES_CLOUD_TYPE.PHONEMES.getStatus()){
			criteria.add(Restrictions.eq("mes_cloud_type", o.getMes_cloud_type()));
		}else{
			criteria.add(Restrictions.or(Restrictions.eq("mes_cloud_type", MES_CLOUD_TYPE.MES.getStatus()),Restrictions.eq("mes_cloud_type", MES_CLOUD_TYPE.CLOUD.getStatus())));
		}
		criteria.add(Restrictions.eq("delstatus", 0));
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<TerraceMessage> pageFinder = terraceMessageDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		terraceMessageDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public TerraceMessage getById(Integer id) {
		return terraceMessageDao.getById(id);
	}
}
