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
 * 比赛,赛事表
 * @author lxc
 *
 */
@Entity
@Table(name = "events")
public class Events  extends BaseEntity{
	private int id;
	private String title;			//标题
	private String propaganda;		//宣传语
	private String conductor;		//举办商
	private String propagandaImg;	//宣传图片	
	private String ztitle;			//子标题
	private String gameLocation;	//比赛地点
	private double longitude=0;		// '坐标经度' ,
	private double latitude=0;		// '坐标纬度' ,
	private String context;			//介绍内容
	private String sign_time;			//开始报名时间
	private String end_time;			//截止时间
	private String time;				//比赛时间
	private String timeExplanation;			//比赛时间说明
	private String signTimeExplanation; 	//报名时间说明
	private double price;
	private String create_time=CommonUtil.getTimeNow();		//创建时间
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
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	@Column(name = "propaganda")
	public String getPropaganda() {
		return propaganda;
	}
	@Column(name = "conductor")
	public String getConductor() {
		return conductor;
	}
	@Column(name = "propagandaImg")
	public String getPropagandaImg() {
		return propagandaImg;
	}
	@Column(name = "ztitle")
	public String getZtitle() {
		return ztitle;
	}
	@Column(name = "timeExplanation")
	public String getTimeExplanation() {
		return timeExplanation;
	}
	@Column(name = "gameLocation")
	public String getGameLocation() {
		return gameLocation;
	}
	@Column(name = "longitude")
	public double getLongitude() {
		return longitude;
	}
	@Column(name = "latitude")
	public double getLatitude() {
		return latitude;
	}
	@Column(name = "signTimeExplanation")
	public String getSignTimeExplanation() {
		return signTimeExplanation;
	}
	@Column(name = "end_time")
	public String getEnd_time() {
		return end_time;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPropaganda(String propaganda) {
		this.propaganda = propaganda;
	}
	public void setConductor(String conductor) {
		this.conductor = conductor;
	}
	public void setPropagandaImg(String propagandaImg) {
		this.propagandaImg = propagandaImg;
	}
	public void setZtitle(String ztitle) {
		this.ztitle = ztitle;
	}
	public void setTimeExplanation(String timeExplanation) {
		this.timeExplanation = timeExplanation;
	}
	public void setGameLocation(String gameLocation) {
		this.gameLocation = gameLocation;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setSignTimeExplanation(String signTimeExplanation) {
		this.signTimeExplanation = signTimeExplanation;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	@Column(name = "context")
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	@Column(name = "sign_time")
	public String getSign_time() {
		return sign_time;
	}
	public void setSign_time(String sign_time) {
		this.sign_time = sign_time;
	}
	@Column(name = "price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Column(name = "time")
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
