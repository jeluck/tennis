package com.project.wechat.web.controller;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;

import com.project.common.Settings;
import com.project.pojo.OrderMain;
import com.project.pojo.PayInfo_thirdPay_orderNo;
import com.project.pojo.Trade_recode;
import com.project.service.IOrderComponent;
import com.project.service.IOrderMainService;
import com.project.service.ITrade_recodeService;
import com.project.wechat.common.WECHATConf;
import com.project.wechat.common.utils.GetWxOrderno;
import com.project.wechat.common.utils.RequestHandler;
import com.project.wechat.common.utils.Sha1Util;
import com.project.wechat.common.utils.TenpayUtil;
import com.project.wechat.model.pojo.WxPayDto;
import com.project.wechat.service.IWxPayResultService;

/**
 * 邮储银行网上支付接口
 * 
 * @author dengchenggang
 * 
 */
@Controller
@RequestMapping("/pay/wechat")
public class WECHATController {
	private static final Logger log = Logger.getLogger(WECHATController.class);


	@Resource
	private IOrderComponent orderComponent;
	@Resource
	private IWxPayResultService wxPayResultService;
	@Resource
	private ITrade_recodeService trade_recodeService;
	@Resource
	private IOrderMainService mainOrderService;
	
	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	private static String appid = WECHATConf.APPID;
	private static String appsecret = WECHATConf.APPSECRET;
	private static String partner = WECHATConf.PARTNER;
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private static String partnerkey = WECHATConf.PARTNERKEY;
	//openId 是微信用户针对公众号的标识，授权的部分这里不解释	(edit by lxc 2015-05-30	扫码支付接口,好像不需要openId)
	private static String openId = "";
	//微信支付成功后通知地址 必须要求80端口并且地址不能带参数
//	private static String notifyurl = WECHATConf.NOTIFYURL;	

