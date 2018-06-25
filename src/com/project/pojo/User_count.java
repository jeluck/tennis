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
 * user_count
 * @author lxc
 *
 */
@Entity
@Table(name = "user_count")
public class User_count   extends BaseEntity {
	private int id;
    private Weuser weuser;      //用户
    private int reservecount=0;// '预定次数' ,
    private int playtimecount=0;// '打球时间总数' ,
    private int joineventscount=0;// '参与活动次数' ,
    private int invitecount=0;// '邀请人数' ,
    private int join_clubcount=0;// '参与俱乐部个数' ,
    private int join_teamcount=0;// '参与球队个数' ,
    private int attentecount=0;// '关注人数' ,
    private int beconcernedcount=0;// '被关注数' ,
    private int traineecount=0;//学员人数
	private int lessonCount=0;//教练累计课时  
	private int userlessonCount=0;//用户训练课时
	private int subjectCount=0;//教练课时次数  
	private int trainingtimes=0; //用户训练次数
	
	
	
	
	@Column(name = "lessonCount")
	public int getLessonCount() {
		return lessonCount;
	}
	public void setLessonCount(int lessonCount) {
		this.lessonCount = lessonCount;
	}
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weuser_id")
	public Weuser getWeuser() {
		return weuser;
	}

	public void setWeuser(Weuser weuser) {
		this.weuser = weuser;
	}
	
    @Column(name="reservecount")
	public int getReservecount() {
		return reservecount;
	}

	public void setReservecount(int reservecount) {
		this.reservecount = reservecount;
	}
	
    @Column(name="playtimecount")
	public int getPlaytimecount() {
		return playtimecount;
	}

	public void setPlaytimecount(int playtimecount) {
		this.playtimecount = playtimecount;
	}
	
    @Column(name="joineventscount")
	public int getJoineventscount() {
		return joineventscount;
	}

	public void setJoineventscount(int joineventscount) {
		this.joineventscount = joineventscount;
	}
	
    @Column(name="invitecount")
	public int getInvitecount() {
		return invitecount;
	}

	public void setInvitecount(int invitecount) {
		this.invitecount = invitecount;
	}
	
    @Column(name="join_clubcount")
	public int getJoin_clubcount() {
		return join_clubcount;
	}

	public void setJoin_clubcount(int join_clubcount) {
		this.join_clubcount = join_clubcount;
	}
	
    @Column(name="join_teamcount")
	public int getJoin_teamcount() {
		return join_teamcount;
	}

	public void setJoin_teamcount(int join_teamcount) {
		this.join_teamcount = join_teamcount;
	}
	
    @Column(name="attentecount")
	public int getAttentecount() {
		return attentecount;
	}

	public void setAttentecount(int attentecount) {
		this.attentecount = attentecount;
	}
	
    @Column(name="beconcernedcount")
	public int getBeconcernedcount() {
		return beconcernedcount;
	}

	public void setBeconcernedcount(int beconcernedcount) {
		this.beconcernedcount = beconcernedcount;
	}
	
	@Column(name="traineecount")
	public int getTraineecount() {
		return traineecount;
	}
	public void setTraineecount(int traineecount) {
		this.traineecount = traineecount;
	}
	
	@Column(name="subjectCount")
	public int getSubjectCount() {
		return subjectCount;
	}
	public void setSubjectCount(int subjectCount) {
		this.subjectCount = subjectCount;
	}
	
	@Column(name = "trainingtimes")
	public int getTrainingtimes() {
		return trainingtimes;
	}
	public void setTrainingtimes(int trainingtimes) {
		this.trainingtimes = trainingtimes;
	}
	@Column(name="userlessonCount")
	public int getUserlessonCount() {
		return userlessonCount;
	}
	public void setUserlessonCount(int userlessonCount) {
		this.userlessonCount = userlessonCount;
	}
}
