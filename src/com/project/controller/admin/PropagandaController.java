package com.project.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Advertise;
import com.project.pojo.FriendlyLink;
import com.project.pojo.Manager;
import com.project.pojo.Notice;
import com.project.pojo.PlatformInfo;
import com.project.pojo.Region;
import com.project.service.IManagerService;
import com.project.service.IPropagandaService;
import com.project.service.IRegionService;
import com.project.util.CommonUtil;


@Controller("advertiseController")
@RequestMapping("/admin")
public class PropagandaController   extends BaseController{
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IPropagandaService propagandaService;
	@Resource
	private IRegionService regionService;
	@Resource
	private IManagerService managerService;
	
	//广告管理
	@UserRightAuth(menuCode="advertise_list")
	@RequestMapping(value="advertise.do",method = RequestMethod.GET,params="action=advertise_list")
	public String advertise_list(@RequestParam(defaultValue="1")int pageNumber,ModelMap map)  {
		map.put("advertisepage",propagandaService.getAdvertisePageBean(null, pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/propaganda/advertise_manager";
	}
	
	//保存广告信息
	@UserRightAuth(menuCode="advertise_list")
	@RequestMapping(value="advertise.do",method = RequestMethod.POST,params="action=save")
	public String save(MultipartHttpServletRequest request) {
        //保存
		Advertise advertise = CommonUtil.SerializableObj(request.getParameterMap(), Advertise.class);
        try {
        	MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
        	//上传文件
        	MultipartFile imgFile = mhs.getFile("imgFile");
        	if(imgFile!=null && imgFile.getSize()>0){
        		String image_name="banner"+System.currentTimeMillis()+".jpg";
        		advertise.setAd_picture_url(uploadFile(imgFile,request,Settings.IMAGE_SAVE_FILE_NAME+"/banner/"+image_name,""));
        	}
        	propagandaService.saveAdvertise(advertise);
        	return "redirect:advertise.do?action=advertise_list&pageNumber=1";
        } catch (Exception e) {
        	logger.error("添加广告信息出现错误！错误信息："+e.getMessage());
            e.printStackTrace();
        }
        return "redirect:advertise.do?action=advertise_list&pageNumber=1";
	}
	
	
	//修改广告信息
	@UserRightAuth(menuCode="advertise_list")
	@RequestMapping(value="advertise.do",method = RequestMethod.POST,params="action=update")
	public String update(MultipartHttpServletRequest request) {
		Advertise advertise =  CommonUtil.SerializableObj(request.getParameterMap(), Advertise.class);
        //保存
        try {
        	MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
        	MultipartFile imgFile = mhs.getFile("imgFile");
        	if(imgFile!=null && imgFile.getSize()>0){
        		String image_name="banner"+System.currentTimeMillis()+".jpg";
        		advertise.setAd_picture_url(uploadFile(imgFile,request,Settings.IMAGE_SAVE_FILE_NAME+"/banner/"+image_name,""));
        	}else{
        		advertise.setAd_picture_url(request.getParameter("ad_pic_url"));
        	}
        	propagandaService.updateAdvertise(advertise);
        	return "redirect:advertise.do?action=advertise_list&pageNumber=1";
        } catch (Exception e) {
        	logger.error("修改广告信息出现错误！错误信息："+e.getMessage());
            e.printStackTrace();
        }
        return "redirect:advertise.do?action=advertise_list&pageNumber=1";
	}
	
	//删除广告信息
	@UserRightAuth(menuCode="advertise_list")
	@RequestMapping(value="advertise.do",method = RequestMethod.POST,params="action=del")
	@ResponseBody
	public Object del(int ad_id) {
		try {
			propagandaService.delAdvertise(ad_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pushmsg("成功删除广告信息", true);
	}
	
	//得到广告详情信息
	@UserRightAuth(menuCode="advertise_list")
	@RequestMapping(value="advertise.do",method = RequestMethod.POST,params="action=getinfo")
	@ResponseBody
	public Object getinfo(int ad_id) {
		return propagandaService.getAdvertiseInfo(ad_id);
	}
	
	//得到网站公告列表
	@UserRightAuth(menuCode="notice_list")
	@RequestMapping(value="notice_list.do",method = RequestMethod.GET)
	public String getnotice_list(ModelMap map,@RequestParam(defaultValue="1")int pageNumber){
		PageFinder<Notice> pf = propagandaService.getNoticeList(pageNumber,Constants.BACKGROUND_PAGESIZE);
		if(pf.getDataList()!=null && pf.getDataList().size()>0){
        	Manager m = null;
        	for (Notice n : pf.getDataList()) {
				if(n.getCreater_uid()>0){
					m = managerService.getManagerById(n.getCreater_uid());
					n.setManager(m);
				}
			}
        }
		
		map.put("notice_page",pf);
		List<Region> list = regionService.getProvince();
		map.put("provincelist",list);
		return "admin/propaganda/notice_list";
	}
	
	//添加网站公告信息
	@UserRightAuth(menuCode="notice_list")
	@RequestMapping(value="notice_add.do",method = RequestMethod.POST)
	public String notice_add(HttpServletRequest request,@RequestParam(defaultValue="1")int pageNumber,Notice notice){
		notice.setCreater_uid(getManager(request).getId());
		propagandaService.saveNotice(notice);
		return "redirect:notice_list.do?pageNumber="+pageNumber;
	}
	
	//得到网站公告信息
	@ResponseBody
	@UserRightAuth(menuCode="notice_list")
	@RequestMapping(value="notice_info.do",method = RequestMethod.GET)
	public Object getNoticeInfo(ModelMap map,int id ){
		Notice notice = propagandaService.getNoticeById(id);
		notice.setProvince_id(regionService.getRegionById(notice.getCity_show_id()).getParent_id());
		map.put("r",regionService.getRegionById(notice.getCity_show_id()));
		return notice;
	}
	
	//编辑网站公告信息
	@UserRightAuth(menuCode="notice_list")
	@RequestMapping(value="notice_update.do",method = RequestMethod.POST)
	public String notice_update(HttpServletRequest request,@RequestParam(defaultValue="1")int pageNumber,Notice notice){
		propagandaService.updateNotice(notice);
		return "redirect:notice_list.do?pageNumber="+pageNumber;
	}
	
	
	//删除网站公告信息
	@UserRightAuth(menuCode="notice_list")
	@RequestMapping(value="notice_del.do",method = RequestMethod.GET)
	public String notice_del(HttpServletRequest request,@RequestParam(defaultValue="1")int pageNumber,int id){
		try {
			propagandaService.delNotice(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:notice_list.do?pageNumber="+pageNumber;
	}
	

	
	//得到友情链接列表
	@UserRightAuth(menuCode="friendlylink_list")
	@RequestMapping(value="friendlylink_list.do",method = RequestMethod.GET)
	public String friendlylink_list(ModelMap map,@RequestParam(defaultValue="1")int pageNumber){
		map.put("link_page", propagandaService.getFriendlyLinkList(pageNumber,5));
		return "admin/propaganda/friendlylink_list";
	}
	
	//添加友情链接信息
	@UserRightAuth(menuCode="friendlylink_list")
	@RequestMapping(value="friendlylink_add.do",method = RequestMethod.POST)
	public String friendlylink_add(MultipartHttpServletRequest request,@RequestParam(defaultValue="1")int pageNumber,FriendlyLink link){
		String image_name="link"+System.currentTimeMillis()+".jpg";
		link.setLink_img(uploadFile(request.getFile("img_url"), request, Settings.IMAGE_SAVE_FILE_NAME+"/list/link/"+image_name,""));//友情链接图片
		try {
			propagandaService.saveFriendlyLink(link);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:friendlylink_list.do?pageNumber="+pageNumber;
	}
	
	//删除友情链接
	@UserRightAuth(menuCode="friendlylink_list")
	@RequestMapping(value="friendlylink_del.do",method = RequestMethod.GET)
	public String friendlylink_del(int id,@RequestParam(defaultValue="1")int pageNumber){
		try {
			propagandaService.delFriendlink(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:friendlylink_list.do?pageNumber="+pageNumber;
	}
	
	//得到友情链接信息
	@ResponseBody
	@UserRightAuth(menuCode="friendlylink_list")
	@RequestMapping(value="friendlylink_info.do",method = RequestMethod.GET)
	public Object friendlylink_Info(int id){
		return propagandaService.getFriendlyLinkById(id);
	}
	
	//修改友情链接信息
	@UserRightAuth(menuCode="friendlylink_list")
	@RequestMapping(value="friendlylink_update.do",method = RequestMethod.POST)
	public String friendlylink_update(MultipartHttpServletRequest request,@RequestParam(defaultValue="1")int pageNumber,FriendlyLink link){
		String image_name="link"+System.currentTimeMillis()+".jpg";
		String pic = uploadFile(request.getFile("img_url"), request, Settings.IMAGE_SAVE_FILE_NAME+"/list/link/"+image_name,"");
		if(pic!=null){
			link.setLink_img(pic);//友情链接图片
		}else{
			link.setLink_img(request.getParameter("pic_url"));//友情链接图片
		}
		propagandaService.updateFriendlyLink(link);
		return "redirect:friendlylink_list.do?pageNumber="+pageNumber;
	}
	
	//编辑公司介绍
	@UserRightAuth(menuCode="company_info")
	@RequestMapping(value="company_info.do",method = RequestMethod.GET)
	public String company_info(ModelMap map){
		map.put("company_info", propagandaService.getPlatformInfo());
		return "admin/propaganda/company_recommend";
	}
	
	
	//编辑联系我们
	@UserRightAuth(menuCode="contact_us_info")
	@RequestMapping(value="contact_us_info.do",method = RequestMethod.GET)
	public String contact_us_info(ModelMap map){
		map.put("company_info", propagandaService.getPlatformInfo());
		return "admin/propaganda/contact_us";
	}
	
	//编辑协议
	@UserRightAuth(menuCode="agreement_info")
	@RequestMapping(value="agreement_info.do",method = RequestMethod.GET)
	public String agreement_info(ModelMap map){
		map.put("company_info", propagandaService.getPlatformInfo());
		return "admin/propaganda/agreement";
	}
	
	//编辑等级说明
	@RequestMapping(value="level_explain.do",method = RequestMethod.GET)
	public String level_explain(ModelMap map){
		map.put("company_info", propagandaService.getPlatformInfo());
		return "admin/propaganda/level_explain";
	}
		
	//保存公司介绍
	@RequestMapping(value="company_submit.do",method = RequestMethod.POST)
	public String company_submit(ModelMap map,PlatformInfo platformInfo,@RequestParam(defaultValue="company_info")String fieldName){
		String page = "";
		switch (fieldName) {
			case "company_info":
				propagandaService.updatePlatformInfoForCompany_info(platformInfo);
				page = "redirect:company_info.do";
				break;
			case "contact_us":
				propagandaService.updatePlatformInfoForContact_us(platformInfo);
				page = "redirect:contact_us_info.do";
				break;
			case "go_licai":
				page = "redirect:to_licai.do";
				break;
			case "go_jiekuan":
				page = "redirect:to_jiekuan.do";
				break;
			case "anquan":
				page = "redirect:to_anquan.do";
				break;
			case "agreement":
				propagandaService.updatePlatformInfoAgreement(platformInfo);
				page = "redirect:agreement_info.do";
				break;
			case "level_explain":
				propagandaService.updatePlatformInfoLevel_explain(platformInfo);
				page = "redirect:level_explain.do";
				break;
				
		}
		return page;
	}
}
