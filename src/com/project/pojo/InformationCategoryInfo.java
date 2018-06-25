package com.project.pojo;



import javax.persistence.*;

import com.project.util.CommonUtil;

/***
 * 资讯分类管理信息
 * @author lxc
 *
 */
@Entity
@Table(name = "category_info")
public class InformationCategoryInfo  extends BaseEntity {
    private int id;
    private String category_name;  //分类名称
	/**
	 * Constants.NORMAL_FLAG		
	 * Constants.DETELE_FLAG
	 */
    private int flag;    //标志位1 可用0 停用
    private String imgurl;			//分类图标
	
	
	
	
	
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

    @Column(name="category_name")
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Column(name = "flag")
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Column(name = "imgurl")
	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
}
