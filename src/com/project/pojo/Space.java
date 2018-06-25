package com.project.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
 * 场地
 * 
 * @author lxc
 */
@Entity
@Table(name = "space")
public class Space  extends BaseEntity{
	private int id;
	private String name;	
	private String spaceNo;				//场地编号
	private Playground playground_id;	//场馆ID
	private Coach coach_id=null;				//教练ID
	private Integer belongto=0;			//属于场馆还是教练    0，为场馆1位教练
	private SpaceManager spacemanager_id;	//场地管理者ID
	private String spacetype;		// '场地类型(红土/硬土)' ,
	private String in_out;		// '室内、室外' ,
	private float price=0;		// 场地的单价
	private float vip_price=0;		// 场地的会员优惠价
	private String court_type="";		//球场类型    为普通,智能
	
	private List<Space_time_price> stpList=new ArrayList<Space_time_price>();
	@Column(name = "court_type")
	public String getCourt_type() {
		return court_type;
	}
	public void setCourt_type(String court_type) {
		this.court_type = court_type;
	}
	@Transient
	public List<Space_time_price> getStpList() {
		return stpList;
	}
	public void setStpList(List<Space_time_price> stpList) {
		this.stpList = stpList;
	}

	private String update_time=CommonUtil.getTimeNow();			//更新时间
	private String create_time=CommonUtil.getTimeNow();		//创建时间
	
	@Column(name = "belongto")
	public Integer getBelongto() {
		return belongto;
	}
	public void setBelongto(Integer belongto) {
		this.belongto = belongto;
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

	@Column(name="spaceNo")
	public String getSpaceNo() {
		return spaceNo;
	}

	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playground_id")
	public Playground getPlayground_id() {
		return playground_id;
	}

	public void setPlayground_id(Playground playground_id) {
		this.playground_id = playground_id;
	}
	
    @ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.PERSIST})
    @JoinColumn(name = "coach_id")
    @JsonIgnore
	public Coach getCoach_id() {
		return coach_id;
	}

	public void setCoach_id(Coach coach_id) {
		this.coach_id = coach_id;
	}
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spacemanager_id")
    @JsonIgnore
	public SpaceManager getSpacemanager_id() {
		return spacemanager_id;
	}

	public void setSpacemanager_id(SpaceManager spacemanager_id) {
		this.spacemanager_id = spacemanager_id;
	}

	@Column(name="spacetype")
	public String getSpacetype() {
		return spacetype;
	}

	public void setSpacetype(String spacetype) {
		this.spacetype = spacetype;
	}

	@Column(name="in_out")
	public String getIn_out() {
		return in_out;
	}

	public void setIn_out(String in_out) {
		this.in_out = in_out;
	}
	
	@Column(name="price")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Column(name="vip_price")
	public float getVip_price() {
		return vip_price;
	}

	public void setVip_price(float vip_price) {
		this.vip_price = vip_price;
	}


}
