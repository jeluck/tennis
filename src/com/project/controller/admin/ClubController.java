package com.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.common.Constants;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.pojo.Club;
import com.project.pojo.ClubImg;
import com.project.pojo.ClubIndex;
import com.project.pojo.Gallery;
import com.project.service.IClubImgService;
import com.project.service.IClubIndexService;
import com.project.service.IClubService;
import com.project.service.IGalleryService;
import com.project.util.CommonUtil;

@Controller("clubController")
@RequestMapping(value="/admin")
public class ClubController extends BaseController {
	
	@Resource
	private IClubIndexService clubIndexService;
	@Resource
	private IClubService clubService;
	@Resource
	private IClubImgService clubImgService;
	@Resource
	private IGalleryService galleryService;
 	
	/**
	 * 跳转俱乐部首页
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "club_index")
	public String club_index(HttpServletRequest request, ModelMap map,ClubIndex o){
		map.put("o", clubIndexService.getClubIndex());
		return "admin/club/club_index";
	}
	
	
	/**
	 * 添加或者修改俱乐部首页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addOrUpdateClub_index")
	public String addOrUpdateClub_index(HttpServletRequest request) throws Exception{
		ClubIndex o = CommonUtil.SerializableObj(request.getParameterMap(),
				ClubIndex.class);
		MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
		// 上传文件img1
		MultipartFile imgFile1 = mhs.getFile("img");
		if (imgFile1 != null && imgFile1.getSize() > 0) {
			String image_name = "banner" + System.currentTimeMillis()
					+ ".jpg";
			o.setImg(uploadFile(imgFile1,request,
					Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,""));
		}else{
			o.setImg(request.getParameter("imgSrc"));
		}
		clubIndexService.updateClubIndex(o);
		return "redirect:club_index.do";
	}
	
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "club_list")
	public String playground_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Club o) {
		CommonUtil.printHTTP(request);
		map.put("data_page",clubService.getPageFindeClub(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("company_name", o.getName());
		return "admin/club/club_list";
	}
	
	/**
	 * 去增加俱乐部页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "toadd_club")
	public String toadd_club(HttpServletRequest request, ModelMap map){
		return "admin/club/club_add";
	}
	
	/**
	 * 增加俱乐部
	 * @param request
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add_club")
	public String addClub(HttpServletRequest request,Club o) throws Exception{
		clubService.addClub(o);
		return "redirect:club_list.do";
	}
	
	/**
	 * 修改俱乐部
	 * @param request
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateClub")
	public String updateClub(HttpServletRequest request,Club o) throws Exception{
		clubService.mergeClub(o);
		return "redirect:club_list.do";
	}
	
	/**
	 * 修改或者查看俱乐部页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "edit_club")
	public String edit_club(HttpServletRequest request, ModelMap map,int oid){
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", clubService.getclubById(oid));
		return "admin/club/club_edit";
	}
	
	/**
	 * 俱乐部图片列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "clubImgList")
	public String clubImgList(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,ClubImg o,int oid) {
		CommonUtil.printHTTP(request);
		o.setClubId(oid);
		map.put("data_page",clubImgService.getPageFindeClubImg(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("name",clubService.getclubById(oid).getName());
		map.put("clubId", oid);
		return "admin/club/club_img_list";
	}
	
	/**
	 * 修改或者添加俱乐部图片页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "toaddOrUpdateClubImg")
	public String toaddOrUpdateClubImg(HttpServletRequest request, ModelMap map,int oid,int clubId){
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		if(oid>0){
			map.put("o", clubImgService.getClubImgById(oid));
		}
		map.put("clubId", clubId);
		return "admin/club/club_img_addOrUpdate";
	}
	
	/**
	 * 添加或者修改俱乐部图片
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addOrUpdate_clubImg")
	public String addOrUpdate_clubImg(HttpServletRequest request) throws Exception{
		ClubImg o = CommonUtil.SerializableObj(request.getParameterMap(),
				ClubImg.class);
		MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
		// 上传文件img1
		MultipartFile imgFile1 = mhs.getFile("img");
		if (imgFile1 != null && imgFile1.getSize() > 0) {
			String image_name = "banner" + System.currentTimeMillis()
					+ ".jpg";
			o.setImg(uploadFile(imgFile1,request,
					Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,""));
		}else{
			o.setImg(request.getParameter("imgSrc"));
		}
		String oid = request.getParameter("clubId");
		o.setClubId(Integer.valueOf(oid));
		clubImgService.mergeClubImg(o);
		return "redirect:clubImgList.do?pageNumber=1&oid="+Integer.valueOf(oid)+"";
	}
	
	/**
	 * 删除俱乐部图片
	 * @param request
	 * @param id
	 * @param clubId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delClubImg")
	public String delClubImg(HttpServletRequest request,int id,int clubId) throws Exception{
		clubImgService.deleteClubImgById(id);
		return "redirect:clubImgList.do?pageNumber=1&oid="+clubId+"";
	}
	
	
	/**
	 * 俱乐部活动列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "clubGalleryList")
	public String clubGalleryList(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Gallery o,int oid) {
		CommonUtil.printHTTP(request);
		o.setType(1);
		o.setAcId(oid);
		map.put("data_page",galleryService.getPageFindeClubGallery(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
		map.put("name",clubService.getclubById(oid).getName());
		map.put("clubId", oid);
		return "admin/club/club_gallery_list";
	}
	
	/**
	 * 修改或者添加俱乐部活动集锦页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "toaddOrUpdateClubGallery")
	public String toaddOrUpdateClubGallery(HttpServletRequest request, ModelMap map,int oid,int clubId){
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("check", check);
		if(oid>0){
			map.put("o", galleryService.getGalleryById(oid));
		}
		map.put("clubId", clubId);
		return "admin/club/club_gallery_addOrUpdate";
	}
	
	/**
	 * 添加或者修改俱乐部活动集锦
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addOrUpdate_clubGallery")
	public String addOrUpdate_clubGallery(HttpServletRequest request) throws Exception{
		Gallery o = CommonUtil.SerializableObj(request.getParameterMap(),
				Gallery.class);
		o.setType(1);
		MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
		// 上传文件img1
		MultipartFile imgFile1 = mhs.getFile("img");
		if (imgFile1 != null && imgFile1.getSize() > 0) {
			String image_name = "banner" + System.currentTimeMillis()
					+ ".jpg";
			o.setImg(uploadFile(imgFile1,request,
					Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,""));
		}else{
			o.setImg(request.getParameter("imgSrc"));
		}
		String oid = request.getParameter("clubId");
		o.setAcId(Integer.valueOf(oid));
		galleryService.mergeGallery(o);
		return "redirect:clubGalleryList.do?pageNumber=1&oid="+Integer.valueOf(oid)+"";
	}
	
	/**
	 * 删除俱乐部活动集锦
	 * @param request
	 * @param id
	 * @param clubId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delClubGallery")
	public String delClubGallery(HttpServletRequest request,int id,int clubId) throws Exception{
		galleryService.deleteGalleryById(id);
		return "redirect:clubGalleryList.do?pageNumber=1&oid="+clubId+"";
	}
	
	
}
