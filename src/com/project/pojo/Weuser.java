package com.project.pojo;

import java.util.Date;

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
import org.springframework.transaction.annotation.Transactional;

import com.project.util.CommonUtil;
import com.project.util.SecurityUtil;


/***
 * 用户表
 * @author lxc
 *
 */
@Entity
@Table(name = "weuser_info")
public class Weuser   extends BaseEntity{
    private int    id;
    private String wenumber="";
    private String uphone="";// 手机
    private String pass="";// 密码
    private String username="";// 名字				
    private int    flag =1;// //用户状态，1启用(可以登录)，0禁用(不能登录)	Constants.NORMAL_FLAG	Constants.DETELE_FLAG
    private String last_login_ip="";// 最后登录IP
    private String last_login_time="";// 最后登录时间
    private int login_count=0;//登录次数统计
    private String real_name="";// 真实姓名
    private String idcard_no="";			//身份证号码
    private String head_photo="";			//头像
    private String idcard_photo_positive="";			//身份证照片  正面
    private String idcard_photo_anti="";			//身份证照片  反面
    private double amount=0d;				//用户账户里的金额(可用余额):recharge+totalcommission
    private double recharge=0d;				//(充值进去的钱,做记录)
    private String address="";				//个人住址
    private int score=0;					//积分
    private String invite_code="";				//邀请码
    private int age=0;						//年龄
    private int vip_type = 0;				//0为普通会员,1为高级会员,2为白金会员
    private String alipay_realname="";		//支付宝真实姓名
    private String alipay_account="";			//支付宝账号
    private String alipay_account_show="";
    private String bankcard_realname="";		//银行卡真实姓名
    private BankInfo bankinfo;				//银行
    private String bankinfoid="";				//银行id
    private String cardnumber="";				//银行卡号
	private double coordinateslongitude=0;	// '坐标经度' ,
	private double coordinateslatitude=0;		// '坐标纬度' ,
    
	private String sex="男";				//性别
	private String birthday;    	//生日
	private String signature="";		//签名
	private Integer cityid;				//所在城市
	private Integer areaid;				//所在区
	private Integer hometown;			//籍贯
	private String school="";			//学校
	private String employer="";		//工作单位
	private int idcard_status=0;	//身份认证状态  0为：未认证  1为：已认证
	private String tennis_level="";		//网球级别

	
    //临时变量
    private String card_num_show="";
	//临时变量
	private String encryptuphone="";			//加密后的电话
	//临时变量
	private Coach coach; 					//教练对象
	
	private Integer is_coach=0;				//是否是教练0,为用户1,为教练				Constants.IS_COACH0
	

	private String update_time=CommonUtil.getTimeNow();			//更新时间
	private String create_time=CommonUtil.getTimeNow();		//创建时间
	
	private User_vip vip;
	private String vip_img=""; 
	private int vip_id=0;
	
	@Column(name="is_coach")
    public Integer getIs_coach() {
		return is_coach;
	}

	public void setIs_coach(Integer is_coach) {
		this.is_coach = is_coach;
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

    @Column(name = "wenumber")
    public String getWenumber() {
        return wenumber;
    }
    
    public void setWenumber(String wenumber) {
        this.wenumber = wenumber;
    }

    @Column(name="uphone")
    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
        this.encryptuphone = SecurityUtil.encrypt(uphone);
    }

    @Column(name="pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Column(name="username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="flag")
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Column(name = "last_login_ip")
    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    @Column(name = "login_count")
    public int getLogin_count() {
        return login_count;
    }

    public void setLogin_count(int login_count) {
        this.login_count = login_count;
    }

    @Column(name = "last_login_time")
    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    @Column(name="idcard_no")
    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    @Column(name="head_photo")
    public String getHead_photo() {
        return head_photo;
    }

    public void setHead_photo(String head_photo) {
        this.head_photo = head_photo;
    }


    @Column(name="amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
    	java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
    	amount = Double.parseDouble(df.format(amount));
    	this.amount = amount;
    }

    @Column(name="address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
	@Column(name = "real_name")
	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Column(name = "recharge")
	public double getRecharge() {
		return recharge;
	}

	public void setRecharge(double recharge) {
		this.recharge = recharge;
	}
	
    @Column(name = "invite_code")
	public String getInvite_code() {
		return invite_code;
	}

	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}
	
	@Column(name="alipay_realname")
	public String getAlipay_realname() {
		return alipay_realname;
	}

