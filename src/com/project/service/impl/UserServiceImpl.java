package com.project.service.impl;

import com.project.dao.IManagerDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Manager;
import com.project.service.IUserService;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


//public class UserServiceImpl extends BaseService implements IUserService {
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

	@Resource
	private IManagerDao managerDao;
	
	@Override
	public Manager managerlogin(Manager manager) {
		//String hql = "from manager m where m.usercode=?";
		//Manager mt = managerDao.find(hql, new Object[] { manager.getUsercode() }).get(0);
		//Manager mt = managerDao.find(hql,manager.getUsercode()).get(0);
		
//		Manager mt = managerDao.findUniqueBy("usercode", manager.getUsercode());
//		System.err.println(mt);
		
//		CritMap crit = new CritMap();
//		crit.addEqual("usercode",  manager.getUsercode());
//		List<Manager> ll = managerDao.findByCritMap(crit, false);
//		System.err.println(ll);
		
		CriteriaAdapter criteriaAdapter = managerDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("usercode", manager.getUsercode()));
		criteria.add(Restrictions.eq("password", manager.getPassword()));
		List<Manager> l = criteria.list();
		managerDao.releaseHibernateSession(criteriaAdapter.getSession());
		
		if(l!=null && l.size()>0) {
			Manager m = l.get(0);
			return m;
		}
		return null;
	}

	@Override
	public boolean updateUserPswd(Manager manager) {
		
		try{
			managerDao.merge(manager);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean sendEmail(String toemail, HttpServletRequest request,
			String code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateEmailInfo(String toemail, int status, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateUserStatus(String key, int i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean valiunique(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePhoneInfo(String phone, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateNickNameInfo(String nickname, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateIdCardInfo(String idcard, String real_name,
			String birthday, String sex, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateLogpwdInfo(String login_pwd, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateEmailInfo(String email, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTixianpwdInfo(String withdrawal_pwd, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save_authInfo(int id, String auth_type, String uploadpath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateInfoProgress(int uid, String columnName, int status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAuthInfoStatus(int is_auth_verified, int uid,
			String auth_type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAuthInfoStatus(int is_auth_verified, int uid,
			String auth_code, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean sendEmail(String toemail, String subject, String content) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmail_KEY(int uid, String eml_Key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateEmailInfo(String toemail, String key, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkPhone(String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkEmail(String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateNickNameByUid(int uid, String nickname) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateWithdrawPwdByUid(int uid, String newpwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLoginPwdByUid(int uid, String newpwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLoginInfo(int uid, String last_login_time,
			String last_login_ip, int last_login_device) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIdCardUnique(String pswd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateidcard(int uid, String realname, String idcard,
			String birthday, int sex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPhoneAuthBystatus(int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBaseInfoNopassCount(int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateBasicInfo(HttpServletRequest request, int uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> getUserAuthMapForMobile(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getUserSafeInfoForMobile(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUserMobileRecord(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
