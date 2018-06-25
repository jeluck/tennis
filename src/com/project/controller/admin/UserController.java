package com.project.controller.admin;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.Module;
import com.project.pojo.User_count;
import com.project.pojo.User_level;
import com.project.pojo.User_vip;
import com.project.pojo.Weuser;
import com.project.service.IBankService;
import com.project.service.ICoachService;
import com.project.service.IUser_countService;
import com.project.service.IUser_levelService;
import com.project.service.IUser_vipService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;

@Controller("adminUserWebController")
@RequestMapping(value="/admin")
public class UserController  extends BaseController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserController.class);


    @Resource
    private IWeuserService weuserService;
    @Resource
    private IBankService bankService;
    @Resource
    private IUser_countService user_countService;
	@Resource
	private ICoachService coachService;
	@Resource
	private IUser_levelService user_levelService;
	@Resource
	private IUser_vipService user_vipService;

    @RequestMapping(value = "userlist")
    public String account_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request,ModelMap map,Weuser weuser) {
        map.put("uphone", weuser.getUphone());
        map.put("username", weuser.getUsername());
        map.put("idcard_no", weuser.getIdcard_no());
        PageFinder<Weuser> pf = weuserService.getUserList(weuser, pageNumber, Constants.BACKGROUND_PAGESIZE);
        if(pf.getDataList()!=null && pf.getDataList().size()>0){
        	Coach c = null;
        	for (Weuser w : pf.getDataList()) {
				if(Constants.IS_COACH1==w.getIs_coach()){
					c = coachService.getCoachByUserId(w.getId());
					w.setCoach(c);
				}
			}
        }
        map.put("data_page", pf);
        return "admin/user/user_list";
    }

    @RequestMapping(value = "useramountlist")
    public String account_amount_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request,ModelMap map,Weuser weuser) {
      
        PageFinder<Weuser> pf = weuserService.getUserList(weuser, pageNumber, Constants.BACKGROUND_PAGESIZE);
        if(pf.getDataList()!=null && pf.getDataList().size()>0){
        	Coach c = null;
        	for (Weuser w : pf.getDataList()) {
				if(Constants.IS_COACH1==w.getIs_coach()){
					c = coachService.getCoachByUserId(w.getId());
					w.setCoach(c);
				}
			}
        }
        map.put("data_page",pf);
        return "admin/user/user_amount_list";
    }

    /**
     * 用户列表->锁定或者启用用户
     * @param pageNumber
     * @param map
     * @param aid
     * @param flag
     * @return
     */
    @UserRightAuth(menuCode = "user_list")
    @RequestMapping(value = "lock_user_flag", method = RequestMethod.GET)
    public String lock_account_flag(@RequestParam(defaultValue="1")int pageNumber,ModelMap map,int aid,int flag){
        weuserService.updateLockFlag(aid, flag);
        return "redirect:userlist.do?pageNumber="+pageNumber;
    }

    /***
     * 查看个人资料
     * @param request
     * @param response
     * @param idcard_no		身份证号码
     * @param aid			用户ID
     * @return
     */
    @UserRightAuth(menuCode = "user_list")
    @RequestMapping("queryuser")
    public String queryAccount(HttpServletRequest request, HttpServletResponse response,ModelMap map,String idcard_no,int aid,String backurl){
        Weuser weuser = weuserService.getUserById(aid);
        if(weuser!=null){
        	if(Constants.IS_COACH1==weuser.getIs_coach()){
        		Coach c = coachService.getCoachByUserId(weuser.getId());
				weuser.setCoach(c);
			}
            map.put("a", weuser);
        }
        if(CommonUtil.NotEmpty(backurl)){
        	map.put("backurl", backurl);
        }
        return "admin/user/user_detail";
    }
    
    
    /***
     * 获得用户资金列表
     * @param pageNumber
     * @param request
     * @param map
     * @param weuser
     * @return
     */
    @RequestMapping(value = "money_list")
    public String money_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request,ModelMap map,Weuser weuser) {
        map.put("uphone", weuser.getUphone());
        map.put("username", weuser.getUsername());
        map.put("idcard_no", weuser.getIdcard_no());
        map.put("data_page", weuserService.getUserList(weuser, pageNumber, Constants.BACKGROUND_PAGESIZE));
        return "admin/money_management/money_list";
    }
    
    /***
     * 获得用户实名认证
     * @param pageNumber
     * @param request
     * @param map
     * @param weuser
     * @return
     */
    @RequestMapping(value = "real_name_authentication")
    public String real_name_authentication(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request,ModelMap map,Weuser weuser) {
        map.put("uphone", weuser.getUphone());
        map.put("username", weuser.getUsername());
        map.put("idcard_no", weuser.getIdcard_no());
        map.put("data_page", weuserService.getUserList(weuser, pageNumber, Constants.BACKGROUND_PAGESIZE));
        return "admin/authentication/real_name_list";
    }
    
    @RequestMapping(value = "toSaveuserlist")
	public String tobatchadduser(ModelMap map,HttpServletRequest request){
    	String uPhone=request.getParameter("uPhone");
    	String Phone=request.getParameter("Phone");
    	
    	if(!"".equals(uPhone)){
    		map.put("uPhone", uPhone);
    	}
    	if(!"".equals(Phone)){
    		map.put("Phone", Phone);
    	}
    	
		return "admin/user/batchuser_list";
	}
	
	/** 
     * 读取excel报表 
     */  
    @RequestMapping(value = "saveuserlist")  
    public String getReadReport(@RequestParam MultipartFile file,HttpServletResponse response) 
            throws IOException {  
        List<Weuser> list = weuserService.readReport(file.getInputStream());  
        
        try {
        	
        	for(int i=0;i<list.size();i++){
            	for(int j=0;j<list.size();j++){
            		if(j!=i){
            			if(list.get(i).getUphone().equals(list.get(j).getUphone())){
            				return "redirect:toSaveuserlist.do?Phone="+list.get(j).getUphone();
                		}
            		}
            		
            	}
            }
        	
        	for(Weuser user:list){
        		if(weuserService.getUserByPhone(user.getUphone())!=null){
        			return "redirect:toSaveuserlist.do?uPhone="+user.getUphone();
        		}
        	}
        	
    		
        	
        	
			weuserService.insertComputer(list);
			
			for(Weuser user:list){
        		//设置用户统计表
    			User_count uCount=new User_count();
    			uCount.setWeuser(user);
    			user_countService.saveuser_Count(uCount);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "redirect:userlist.do";  
  
    }
    
    /**
     * 用户操作资金
     * @param request
     * @param map
     * @return
     */
	@RequestMapping(value = "opusermoney")
	public String opusermoney(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request,ModelMap map,Weuser weuser) {
		long s = System.currentTimeMillis();		//开始计时
		logger.info("加载页面时长——————"+(System.currentTimeMillis()-s));
		map.put("data_page", weuserService.getUserList(weuser, pageNumber, Constants.BACKGROUND_PAGESIZE));
        return "admin/user/opusermoney";
	}
	
	/**
	 * 后台直接操作用户金额(充值)
	 * @param pageNumber
	 * @param userId
	 * @param amount
	 * @param request
	 * @param map
	 * @param weuser
	 * @return
	 */
	@RequestMapping(value = "saveopusermoney")
	public String saveopusermoney(int pageNumber,int userId,double amount,HttpServletRequest request,ModelMap map,Weuser weuser) {
		long s = System.currentTimeMillis();		//开始计时
		logger.info("加载页面时长——————"+(System.currentTimeMillis()-s));
		Weuser user = weuserService.getUserById(userId);
		if(user!=null){
			user.setAmount(user.getAmount()+amount);
			weuserService.mergeAndUpdateTime(user);
		}
        map.put("data_page", weuserService.getUserList(weuser, pageNumber, Constants.BACKGROUND_PAGESIZE));
        return "admin/user/opusermoney";
	}
	
	

	/**
	 * 用户等级列表数据
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param red
	 * @return
	 */
	@RequestMapping(value = "user_level.do")
	public String user_level_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,User_level us) {
		CommonUtil.printHTTP(request);
		map.put("level",us.getLevel());
		map.put("data_page",user_levelService.getPageFindeUser_level(us, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("user_all", user_levelService.getUserLevleList());
		return "admin/user/user_level_list";
	}
	
	/**
	 * 删除用户等级信息
	 */
	@RequestMapping(value = "del_user_level")
	public String del_user_level(@RequestParam(defaultValue="1")int pageNumber,int id) {
		try {
			user_levelService.deleteUser_level(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:user_level.do?pageNumber="+pageNumber;
	}
	
	/**
	 * 去添加用户等级信息页面
	 * @throws Exception 
	 */
	@RequestMapping(value = "toadd_user_level")
	public String toadd_user_level() throws Exception {
		return "admin/user/user_level_add";
	}
	
	/**
	 * 添加用户等级信息
	 * @throws Exception 
	 */
	@RequestMapping(value = "add_user_level")
	public String add_user_level(@RequestParam(defaultValue="1")int pageNumber,User_level us) throws Exception {
		user_levelService.saveUser_level(us);
		return "redirect:user_level.do?pageNumber="+pageNumber;
	}
	
	
	/**
	 * 跳转查看或者修改用户等级页面
	 * @param request
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "touser_level_edit")
	public String touser_level_edit(HttpServletRequest request, ModelMap map,int id) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", user_levelService.getUserLevelById(id));
		return "admin/user/user_level_edit";
	}
	
	/**
	 * 修改用户等级数据
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "user_level_edit")
	public String user_level_edit(HttpServletRequest request, ModelMap map,User_level us) {
		CommonUtil.printHTTP(request);
		try {
			User_level o = user_levelService.getUserLevelById(us.getId());
			us.setCreate_time(o.getCreate_time());
			user_levelService.updateUser_level(us);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:user_level.do?pageNumber=1";
	}
	
	
	/**
	 * 会员管理列表数据
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param red
	 * @return
	 */
	@RequestMapping(value = "user_vip.do")
	public String user_vip(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,User_vip o) {
		CommonUtil.printHTTP(request);
		map.put("name",o.getName());
		map.put("data_page",user_vipService.getPageFindeUser_vip(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/user/user_vip_list";
	}
	
	
	/**
	 * 去添加会员等级信息页面
	 * @throws Exception 
	 */
	@RequestMapping(value = "toadd_user_vip")
	public String toadd_user_vip() throws Exception {
		return "admin/user/user_vip_add";
	}
	
	/**
	 * 添加会员等级信息
	 * @throws Exception 
	 */
	@RequestMapping(value = "add_user_vip")
	public String add_user_vip(MultipartHttpServletRequest request,@RequestParam(defaultValue="1")int pageNumber) throws Exception {
		CommonUtil.printHTTP(request);
		User_vip o = CommonUtil.SerializableObj(request.getParameterMap(),
				User_vip.class);
		MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
		// 上传文件img1
		MultipartFile imgFile1 = mhs.getFile("img");
		if (imgFile1 != null && imgFile1.getSize() > 0) {
			String image_name = "banner" + System.currentTimeMillis()
					+ ".jpg";
			o.setImg(uploadFile(imgFile1,request,
					Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,""));
		}
		user_vipService.saveUser_vip(o);
		return "redirect:user_vip.do?pageNumber="+pageNumber;
	}
	
	
	/**
	 * 跳转查看或者修改会员等级页面
	 * @param request
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "touser_vip_edit")
	public String touser_vip_edit(HttpServletRequest request, ModelMap map,int id) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", user_vipService.getUser_vipById(id));
		return "admin/user/user_vip_edit";
	}
	
	/**
	 * 修改会员等级数据
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "user_vip_edit")
	public String user_vip_edit(MultipartHttpServletRequest request, ModelMap map) {
		CommonUtil.printHTTP(request);
		try {
			CommonUtil.printHTTP(request);
			User_vip o = CommonUtil.SerializableObj(request.getParameterMap(),
					User_vip.class);
			MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
			MultipartFile imgFile1 = mhs.getFile("vipImg");
			if(imgFile1!=null && imgFile1.getSize()>0){
				String image_name="banner"+System.currentTimeMillis()+".jpg";
				o.setImg(uploadFile(imgFile1,request,Settings.IMAGE_SAVE_FILE_NAME+"/banner/"+image_name,""));
			}else{
				o.setImg(request.getParameter("img1"));
			}
			user_vipService.updateUser_vip(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:user_vip.do?pageNumber=1";
	}
	
	
}
