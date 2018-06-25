package com.project.pojo;

import javax.persistence.*;

@Entity
@Table(name = "region")
public class Region {
    private int region_id;
    private String region_code;
    private String region_name;
    private int parent_id;
    private int region_level;
    private int region_order;
    private String region_name_en;
    private String region_shortname_en;
	private Integer status=0;//状态,1为热门,0开放' ,			 Constants.NORMAL_FLAG	 Constants.DETELE_FLAG

    @Id
    @Column(name="region_id")
    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    @Column(name="region_code")
    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    @Column(name="region_name")
    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    @Column(name="parent_id")
    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    @Column(name="region_level")
    public int getRegion_level() {
        return region_level;
    }

    public void setRegion_level(int region_level) {
        this.region_level = region_level;
    }

    @Column(name="region_order")
    public int getRegion_order() {
        return region_order;
    }

    public void setRegion_order(int region_order) {
        this.region_order = region_order;
    }

    @Column(name="region_name_en")
    public String getRegion_name_en() {
        return region_name_en;
    }

    public void setRegion_name_en(String region_name_en) {
        this.region_name_en = region_name_en;
    }

    @Column(name="region_shortname_en")
    public String getRegion_shortname_en() {
        return region_shortname_en;
    }

    public void setRegion_shortname_en(String region_shortname_en) {
        this.region_shortname_en = region_shortname_en;
    }

    @Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
