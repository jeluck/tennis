package com.project.pojo;

public class VerifyCode {
	private String phone ;
	private String verifycode;
	private long createtime;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVerifycode() {
		return verifycode;
	}
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	/**
	 * @return the create_time
	 */
	public long getCreatetime() {
		return createtime;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreatetime(long create_time) {
		this.createtime = create_time;
	}
}
