package com.project.service;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Weuser;

public interface IWeuserService {
	public PageFinder<Weuser> getUserList(Weuser a,int pageNumber, int pageSize);
	
	public Weuser saveUser(Weuser user);

	public Weuser getUserById(int parseInt);

	public Weuser mergeAndUpdateTime(Weuser w);

	public boolean updateLockFlag(int aid, int flag);

	public List<Weuser> getAllUser();

	public Weuser getUserByPhone(String userPhone);

	public Weuser getWeuserByinvite_code(String invitedCode);

	public Weuser saveNewUser(Weuser weuser) throws Exception;

	public Weuser userlogin(String userPhone, String password);

	public Weuser getUserByIdcardNo(String idcard_no);

	public void updateUser(Weuser w);

	/**
	 * 读取表格
	 * @param inputStream
	 * @return
	 */
	public List<Weuser> readReport(InputStream inputStream);

	/**
	 * 批量插入
	 * @param list
	 */
	public void insertComputer(List<Weuser> list)throws Exception;
	

	/**
     * 根据前端传的ID,找当前登录者,并且保存到session
     * @param request
     * @param o
     * @return
     */
	public Weuser getLonginByHttpServletRequest_Id(HttpServletRequest request,Weuser o,Class c);
	
	/**
	 * 根据是否是教练获取全部用户
	 * @return
	 */
	public List<Weuser> getAllUserByIs_coach(int is_coach);
	
	
	public List<Weuser> getUserByObj(Weuser o);
	
	/**
	 * 根据身份证判断教练身份证是否已存在
	 * @param idcard_no
	 * @return
	 */
	public boolean checkIdcard_no(String idcard_no,int id);
}
