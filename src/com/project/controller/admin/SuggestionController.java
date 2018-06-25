package com.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.auth.NoLoginAuth;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.pojo.Suggestion;
import com.project.service.ISuggestionService;

@Controller("adminsuggestionWebController")
@RequestMapping(value="/admin")
public class SuggestionController extends BaseController{
	
	@Resource
	private ISuggestionService suggestionService;
	
	/***
	 * 建议列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @return
	 */
	@NoLoginAuth
	@RequestMapping(value = "suggestionlist", method = RequestMethod.GET)
	public String suggestion_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request,ModelMap map) {
		map.put("data_page", suggestionService.getSuggestionList(pageNumber, Constants.BACKGROUND_PAGESIZE));
		return "admin/suggestion/suggestion_list";
	}
	
	/****
	 * 查看建议
	 * @param request
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "readSuggestion")
	public String readSuggestion(HttpServletRequest request,ModelMap map,int id) {
		Suggestion s = suggestionService.getSuggestionById(id);
		s.setReadstatus(Constants.MessageReadStatus.HAVE_READ.getStatus());
		suggestionService.updateSuggestionByid(s);
		map.put("suggestion", suggestionService.getSuggestionById(id));
		return "admin/suggestion/suggestion_detail";
	}
	
	/***
	 * 根据ID删除建议
	 * @param request
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deletesuggestion")
	public String deletesuggestion(HttpServletRequest request,ModelMap map,int id) {
		suggestionService.deleteSuggestionById(id);
		return "redirect:suggestionlist.do";
	}
}