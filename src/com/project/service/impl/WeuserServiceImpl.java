package com.project.service.impl;

import com.project.common.Constants;
import com.project.common.Settings;
import com.project.dao.IWeuserDao;
import com.project.dao.impl.WeuserDaoImpl;
import com.project.orm.basedao.CritMap;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.Weuser;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;
import com.project.util.SecurityUtil;
import com.sun.media.sound.InvalidFormatException;

import org.apache.poi.ss.usermodel.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("weuserServiceImpl")
public class WeuserServiceImpl implements IWeuserService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(WeuserServiceImpl.class);

    @Resource
    private IWeuserDao weuserDao;
    
    /**
     * 获得用户列表(带分页)
     * @param a
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageFinder<Weuser> getUserList(Weuser a,int pageNumber, int pageSize) {
        CriteriaAdapter criteriaAdapter = weuserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        if (CommonUtil.NotEmpty(a.getWenumber()))
        {
            criteria.add(Restrictions.like("wenumber", a.getWenumber(), MatchMode.ANYWHERE));
        }
        if(CommonUtil.NotEmpty(a.getUphone())){
            criteria.add(Restrictions.like("uphone", a.getUphone(), MatchMode.ANYWHERE));
        }
        if(CommonUtil.NotEmpty( a.getUsername())){
            criteria.add(Restrictions.like("username", a.getUsername(),MatchMode.ANYWHERE));
        }
        if(CommonUtil.NotEmpty( a.getIdcard_no())){
            criteria.add(Restrictions.like("idcard_no", a.getIdcard_no(),MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("create_time"));
        PageFinder<Weuser> pageFinder = weuserDao.pagedByCriteria(criteria, pageNumber, pageSize);
        weuserDao.releaseHibernateSession(criteriaAdapter.getSession());
        return pageFinder;
    }

    /**
     * 获得用户列表(带分页)
     * @param a
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageFinder<Weuser> getUserListCritMap(int pageNumber, int pageSize) {
        CritMap critMap=new CritMap();
        critMap.addAsc("create_time");
        PageFinder<Weuser> pageFinder = weuserDao.pagedByCritMap(critMap, pageNumber, pageSize);
        return pageFinder;
    }

    /***
     * 保存用户
     * @param user
     * @return
     */
    public Weuser saveUser(Weuser user) {
        try {
            user.setUpdate_time(CommonUtil.getTimeNow());
            user.setCreate_time(CommonUtil.getTimeNow());
            weuserDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.equals(Constants.OperationResult.UNKNOWN_MISTAKE.getMsg());
            for(StackTraceElement ste : e.getStackTrace())
            {
                logger.error(ste.toString());
            }
            return null;
        }
        return user;
    }

    /***
     * 保存用户
     * @param user
     * @return
     */
    @Transactional
    public Weuser saveNewUser(Weuser user) throws Exception
    {
        user.setUpdate_time(CommonUtil.getTimeNow());
        user.setCreate_time(CommonUtil.getTimeNow());
        weuserDao.save(user);
        
        return user;
    }

    @Transactional
    public void updateUser(Weuser weuser)
    {
    	weuser.setUpdate_time(CommonUtil.getTimeNow());
        weuserDao.merge(weuser);
    }

    /**
     * 通过用户id更新用户密码
     * @param password
     * @param id
     * @return
     */
    @Transactional
    public boolean updatePasswordByid(String password,int id) {
        Weuser weuser = weuserDao.getById(id);
        if(weuser!=null){
            weuser.setPass(password);
            weuser.setUpdate_time(CommonUtil.getTimeNow());
            weuserDao.merge(weuser);
            return true;
        }
        return false;
    }

    /**
     * 根据用户ID获得用户数据
     * @param id
     * @return
     */
    public Weuser getUserById(int id) {
        return weuserDao.getById(id);
    }

    public boolean updateLockFlag(int id, int flag) {
        Weuser weuser = weuserDao.getById(id);
        if(weuser!=null){
            weuser.setFlag(flag);
            weuser.setUpdate_time(CommonUtil.getTimeNow());
            weuserDao.merge(weuser);
            return true;
        }
        return false;
    }

    public void testDB() {
        final Long begin_time  = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        //logger.info("当前线程"+Thread.currentThread().getName()+"第"+j+"次执行");
                        //queryForObject(Account.class, "select * from account where id=:id", "id",1);
                        System.out.println("耗时（毫秒）："+(System.currentTimeMillis()-begin_time));
                    }
                }
            });
            thread.start();
        }

    }

    /**
     * 通过身份证号码获取用户
     * @param idcard_no
     * @return
     */
    public Weuser getUserByIdcardNo(String idcard_no) {
        CriteriaAdapter criteriaAdapter = weuserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("idcard_no",idcard_no));
        Weuser weuser = null;
        try {
            weuser = (Weuser) criteria.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
            logger.equals(Constants.OperationResult.UNKNOWN_MISTAKE.getMsg());
            for(StackTraceElement ste : e.getStackTrace())
            {
                logger.error(ste.toString());
            }
            return null;
        }
        weuserDao.releaseHibernateSession(criteriaAdapter.getSession());
        return weuser;
    }

    public Weuser getUserByPhone(String phone) {
        CriteriaAdapter criteriaAdapter = weuserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("uphone",phone));
        Weuser weuser = null;
        weuser = (Weuser) criteria.uniqueResult();
        weuserDao.releaseHibernateSession(criteriaAdapter.getSession());
        return weuser;
    }

    public Weuser userlogin(String uphone, String pass) {
        CriteriaAdapter criteriaAdapter = weuserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("uphone",uphone));
        criteria.add(Restrictions.eq("pass",pass));
        Weuser weuser = null;
        try {
            weuser = (Weuser) criteria.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        weuserDao.releaseHibernateSession(criteriaAdapter.getSession());
        return weuser;
    }

    public boolean find_edit_loginpswd_byphone(String uphone,String newpass) {
        Weuser weuser = getUserByPhone(uphone);
        if(weuser!=null){
            weuser.setPass(SecurityUtil.encrypt(newpass));
            weuser.setUpdate_time(CommonUtil.getTimeNow());
            weuserDao.merge(weuser);
            return true;
        }
        return false;
    }

    @Transactional
    public Weuser mergeAndUpdateTime(Weuser weuser){
        weuser.setUpdate_time(CommonUtil.getTimeNow());
        weuserDao.merge(weuser);
        return weuser;
    }

    @Transactional
    public Weuser saveHeadPhotoByAid(int aid, String head_path) {
        Weuser weuser = getUserById(aid);
        weuser.setHead_photo(head_path);
        mergeAndUpdateTime(weuser);
        return weuser;
    }

    public List<Weuser> getAllUser()
    {
        CriteriaAdapter criteriaAdapter = weuserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        List<Weuser> list = criteria.list();
        weuserDao.releaseHibernateSession(criteriaAdapter.getSession());

        return list;
    }

    public Weuser getUserWithWechat(Weuser weuser) throws Exception
    {
        CriteriaAdapter criteriaAdapter = weuserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("wenumber",weuser.getWenumber()));
        Weuser r = (Weuser) criteria.uniqueResult();
        weuserDao.releaseHibernateSession(criteriaAdapter.getSession());

        return r;
    }

    /***
     * 根据邀请码查找店铺
     * @param userId
     * @return
     */
    public Weuser getWeuserByinvite_code(String invite_code)
    {
        CriteriaAdapter criteriaAdapter = weuserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("invite_code", invite_code));
        Weuser w = (Weuser) criteria.uniqueResult();
        weuserDao.releaseHibernateSession(criteriaAdapter.getSession());

        return w;
    }
    
    /**
     * 获得邀请码值(由于邀请码需要自动增长,因此新建一个表,负责生成邀请码)
     * @return
     */
    public String getInviteCode(String uphone){
    	org.hibernate.Query query=null;
		org.hibernate.Session sessionTemp = weuserDao.getHibernateSession();
		String sql = "INSERT invate_code  ( uphone) VALUES ('"+uphone+"')";
		query= sessionTemp.createSQLQuery(sql);
		int i = query.executeUpdate();
		sessionTemp.flush();
		
		sessionTemp = weuserDao.getHibernateSession();
		sql = "SELECT id FROM invate_code  ORDER BY id DESC LIMIT 1;";
		query= sessionTemp.createSQLQuery(sql);
		Object ob = query.uniqueResult();
		if(ob==null)ob=0;
		String count = ob.toString();
		sessionTemp.flush();			//需要关闭session,但一关就地报错
		return count;
    }

	@Override
	public List<Weuser> readReport(InputStream inputStream) {
		List<Weuser> userList = new ArrayList<Weuser>();  
		  
        try {  
            String cellStr = null;  
  
            Workbook wb = null;
			try {
				wb = WorkbookFactory.create(inputStream);
			} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
				e.printStackTrace();
			}  
  
            Sheet sheet = wb.getSheetAt(0);// 取得第一个sheets  
  
            //从第四行开始读取数据  
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  
  
            	Weuser user = new Weuser();  
            	Weuser addUser = new Weuser();  
  
                Row row = sheet.getRow(i); // 获取行(row)对象  
                System.out.println(row);
                if (row == null) {  
                    // row为空的话,不处理  
                    continue;  
                }  
  
                for (int j = 0; j < row.getLastCellNum(); j++) {  
  
                    Cell cell = row.getCell(j); // 获得单元格(cell)对象  
  
                    // 转换接收的单元格  
                    cellStr = ConvertCellStr(cell, cellStr);  
  
                    // 将单元格的数据添加至一个对象  
                    addUser = addingWeuser(j, user, cellStr);  
  
                }  
                // 将添加数据后的对象填充至list中  
                userList.add(addUser);  
            }
  
        } catch (InvalidFormatException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (inputStream != null) {  
                try {  
                	inputStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            } else {  
                
            }  
        }  
        return userList;  
	}

	@Override
	public void insertComputer(List<Weuser> list)throws Exception {
		weuserDao.saveAll(list);
	}
	
	/** 
     * 获得单元格的数据添加至computer 
     *  
     * @param j 
     *            列数 
     * @param computer 
     *            添加对象 
     * @param cellStr 
     *            单元格数据 
     * @return 
     */  
    private Weuser addingWeuser(int j, Weuser user, String cellStr) {  
        switch (j) {  
        case 0:  
        	user.setUphone(cellStr);
            break;  
        case 1:  
        	user.setPass(cellStr);
            break;  
        case 2:  
        	user.setAmount(Double.valueOf(cellStr));
            break;  
        case 3:  
        	user.setUsername(cellStr);
            break;  
        case 4:  
            break;  
        case 5:  
            break;  
        }  
  
        return user;  
    }
    
    /** 
     * 把单元格内的类型转换至String类型 
     */  
    private String ConvertCellStr(Cell cell, String cellStr) {  
  
        switch (cell.getCellType()) {  
  
        case Cell.CELL_TYPE_STRING:  
            // 读取String  
            cellStr = cell.getStringCellValue().toString();  
            break;  
  
        case Cell.CELL_TYPE_BOOLEAN:  
            // 得到Boolean对象的方法  
            cellStr = String.valueOf(cell.getBooleanCellValue());  
            break;  
  
        case Cell.CELL_TYPE_NUMERIC:  
  
            // 先看是否是日期格式  
            if (DateUtil.isCellDateFormatted(cell)) {  
  
                // 读取日期格式  
                cellStr = cell.getDateCellValue().toString();  
  
            } else {  
  
                // 读取数字  
                cellStr = String.valueOf((long)cell.getNumericCellValue());  
            }  
            break;  
  
        case Cell.CELL_TYPE_FORMULA:  
            // 读取公式  
            cellStr = cell.getCellFormula().toString();  
            break;  
        }  
        return cellStr;  
    }

	@Override
	public Weuser getLonginByHttpServletRequest_Id(HttpServletRequest request,
			Weuser o, Class c) {
		String id = request.getParameter("userId");
		if(CommonUtil.isEmpty(id)){
			id = request.getParameter("weuserId");
		}
    	System.err.println("从request获得的id: "+id+"------------------------------------,调用的class:"+c.getName());
    	if(o!=null&&CommonUtil.NotEmpty(id)){
    		System.err.println("从登录者获得的id: "+o.getId()+"------------------------------------,调用的class:"+c.getName());
    		if( o.getId()!=Integer.parseInt(id)){
    			try{
    	    		o = getUserById(Integer.parseInt(id));
    	    	}catch(Exception e){
    	    		
    	    	}
    	    	HttpSession session = request.getSession();
    			session.setAttribute(Settings.USER_SESSION, o);
    			session.setMaxInactiveInterval(1 * 60);
    		}
    	}
		if(o==null){
	    	try{
	    		o = getUserById(Integer.parseInt(id));
	    		HttpSession session = request.getSession();
				session.setAttribute(Settings.USER_SESSION, o);
				session.setMaxInactiveInterval(1 * 60);
	    	}catch(Exception e){
	    	}
	    	
		}
		return o;
	}

	@Override
	public List<Weuser> getAllUserByIs_coach(int is_coach) {
		CriteriaAdapter criteriaAdapter = weuserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        if(is_coach>=0){
        	criteria.add(Restrictions.eq("is_coach", is_coach));
        }
        List<Weuser> list = criteria.list();
        weuserDao.releaseHibernateSession(criteriaAdapter.getSession());

        return list;
	}

	@Override
	public List<Weuser> getUserByObj(Weuser o) {
		CriteriaAdapter criteriaAdapter = weuserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        if(o.getIs_coach()>=0){
        	criteria.add(Restrictions.eq("is_coach", o.getIs_coach()));
        }
        if(CommonUtil.NotEmpty(o.getUphone())){
        	criteria.add(Restrictions.eq("uphone", o.getUphone()));
        }
        
        List<Weuser> list = criteria.list();
        weuserDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
	}

	@Override
	public boolean checkIdcard_no(String idcard_no, int id) {
		CriteriaAdapter criteriaAdapter = weuserDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("idcard_no", idcard_no));
        if(id>0){
        	 criteria.add(Restrictions.not(Restrictions.eq("id", id)));
        }
        List<Weuser> list = criteria.list();
        weuserDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()<=0){
        	return true;
		}else{
			return false;
		}
	}
}
