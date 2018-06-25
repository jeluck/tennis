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


/**
 * 提现实体
 * 
 * @author daybreak
 * 
 */
@Entity
@Table(name = "withdraw_order")
public class WithdrawOrder   extends BaseEntity{
	private int id;
	private String req_sn;// 交易流水号,全局唯一
	private String serial_no;// 系统流水号
	private int withdrawer_role;// 提现者角色(1用户或者2场馆)			WithdrawerRole.WITHDRAWERUSER.getStatus()	,	WithdrawerRole.WITHDRAWERCOMPANY.getStatus()
	private String withdrawer;//提现者ID(用户或者场馆)
	private int withdrawType;	//提现方式:1银行卡,2支付宝			WithdrawType.BANKACCOUNT.getStatus(),	WithdrawType.APLIYACCOUNT.getStatus()
	private String account_num;		//账号
	private String realname;		//提现真实姓名
	private String wd_money;// 提现金额
	private String withdraw_rate;// 手续费
	private String amount_money;// 到帐金额
	private String amount_time;// 到帐时间
	private int wd_status;// 提现状态 1:审核中 2：转账中 3：成功 4：失败
	private String check_time;// 审核时间
	private String check_remark;// 审核意见
	private String submit_time;// 转账提交时间
	private Manager manager;		//管理员	审核人ID
	private String create_time;

	@Column(name="create_time")
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	/**
     * 临时变量
     */
    private String operaer;				//处理员
    /**
     * 临时变量
     */
    private Weuser weuser;				//用户信息

    @Id
    @GeneratedValue
    @Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "req_sn")
	public String getReq_sn() {
		return req_sn;
	}

	public void setReq_sn(String req_sn) {
		this.req_sn = req_sn;
	}

	@Column(name = "serial_no")
	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}

	@Column(name = "wd_money")
	public String getWd_money() {
		return wd_money;
	}

	public void setWd_money(String wd_money) {
		this.wd_money = wd_money;
	}

	@Column(name = "withdraw_rate")
	public String getWithdraw_rate() {
		return withdraw_rate;
	}

	public void setWithdraw_rate(String withdraw_rate) {
		this.withdraw_rate = withdraw_rate;
	}

	@Column(name = "amount_money")
	public String getAmount_money() {
		return amount_money;
	}

	public void setAmount_money(String amount_money) {
		this.amount_money = amount_money;
	}

	@Column(name = "amount_time")
	public String getAmount_time() {
		return amount_time;
	}

	public void setAmount_time(String amount_time) {
		this.amount_time = amount_time;
	}

	@Column(name = "wd_status" )
	public int getWd_status() {
		return wd_status;
	}

	public void setWd_status(int wd_status) {
		this.wd_status = wd_status;
	}

	@Column(name = "check_time")
	public String getCheck_time() {
		return check_time;
	}

	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}

	@Column(name = "check_remark")
	public String getCheck_remark() {
		return check_remark;
	}

	public void setCheck_remark(String check_remark) {
		this.check_remark = check_remark;
	}

	@Column(name = "submit_time")
	public String getSubmit_time() {
		return submit_time;
	}

	public void setSubmit_time(String submit_time) {
		this.submit_time = submit_time;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

    @Column(name = "withdraw_type")
	public int getWithdrawType() {
		return withdrawType;
	}

	public void setWithdrawType(int withdrawType) {
		this.withdrawType = withdrawType;
	}

	@Column(name = "account_num")
	public String getAccount_num() {
		return account_num;
	}

	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}

	@Column(name = "realname")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getWithdrawer_role() {
		return withdrawer_role;
	}

	public void setWithdrawer_role(int withdrawer_role) {
		this.withdrawer_role = withdrawer_role;
	}

	public String getWithdrawer() {
		return withdrawer;
	}

	public void setWithdrawer(String withdrawer) {
		this.withdrawer = withdrawer;
	}

    @Transient
	public Weuser getWeuser() {
		return weuser;
	}

	public void setWeuser(Weuser weuser) {
		this.weuser = weuser;
	}

	@Transient
	public String getOperaer() {
		return operaer;
	}

	public void setOperaer(String operaer) {
		this.operaer = operaer;
	}

}
