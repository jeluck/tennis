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
 * 场馆
 * 
 * @author lxc
 */
@Entity
@Table(name = "playground")
public class Playground  extends BaseEntity{
	private int id;
	private String name="";	
	private String site_type="";		//场地类型    为红土,硬地,草地,地毯
	private String court_type="";		//球场类型    为普通,智能
	private String space_type="";		//空间类型    为室内,室外
	private int playgroundmanager_id=0;
	private int provinceid;		// 所在省
	private int cityid;			// 所在市
	private int areaid;			// 所在区
    private String address="";     //场馆具体地址地址
	private String province="";
	private String city="";
	private String area="";
	private double coordinateslongitude=0;	// '坐标经度' ,
	private double coordinateslatitude=0;		// '坐标纬度' ,
	private String telphone="";
	private String opentime="";				// '开门时间	,格式		6
	private String endtime="";				// '关门时间		,格式		22
	private int is_reserve=1;		//'是否可以在线预定场地(0不可以,1可以)' ,	Constants.IS_RESERVE1	Constants.IS_RESERVE0
	private String evaluate_score="0";		//'评价分数(几颗星星)'根据评价表统计分数
	private int auditStatus=1;		//审核状态  1.申请  AUDITSTATUS_APPLY  2.通过3.拒绝4.驳回   
	private String CertificateValidityPeriod="";	//场馆证件有效期
	private double price;		//原价
	private Peripheral_services pservices;	//服务
	private String pdImg="";		//图片
	private double money;		//最低价
	private Qualification_certificate certificate; //证书类
	
	/**临时变量*/
	private int distanceMeters;		//距离米
	private String huodong;			//活动
	
	private String details="";		//详情
	private int stick=0;				//是否置顶，0否，1是,			Constants.STICK1		Constants.STICK0
	private String update_time=CommonUtil.getTimeNow();			//更新时间
	private String create_time=CommonUtil.getTimeNow();		//创建时间
	private double profit=0;								//场馆所获得的利润
	private Integer audCount=0;								//预订次数s
	
	
	private String effective_time="";  //有效时间
	private String return_reason="";  //退回理由
	private int return_count= 0;		  //退回次数
	private int is_locked=0;			//是否锁定  0 未锁定 1锁定
	
	
	private String bank="";   //开户银行
	private String zbank="";	//支行
	private String bankName = ""; //户名
	private String bankZh;		//银行帐号
	private Integer scoreCount=0;	//评价总人数
	
	private String spType="";  //场地类型
	
	@Column(name = "scoreCount")
	public Integer getScoreCount() {
		return scoreCount;
	}
	public void setScoreCount(Integer scoreCount) {
		this.scoreCount = scoreCount;
	}
	@Column(name = "audCount")
	public Integer getAudCount() {
		return audCount;
	}
	public void setAudCount(Integer audCount) {
		this.audCount = audCount;
	}
	@Column(name = "profit")
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
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


	@Column(name="playgroundmanager_id")
	public int getPlaygroundmanager_id() {
		return playgroundmanager_id;
	}

	public void setPlaygroundmanager_id(int playgroundmanager_id) {
		this.playgroundmanager_id = playgroundmanager_id;
	}

	@Column(name="provinceid")
	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	@Column(name="cityid")
	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	@Column(name="areaid")
	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Transient
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Transient
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Transient
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name="coordinateslongitude")
	public double getCoordinateslongitude() {
		return coordinateslongitude;
	}

	public void setCoordinateslongitude(double coordinateslongitude) {
		this.coordinateslongitude = coordinateslongitude;
	}

	@Column(name="coordinateslatitude")
	public double getCoordinateslatitude() {
		return coordinateslatitude;
	}

	public void setCoordinateslatitude(double coordinateslatitude) {
		this.coordinateslatitude = coordinateslatitude;
	}

	@Column(name="telphone")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name="opentime")
	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	@Column(name="is_reserve")
	public int getIs_reserve() {
		return is_reserve;
	}

	public void setIs_reserve(int is_reserve) {
		this.is_reserve = is_reserve;
	}
	
	@Column(name="evaluate_score")
	public String getEvaluate_score() {
		return evaluate_score;
	}

	public void setEvaluate_score(String evaluate_score) {
		this.evaluate_score = evaluate_score;
	}
	
	@Column(name="endtime")
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	@Column(name="auditStatus")
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	@Column(name="certificateValidityPeriod")
	public String getCertificateValidityPeriod() {
		return CertificateValidityPeriod;
	}
	public void setCertificateValidityPeriod(String certificateValidityPeriod) {
		CertificateValidityPeriod = certificateValidityPeriod;
	}
	
	@Column(name="pdImg")
	public String getPdImg() {
		return pdImg;
	}
	
	public void setPdImg(String pdImg) {
		this.pdImg = pdImg;
	}
	
	
	
	@Column(name="price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name="site_type")
	public String getSite_type() {
		return site_type;
	}
	@Column(name="court_type")
	public String getCourt_type() {
		return court_type;
	}
	@Column(name="space_type")
	public String getSpace_type() {
		return space_type;
	}
	
	public void setSite_type(String site_type) {
		this.site_type = site_type;
	}
	public void setCourt_type(String court_type) {
		this.court_type = court_type;
	}
	public void setSpace_type(String space_type) {
		this.space_type = space_type;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pservices")
	@JsonIgnore
	public Peripheral_services getPservices() {
		return pservices;
	}
	public void setPservices(Peripheral_services pservices) {
		this.pservices = pservices;
	}
	@Column(name="money")
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
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
	
	@Column(name="details")
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Column(name="stick")
	public int getStick() {
		return stick;
	}
	public void setStick(int stick) {
		this.stick = stick;
	}
	
	@Transient
	public int getDistanceMeters() {
		return distanceMeters;
	}
	public void setDistanceMeters(int distanceMeters) {
		this.distanceMeters = distanceMeters;
	}
	
	@Transient
	public String getHuodong() {
		return huodong;
	}
	public void setHuodong(String huodong) {
		this.huodong = huodong;
	}
	
	@Column(name="effective_time")
	public String getEffective_time() {
		return effective_time;
	}
	@Column(name="return_reason")
	public String getReturn_reason() {
		return return_reason;
	}
	@Column(name="return_count")
	public int getReturn_count() {
		return return_count;
	}
	@Column(name="is_locked")
	public int getIs_locked() {
		return is_locked;
	}
	public void setEffective_time(String effective_time) {
		this.effective_time = effective_time;
	}
	public void setReturn_reason(String return_reason) {
		this.return_reason = return_reason;
	}
	public void setReturn_count(int return_count) {
		this.return_count = return_count;
	}
	public void setIs_locked(int is_locked) {
		this.is_locked = is_locked;
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
	@Column(name="spType")
	public String getSpType() {
		return spType;
	}
	public void setSpType(String spType) {
		this.spType = spType;
	}
	
}
