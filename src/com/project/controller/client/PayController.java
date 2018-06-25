package com.project.controller.client;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.rmi.runtime.Log;

import com.project.common.Constants;
import com.project.common.Constants.OperationResult;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.pojo.BusinessResponse;
import com.project.pojo.OrderMain;
import com.project.pojo.PayInfo_thirdPay_orderNo;
import com.project.pojo.RechargeEvents;
import com.project.pojo.SystemConfig;
import com.project.pojo.Trade_recode;
import com.project.pojo.User_vip;
import com.project.pojo.Weuser;
import com.project.service.ICoachService;
import com.project.service.IOrderComponent;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IPayInfo_thirdPay_orderNoService;
import com.project.service.IPlaygroundService;
import com.project.service.IRechargeEventsService;
import com.project.service.ISystemConfigService;
import com.project.service.ITrade_recodeService;
import com.project.service.IUser_vipService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;

/**
 * 充值提现控制器
 * @author daybreak
 *
 */
@Controller("payController")
@RequestMapping("/")
public class PayController extends BaseController {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PayController.class);
	
	@Resource
	private IOrderComponent orderComponent;
	@Resource
	private ICoachService coachService;
	@Resource
	private IOrderInfoService orderInfoService;
    @Resource
    private IPlaygroundService orderDetail4subService;
    @Resource
    private ISystemConfigService systemConfigService;
    @Resource
    private IWeuserService weuserService;
    @Resource
    private IPayInfo_thirdPay_orderNoService payInfo_thirdPay_orderNoService; 
    @Resource
    private IOrderMainService orderMainService;
	@Resource
	private IOrderMainService mainOrderService;
	@Resource
	private ITrade_recodeService trade_recodeService;
	@Resource
	private IRechargeEventsService rechargeEventsService; 
	@Resource
	private IUser_vipService user_vipService;
	//=====================================================下面为订单支付,调用的方法==================================
    
    
