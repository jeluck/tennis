package com.project.pojo;

import javax.persistence.*;

import com.project.util.CommonUtil;


/***
 * 活动订单信息
 * @author lxc
 *
 */
@Entity
@Table(name = "orderinfo")
public class Orderinfo extends BaseEntity implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5257075487759789486L;
	private int id;             //
    private String create_date; //创建日期(格式2015-01-01),打球日期时间
    private String today_time; //当天时间(格式9,10,11,12)
    private Weuser weuser;      //用户
    
    private String  phone;		//用户电话
    
	/** 子订单编号 */
	private String               orderMainNo;			//
    
	/** 子订单编号 */
	private String               orderSubNo;			//getOrderSubNo(i,creatOrderNo("W"))
	
    /**
     * 审核时间
     */
    private String                 examineTime;
    
    /** 订单支付金额 ：下订单时候应该付款的总金额   */
    private Double               orderPayTotalAmont = 0.0;

    /** 
     * 未支付1;  部分支付 2;  已支付 3;  执行中4;	已完成5;		退款申请中 6;  部分退款 7;  全额退款 8;  退款失败9;  作废10;*/
    private Integer              status          = 1;					//Constants.OrderStatus

    /** 在线支付 已支付金额 */
    private Double               onlinePayAmount=0d;
    
    /** 订单支付时间 **/
    private String 				 payTime;
    
    private String activeID;			//场馆ID,教练ID,活动ID,培训ID
	private int orderType;			//1场馆,2教练,3活动,4培训,5赛事			Constants.DATATYPE
	private Integer playgroundmanager_id=0;				//场馆管理者ID
	private Integer is_member=1;	//默认为会员 1为会员   2为非会员
	private String phoneName;

	
    private Integer spaceId=0;		//场地ID
    
    private int comment_status=0;//'状态,	0未评论,1已评论,					Constants.OrderEvaluationStatus
    
    private Integer is_back=0;	//是否是后台预订是为1，否为0	
    
    
	private int trade_balance_status=1;				//交易结算状态	0为结算,1未结算
	private String trade_balance_time="";				//交易结算时间
	private int subsidies_grant_status=1;			//补贴发放状态		0为发放,1未发放
	private String subsidies_grant_time="";			//补贴发放时间
	
    
    
    private String details;
    
    private Coach coachName;
    private String playgroundName;
    private String spaceName;
    
    
    @Transient
    public Coach getCoachName() {
		return coachName;
	}

	public void setCoachName(Coach coachName) {
		this.coachName = coachName;
	}
	@Transient
	public String getPlaygroundName() {
		return playgroundName;
	}

	public void setPlaygroundName(String playgroundName) {
		this.playgroundName = playgroundName;
	}
	@Transient
	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	private String creat_order_time=CommonUtil.getTimeNow();			//创建时间
	@Column(name = "creat_order_time")
	public String getCreat_order_time() {
		return creat_order_time;
	}

	public void setCreat_order_time(String creat_order_time) {
		this.creat_order_time = creat_order_time;
	}


    @Column(name="details")
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

	@Column(name="is_back")
	public Integer getIs_back() {
		return is_back;
	}

	public void setIs_back(Integer is_back) {
		this.is_back = is_back;
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

    @Column(name="create_date")
    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    @Column(name="create_time")
    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weuser_id")
    public Weuser getWeuser() {
        return weuser;
    }

    public void setWeuser(Weuser weuser) {
        this.weuser = weuser;
    }

    @Column(name = "order_sub_no", length = 32)
    public String getOrderSubNo() {
        return this.orderSubNo;
    }

    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    @Column(name = "examine_time")
	public String getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

    @Column(name = "order_pay_total_amont")
	public Double getOrderPayTotalAmont() {
		return orderPayTotalAmont;
	}

	public void setOrderPayTotalAmont(Double orderPayTotalAmont) {
		this.orderPayTotalAmont = orderPayTotalAmont;
	}


    @Column(name = "online_pay_amount")
	public Double getOnlinePayAmount() {
		return onlinePayAmount;
	}

	public void setOnlinePayAmount(Double onlinePayAmount) {
		this.onlinePayAmount = onlinePayAmount;
	}

    @Column(name = "pay_time")
	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}


    @Column(name="today_time")
	public String getToday_time() {
		return today_time;
	}

	public void setToday_time(String today_time) {
		this.today_time = today_time;
	}

    @Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

    @Column(name="phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

   
    @Column(name="comment_status")
	public int getComment_status() {
		return comment_status;
	}

	public void setComment_status(int comment_status) {
		this.comment_status = comment_status;
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

	@Column(name="playgroundmanager_id")
	public Integer getPlaygroundmanager_id() {
		return playgroundmanager_id;
	}

	public void setPlaygroundmanager_id(Integer playgroundmanager_id) {
		this.playgroundmanager_id = playgroundmanager_id;
	}
	
	@Column(name="orderMainNo")
	public String getOrderMainNo() {
		return orderMainNo;
	}

	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}
	
    @Column(name="spaceId")
    public Integer getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
	}
	
	@Column(name="phoneName")
	public String getPhoneName() {
		return phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	@Column(name="is_member")
	public Integer getIs_member() {
		return is_member;
	}

	public void setIs_member(Integer is_member) {
		this.is_member = is_member;
	}

	@Column(name="trade_balance_status")
	public int getTrade_balance_status() {
		return trade_balance_status;
	}

	public void setTrade_balance_status(int trade_balance_status) {
		this.trade_balance_status = trade_balance_status;
	}

	@Column(name="trade_balance_time")
	public String getTrade_balance_time() {
		return trade_balance_time;
	}

	public void setTrade_balance_time(String trade_balance_time) {
		this.trade_balance_time = trade_balance_time;
	}

	@Column(name="subsidies_grant_status")
	public int getSubsidies_grant_status() {
		return subsidies_grant_status;
	}

	public void setSubsidies_grant_status(int subsidies_grant_status) {
		this.subsidies_grant_status = subsidies_grant_status;
	}

	@Column(name="subsidies_grant_time")
	public String getSubsidies_grant_time() {
		return subsidies_grant_time;
	}

	public void setSubsidies_grant_time(String subsidies_grant_time) {
		this.subsidies_grant_time = subsidies_grant_time;
	}
}
