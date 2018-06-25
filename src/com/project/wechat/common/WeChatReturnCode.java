package com.project.wechat.common;
/**
 * 返回值的说明。
 * @author lxc
 *
 */
public class WeChatReturnCode {
	/***/
	private WeChatReturnCode(){}
	
	/** 成功的返回码 */
	public static final String SUCESS_CODE="0";
	
	/**
	 * 其他返回码说明。
	 */
	private static final String[][] RETURN_CODE_INFO = new String[][]{
		{"SUCCESS"   ,   "处理成功"},
		{"SYSTEMERROR"   ,   "接口返回错误"}, 
		{"PARAM_ERROR"   ,   "参数错误"}, 
		{"ORDERPAID"   ,   "订单已支付"}, 
		{"NOAUTH"   ,   "商户无权限"}, 
		{"AUTHCODEEXPIRE"   ,   "二维码已过期，请用户在微信上刷新后再试"}, 
		{"NOTENOUGH"   ,   "余额不足"}, 
		{"NOTSUPORTCARD"   ,   "不支持卡类型"}, 
		{"ORDERCLOSED"   ,   "订单已关闭"}, 
		{"ORDERREVERSED"   ,   "订单已撤销"}, 
		{"BANKERROR"   ,   "银行系统异常"}, 
		{"USERPAYING"   ,   "用户支付中，需要输入密码"}, 
		{"AUTH_CODE_ERROR"   ,   "授权码参数错误"}, 
		{"AUTH_CODE_INVALID"   ,   "授权码检验错误"}, 
		{"XML_FORMAT_ERROR"   ,   "XML格式错误"}, 
		{"REQUIRE_POST_METHOD"   ,   "请使用post方法"}, 
		{"SIGNERROR"   ,   "签名错误"}, 
		{"LACK_PARAMS"   ,   "缺少参数"}, 
		{"NOT_UTF8"   ,   "编码格式错误"}, 
		{"BUYER_MISMATCH"   ,   "支付帐号错误"}, 
		{"APPID_NOT_EXIST"   ,   "APPID不存在"}, 
		{"MCHID_NOT_EXIST"   ,   "MCHID不存在"}, 
		{"OUT_TRADE_NO_USED"   ,   "商户订单号重复"}, 
		{"APPID_MCHID_NOT_MATCH"   ,   "appid和mch_id不匹配"}
	};
	/**
	 * 获取返回码对应的信息；
	 * @param reCode 接口返回码
	 * @return 对应的错误信息
	 */
	public static String getReCodeInfo(String reCode){
		if(null == reCode || reCode.trim().length()== 0){
			return "";
		}else{
			reCode = reCode.trim();
			for( String[] reInfo : RETURN_CODE_INFO){
				if(reInfo[0].equals(reCode)){
					return reInfo[1];
				}
			}
			return "未定义接口返回值：" + reCode;
		}
	}
	
}
