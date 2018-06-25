package com.project.service.impl;

import com.project.common.Constants;
import com.project.dao.ICommentDao;
import com.project.dao.impl.CategoryDaoImpl;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.Coach_teach_person;
import com.project.pojo.Comment;
import com.project.pojo.InformationCategoryInfo;
import com.project.service.ICommentService;
import com.project.util.CommonUtil;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Transient;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CommentServiceImpl.class);

    @Resource
    private ICommentDao commentDao;
    
	@Override
	@Transactional
	public Comment saveComment(Comment c) {
		try {
			c.setCreate_time(CommonUtil.getTimeNow());
            c.setUpdate_time(CommonUtil.getTimeNow());
			return commentDao.saveObject(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Comment mergeComment(Comment c) {
		try {
            c.setUpdate_time(CommonUtil.getTimeNow());
			return commentDao.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public void deleteComment(int id) {
		try {
			commentDao.removeById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Comment getCommentById(int id) {
		return commentDao.getById(id);
	}

	@Override
	public PageFinder<Comment> getPageFinderComment(Comment c, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = commentDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
//		if (CommonUtil.NotEmpty(o.getOrderNo())) {
//			criteria.add(Restrictions.like("orderNo", o.getOrderNo(),MatchMode.ANYWHERE));
//		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Comment> pageFinder = commentDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		commentDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public List<Comment> getComment(Integer activeId, Integer orderType) {
		CriteriaAdapter criteriaAdapter = commentDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("activeID", String.valueOf(activeId)));
        criteria.add(Restrictions.eq("orderType", orderType));
        criteria.addOrder(org.hibernate.criterion.Order.desc("create_time"));
        List<Comment> list = criteria.list();
        commentDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	
}
