package com.project.pojo.vo;

// Generated 2011-5-13 12:42:19 by Hibernate Tools 3.4.0.CR1

import com.project.pojo.Weuser;
import com.project.util.CommonUtil;

/**
 * 订单字段
 */
public class OrderVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	

	/** 父订单编号 */
	private String orderMainNo;			//GenNumberTool.creatOrderNo("A")
	
	/** 子订单编号 */
	private String               orderSubNo;

	/** 会员名称 */
	private String memberName;
	
    private Weuser weuser;      //用户

	/** 货品总数量 */
	private Integer prodTotalNum;

	/** 订单总费用 */
	private double totalAmount;


	/** 创建时间 */
	private String createTime=CommonUtil.getTimeNow();

	/** 订单保留时间 */
	private String reserveTime;

	/** 赠送积分总计 */
	private Integer totalGivingScores;

	/** 订单留言 */
	private String orderMessage;
	
	/** 给收件人的留言 */
	private String orderMessageNew;

	/**
	 * 删除标志 <br/>
	 * 订单已被刪除 0; 订单正常 1;
	 */
	private Short delFlag = 1;

	 /** 
     * 未支付1;  部分支付 2;  已支付 3;  执行中4;	已完成5;		退款申请中 6;  部分退款 7;  全额退款 8;  退款失败9;  作废10;  已评价11; */
	private Integer payStatus=1;
	
	/**
	 * 在线支付 已支付金额
	 */
	private Double onlinePayAmount=0d;
	
	/**
	 * 优惠券抵掉的价格
	 */
	private Double prePrice=0.0;
	
	private String activeID;			//场馆ID,教练ID,活动ID,培训ID
	
	private int orderType;			//1场馆,2教练,3活动,4培训,5赛事			Constants.DATATYPE
	
	private String name;	//名称
	private String img;		//图片
	private Integer person=0;	//教练带人

	private Integer is_back=0;	//是否是后台预订是为1，否为0	
	private Integer is_member=1;	//默认为会员 1为会员   2为非会员
	private String phone;
	private String phoneName;
	private Integer weekCount;		//周次
	private String initialDate;		//最早打球时间  年月日 时（2015-12-25 5）
	
	private String start_time;		//开始时间
	private String end_time;		//结束时间
	
	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}


    public void setWeuser(Weuser weuser) {
        this.weuser = weuser;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderMainNo() {
		return orderMainNo;
	}

	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getProdTotalNum() {
		return prodTotalNum;
	}

	public void setProdTotalNum(Integer prodTotalNum) {
		this.prodTotalNum = prodTotalNum;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}

	public Integer getTotalGivingScores() {
		return totalGivingScores;
	}

	public void setTotalGivingScores(Integer totalGivingScores) {
		this.totalGivingScores = totalGivingScores;
	}

	public String getOrderMessage() {
		return orderMessage;
	}

	public void setOrderMessage(String orderMessage) {
		this.orderMessage = orderMessage;
	}

	public String getOrderMessageNew() {
		return orderMessageNew;
	}

	public void setOrderMessageNew(String orderMessageNew) {
		this.orderMessageNew = orderMessageNew;
	}

	public Short getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Double getOnlinePayAmount() {
		return onlinePayAmount;
	}

	public void setOnlinePayAmount(Double onlinePayAmount) {
		this.onlinePayAmount = onlinePayAmount;
	}

	public Double getPrePrice() {
		return prePrice;
	}

	public void setPrePrice(Double prePrice) {
		this.prePrice = prePrice;
	}

	public String getActiveID() {
		return activeID;
	}

	public void setActiveID(String activeID) {
		this.activeID = activeID;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}

	public Integer getIs_back() {
		return is_back;
	}

	public void setIs_back(Integer is_back) {
		this.is_back = is_back;
	}

	public Integer getIs_member() {
		return is_member;
	}

	public void setIs_member(Integer is_member) {
		this.is_member = is_member;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneName() {
		return phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	public Integer getWeekCount() {
		return weekCount;
	}

	public void setWeekCount(Integer weekCount) {
		this.weekCount = weekCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Weuser getWeuser() {
		return weuser;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}

}
