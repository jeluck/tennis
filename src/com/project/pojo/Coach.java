package com.project.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.project.util.CommonUtil;


/**
 * 教练
 * @author lxc
 */
@Entity
@Table(name = "coach")
public class Coach  extends BaseEntity{
	private int id;
	private String name="";
	private String phone="";
	private int age=0;
	private int teaching=0;		// '教学年限' ,
	private String detailinfo="";
	private String teachtype="初级";		// '教学资深程度,初级,中级,高级等' ,
	private int now_live_city;			//'现在居住城市,(首页选择城市,根据此字段,找教练)' ,关联region表的 region_id
	private int areaid;					// 所在区
	private Weuser userid;				//用户ID ,
	private Integer playground_id=0;			//属于某个场馆的
	private Integer playgroundmanager_id=0;		//代表某个场馆管理者添加
	private String evaluate_score="0";		//'评价分数(几颗星星)'根据评价表统计分数
	private int status=1;					//状态,1为申请,2为开通,3为不通过				Constants.O_STATUS
	private String head_portrait="";		//头像
	private String services="";			//服务
	private String professional="";		//教练专长
	private String schoolzy="";			//学校专业
	private String sex="男";					//性别
	private String nationality="";			//国籍
	private double price=0d;				//价格
	private double money=0d;				//最低价
	
	private int distanceMeters;		//距离米
	private double coordinateslongitude=0;	// '坐标经度' ,
	private double coordinateslatitude=0;		// '坐标纬度' ,
	private String educationalBackground="";		//学历  幼儿园 。小学 。初中 。中专 。高中 。大专  。大学  。研究生。博士生
	private String goodAt="";					//擅长技能
	
	//临时变量
	private String city="";			// 所在市
	private String area="";			// 所在区
	private int attention;			//关注数量
	private int uid;				//用户编号
	private int AreConcerned;		//是否关注  	1、关注  0、未关注
	private String courseTitle="";			//培训课程
	private Weuser user;				//用户ID ,
	
	private Qualification_certificate certificate;	//教练证书
	
	
	private Coach_teach_person ctp;
	
	
	private String bank="";   //开户银行
	private String zbank="";	//支行
	private String bankName = ""; //户名
	private String bankZh;		//银行帐号
	private Integer scoreCount=0;//评分总人数
	
	private String backgroundImg="";  //个人背景图片
	
