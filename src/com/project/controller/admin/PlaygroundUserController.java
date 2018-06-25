package com.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.common.Constants;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundUser;
import com.project.pojo.Weuser;
import com.project.service.IPlaygroundUserService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;

@Controller("adminplayground_memberWebController")
@RequestMapping(value = "/pgm")
public class PlaygroundUserController {
	
	@Resource
	private IPlaygroundUserService playgroundUserService;
	@Resource
	private IWeuserService weuserService;
	
	@RequestMapping(value = "memberList")
    public String account_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request,ModelMap map,Integer member,Weuser user) {
		
		PlaygroundUser userMember=new PlaygroundUser();
		userMember.setPlayground(new Playground());
		userMember.getPlayground().setId(member);
		
		if(user!=null){
			userMember.setUser(user);
		}
		
		map.put("member", member);
        map.put("data_page", playgroundUserService.getPlaygroundUserList(userMember, pageNumber, Constants.BACKGROUND_PAGESIZE));
        return "admin/playground/pg_member_list";
    }
	
}
