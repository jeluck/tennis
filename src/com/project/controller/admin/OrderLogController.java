package com.project.controller.admin;


import java.util.Map;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.controller.BaseController;
import com.project.pojo.*;
import com.project.service.IOrderComponent;
import com.project.util.OrderUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("adminOrderLogWebController")
@RequestMapping(value="/pgm")
public class OrderLogController extends BaseController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrderLogController.class);

    @Resource
    private IOrderComponent orderComponent;
    
    @UserRightAuth(menuCode = "order_manager")
    @RequestMapping(value = "orderloglist")
    public String orderloglist(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,OrderLog o)
    {
    	map.put("data_page",orderComponent.getPageFindeOrderLog(o, pageNumber, Constants.BACKGROUND_PAGESIZE));
        Map<String,String> orderLogTypeMap = OrderUtils.getOrderLogType();
        map.addAttribute("orderLogTypeMap", orderLogTypeMap);
    	return "admin/order/orderloglist";
    }
     
}