	public Integer getScoreCount() {
		return scoreCount;
	}
	public void setScoreCount(Integer scoreCount) {
		this.scoreCount = scoreCount;
	}
	@Transient
	public Coach_teach_person getCtp() {
		return ctp;
	}
	public void setCtp(Coach_teach_person ctp) {
		this.ctp = ctp;
	}
	@Column(name="head_portrait")
	public String getHead_portrait() {
		return head_portrait;
	}
	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}
	/**
	 * Constants.IS_RESERVE1		
	 * Constants.IS_RESERVE0
	 */
	private int is_reserve=1;				//'教练是否可以预定场地(0不可以,1可以)' ,
	/**
	 * Constants.IS_RESERVE1		
	 * Constants.IS_RESERVE0
	 */
	private int reserve_me=1;				//'用户是否可以预定我(0不可以,1可以)' ,
	/**
	 * 置顶则为明星教练
	 */
	private int stick=0;				//是否置顶，0否，1是,			Constants.STICK1		Constants.STICK0
	
	private Integer staffCount=0;		//教练的学员人数
	
	private Float height=0f;
	
	private Float weight=0f;
	
	private String personalitySignature="";		//个性签名
	
	private Integer coachType=1;				//教练类型 1，自由教练     2，驻场教练      3，运营商					Constants.COACHTYPE
	
	private Integer free=2;					//教练是否免场地费   1，是   2，否
	
	private Integer equipment = 2;			// 免费提供器材   1，是   2，否
	
	private Integer verified = 0;			//实名认证  0 为未认证  1为已认证  2认证中
	
	@Column(name = "coachType")
	public Integer getCoachType() {
		return coachType;
	}
	public void setCoachType(Integer coachType) {
		this.coachType = coachType;
	}
	
	@Column(name = "free")
	public Integer getFree() {
		return free;
	}
	public void setFree(Integer free) {
		this.free = free;
	}
	@Column(name = "height")
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	
	@Column(name = "weight")
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	@Column(name = "personalitySignature")
	public String getPersonalitySignature() {
		return personalitySignature;
	}
	public void setPersonalitySignature(String personalitySignature) {
		this.personalitySignature = personalitySignature;
	}
	@Column(name = "staffCount")
	public Integer getStaffCount() {
		return staffCount;
	}
	public void setStaffCount(Integer staffCount) {
		this.staffCount = staffCount;
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
    @Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(name="teaching")
	public int getTeaching() {
		return teaching;
	}

	public void setTeaching(int teaching) {
		this.teaching = teaching;
	}

	@Column(name="detailinfo")
	public String getDetailinfo() {
		return detailinfo;
	}

	public void setDetailinfo(String detailinfo) {
		this.detailinfo = detailinfo;
	}

	@Column(name="teachtype")
	public String getTeachtype() {
		return teachtype;
	}

	public void setTeachtype(String teachtype) {
		this.teachtype = teachtype;
	}

	@Column(name="now_live_city")
	public int getNow_live_city() {
		return now_live_city;
	}

	public void setNow_live_city(int now_live_city) {
		this.now_live_city = now_live_city;
	}

	@Column(name="is_reserve")
	public int getIs_reserve() {
		return is_reserve;
	}

	public void setIs_reserve(int is_reserve) {
		this.is_reserve = is_reserve;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weuser_id")
    @JsonIgnore
	public Weuser getUserid() {
		return userid;
	}

	public void setUserid(Weuser userid) {
		this.userid = userid;
	}

	@Column(name="playground_id")
	public Integer getPlayground_id() {
		return playground_id;
	}

	public void setPlayground_id(Integer playground_id) {
		this.playground_id = playground_id;
	}

	@Column(name="status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="evaluate_score")
	public String getEvaluate_score() {
		return evaluate_score;
	}

	public void setEvaluate_score(String evaluate_score) {
		this.evaluate_score = evaluate_score;
	}

	@Column(name="reserve_me")
	public int getReserve_me() {
		return reserve_me;
	}

	public void setReserve_me(int reserve_me) {
		this.reserve_me = reserve_me;
	}
	
	@Column(name="stick")
	public int getStick() {
		return stick;
	}
	public void setStick(int stick) {
		this.stick = stick;
	}
	
	@Column(name="services")
	public String getServices() {
		return services;
	}
	@Column(name="professional")
	public String getProfessional() {
		return professional;
	}
	@Column(name="sex")
	public String getSex() {
		return sex;
	}
	@Column(name="nationality")
	public String getNationality() {
		return nationality;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	@Column(name="price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate")
    @JsonIgnore
	public Qualification_certificate getCertificate() {
		return certificate;
	}
	public void setCertificate(Qualification_certificate certificate) {
		this.certificate = certificate;
	}
	
	@Column(name="areaid")
	public int getAreaid() {
		return areaid;
	}
	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}
	
	@Column(name="money")
	public double getMoney() {
		return money;
	}
	@Transient
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	@Column(name="coordinateslongitude")
	public double getCoordinateslongitude() {
		return coordinateslongitude;
	}
	
	@Column(name="coordinateslatitude")
	public double getCoordinateslatitude() {
		return coordinateslatitude;
	}
	public void setCoordinateslongitude(double coordinateslongitude) {
		this.coordinateslongitude = coordinateslongitude;
	}
	public void setCoordinateslatitude(double coordinateslatitude) {
		this.coordinateslatitude = coordinateslatitude;
	}
	
	@Transient
	public int getDistanceMeters() {
		return distanceMeters;
	}
	public void setDistanceMeters(int distanceMeters) {
		this.distanceMeters = distanceMeters;
	}
	@Transient
	public String getCity() {
		return city;
	}
	@Transient
	public String getArea() {
		return area;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Transient
	public int getAttention() {
		return attention;
	}
	public void setAttention(int attention) {
		this.attention = attention;
	}
	@Transient
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Transient
	public int getAreConcerned() {
		return AreConcerned;
	}
	public void setAreConcerned(int areConcerned) {
		AreConcerned = areConcerned;
	}
	@Transient
	public Weuser getUser() {
		return user;
	}
	public void setUser(Weuser user) {
		this.user = user;
	}
	
	@Column(name="verified")
	public Integer getVerified() {
		return verified;
	}
	public void setVerified(Integer verified) {
		this.verified = verified;
	}
	
	@Column(name="educationalBackground")
	public String getEducationalBackground() {
		return educationalBackground;
	}
	
	@Column(name="goodAt")
	public String getGoodAt() {
		return goodAt;
	}
	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}
	public void setGoodAt(String goodAt) {
		this.goodAt = goodAt;
	}
	
	@Column(name="equipment")
	public Integer getEquipment() {
		return equipment;
	}
	public void setEquipment(Integer equipment) {
		this.equipment = equipment;
	}
	
	@Column(name="playgroundmanager_id")
	public Integer getPlaygroundmanager_id() {
		return playgroundmanager_id;
	}

	public void setPlaygroundmanager_id(Integer playgroundmanager_id) {
		this.playgroundmanager_id = playgroundmanager_id;
	}
	
	@Column(name="bank")
	public String getBank() {
		return bank;
	}
	
	@Column(name="zbank")
	public String getZbank() {
		return zbank;
	}
	
	@Column(name="bankName")
	public String getBankName() {
		return bankName;
	}
	
	@Column(name="bankZh")
	public String getBankZh() {
		return bankZh;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setZbank(String zbank) {
		this.zbank = zbank;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setBankZh(String bankZh) {
		this.bankZh = bankZh;
	}
	
	@Column(name = "backgroundImg")
	public String getBackgroundImg() {
		return backgroundImg;
	}
	public void setBackgroundImg(String backgroundImg) {
		this.backgroundImg = backgroundImg;
	}
	
	@Column(name = "schoolzy")
	public String getSchoolzy() {
		return schoolzy;
	}
	
	@Column(name = "schoolzy")
	public void setSchoolzy(String schoolzy) {
		this.schoolzy = schoolzy;
	}
}
