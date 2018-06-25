package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.util.CommonUtil;


/**
 * 交易记录查询
 * @author daybreak
 * 
 */
@Entity
@Table(name = "trade_recode")
public class Trade_recode extends BaseEntity{
	private int id;
	private Weuser weuser;      //用户账户ID' ,
	/**
	 * Constants.RECHARGE_TRADE_TYPE	
	 * Constants.PAY_TRADE_TYPE	
	 * Constants.INCOME_TYPE	
	 */
	private int tradetype;      //资金流向(1充值,2支付,3收入,4退款)' ,		
	private double balance;      //可用余额
	private int flag=1;//'状态,1为成功,0为失败' ,
	private double mny_amount; // 操作的资金数量
	private int business_type; // 交易类型,1用户支付,2网上充值，3网上提现，			Constants.TradeRecodeBusinessType
	private String orderMainNo;//主订单编号   充值时为支付订单号
	private String desccp;		//详情，当主订单下的子订单退款时，写上那些子订单退款了
	private Integer pay_type;				//支付方式:	0余额支付,1微信,2支付宝
	
	
	private int recharId=0;	//充值活动编号 或者 vip编号   不是充值活动默认为0    
	 
	
	@Column(name = "orderMainNo")
	public String getOrderMainNo() {
		return orderMainNo;
	}
	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}
	
	@Column(name = "desccp")
	public String getDesccp() {
		return desccp;
	}
	public void setDesccp(String desccp) {
		this.desccp = desccp;
	}

	private String update_time=CommonUtil.getTimeNow();			//更新时间
	private String create_time=CommonUtil.getTimeNow();		//创建时间
	@Column(name = "update_time")
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	@Column(name = "create_time")
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Trade_recode() {
	}

	public Trade_recode(int ad_id) {
		this.id = ad_id;
	}
    @Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    @Column(name = "flag")
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weuser_id")
	public Weuser getWeuser() {
		return weuser;
	}

	public void setWeuser(Weuser weuser) {
		this.weuser = weuser;
	}
	
    @Column(name="tradetype")
	public int getTradetype() {
		return tradetype;
	}

	public void setTradetype(int tradetype) {
		this.tradetype = tradetype;
	}
	
    @Column(name="balance")
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
    @Column(name="mny_amount")
	public double getMny_amount() {
		return mny_amount;
	}

	public void setMny_amount(double mny_amount) {
		this.mny_amount = mny_amount;
	}
	
    @Column(name="business_type")
	public int getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(int business_type) {
		this.business_type = business_type;
	}
	
	@Column(name = "recharId")
	public int getRecharId() {
		return recharId;
	}
	public void setRecharId(int recharId) {
		this.recharId = recharId;
	}
	@Column(name = "pay_type")
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
}
