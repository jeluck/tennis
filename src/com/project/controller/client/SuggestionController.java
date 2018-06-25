package com.project.controller.client;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.Constants.OperationResult;
import com.project.controller.BaseController;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Manager;
import com.project.pojo.Suggestion;
import com.project.pojo.Weuser;
import com.project.service.ISuggestionService;

@Controller("suggestionWebController")
@RequestMapping(value="")
public class SuggestionController extends BaseController{
	
	@Resource
	private ISuggestionService suggestionService;
	
    /***
     * 用户端跳到意见反馈
     * @param map
     * @return
     */
    @RequestMapping(value = "tofeedback_user")
    public String tofeedback_user(ModelMap map,HttpServletRequest request){
    	Weuser w = getUser(request);
    	if(w==null){
			return "redirect:touserlogin.do";
		}
    	map.put("w", w);
    	map.put("phone", w.getUphone());
    	return "/mobile/feedback";
    }
    
    /***
     * 商家端跳到意见反馈
     * @param map
     * @return
     */
    @RequestMapping(value = "tofeedback_company")
    public String tofeedback_company(ModelMap map,HttpServletRequest request){
    	Manager m = getManager(request);
    	if(m==null){
			return "redirect:agenttologin.do";
		}
    	map.put("m", m);
    	map.put("phone", m.getMobile());
    	return "/mobile/feedback";
    }
	
	/**
	 * 新增suggestion
	 * @param request
	 * @param map
	 * @param suggestion
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addsuggestion")
	public Object addsuggestion(HttpServletRequest request,ModelMap map,Suggestion suggestion) {
		if(suggestionService.saveSuggestion(suggestion)!=null){
			return new BusinessResponse(OperationResult.SUCCESS.getStatus(),"成功","");
		}
		return new BusinessResponse(OperationResult.UNKNOWN_MISTAKE.getStatus(),"失败","");
	}
	
}

