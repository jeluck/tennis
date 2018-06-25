package com.project.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Business_time;
import com.project.pojo.Playground;
import com.project.pojo.Space;
import com.project.pojo.TimeMoeny;
import com.project.service.IBusiness_timeService;
import com.project.service.ICoachService;
import com.project.service.IPlaygroundService;
import com.project.service.ISpaceService;
import com.project.service.ITimeMoenyService;
import com.project.util.CommonUtil;

@Controller("adminspaceController")
@RequestMapping(value = "/pgm")	
public class SpaceController extends BaseController{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SpaceController.class); 

	@Resource
	private ISpaceService spaceService;
	@Resource
	private ICoachService coachService;
	@Resource
	private ITimeMoenyService timeMoenyService;
	@Resource
	private IBusiness_timeService business_timeService;
	@Resource
	private IPlaygroundService playgroundService;
	
	
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "space") 
	@RequestMapping(value = "space_list")
	public String playground_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Space o,int oid) {
		CommonUtil.printHTTP(request);
		String is_play=request.getParameter("is_play");
		map.put("is_play", is_play);
		map.put("oid", oid);
		map.put("data_page",spaceService.getPageFindeSpace(o, pageNumber, Constants.BACKGROUND_PAGESIZE,oid));
		return "admin/space/space_list";
	}
	
	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "toaddspace")
	public String toaddcoach(HttpServletRequest request, ModelMap map,int oid){
		CommonUtil.printHTTP(request);
		String is_play=request.getParameter("is_play");
		map.put("is_play", is_play);
		map.put("oid", oid);
		map.put("coachList", coachService.getcoachByPlayId(oid));
		
		return "admin/space/space_add";
	}
	
	/**
	 * 增加
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "add_space")
	public String add_space(HttpServletRequest request, ModelMap map,Space o) {
		CommonUtil.printHTTP(request);
		try {
//			if(o.getCoach_id().getId()==0){
//				o.setCoach_id(null);
//			}
			o.setBelongto(0);//默认为场馆的.			add by lxc	2015-12-5
			
			spaceService.saveSpace(o);
			
			//修改场馆的场地类型  
			Playground p = playgroundService.getPlaygroundById(o.getPlayground_id().getId());
			List<Space> spaceList = spaceService.getPlaygroundSpaceBy_PGid(o.getPlayground_id().getId(),0);
			String spType = "";
			for (Space s : spaceList) {
				spType += s.getSpacetype()+","+s.getIn_out()+","+s.getCourt_type()+",";
			}
			p.setSpType(spType);
			playgroundService.mergePlayground(p);
			
			List<Business_time> businessList=business_timeService.findByplaygroundId(o.getPlayground_id().getId());
				
			for(Business_time b : businessList){
				for(int i=b.getStart_time();i<=b.getEnd_time();i++){
					TimeMoeny tm=new TimeMoeny();
					tm.setPlayground_id(o.getPlayground_id().getId());
					tm.setHelp_filed(1);
					tm.setSpace(o);
					tm.setHour(i);
					tm.setPrice(o.getPrice());
					tm.setTime_type(b.getDateType());
					timeMoenyService.saveTimeMoeny(tm);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:space_list.do?oid="+o.getPlayground_id().getId();
	}
	
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "toedit_space")
	public String toedit_coach(HttpServletRequest request, ModelMap map,int oid,int playgroundId) {
		CommonUtil.printHTTP(request);
		String check = request.getParameter("check");
		map.put("oid", playgroundId);
		map.put("check", check);
		map.put("coachList", coachService.getcoachByPlayId(playgroundId));
		map.put("o",spaceService.getSpace(oid));
		
		return "admin/space/space_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "edit_space")
	public String edit_playground_manager(HttpServletRequest request, ModelMap map,Space o) {
		CommonUtil.printHTTP(request);
		try {
			o.setBelongto(0);//默认为场馆的.			add by lxc	2015-12-5
			spaceService.updateSpace(o);
			
			//修改场馆的场地类型  
			Playground p = playgroundService.getPlaygroundById(o.getPlayground_id().getId());
			List<Space> spaceList = spaceService.getPlaygroundSpaceBy_PGid(o.getPlayground_id().getId(),0);
			String spType = "";
			for (Space s : spaceList) {
				spType += s.getSpacetype()+","+s.getIn_out()+","+s.getCourt_type()+",";
			}
			p.setSpType(spType);
			playgroundService.mergePlayground(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:space_list.do?oid="+o.getPlayground_id().getId();
	}
	
	
	/**
	 * 教练场地列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "coach_space_list")
	public String coach_space_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,Space o,int oid) {
		CommonUtil.printHTTP(request);
		map.put("oid", oid);
		map.put("name", o.getName());
		map.put("data_page",spaceService.getPageFindeSpaceByCoach(o, pageNumber, Constants.BACKGROUND_PAGESIZE,oid));
		return "admin/space/coach_space_list";
	}
	
	/**
	 * 去教练增加场地页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "toaddcoachspace")
	public String coach_space_add(HttpServletRequest request, ModelMap map,int oid){
		CommonUtil.printHTTP(request);
		map.put("oid", oid);
		map.put("p", playgroundService.getAll());
		return "admin/space/coach_space_add";
	}
	
	/**
	 * 教练增加场地
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "add_space_coach")
	public String add_space_coach(HttpServletRequest request, ModelMap map) {
		CommonUtil.printHTTP(request);
		int spaceId = Integer.valueOf(request.getParameter("spaceId"));
		int oId = Integer.valueOf(request.getParameter("oid"));
		try {
			Space o = spaceService.getSpace(spaceId);
			o.setCoach_id(coachService.getcoachById(oId));
			o.setUpdate_time(CommonUtil.getTimeNow());
			o.setBelongto(1);
			spaceService.updateSpace(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:coach_space_list.do?oid="+oId;
	}
	
	
	/**
	 * 去教练场地查看页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "space")
	@RequestMapping(value = "tocoachedit_space")
	public String tocoachedit_space(HttpServletRequest request, ModelMap map,int oid,int coachId) {
		CommonUtil.printHTTP(request);
		Space o =spaceService.getSpace(oid);
		map.put("o",o);
		map.put("coachId",coachId);
		map.put("name",playgroundService.getPlaygroundById(o.getPlayground_id().getId()).getName());
		return "admin/space/coach_space_edit";
	}
	
	
	
	@RequestMapping(value = "spaceName")
    @ResponseBody
    public Object getCity(HttpServletRequest request)
    {
        String pdId = request.getParameter("pdId");
        try {
        	List<Space> list = spaceService.getPlaygroundSpaceBy_PGid(Integer.valueOf(pdId), 0);
            return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), list);
        }
        catch (NumberFormatException e)
        {
            return new BusinessResponse(Constants.OperationResult.INVALID_PARAMETER.getStatus(),Constants.OperationResult.INVALID_PARAMETER.getMsg(), "");
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BusinessResponse(Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
        }
    }
}