	/**
	 * add by lxc	2015-12-28		
	 * 生成微信支付数据
	 * @param out_trade_no	支付订单号
	 * @param price			价格
	 * @param notify_url	异步调用URL,根据异步调用URL判断当前的操作是 充值 还是支付
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/gotoPay")
	public String gotowechatPay(String out_trade_no,String price,String notifyurl) {
		try {

			String totalFee = "0.01";
			String body = "";
			String notifyUrl = "";
			if(Settings.NOTIFYURL_WECHAT.equals(notifyurl)){
				Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(out_trade_no);
				totalFee = String.valueOf(t.getMny_amount());
				body = Settings.BODY_RECHARGE;
				notifyUrl = Settings.NOTIFYURL_WECHAT;
			}else if(Settings.NOTIFYURL_WECHAT_PAY_ORDER.equals(notifyurl)){
				PayInfo_thirdPay_orderNo o = orderComponent.getPayInfo_thirdPay_orderNo(out_trade_no);
				OrderMain om = mainOrderService.getOrderMainByNO(o.getOrderMainNo());
				totalFee = String.valueOf(om.getTotalAmount());
				body = Settings.BODY_PAY_ORDER;
				notifyUrl = Settings.NOTIFYURL_WECHAT_PAY_ORDER;
			}else if(Settings.MEMBERSHIP_WECHAT.equals(notifyurl)){
				Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(out_trade_no);
				totalFee = String.valueOf(t.getMny_amount());
				body = Settings.BODY_MEMBERSHIP;
				notifyUrl = Settings.MEMBERSHIP_WECHAT;
			}


			//APP支付封装参数
		    WxPayDto tpWxPay1 = new WxPayDto();
		    tpWxPay1.setBody(body);
		    tpWxPay1.setOrderId(String.valueOf(System.currentTimeMillis()));					
		    tpWxPay1.setSpbillCreateIp(WECHATConf.SPBILLCREATEIP);		//订单生成的机器 IP
		    tpWxPay1.setTotalFee(totalFee);					//测试
		    tpWxPay1.setNotifyUrl(notifyUrl);
		    Map mt = getMap(tpWxPay1);
		    String  appid =(String) mt.get("appid");
		    String  noncestr =(String) mt.get("nonce_str");
		    String  partnerid =(String) mt.get("mch_id");
		    String  prepayid =(String) mt.get("prepay_id");
		    String  sign =(String) mt.get("sign");
		    
		    
		    //拿到prepayid后,再签名
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String timestamp = Sha1Util.getTimeStamp();
			noncestr =  getNonceStr();
			timestamp = String.valueOf(System.currentTimeMillis());
			finalpackage.put("appid", appid);
			finalpackage.put("noncestr", noncestr);  
			finalpackage.put("package", "Sign=WXPay");
			finalpackage.put("partnerid", partnerid);
			finalpackage.put("prepayid", prepayid);
			finalpackage.put("timestamp", timestamp);  
			
			RequestHandler reqHandler = new RequestHandler(null, null);
			reqHandler.init(appid, appsecret, partnerkey);
			//要签名
			String finalsign = reqHandler.createSign(finalpackage);
			
		    StringBuffer sb = new StringBuffer();
		    sb.append("{");
		    
		    sb.append("\"appid\":");
		    sb.append("\""+appid+"\"");
		    sb.append(",");
		    sb.append("\"noncestr\":");
		    sb.append("\""+noncestr+"\"");
		    sb.append(",");
		    sb.append("\"package\":");
		    sb.append("\"Sign=WXPay\"");
		    sb.append(",");
		    sb.append("\"partnerid\":");
		    sb.append("\""+partnerid+"\"");
		    sb.append(",");
		    sb.append("\"prepayid\":");
		    sb.append("\""+prepayid+"\"");
		    sb.append(",");
		    sb.append("\"timestamp\":");
		    sb.append("\""+timestamp+"\"");
		    sb.append(",");
		    sb.append("\"sign\":");
		    sb.append("\""+finalsign+"\"");
		    
		    sb.append("}");
		    return sb.toString();
		    
		} catch (Exception e) {
			log.info("send PSBC Order failed", e);
		}
		
		 return "{}";
	}
	
	/**
	 * 生成预支付订单号
	 */
	public static Map getMap(WxPayDto tpWxPayDto){
		
		// 1 参数
		// 订单号
		String orderId = tpWxPayDto.getOrderId();
		// 附加数据 原样返回
		String attach = tpWxPayDto.getAttach();
		// 总金额以分为单位，不带小数点
		String totalFee = getMoney(tpWxPayDto.getTotalFee());
		
		// 订单生成的机器 IP
		String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = tpWxPayDto.getNotifyUrl();
		/**
		 * 交易类型
		JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
		MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
		 */
		String trade_type = "APP";

		// 商户号
		String mch_id = partner;
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商品描述根据情况修改
		String body = tpWxPayDto.getBody();

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("attach", attach);
		packageParams.put("body", body);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("notify_url", notify_url);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("total_fee", totalFee);
		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "</xml>";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//获取预支付订单号URL
		
		
		Map map = new GetWxOrderno().wechat_jsonStr(createOrderURL, xml);
		
		
		return map;
	}
	
	public static void main(String[] args) {
	    
	    //扫码支付
	    WxPayDto tpWxPay1 = new WxPayDto();
	    tpWxPay1.setBody("商品信息");
	    tpWxPay1.setOrderId(getNonceStr());
	    tpWxPay1.setSpbillCreateIp("127.0.0.1");
	    tpWxPay1.setTotalFee("0.01");
//		getCodeurl(tpWxPay1);

	}
	
