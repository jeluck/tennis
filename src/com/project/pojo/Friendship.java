package com.project.pojo;



import javax.persistence.*;

import com.project.util.CommonUtil;

/***
 * friendship
 * @author lxc
 *
 */
@Entity
@Table(name = "friendship")
public class Friendship  extends BaseEntity{
    private int id;
    private Weuser user_id;// '用户ID' ,
    private Weuser friend_user_id;// '好友ID' ,
	
	
	
	
	
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
	public Weuser getUser_id() {
		return user_id;
	}

	public void setUser_id(Weuser user_id) {
		this.user_id = user_id;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_user_id")
	public Weuser getFriend_user_id() {
		return friend_user_id;
	}

	public void setFriend_user_id(Weuser friend_user_id) {
		this.friend_user_id = friend_user_id;
	}
}
