package com.project.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.project.util.CommonUtil;

/**
 * 网站通告实体(头条)
 * @author lxc
 *
 */
@Entity
@Table(name = "notice")
public class Notice extends BaseEntity{
	private int id;
	private String title="";
	private String content="";
	private int creater_uid;
	private int type;//新闻类型：1:PC 2：Mobile
	private String isRecently="";	//是否是最新的(8天内的) 0 是 1 否
	private int city_show_id;//'关联region表的 region_id' ,
	
	private int province_id; // 省份编号
	//临时变量
	private Manager manager;	//管理员对象
	
	
	
	
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

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "creater_uid" )
	public int getCreater_uid() {
		return creater_uid;
	}

	public void setCreater_uid(int creater_uid) {
		this.creater_uid = creater_uid;
	}
	
	@Column(name = "type" )
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIsRecently() {
		return isRecently;
	}

	public void setIsRecently(String isRecently) {
		this.isRecently = isRecently;
	}

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
        try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long time = sdf.parse(create_time).getTime();
			long cur_time = System.currentTimeMillis();
			long ar = 8l*24l*60l*60l*1000l;
			if (cur_time - time <= ar) {
				this.isRecently="0";
			} else {
				this.isRecently="1";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			this.isRecently="1";
		}
    }
    
	@Column(name = "city_show_id")
	public int getCity_show_id() {
		return city_show_id;
	}

	public void setCity_show_id(int city_show_id) {
		this.city_show_id = city_show_id;
	}
	
	@Column(name = "province_id")
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	
	@Transient
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