	public void setAlipay_realname(String alipay_realname) {
		this.alipay_realname = alipay_realname;
	}

	@Column(name="alipay_account")
	public String getAlipay_account() {
		return alipay_account;
	}

	public void setAlipay_account(String alipay_account) {
		this.alipay_account = alipay_account;
		this.alipay_account_show = alipay_account != null && !alipay_account.trim().equals("") ? alipay_account
				.substring(0, 4)
				+ "***********"
				+ alipay_account.substring(alipay_account.length() - 4, alipay_account.length())
				: alipay_account;
	}

	@Column(name="bankcard_realname")
	public String getBankcard_realname() {
		return bankcard_realname;
	}

	public void setBankcard_realname(String bankcard_realname) {
		this.bankcard_realname = bankcard_realname;
	}

	@Transient
	public BankInfo getBankinfo() {
		return bankinfo;
	}

	public void setBankinfo(BankInfo bankinfo) {
		this.bankinfo = bankinfo;
	}

	@Column(name="cardnumber")
	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
		this.card_num_show = cardnumber != null && !cardnumber.trim().equals("") ? cardnumber
				.substring(0, 4)
				+ "***********"
				+ cardnumber.substring(cardnumber.length() - 4, cardnumber.length())
				: cardnumber;
	}

	@Transient
	public String getCard_num_show() {
		return card_num_show;
	}

	public void setCard_num_show(String card_num_show) {
		this.card_num_show = card_num_show;
	}

	@Transient
	public String getAlipay_account_show() {
		return alipay_account_show;
	}

	public void setAlipay_account_show(String alipay_account_show) {
		this.alipay_account_show = alipay_account_show;
	}

	@Column(name="bankinfoid")
	public String getBankinfoid() {
		return bankinfoid;
	}

	public void setBankinfoid(String bankinfoid) {
		this.bankinfoid = bankinfoid;
	}

	@Transient
	public String getEncryptuphone() {
		return encryptuphone;
	}

	public void setEncryptuphone(String encryptuphone) {
		this.encryptuphone = encryptuphone;
	}

	@Column(name="age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(name="vip_type")
	public int getVip_type() {
		return vip_type;
	}

	public void setVip_type(int vip_type) {
		this.vip_type = vip_type;
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

	@Column(name="sex")
	public String getSex() {
		return sex;
	}

	@Column(name="birthday")
	public String getBirthday() {
		return birthday;
	}

	@Column(name="signature")
	public String getSignature() {
		return signature;
	}

	@Column(name="cityid")
	public Integer getCityid() {
		return cityid;
	}

	@Column(name="hometown")
	public Integer getHometown() {
		return hometown;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public void setHometown(Integer hometown) {
		this.hometown = hometown;
	}

	@Column(name="school")
	public String getSchool() {
		return school;
	}

	@Column(name="employer")
	public String getEmployer() {
		return employer;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	@Column(name="idcard_status")
	public int getIdcard_status() {
		return idcard_status;
	}

	@Column(name="tennis_level")
	public String getTennis_level() {
		return tennis_level;
	}

	public void setIdcard_status(int idcard_status) {
		this.idcard_status = idcard_status;
	}

	public void setTennis_level(String tennis_level) {
		this.tennis_level = tennis_level;
	}

	@Column(name="idcard_photo_positive")
	public String getIdcard_photo_positive() {
		return idcard_photo_positive;
	}

	@Column(name="idcard_photo_anti")
	public String getIdcard_photo_anti() {
		return idcard_photo_anti;
	}

	public void setIdcard_photo_positive(String idcard_photo_positive) {
		this.idcard_photo_positive = idcard_photo_positive;
	}

	public void setIdcard_photo_anti(String idcard_photo_anti) {
		this.idcard_photo_anti = idcard_photo_anti;
	}
	
	@Transient
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	@Column(name="areaid")
	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vip")
    @JsonIgnore
	public User_vip getVip() {
		return vip;
	}
	@Transient
	public String getVip_img() {
		return vip_img;
	}
	public void setVip(User_vip vip) {
		this.vip = vip;
	}

	public void setVip_img(String vip_img) {
		this.vip_img = vip_img;
	}

	@Column(name="vip_id")
	public int getVip_id() {
		return vip_id;
	}

	public void setVip_id(int vip_id) {
		this.vip_id = vip_id;
	}
	
	
}
