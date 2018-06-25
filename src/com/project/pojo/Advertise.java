package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.util.CommonUtil;


/**
 * 广告实体类
 * @author daybreak
 * 
 */
@Entity
@Table(name = "advertise")
public class Advertise extends BaseEntity{
	private int id;
	private String ad_title;// 广告标题
	private String ad_http_url;// 广告路径
	private String ad_picture_url;// 广告图片路径
	private String start_time;// 开始有效期
	private String end_time;// 结束有效期
	private int sort_num;// 排序
	private int adtype;// 广告类型,展示的位置(1首页,2场馆列表,3网球圈,4赛事,5首页中间悬浮广告,6教练列表轮播图,7微信端首页端轮播图)		Constants.ADTYPE
	private int flag=1;//'状态,1为正常,0为关闭' ,			Constants.NORMAL_FLAG		Constants.DETELE_FLAG
	private String bg_color;//广告背景色
	
	
	
	
	
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
	
	public Advertise() {
	}

	public Advertise(int ad_id) {
		this.id = ad_id;
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

	@Column(name = "ad_title")
	public String getAd_title() {
		return ad_title;
	}

	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}

	@Column(name = "sort_num")
	public int getSort_num() {
		return sort_num;
	}

	public void setSort_num(int sort_num) {
		this.sort_num = sort_num;
	}

	@Column(name = "ad_http_url")
	public String getAd_http_url() {
		return ad_http_url;
	}

	public void setAd_http_url(String ad_http_url) {
		this.ad_http_url = ad_http_url;
	}

	@Column(name = "ad_picture_url")
	public String getAd_picture_url() {
		return ad_picture_url;
	}

	public void setAd_picture_url(String ad_picture_url) {
		this.ad_picture_url = ad_picture_url;
	}

	@Column(name = "start_time")
	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	@Column(name = "end_time")
	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	@Column(name = "adtype" )
	public int getAdtype() {
		return adtype;
	}

	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}

	@Column(name = "bg_color")
	public String getBg_color() {
		return bg_color;
	}

	public void setBg_color(String bg_color) {
		this.bg_color = bg_color;
	}

    @Column(name = "flag")
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