//	 /**
//     * 跳到选择支付方式页
//     * @param request
//     * @param map
//     * @param orderMainNo
//     * @return
//     */
//    @RequestMapping("gotoOrderPayType")
//    public String gotoOrderPayType(HttpServletRequest request,ModelMap map,String orderMainNo){
//    	Weuser w = getUser(request);
//    	if(w==null){
//			return "redirect:touserlogin.do";
//		}
//    	return "redirect:orders.do";
//    }

    /***
     * 获得订单支付订单号
     * @param request
     * @param map
     * @param orderMainNo
     * @param pay_type
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping("getPayOrderNo")
    public Object getPayOrderNo(HttpServletRequest request,String orderMainNo,int pay_type,String playgroundName) throws Exception{
    	System.err.println("调用方法getPayOrderNo获得订单支付订单号");
//    	Weuser w = getUser(request);
//    	if(w==null){s
//    		return new BusinessResponse(OperationResult.NOT_LOGIN.getStatus(), OperationResult.NOT_LOGIN.getMsg(),"");
//		}
    	OrderMain order = orderMainService.getOrderMainByNO(orderMainNo);
    	if(order != null){
    		if(order.getPayStatus() == Constants.OrderStatus.PAY_PAID.getStatus()
    				|| order.getPayStatus() ==  Constants.OrderStatus.EXECUTION_ING.getStatus()){	//如果订单状态为 已支付或执行中返回订单已支付,请勿重复支付
    			return new BusinessResponse(OperationResult.ORDER_PAID.getStatus(), OperationResult.ORDER_PAID.getMsg(),"");
    		}else if(order.getPayStatus() == Constants.OrderStatus.DELETE.getStatus()){ //如果订单状态为 作废返回订单已作废
    			return new BusinessResponse(OperationResult.DELETE.getStatus(), OperationResult.DELETE.getMsg(),"");
    		}
    	}else{ //如果订单为空返回未知错误
    		return new BusinessResponse(OperationResult.ORDER_PAID_OR_NO_NUll.getStatus(), OperationResult.ORDER_PAID_OR_NO_NUll.getMsg(),"");
    	}
    	String payOrderNo = orderComponent.savePayInfo_thirdPay_orderNo(orderMainNo, order.getTotalAmount(), pay_type);
    	Map<String,Object> map = new HashMap<String, Object>(); 
    	if(CommonUtil.NotEmpty(orderMainNo)){
			map.put("subject", playgroundName);
			map.put("body", Settings.BODY_PAY_ORDER);
			map.put("price", order.getTotalAmount());
//			map.put("price", 0.01);
			map.put("orderno", payOrderNo);
			
			if(Constants.PayType.APLIYPAY.getStatus() == pay_type){
				map.put("notifyurl", "apliyPay.do");
			}else if(Constants.PayType.WECHATPAY.getStatus() == pay_type){
				map.put("notifyurl", Settings.NOTIFYURL_WECHAT_PAY_ORDER);
			}
			
    	}
    	return new BusinessResponse(OperationResult.SUCCESS.getStatus(), OperationResult.SUCCESS.getMsg(),map);
    }
    
    
    /***
     * 帐号充值
     * @param request
     * @param map
     * @param orderMainNo
     * @param pay_type
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping("accountRecharge")
    public Object accountRecharge(HttpServletRequest request,int pay_type,double money,int userId,int recharId) throws Exception{
    	Weuser w  = weuserService.getUserById(userId);
		if(w==null){
    		return new BusinessResponse(OperationResult.NOT_LOGIN.getStatus(), OperationResult.NOT_LOGIN.getMsg(),"");
		}
		
		//保存支付信息
    	String payOrderNo = orderComponent.savePayInfo_thirdPay_orderNo("", money, pay_type);
    	
    	//保存交易记录
    	Trade_recode t = new Trade_recode();
    	t.setWeuser(w);
    	t.setTradetype(1);
    	if(recharId > 0){	//当选择的是充值活动时
    		t.setRecharId(recharId);
    		RechargeEvents rechargeEvents = rechargeEventsService.getRechargeEventsById(recharId);
    		t.setMny_amount(rechargeEvents.getRecharge_money());
    		money = rechargeEvents.getRecharge_money();
    	}else{
    		t.setMny_amount(money);
    	}
    	t.setBusiness_type(Constants.TradeRecodeBusinessType.RECHARGE.getStatus());
    	t.setOrderMainNo(payOrderNo); 
    	t.setFlag(Constants.OPERATE_FAIL0);
    	t.setPay_type(pay_type);
    	trade_recodeService.saveTrade_recode(t);
    	Map<String,Object> map = new HashMap<String, Object>(); 
			map.put("subject", Settings.BODY_RECHARGE);
			map.put("body", Settings.BODY_RECHARGE);
			map.put("price", money); 
			map.put("orderno", payOrderNo);
			if(Constants.PayType.APLIYPAY.getStatus() == pay_type){
				map.put("notifyurl", "zhifubaCharPay.do");
			}else if(Constants.PayType.WECHATPAY.getStatus() == pay_type){
				map.put("notifyurl", Settings.NOTIFYURL_WECHAT);
			}
			
    	return new BusinessResponse(OperationResult.SUCCESS.getStatus(), OperationResult.SUCCESS.getMsg(),map);
    }
    
    
	/**
	 * 充值时手机端异步调用
	 * (2015-12-28方法有用到)
	 * 微信支付后的处理(异步调用)
	 * @param out_trade_no		支付订单号
	 * @param price				金额
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(Settings.NOTIFYURL_WECHAT)
	public String wechatCharPay(HttpServletRequest request,String out_trade_no,String price,ModelMap map,String trade_status) throws Exception{
		CommonUtil.printHTTP(request);
		System.err.println("微信异步调用----支付后访问....支付订单号:"+out_trade_no+"  ,price:"+price);
		if("TRADE_SUCCESS".equals(trade_status)){
			Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(out_trade_no);
			if(t.getFlag()==Constants.OPERATE_FAIL0){ //状态为失败时再进行用户充值的操作
				Weuser w  = weuserService.getUserById(t.getWeuser().getId());
				if(t.getRecharId()>0){ //选择充值活动时
					RechargeEvents rechargeEvents = rechargeEventsService.getRechargeEventsById(t.getRecharId());
					w.setAmount(w.getAmount()+t.getMny_amount()+rechargeEvents.getGet_money());
				}else{
					w.setAmount(w.getAmount()+t.getMny_amount());
				}
				weuserService.mergeAndUpdateTime(w);
				t.setFlag(Constants.OPERATE_SUCCESS);
				trade_recodeService.updateTrade_recode(t);
				map.put("result", "success");	
			}else{
				map.put("result", "fail");		
			}
			return "phone/user/pay";
		}else{
			return "phone/user/fail";
		}
	}
	
	
	/**
	 * 充值时手机端异步调用
	 * (2015-12-28方法有用到)
	 * 支付宝支付后的处理(异步调用)
	 * @param out_trade_no		支付订单号(支付宝的参数)
	 * @param price				金额
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("zhifubaCharPay")
	public String zhifubaCharPay(HttpServletRequest request,String out_trade_no,String price,ModelMap map,String trade_status) throws Exception{
		CommonUtil.printHTTP(request);
		System.err.println("支付宝异步调用----支付后访问....支付订单号:"+out_trade_no+"  ,price:"+price);
		if("TRADE_SUCCESS".equals(trade_status)){
			Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(out_trade_no);
			if(t.getFlag()==Constants.OPERATE_FAIL0){ //状态为失败时再进行用户充值的操作
				Weuser w  = weuserService.getUserById(t.getWeuser().getId());
				if(t.getRecharId()>0){ //选择充值活动时
					RechargeEvents rechargeEvents = rechargeEventsService.getRechargeEventsById(t.getRecharId());
					w.setAmount(w.getAmount()+t.getMny_amount()+rechargeEvents.getGet_money());
				}else{
					w.setAmount(w.getAmount()+t.getMny_amount());
				}
				weuserService.mergeAndUpdateTime(w);
				t.setFlag(Constants.OPERATE_SUCCESS);
				trade_recodeService.updateTrade_recode(t);
				map.put("result", "success");	
			}else{
				map.put("result", "fail");		
			}
			return "phone/user/pay";
		}else{
			return "phone/user/fail";
		}
	}
    
	
//    /***
//     * 根据用户选择的支付方式,展示支付二维码
//     * @param request
//     * @param orderMainNo
//     * @param map
//     * @param paytype
//     * @return
//     */
//    @RequestMapping("orderpaytype_code")
//    public String orderpaytype_code(HttpServletRequest request,String orderMainNo,ModelMap map,String paytype){
//    	Weuser w = getUser(request);
//    	if(w==null){
//			return "redirect:touserlogin.do";
//		}
//    	if(CommonUtil.NotEmpty(orderMainNo)){
////    		if(orderMain.getPayStatus()==OrderPayStatus.PAY_STAY_PAID.getStatus()){
////    			
////    			map.put("paytype", paytype);
////    			return "/mobile/user/orderpaytype_code";
////    		}
//    	}
//    	return "redirect:orders.do";
//    }
	
	/**
	 * 支付预定订单时调用
	 * 微信支付后的处理(异步调用)
	 * @param out_trade_no		支付订单号
	 * @param price				金额
	 * @return
	 */
	@RequestMapping(Settings.NOTIFYURL_WECHAT_PAY_ORDER)
	public String wechatPay(String out_trade_no,String price,ModelMap map){
		System.err.println("微信异步调用----支付返回了....支付订单号:"+out_trade_no+"  ,price:"+price);
		if(CommonUtil.NotEmpty(out_trade_no)){
			paid_handle(out_trade_no, price, Constants.PayType.WECHATPAY.getStatus());
			map.put("result", "success");	
		}else{
			map.put("result", "fail");			//
		}
		return "/mobile/user/result";
	}
	
	
	/**
	 * 支付预定订单时调用
	 * 支付宝支付后的处理(异步调用)
	 * @param out_trade_no		支付订单号(支付宝的参数)
	 * @param price				金额
	 * @return
	 */
	@RequestMapping(Settings.NOTIFYURL_APLIY_PAY_ORDER)
	public String apliyPay(String out_trade_no,String price,ModelMap map){
		System.err.println("支付宝异步调用----支付后访问....支付订单号:"+out_trade_no+"  ,price:"+price);
		if(CommonUtil.NotEmpty(out_trade_no)){
			paid_handle(out_trade_no, price, Constants.PayType.APLIYPAY.getStatus());
			map.put("result", "success");	
		}else{
			map.put("result", "fail");			//支付宝
		}
		return "/mobile/user/result";
	}
	
	/**
	 * 支付完成后的处理
	 * @param payOrderNo		支付订单号
	 * @param price				金额
	 * @param PayType			支付方式:	1微信,2支付宝
	 */
	public synchronized void paid_handle(String payOrderNo,String price,int paytype){
		logger.info("支付完成后的处理 payOrderNo:" + payOrderNo);
		try {
			PayInfo_thirdPay_orderNo o = orderComponent.getPayInfo_thirdPay_orderNo(payOrderNo);
			
			if(o!=null){
				OrderMain om = mainOrderService.getOrderMainByNO(o.getOrderMainNo());
				
				Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(om.getOrderMainNo());
				
				if(t==null){
					if(o.getPay_type() == Constants.PayType.BALANCE.getStatus()){  //余额支付
						if(weuserService.getUserById(om.getWeuser().getId()).getAmount() < om.getTotalAmount()){
							System.err.println("余额不足！"+ payOrderNo);
							return; 
						}
					}
					if(om.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
						mainOrderService.pay(om.getId(), om.getWeuser().getId(),0,Integer.valueOf(om.getActiveID()),o.getPay_type());
					}else if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
						mainOrderService.pay(om.getId(), om.getWeuser().getId(),Integer.valueOf(om.getActiveID()),0,o.getPay_type());
					}
				}else{
					logger.info("已处理订单！");
				}
			}else{
				logger.info("支付订单号为空或者不正确！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 支付后手机端访问的页面
//	 * @param payOrderNo
//	 * @param state				0为成功,	其他为失败
//	 * @param payOrderNo		支付订单号
//	 * @param price				金额
//	 * @param paytype			支付方式:	1微信,2支付宝
//	 * @param buy_vip_order		购买 vip，或者商品
//	 * @return
//	 */
//	@RequestMapping("paid_viewpage")
//	public String paid_viewpage(HttpServletRequest request,String state,String payOrderNo,String price,String paytype,String buy_vip_order,ModelMap map){
//		System.err.println("手机端----支付后访问...payOrderNo:"+payOrderNo+",state:"+state+",price:"+price+",paytype:"+paytype+", buy_vip_order:"+buy_vip_order);
//		if(CommonUtil.NotEmpty(state)){
//			if("0".equals(state)){
//				if(CommonUtil.NotEmpty(buy_vip_order) && !"(null)".equals(buy_vip_order)){
//					map.put("buy_vip_order", buy_vip_order);
//					Weuser w = getUser(request);
//					w = weuserService.getUserById(w.getId());
//					setWeuserSession(request, w);
//				}else{
//					paid_handle(payOrderNo, price, Integer.parseInt(paytype));
//				}
//				return "/mobile/user/pay_success";
//			}
//		}
//		return "/mobile/user/pay_fail";
//		
//	}
	
	/**
	 * 充值或入会完成跳转（同步）
	 * 现订单支付后也完成跳转(同步) edit by lxc 2015-12-28
	 * @param request
	 * @param map
	 * @param resultStatus			支付状态,9000为成功,其他为失败
	 * @param out_trade_no			支付订单号
	 * @param price					支付价格
	 * @param notifyurl				异步调用URL,根据异步调用URL判断当前的操作是 充值 还是支付
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "rechargeCompletion")
	public synchronized String rechargeCompletion(HttpServletRequest request, ModelMap map,String resultStatus,String out_trade_no,String price,String notifyurl) throws Exception{
		System.err.println("同步调用----支付后访问....支付订单号:"+out_trade_no+"  ,price:"+price+"   ,notifyurl:"+notifyurl);
		CommonUtil.printHTTP(request);
		PayInfo_thirdPay_orderNo o = null;
		int payType =-1;
		if(Settings.NOTIFYURL_APLIY_PAY_ORDER.equals(notifyurl)){
			o = orderComponent.getPayInfo_thirdPay_orderNo(out_trade_no);
			payType = o.getPay_type();
		}else if(Settings.NOTIFYURL_WECHAT_PAY_ORDER.equals(notifyurl)){
			o = orderComponent.getPayInfo_thirdPay_orderNo(out_trade_no);
			payType = o.getPay_type();
		}else{
			Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(out_trade_no);
			try {
				payType = t.getPay_type();	
			} catch (Exception e) {
				logger.error("out_trade_no: "+out_trade_no+"不存在");
				resultStatus = "errer";//支付完成后订单号不知道什么原因出错返回为（空）所以把resultStatus改成错误不执行下面的操作
			}
		}
		System.err.println("支付方式为:"+payType+"-----0.余额,1.微信,2.支付宝 ,  ,");
		if("9000".equals(resultStatus)){
			if(Settings.NOTIFYURL_APLIY_PAY_ORDER.equals(notifyurl)){
				paid_handle(out_trade_no, price, o.getPay_type());
			}else if(Settings.NOTIFYURL_WECHAT_PAY_ORDER.equals(notifyurl)){
				paid_handle(out_trade_no, price, o.getPay_type());
			}else if(Settings.MEMBERSHIP_ALIPAY.equals(notifyurl) || Settings.MEMBERSHIP_WECHAT.equals(notifyurl)) {
				Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(out_trade_no);
				if(t.getFlag()==Constants.OPERATE_FAIL0){ //状态为失败时再进行用户充值的操作
					Weuser w  = weuserService.getUserById(t.getWeuser().getId());		
					User_vip vip = user_vipService.getUser_vipById(t.getRecharId());
					w.setVip(vip);
					SystemConfig s = systemConfigService.getSystemConfigBykey("user_vip");
					int vipId = Integer.valueOf(s.getValue());
					vipId = vipId + 1;
					w.setVip_id(vipId);
					weuserService.mergeAndUpdateTime(w);
					s.setValue(""+vipId);
					systemConfigService.updateSystemConfig(s);
					t.setFlag(Constants.OPERATE_SUCCESS);
					trade_recodeService.updateTrade_recode(t);
					map.put("result", "success");	
				}else{
					map.put("result", "fail");		
				}
			}else{
				Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(out_trade_no);
				payType = t.getPay_type();
				if(t.getFlag()==Constants.OPERATE_FAIL0){ //状态为失败时再进行用户充值的操作
					Weuser w  = weuserService.getUserById(t.getWeuser().getId());
					if(t.getRecharId()>0){ //选择充值活动时
						RechargeEvents rechargeEvents = rechargeEventsService.getRechargeEventsById(t.getRecharId());
						w.setAmount(w.getAmount()+t.getMny_amount()+rechargeEvents.getGet_money());
					}else{
						w.setAmount(w.getAmount()+t.getMny_amount());
					}
					weuserService.mergeAndUpdateTime(w);
					t.setFlag(Constants.OPERATE_SUCCESS);
					trade_recodeService.updateTrade_recode(t);
					map.put("result", "success");	
				}else{
					map.put("result", "fail");		
				}
			}
			if(payType == 0){
				map.put("payType", 2);   //1.表示支付宝 2.余额 3.微信
			}
			if(payType == 1){
				map.put("payType", 3);   //1.表示支付宝 2.余额 3.微信
			}
			if(payType == 2){
				map.put("payType", 1);   //1.表示支付宝 2.余额 3.微信
			}
			map.put("flag", 1); 	 //1.成功 2.失败 3.取消
			return "phone/user/pay";
		}else{
			if(payType == 0){
				map.put("payType", 2);   //1.表示支付宝 2.余额 3.微信
			}
			if(payType == 1){
				map.put("payType", 3);   //1.表示支付宝 2.余额 3.微信
			}
			if(payType == 2){
				map.put("payType", 1);   //1.表示支付宝 2.余额 3.微信
			}
			map.put("flag", 2);		//1.成功 2.失败 3.取消
			return "phone/user/pay";
		}
		
	}
	
	
    public String setWeuserSession(HttpServletRequest request,Weuser w){
    	HttpSession session = request.getSession();
		session.setAttribute(Settings.USER_SESSION, w);
		session.setMaxInactiveInterval(1*60);
		return session.getId();
    }
    
    
    /***
     * 用户入会
     * @param request
     * @param map
     * @param orderMainNo
     * @param pay_type
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping("membershipRecharge")
    public Object membershipRecharge(HttpServletRequest request,int pay_type,int vip_id,int userId) throws Exception{
    	Weuser w  = weuserService.getUserById(userId);
		if(w==null){
    		return new BusinessResponse(OperationResult.NOT_LOGIN.getStatus(), OperationResult.NOT_LOGIN.getMsg(),"");
		}
		if(w.getVip()!=null){
			return new BusinessResponse(OperationResult.IS_VIP.getStatus(), OperationResult.IS_VIP.getMsg(),"");
		}
		User_vip vip = user_vipService.getUser_vipById(vip_id);
		double money = vip.getPrice();
		//保存支付信息
    	String payOrderNo = orderComponent.savePayInfo_thirdPay_orderNo("", money, pay_type);
    	//保存交易记录
    	Trade_recode t = new Trade_recode();
    	t.setRecharId(vip_id);
    	t.setWeuser(w);
    	t.setTradetype(2);
    	t.setMny_amount(money);
    	t.setBusiness_type(Constants.TradeRecodeBusinessType.USERPAID.getStatus());
    	t.setOrderMainNo(payOrderNo); 
    	t.setFlag(Constants.OPERATE_FAIL0);
    	t.setPay_type(pay_type);
    	trade_recodeService.saveTrade_recode(t);
    	Map<String,Object> map = new HashMap<String, Object>(); 
		map.put("subject", Settings.BODY_MEMBERSHIP);
		map.put("body", Settings.BODY_MEMBERSHIP);
		map.put("price", money); 
		map.put("orderno", payOrderNo);
		if(Constants.PayType.APLIYPAY.getStatus() == pay_type){
			map.put("notifyurl", Settings.MEMBERSHIP_ALIPAY);
		}else if(Constants.PayType.WECHATPAY.getStatus() == pay_type){
			map.put("notifyurl", Settings.MEMBERSHIP_WECHAT);
		}
    	return new BusinessResponse(OperationResult.SUCCESS.getStatus(), OperationResult.SUCCESS.getMsg(),map);
    }
    
    
    /**
	 * 入会时手机端异步调用
	 * 微信支付后的处理(异步调用)
	 * @param out_trade_no		支付订单号
	 * @param price				金额
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(Settings.MEMBERSHIP_WECHAT)
	public synchronized String weChatMembership(HttpServletRequest request,String out_trade_no,String price,ModelMap map,String trade_status) throws Exception{
		CommonUtil.printHTTP(request);
		System.err.println("微信异步调用----支付后访问....支付订单号:"+out_trade_no+"  ,price:"+price);
		if("TRADE_SUCCESS".equals(trade_status)){
			Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(out_trade_no);
			if(t.getFlag()==Constants.OPERATE_FAIL0){ //状态为失败时再进行用户充值的操作
				Weuser w  = weuserService.getUserById(t.getWeuser().getId());		
				User_vip vip = user_vipService.getUser_vipById(t.getRecharId());
				w.setVip(vip);
				SystemConfig s = systemConfigService.getSystemConfigBykey("user_vip");
				int vipId = Integer.valueOf(s.getValue());
				vipId = vipId + 1;
				w.setVip_id(vipId);
				weuserService.mergeAndUpdateTime(w);
				s.setValue(""+vipId);
				systemConfigService.updateSystemConfig(s);
				t.setFlag(Constants.OPERATE_SUCCESS);
				trade_recodeService.updateTrade_recode(t);
				map.put("result", "success");	
			}else{
				map.put("result", "fail");		
			}
			return "phone/user/pay";
		}else{
			return "phone/user/fail";
		}
	}
	
	/**
	 * 入会时手机端异步调用
	 * 支付宝支付后的处理(异步调用)
	 * @param out_trade_no		支付订单号(支付宝的参数)
	 * @param price				金额
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(Settings.MEMBERSHIP_ALIPAY)
	public String alipayMembership(HttpServletRequest request,String out_trade_no,String price,ModelMap map,String trade_status) throws Exception{
		CommonUtil.printHTTP(request);
		System.err.println("支付宝异步调用----支付后访问....支付订单号:"+out_trade_no+"  ,price:"+price);
		if("TRADE_SUCCESS".equals(trade_status)){
			Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(out_trade_no);
			if(t.getFlag()==Constants.OPERATE_FAIL0){ //状态为失败时再进行用户充值的操作
				Weuser w  = weuserService.getUserById(t.getWeuser().getId());		
				User_vip vip = user_vipService.getUser_vipById(t.getRecharId());
				w.setVip(vip);
				SystemConfig s = systemConfigService.getSystemConfigBykey("user_vip");
				int vipId = Integer.valueOf(s.getValue());
				vipId = vipId + 1;
				w.setVip_id(vipId);
				weuserService.mergeAndUpdateTime(w);
				s.setValue(""+vipId);
				systemConfigService.updateSystemConfig(s);
				t.setFlag(Constants.OPERATE_SUCCESS);
				trade_recodeService.updateTrade_recode(t);
				map.put("result", "success");	
			}else{
				map.put("result", "fail");		
			}
			return "phone/user/pay";
		}else{
			return "phone/user/fail";
		}
	}
	
	
}
