package com.project.service;

import com.project.pojo.Manager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IUserService {
	/**
	 * 管理员登录
	 * @param manager
	 * @return
	 */
	public Manager managerlogin(Manager manager);
	/**
	 * 用户修改密码
	 * @param manager
	 */
	public boolean updateUserPswd(Manager manager);
	/**
	 * 发送邮箱
	 * 
	 * @return
	 */
	public boolean sendEmail(String toemail, HttpServletRequest request, String code);
	/**
	 * 更新邮箱信息
	 * @param toemail
	 * @param status
	 * @param id
	 */
	public void updateEmailInfo(String toemail, int status, int id);
	/**
	 * 更新用户验证状态
	 * @param key
	 * @param i
	 * @return
	 */
	public boolean updateUserStatus(String key, int i);
	/**
	 * 验证用户名的唯一性
	 * @param username
	 * @return
	 */
	public boolean valiunique(String username);
	/**
	 * 更新手机号码信息
	 * @param phone
	 * @param id
	 */
	public boolean updatePhoneInfo(String phone, int id);
	/**
	 * 更改用户昵称
	 * @param parameter
	 * @param id
	 * @return 
	 */
	public void updateNickNameInfo(String nickname, int id);
	/**
	 * 更新实名认证信息
	 * @param sex 
	 * @param birthday 
	 * @param parameter
	 * @param parameter2
	 * @param id
	 */
	public boolean updateIdCardInfo(String idcard, String real_name, String birthday, String sex, int id);
	/**
	 * 更新登录密码
	 * @param parameter
	 * @param id
	 * @return 
	 */
	public void updateLogpwdInfo(String login_pwd, int id);
	/**
	 * 更新邮箱帐号
	 * @param email
	 * @param id
	 * @return
	 */
	public boolean updateEmailInfo(String email, int id);
	/**
	 * 更新提现密码
	 * @param parameter
	 * @param id
	 * @return 
	 */
	public boolean updateTixianpwdInfo(String withdrawal_pwd, int id);
	/**
	 * 保存认证信息
	 * @param uid
	 * @param auth_type
	 * @param uploadpath
	 * @return
	 */
	public boolean save_authInfo(int id, String auth_type, String uploadpath);

	/**
	 * 更新用户完善资料进度
	 * @param uid UID
	 * @param columnName 列名
	 * @param status 状态
	 */
	public void updateInfoProgress(int uid, String columnName, int status);
	/**
	 * 改变认证的认证状态
	 * @param is_auth_verified 认证状态，1未上传，2已上传，3审核中，4,已通过 5已驳回
	 * @param uid 用户ID
	 * @param auth_type 认证类型
	 */
	public void updateAuthInfoStatus(int is_auth_verified, int uid, String auth_type);
	/**
	 * 管理员审核认证信息
	 * @param is_auth_verified
	 * @param uid
	 * @param auth_code
	 * @param id
	 */
	public void updateAuthInfoStatus(int is_auth_verified, int uid,
									 String auth_code, int id);
	/**
	 * 发送邮件
	 * @param toemail 目标邮箱
	 * @param subject 邮件主题 
	 * @param content 邮件内容
	 * @return
	 */
	public boolean sendEmail(String toemail, String subject, String content);
	/**
	 * 更新邮箱KEY 
	 * @param uid
	 * @param eml_Key
	 * @return
	 */
	public boolean updateEmail_KEY(int uid, String eml_Key);
	/**
	 * 保存邮箱信息
	 * @param toemail
	 * @param key
	 * @param id
	 */
	public void updateEmailInfo(String toemail, String key, int id);
	/**
	 * 检测手机号码的唯一性
	 * @param value
	 * @return
	 */
	public boolean checkPhone(String value);
	/**
	 * 检测电子邮件的唯一性
	 * @param value
	 * @return
	 */
	public boolean checkEmail(String value);
	/**
	 * 更新用户昵称
	 * @param uid
	 * @param oldpwd
	 * @return
	 */
	public boolean updateNickNameByUid(int uid, String nickname);
	/**
	 * 更新提现密码
	 * @param uid
	 * @param newpwd
	 * @return
	 */
	public boolean updateWithdrawPwdByUid(int uid, String newpwd);
	/**
	 * 更新登录密码
	 * @param uid
	 * @param newpwd
	 * @return
	 */
	public boolean updateLoginPwdByUid(int uid, String newpwd);
	/**
	 * 更新用户登录信息
	 * @param uid
	 * @param last_login_time
	 * @param last_login_ip
	 * @param last_login_device
	 * @return
	 */
	public boolean updateLoginInfo(int uid, String last_login_time, String last_login_ip, int last_login_device);
	/**
	 * 检测身份证的唯一性
	 * @param pswd
	 * @return
	 */
	public boolean checkIdCardUnique(String pswd);
	/**
	 * 更新实名信息
	 * @param uid
	 * @param realname
	 * @param idcard
	 * @param birthday
	 * @param sex
	 * @return
	 */
	public boolean updateidcard(int uid, String realname, String idcard,
								String birthday, int sex);
	/**
	 * 得到要审核手机号的数量
	 * @param i
	 * @return
	 */
	public int getPhoneAuthBystatus(int status);
	/**
	 * 需要审核的基本信息
	 * @param status
	 * @return
	 */
	public int getBaseInfoNopassCount(int status);
	/**
	 * 修改个人信息
	 * @param highest_graduate
	 * @param graduate_school
	 * @param is_married
	 * @param residential_address
	 * @param company_industry
	 * @param company_scale
	 * @param job
	 * @param wage
	 */
	public void updateBasicInfo(HttpServletRequest request, int uid);
	public List<Map<String, Object>> getUserAuthMapForMobile(int uid);
	
	/**
	 * 查询手机端用户的安全信息
	 * @return
	 */
	public Map<String, Object> getUserSafeInfoForMobile(int id);
	/**
	 * 删除用户手机更改记录
	 * @param id
	 * @return
	 */
	public boolean deleteUserMobileRecord(int id);
}
