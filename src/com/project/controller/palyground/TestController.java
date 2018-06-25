package com.project.controller.palyground;


import java.util.List;

import com.project.controller.BaseController;
import com.project.pojo.*;
import com.project.service.ICoachService;
import com.project.service.ICommentService;
import com.project.service.IOrderComponent;
import com.project.service.IOrderInfoService;
import com.project.service.IPlaygroundService;
import com.project.service.IRegionService;
import com.project.service.ISpaceManagerService;
import com.project.service.ISpaceService;
import com.project.service.ITennis_circlesService;
import com.project.service.IWeuserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("testcontroller")
@RequestMapping(value="/play")
public class TestController extends BaseController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TestController.class);

    @Resource
    private IWeuserService weuserService;
    @Resource
    private ICommentService commonAddressService;
    @Resource
    private ITennis_circlesService tennis_circlesService;
    @Resource
    private ISpaceManagerService produtService;
    @Resource
    private IOrderInfoService orderInfoService;
    @Resource
    private ICoachService orderMainService;
    @Resource
    private IRegionService regionService;
    
    @Resource
    private IPlaygroundService playgroundService;
	@Resource
	private ISpaceService spaceService;
	@Resource
	private IOrderComponent orderComponent;
    
    /**
     * test 
     * @param request
     * @param map
     * @param pid
     * @return
     */
    @RequestMapping("test2")
    public String test2(HttpServletRequest request, ModelMap map,String pid){
    	List<Weuser> w = weuserService.getAllUser();
    	map.put("w", w);
    	List<Playground> p = playgroundService.getAll();
    	map.put("p", p);
    	int playground_id = 2; 
    	Playground pe = playgroundService.getPlaygroundById(playground_id);
    	map.put("pe", pe);
    	List<Space> s = spaceService.getPlaygroundSpaceBy_PGid(playground_id,0);
    	map.put("s", s);
    	return "test/play";
    }
}
