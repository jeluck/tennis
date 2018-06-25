package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.util.CommonUtil;

@Entity
@Table(name = "payinfo_thirdpay_orderno")
public class PayInfo_thirdPay_orderNo {
	private int id;
	private String orderMainNo;			// 主订单号
	private String pay_orderNo;			// 支付订单号
	private double pay_order_amount;	//支付金额
	private int pay_type;				//支付方式:	0余额支付,1微信,2支付宝
    /**
     * 创建时间
     */
    private String create_time = CommonUtil.getTimeNow();;
    
    @Id
    @GeneratedValue
	@Column(name = "id" )
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "order_main_no")
	public String getOrderMainNo() {
		return orderMainNo;
	}

	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}

	@Column(name = "pay_orderNo")
	public String getPay_orderNo() {
		return pay_orderNo;
	}

	public void setPay_orderNo(String pay_orderNo) {
		this.pay_orderNo = pay_orderNo;
	}

	@Column(name = "pay_order_amount")
	public double getPay_order_amount() {
		return pay_order_amount;
	}

	public void setPay_order_amount(double pay_order_amount) {
		this.pay_order_amount = pay_order_amount;
	}
	@Column(name = "pay_type")
	public int getPay_type() {
		return pay_type;
	}

	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}

	@Column(name = "create_time")
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
