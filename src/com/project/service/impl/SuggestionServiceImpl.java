package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import com.project.common.Constants;
import com.project.dao.ISuggestionDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Suggestion;
import com.project.service.ISuggestionService;
import com.project.util.CommonUtil;
@Service("suggestionServiceImpl")
public class SuggestionServiceImpl implements ISuggestionService {
	
	@Resource
	private ISuggestionDao suggestionDao;
	
	@Override
	public PageFinder<Suggestion> getSuggestionList(int pageNumber, int pageSize) {
		//return getPageBeanBySQL("SELECT * FROM driverinfo ORDER BY create_time ASC ",pageBean, Suggestion.class);
		CriteriaAdapter criteriaAdapter = suggestionDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.addOrder(Order.desc("create_time"));
		PageFinder<Suggestion> querySuggestionList = suggestionDao.pagedByCriteria(criteria, pageNumber, pageSize);
		suggestionDao.releaseHibernateSession(criteriaAdapter.getSession());
		return querySuggestionList;
	}
	
	@Override
	public Suggestion saveSuggestion(Suggestion s) {
		try {
			s.setReadstatus(Constants.MessageReadStatus.NOT_READ.getStatus());
			s.setUpdate_time(CommonUtil.getTimeNow());
			s.setCreate_time(CommonUtil.getTimeNow());
			suggestionDao.save(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return s;
	}
	
	@Override
	public boolean updateSuggestionByid(Suggestion s) {
		s.setUpdate_time(CommonUtil.getTimeNow());
		suggestionDao.merge(s);
		return true;
	}

	@Override
	public Suggestion getSuggestionById(int id) {
		return suggestionDao.getById(id);
	}

	@Override
	public void testDB() {
		final Long begin_time  = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < 1000; j++) {
						//logger.info("当前线程"+Thread.currentThread().getName()+"第"+j+"次执行");
						//queryForObject(Suggestion.class, "select * from d where id=:id", "id",1);
						System.out.println("耗时（毫秒）："+(System.currentTimeMillis()-begin_time));
					}
				}
			});
			thread.start();
		}
		
	}

	@Override
	public boolean deleteSuggestionById(int id) {
		try {
			suggestionDao.removeById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


}