	/**
	 * 处理返回
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getPayResult")
	public ModelAndView getPayResult(HttpServletRequest request,
			HttpServletResponse response) {
//		String status = AlipayConfig.PAY_STATUS_FAIL;
//		try {
//			
//			System.out.print("微信支付回调数据开始");
//			//示例报文
////			String xml = "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
//			String inputLine;
//			String notityXml = "";
//			String resXml = "";
//
//			try {
//				while ((inputLine = request.getReader().readLine()) != null) {
//					notityXml += inputLine;
//				}
//				request.getReader().close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			System.out.println("接收到的报文：" + notityXml);
//			
//			Map m = parseXmlToList2(notityXml);
//			WxPayResult wpr = new WxPayResult();
//			wpr.setAppid(m.get("appid").toString());
//			wpr.setBankType(m.get("bank_type").toString());
//			wpr.setCashFee(m.get("cash_fee").toString());
//			wpr.setFeeType(m.get("fee_type").toString());
//			wpr.setIsSubscribe(m.get("is_subscribe").toString());
//			wpr.setMchId(m.get("mch_id").toString());
//			wpr.setNonceStr(m.get("nonce_str").toString());
//			wpr.setOpenid(m.get("openid").toString());
//			wpr.setOutTradeNo(m.get("out_trade_no").toString());
//			wpr.setResultCode(m.get("result_code").toString());
//			wpr.setReturnCode(m.get("return_code").toString());
//			wpr.setSign(m.get("sign").toString());
//			wpr.setTimeEnd(m.get("time_end").toString());
//			wpr.setTotalFee(m.get("total_fee").toString());
//			wpr.setTradeType(m.get("trade_type").toString());
//			wpr.setTransactionId(m.get("transaction_id").toString());
//			wpr.setReturn_msg(WeChatReturnCode.getReCodeInfo(m.get("result_code").toString()));
//			String combineId = wpr.getOutTradeNo();
//			List<Order4PaySub> subList = orderSuperQueryService.getOrder4PaySub(combineId);
//			
//			if(subList!=null && subList.size()>0){
//				wpr.setOrderMainNo(subList.get(0).getOrderMainNo());
//			}
//			
//			try{
//				wxPayResultService.savaWxPayResult(wpr);
//				log.info("保存成功");
//			}catch(Exception e){
//				log.error("保存失败");
//				e.printStackTrace();
//			}
//			
//			if("SUCCESS".equals(wpr.getResultCode())){
//				//支付成功
//				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
//				+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
//				
//				System.err.println(wpr.getTransactionId());
//				System.err.println("商户订单号:"+wpr.getOutTradeNo());
//				
//				String onlinePayStyleNo = AlipayConfig.PAY_TYPE_WECHATPAY;
//				//List<Order4PaySub> subList = orderSuperQueryService.getOrder4PaySub(combineId);
//				for (Order4PaySub order4PaySub : subList) {
//					PayInfo payInfo = order4PaySub.getPayinfo();
//					String payInfoId = payInfo.getId();
//					String tradeNo = payInfo.getOrderSubNo();
//					String tradeAmount = String.valueOf(payInfo
//							.getPayAmount());
//					orderComponent.paySuccessDeal(OrderConstant.PAY_PAID,
//							payInfoId, tradeNo, onlinePayStyleNo,
//							tradeAmount);
//				}
//				status = AlipayConfig.PAY_STATUS_SUCCESS;
//			}else{
//				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
//				+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
//			}
//
//			System.out.println("微信支付回调数据结束");
//
//			BufferedOutputStream out = new BufferedOutputStream(
//					response.getOutputStream());
//			out.write(resXml.getBytes());
//			out.flush();
//			out.close();
//			
//
//		} catch (Exception e) {
//			log.info("WECHAT return failed", e);
//		}
		ModelMap model = new ModelMap();
		return new ModelAndView(
				"redirect:/ylymall/usercenter/payonline/backOnline.sc", model);
	}
	
	/**
	 * 获取随机字符串
	 * @return
	 */
	public static String getNonceStr() {
		// 随机数
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}

	/**
	 * 元转换成分
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// 金额转化为分为单位
		String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString(); 
	}
	
	/**
	 * description: 解析微信通知xml
	 * 
	 * @param xml
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static Map parseXmlToList2(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

}
