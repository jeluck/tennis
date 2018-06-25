package com.project.controller.wechat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.pojo.Club;
import com.project.pojo.ClubImg;
import com.project.pojo.ClubIndex;
import com.project.service.IClubImgService;
import com.project.service.IClubIndexService;
import com.project.service.IClubService;
import com.project.service.IGalleryService;


@Controller("clubWebController")
@RequestMapping(value="/")
public class ClubController {
	
	@Resource
	private IClubIndexService clubIndexService;
	@Resource
	private IClubImgService clubImgService;
	@Resource
	private IClubService clubService;
	@Resource
	private IGalleryService galleryService;

	/**
	 * 俱乐部首页
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "clubIndex")
	public String getClubIndex(HttpServletRequest request,ModelMap map){
		ClubIndex index = clubIndexService.getClubIndex();
		List<Club> list =  clubService.getAllClub();
		List<Club> clubList = new ArrayList<Club>();
		for (Club c : list) {
			ClubImg ci = clubImgService.getClubImgByClubId(c.getId());
			c.setImg(ci.getImg());
			clubList.add(c);
		}
		map.put("o", index);
		map.put("list",clubList);
		return "/weixin/club_index";
	}
	
	/**
	 * 俱乐部
	 * @param request
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getClub")
	public String getClub(HttpServletRequest request,ModelMap map,int id){
		map.put("o", clubService.getclubById(id));
		map.put("clubImgList", clubImgService.getClubImgsByClubId(id));
		map.put("galleryImgList", galleryService.getGalleryByCidAndType(1, id));
		return "/weixin/club";
	}
}
