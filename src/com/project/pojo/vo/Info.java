package com.project.pojo.vo;

import com.project.pojo.Weuser;

public class Info {
	
	private int coachId;
	private String name="";
	private String personalitySignature="";		//个性签名
	private String head_portrait="";		//头像
	private String evaluate_score="0";		//'评价分数(几颗星星)'根据评价表统计分数
	private Integer verified = 0;			//实名认证  0 为未认证  1为已认证
	private int coachType;
	private Weuser user;

	public int getCoachId() {
		return coachId;
	}

	public Weuser getUser() {
		return user;
	}

	public void setCoachId(int coachId) {
		this.coachId = coachId;
	}

	public void setUser(Weuser user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}


	public String getPersonalitySignature() {
		return personalitySignature;
	}

	public String getHead_portrait() {
		return head_portrait;
	}

	public String getEvaluate_score() {
		return evaluate_score;
	}

	public Integer getVerified() {
		return verified;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setPersonalitySignature(String personalitySignature) {
		this.personalitySignature = personalitySignature;
	}

	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}

	public void setEvaluate_score(String evaluate_score) {
		this.evaluate_score = evaluate_score;
	}

	public void setVerified(Integer verified) {
		this.verified = verified;
	}

	public int getCoachType() {
		return coachType;
	}

	public void setCoachType(int coachType) {
		this.coachType = coachType;
	}
	
}
