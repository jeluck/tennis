package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bank_card")
public class BankCard{
	private int id;
	private String account_name;// 开户名
	private int card_status;// 银行卡审核状态：1:待审核 2：通过 3:未通过
	private int bank_province;// 开户行所在省
	private int bank_city;// 开户行所在市
	private int bank_area;// 开户行所在区
	private String bank_address;// 开户行地址
	private String card_num;// 卡号
	private String card_remark;// 审核意见

	private BankInfo bank;
	private String card_num_show;
	private Weuser weuser;
    /**
     * 更新时间
     */
    private String update_time;
    /**
     * 创建时间
     */
    private String create_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_info_id")
	public BankInfo getBank() {
		return bank;
	}

	public void setBank(BankInfo bank) {
		this.bank = bank;
	}
	
	public void setCard_num_show(String card_num_show) {
		this.card_num_show = card_num_show;
	}

	public String getCard_num_show() {
		return card_num_show;
	}

    @Id
    @GeneratedValue
	@Column(name = "id" )
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "account_name")
	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	@Column(name = "bank_province" )
	public int getBank_province() {
		return bank_province;
	}

	public void setBank_province(int bank_province) {
		this.bank_province = bank_province;
	}

	@Column(name = "bank_city" )
	public int getBank_city() {
		return bank_city;
	}

	public void setBank_city(int bank_city) {
		this.bank_city = bank_city;
	}

	@Column(name = "bank_address")
	public String getBank_address() {
		return bank_address;
	}

	public void setBank_address(String bank_address) {
		this.bank_address = bank_address;
	}

	@Column(name = "card_num")
	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
		this.card_num_show = card_num != null && !card_num.trim().equals("") ? card_num
				.substring(0, 4)
				+ "***********"
				+ card_num.substring(card_num.length() - 4, card_num.length())
				: card_num;
	}

	@Column(name = "card_status" )
	public int getCard_status() {
		return card_status;
	}

	public void setCard_status(int card_status) {
		this.card_status = card_status;
	}

	@Column(name = "card_remark")
	public String getCard_remark() {
		return card_remark;
	}

	public void setCard_remark(String card_remark) {
		this.card_remark = card_remark;
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

    @Column(name = "bank_area")
	public int getBank_area() {
		return bank_area;
	}

	public void setBank_area(int bank_area) {
		this.bank_area = bank_area;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weuser_id")
	public Weuser getWeuser() {
		return weuser;
	}

	public void setWeuser(Weuser weuser) {
		this.weuser = weuser;
	}

}
