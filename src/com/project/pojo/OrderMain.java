package com.project.pojo;

// Generated 2011-5-13 12:42:19 by Hibernate Tools 3.4.0.CR1

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import com.project.util.CommonUtil;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * 主订单
 */
@Entity
@Table(name = "order_main")
public class OrderMain implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	

	/** 父订单编号 */
	private String orderMainNo;			//GenNumberTool.creatOrderNo("A")

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
     * 未支付1;  部分支付 2;  已支付 3;  执行中4;	已完成5;		退款申请中 6;  部分退款 7;  全额退款 8;  退款失败9;  作废10;  已评价11; 现场支付12(不计入补贴)*/
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
	
    private Integer spaceId=0;		//场地ID
	
	private Integer incoachId=0;		//驻场教练id,(如果下单时,选择了驻场教练,则保存的是教练ID)
    
	@Column(name="person")
	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}

	private Integer is_back=0;	//是否是后台预订是为1，否为0	
	@Column(name="is_member")
	private Integer is_member=1;	//默认为会员 1为会员   2为非会员
	@Column(name="phone")
	private String phone;
	private String phoneName;
	private Integer weekCount;		//周次
	@Column(name="initialDate")
	private String initialDate;		//最早打球时间  年月日 时（2015-12-25 5）
	
	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	@Column(name="weekCount")
	public Integer getWeekCount() {
		return weekCount;
	}

	public void setWeekCount(Integer weekCount) {
		this.weekCount = weekCount;
	}

	@Column(name="phoneName")
	public String getPhoneName() {
		return phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
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

	@Column(name="is_back")
	public Integer getIs_back() {
		return is_back;
	}

	public void setIs_back(Integer is_back) {
		this.is_back = is_back;
	}

	@Column(name="prePrice")
    public Double getPrePrice() {
		return prePrice;
	}

	public void setPrePrice(Double prePrice) {
		this.prePrice = prePrice;
	}

	/**
     * 更新时间
     */
    private String update_time;
	
	public OrderMain() {
	}

	public OrderMain(int id) {
		this.id = id;
	}
	
	private Set<Orderinfo> orderSubs = new HashSet<Orderinfo>(0);

    @Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "order_main_no", length = 32)
	public String getOrderMainNo() {
		return this.orderMainNo;
	}

	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}

	@Column(name = "member_name", length = 32)
	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Column(name = "prod_total_num")
	public Integer getProdTotalNum() {
		return this.prodTotalNum;
	}

	public void setProdTotalNum(Integer prodTotalNum) {
		this.prodTotalNum = prodTotalNum;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weuser_id")
    @JsonIgnore
    public Weuser getWeuser() {
        return weuser;
    }

    public void setWeuser(Weuser weuser) {
        this.weuser = weuser;
    }
	
	
	@Column(name = "create_time")
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "reserve_time")
	public String getReserveTime() {
		return this.reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}

	@Column(name = "total_giving_scores")
	public Integer getTotalGivingScores() {
		return this.totalGivingScores;
	}

	public void setTotalGivingScores(Integer totalGivingScores) {
		this.totalGivingScores = totalGivingScores;
	}


	@Column(name = "order_message", length = 150)
	public String getOrderMessage() {
		return this.orderMessage;
	}

	public void setOrderMessage(String orderMessage) {
		this.orderMessage = orderMessage;
	}

	@Column(name = "del_flag")
	public Short getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "pay_status")
	public Integer getPayStatus() {
		return payStatus;
	}

	@Column(name = "online_pay_amount", precision = 22, scale = 0)
	public Double getOnlinePayAmount() {
		return onlinePayAmount;
	}

	public void setOnlinePayAmount(Double onlinePayAmount) {
		this.onlinePayAmount = onlinePayAmount;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	@Column(name = "order_messagenew", length = 150)
	public String getOrderMessageNew() {
		return orderMessageNew;
	}
	
	public void setOrderMessageNew(String orderMessageNew) {
		this.orderMessageNew = orderMessageNew;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderMain")
	@Transient
	public Set<Orderinfo> getOrderSubs() {
		return orderSubs;
	}

	public void setOrderSubs(Set<Orderinfo> orderSubs) {
		this.orderSubs = orderSubs;
	}

	@Column(name = "total_amount")
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Column(name = "update_time" )
	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	@Column(name="activeid")
	public String getActiveID() {
		return activeID;
	}

	public void setActiveID(String activeID) {
		this.activeID = activeID;
	}

    @Column(name="orderType")
	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}

	@Column(name="img")
	public String getImg() {
		return img;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImg(String img) {
		this.img = img;
	}

    @Column(name="spaceId")
    public Integer getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
	}

	 @Column(name="incoachId")
	public Integer getIncoachId() {
		return incoachId;
	}

	public void setIncoachId(Integer incoachId) {
		this.incoachId = incoachId;
	}
}
