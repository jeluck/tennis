
package com.project.wechat.model.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * description: 微信支付回调
 * @author 
 * @since 
 * @see
 */
@Entity
@Table(name = "tbl_wxpay_result")
public class WxPayResult {
	
	private String id;
	private String appid;				//微信支付商户开通后 微信会提供appid
	private String bankType;				//付款银行
	private String cashFee;				//订单现金支付金额
	private String feeType;				//符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private String isSubscribe;				//是否关注公众账号:用户是否关注公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注
	private String mchId;				//调用接口提交的商户号
	private String nonceStr;				//微信返回的随机字符串
	private String openid;				//用户在商户appid 下的唯一标识
	private String outTradeNo;				//商户系统的订单号，与请求一致
	private String resultCode;				//
	private String returnCode;				//SUCCESS/FAIL	此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	private String sign;				//
	private String timeEnd;				//订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。支付完成时间
	private String totalFee;				//订单总金额，单位为分，只能为整数，详见支付金额
	private String tradeType;				//交易类型:支付类型为MICROPAY(即扫码支付)
	private String transactionId;				//微信支付订单号
	private String return_msg;					//返回信息，如非空，为错误原因,	签名失败,		参数格式校验错误
	/** 父订单编号 */
	private String orderMainNo;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1227026039888867970L;
	
	/**
	 * @return the appid
	 */
	@Column(name = "appid")
	public String getAppid() {
		return appid;
	}
	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * @return the bankType
	 */
	@Column(name = "bank_type")
	public String getBankType() {
		return bankType;
	}
	/**
	 * @param bankType the bankType to set
	 */
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	/**
	 * @return the cashFee
	 */
	@Column(name = "cash_fee")
	public String getCashFee() {
		return cashFee;
	}
	/**
	 * @param cashFee the cashFee to set
	 */
	public void setCashFee(String cashFee) {
		this.cashFee = cashFee;
	}
	/**
	 * @return the feeType
	 */
	@Column(name = "fee_type")
	public String getFeeType() {
		return feeType;
	}
	/**
	 * @param feeType the feeType to set
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	/**
	 * @return the isSubscribe
	 */
	@Column(name = "is_subscribe")
	public String getIsSubscribe() {
		return isSubscribe;
	}
	/**
	 * @param isSubscribe the isSubscribe to set
	 */
	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	/**
	 * @return the mchId
	 */
	@Column(name = "mch_id")
	public String getMchId() {
		return mchId;
	}
	/**
	 * @param mchId the mchId to set
	 */
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	/**
	 * @return the nonceStr
	 */
	@Column(name = "nonce_str")
	public String getNonceStr() {
		return nonceStr;
	}
	/**
	 * @param nonceStr the nonceStr to set
	 */
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	/**
	 * @return the openid
	 */
	@Column(name = "openid")
	public String getOpenid() {
		return openid;
	}
	/**
	 * @param openid the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * @return the outTradeNo
	 */
	@Column(name = "out_trade_no")
	public String getOutTradeNo() {
		return outTradeNo;
	}
	/**
	 * @param outTradeNo the outTradeNo to set
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	/**
	 * @return the resultCode
	 */
	@Column(name = "result_code")
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the returnCode
	 */
	@Column(name = "return_code")
	public String getReturnCode() {
		return returnCode;
	}
	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	/**
	 * @return the sign
	 */
	@Column(name = "sign")
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * @return the timeEnd
	 */
	@Column(name = "time_end")
	public String getTimeEnd() {
		return timeEnd;
	}
	/**
	 * @param timeEnd the timeEnd to set
	 */
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	/**
	 * @return the totalFee
	 */
	@Column(name = "total_fee")
	public String getTotalFee() {
		return totalFee;
	}
	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * @return the tradeType
	 */
	@Column(name = "trade_type")
	public String getTradeType() {
		return tradeType;
	}
	/**
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * @return the transactionId
	 */
	@Column(name = "transaction_id")
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="return_msg")
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
	/** 父订单编号 */
	@Column(name = "order_main_no", length = 32)
	public String getOrderMainNo() {
		return this.orderMainNo;
	}
	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}

}