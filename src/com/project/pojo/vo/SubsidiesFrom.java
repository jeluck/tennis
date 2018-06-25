package com.project.pojo.vo;

import com.project.pojo.Weuser;

public class SubsidiesFrom {
	private int id;				//序号
	private String order_time;				//下单日期
	private String orderMainNo;				//主订单编号
	private String orderSubNo;				//子订单编号
	private int status;				//订单状态
	private String status_str;				//订单状态说明
	private String playground;				//所在场馆
	private String space;				//场地
	private String play_date;				//打球日期
	private String play_time;				//打球时间
	private String coach;				//所属教练
	private Weuser user;				//用户
	private double total_amount;				//总金额
	private int trade_balance_status=1;				//交易结算状态	0为结算,1未结算
	private String trade_balance_time;				//交易结算时间
	private double subsidies_proportion;			//补贴比例
	private double subsidies_money;			//补贴额
	private int subsidies_grant_status=1;			//补贴发放状态		0为发放,1未发放
	private String subsidies_grant_time;			//补贴发放时间
	
	
	private String orderType;			//1场馆,2教练,3活动,4培训,5赛事			Constants.DATATYPE

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	public String getOrderMainNo() {
		return orderMainNo;
	}

	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPlayground() {
		return playground;
	}

	public void setPlayground(String playground) {
		this.playground = playground;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getPlay_date() {
		return play_date;
	}

	public void setPlay_date(String play_date) {
		this.play_date = play_date;
	}

	public String getPlay_time() {
		return play_time;
	}

	public void setPlay_time(String play_time) {
		this.play_time = play_time;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public Weuser getUser() {
		return user;
	}

	public void setUser(Weuser user) {
		this.user = user;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public int getTrade_balance_status() {
		return trade_balance_status;
	}

	public void setTrade_balance_status(int trade_balance_status) {
		this.trade_balance_status = trade_balance_status;
	}

	public String getTrade_balance_time() {
		return trade_balance_time;
	}

	public void setTrade_balance_time(String trade_balance_time) {
		this.trade_balance_time = trade_balance_time;
	}

	public double getSubsidies_proportion() {
		return subsidies_proportion;
	}

	public void setSubsidies_proportion(double subsidies_proportion) {
		this.subsidies_proportion = subsidies_proportion;
	}

	public double getSubsidies_money() {
		return subsidies_money;
	}

	public void setSubsidies_money(double subsidies_money) {
		this.subsidies_money = subsidies_money;
	}

	public int getSubsidies_grant_status() {
		return subsidies_grant_status;
	}

	public void setSubsidies_grant_status(int subsidies_grant_status) {
		this.subsidies_grant_status = subsidies_grant_status;
	}

	public String getSubsidies_grant_time() {
		return subsidies_grant_time;
	}

	public void setSubsidies_grant_time(String subsidies_grant_time) {
		this.subsidies_grant_time = subsidies_grant_time;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}

	public String getStatus_str() {
		return status_str;
	}

	public void setStatus_str(String status_str) {
		this.status_str = status_str;
	}
}
