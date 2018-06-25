package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.common.Constants.O_STATUS;
import com.project.dao.IBankCardDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.BankCard;
import com.project.pojo.BankInfo;
import com.project.service.IBankService;
import com.project.util.CommonUtil;

@Service
public class BankServiceImpl  implements IBankService {

	@Resource
	private IBankCardDao bankCardDao;
	
	@Override
	public List<BankInfo> getAllBankInfos() {
		//start_session	add by lxc	2015-08-19
		org.hibernate.Query query=null;
		org.hibernate.Session sessionTemp = bankCardDao.getHibernateSession();
		String sql = "SELECT * FROM bank_info";
		//query= sessionTemp.createSQLQuery(sql).setParameter(0, pay)	.setParameter(1, brandNo).setParameter(2, 8);
		query= sessionTemp.createSQLQuery(sql).addEntity(BankInfo.class);
		List<BankInfo> list = query.list();
		sessionTemp.close();			
		//end
		return list;
	}

	@Override
	public List<BankCard> getAllBankCardsByUid(int uid) {
		CriteriaAdapter criteriaAdapter = bankCardDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id",uid));
		List<BankCard> list = criteria.list();
		bankCardDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public boolean saveBankCardInfo(BankCard card) {
		boolean flag = false;
		card.setCreate_time(CommonUtil.getTimeNow());
		card.setUpdate_time(CommonUtil.getTimeNow());
		try {
			bankCardDao.save(card);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public PageFinder<BankCard> getAllBandCardByPage(int pageNumber, int i,BankCard o) {
		CriteriaAdapter criteriaAdapter = bankCardDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.createCriteria("bank", "bank", CriteriaSpecification.LEFT_JOIN);
		if(CommonUtil.NotEmpty(o.getCard_num())){
			criteria.add(Restrictions.like("card_num",o.getCard_num(),MatchMode.ANYWHERE));
		}
		if(CommonUtil.NotEmpty(o.getAccount_name())){
			criteria.add(Restrictions.like("account_name",o.getAccount_name(),MatchMode.ANYWHERE));
		}
		if(0!=o.getCard_status()){
			criteria.add(Restrictions.eq("card_status",o.getCard_status()));
		}

		PageFinder<BankCard> list = bankCardDao.pagedByCriteria(criteria, pageNumber, i);
		bankCardDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}
	
	@Transactional
	public BankCard updateCardStatus(int card_id,int status){
		BankCard b = getCardInfoById(card_id);
		b.setCard_status(status);
		return updateBankCardInfo(b);
	}

	@Override
	public BankCard getCardInfoById(int card_id) {
		return bankCardDao.getById(card_id);
	}

	@Override
	public List<BankCard> getAuthBankCardsByUid(int uid) {
		CriteriaAdapter criteriaAdapter = bankCardDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.createCriteria("bank", "bank", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id",uid));
		criteria.add(Restrictions.eq("card_status",O_STATUS.PASS_FOR_CHECK.getStatus()));
		List<BankCard> list = criteria.list();
		bankCardDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	@Transactional
	public BankCard updateBankCardInfo(BankCard card) {
		card.setUpdate_time(CommonUtil.getTimeNow());
		return bankCardDao.merge(card);
	}

	@Override
	@Transactional
	public boolean delBankCardInfo(int card_id) {
		try {
			bankCardDao.removeById(card_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public void updateByCardAuth(BankCard card) {
		updateBankCardInfo(card);
	}

	@Override
	public BankCard getCardInfoByCard_num(String card_num) {
		CriteriaAdapter criteriaAdapter = bankCardDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.createCriteria("bank", "bank", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("card_num",card_num));
		BankCard b = (BankCard) criteria.uniqueResult();
		bankCardDao.releaseHibernateSession(criteriaAdapter.getSession());
		return b;
	}

	@Override
	public Integer getBankCardAuthCount(int status) {
		return 0;//queryForObject("SELECT COUNT(0) FROM bank_card WHERE card_status=:status", Integer.class, "status",status);
	}

	@Override
	public BankCard getCardInfoById_Uid(int card_id, int uid) {
		CriteriaAdapter criteriaAdapter = bankCardDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id",uid));
		criteria.add(Restrictions.eq("id",card_id));
		BankCard b = (BankCard) criteria.uniqueResult();
		bankCardDao.releaseHibernateSession(criteriaAdapter.getSession());
		return b;
	}

	@Override
	public BankCard getCardInfoById_cascade(int card_id) {
		CriteriaAdapter criteriaAdapter = bankCardDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.createCriteria("bank", "bank", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("id",card_id));
		BankCard b = (BankCard) criteria.uniqueResult();
		bankCardDao.releaseHibernateSession(criteriaAdapter.getSession());
		return b;
	}
}
