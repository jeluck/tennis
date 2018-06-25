package com.project.common;


import java.io.File;


public class Constants {

	/**
	 * pageSize	后台每页显示条数		10
	 */
	public static final int BACKGROUND_PAGESIZE = 10;
	
	/**
	 * pageSize	前台每页显示条数		5
	 */
	public static final int FOREGROUND_PAGESIZE = 10;
	
	public static final String RETURN_REASON_TIME_OUT = "营业时间过期";
	
    //************************** 订单操作结果 **************************************/
    /** 操作成功 */ 
    public static final int    OPERATE_SUCCESS              = 1;
    /** 操作失败 */ 
    public static final int    OPERATE_FAIL                 = 2;
    /** 操作失败 0*/ 
    public static final int    OPERATE_FAIL0                 = 0;
    
    /**
     * 1未
     *  是否结算,补贴
     */
    public static final int NOTTODO =1;
    /**
     * 0是
     *  是否结算,补贴
     */
    public static final int OKTODO =0;
    
    
    /**
     * 1
     *  1正常使用 	0已删除
     */
    public static final int NORMAL_FLAG =1;
    /**
     * 0
     *  1 正常使用 	0已删除
     */
    public static final int DETELE_FLAG =0;
    
    /**
     *	是否可以预定  	1	(0不可以,1可以)
     */
    public static final int IS_RESERVE1 =1;
    /**
     * 是否可以预定	 0	 (0不可以,1可以)
     */
    public static final int IS_RESERVE0 =0;

    /**
     *	是否需要教练,默认为0不用,1需要.' ,  	0	
     */
    public static final int MUST_COACH0 =0;
    /**
     * 是否需要教练,默认为0不用,1需要.' ,		1
     */
    public static final int MUST_COACH1 =1;
    
    /**
     * 0
     *是否置顶，0否，1是,
     */
    public static final int STICK0 =0;
    /**
     * 1
     * 是否置顶，0否，1是,.' ,
     */
    public static final int STICK1 =1;
    
    /**
     * 1,
	 * 1为创建,2为加入
	 */
	public static final int CREATE_TEAM = 1;
	
	/**
	 * 2,
	 * 1为创建,2为加入
	 */
	public static final int JOIN_TEAM = 2;
	
    /**
     * 1
		场馆管理者类型：// 1为场馆管理者，2场馆管理内的其他人（清洁，人事，行政等等）
     */
    public static final int PLAYGROUND_MANAGER_TYPE1 =1;
    /**
     * 2
     * 场馆管理者类型：// 1为场馆管理者，2场馆管理内的其他人（清洁，人事，行政等等）
     */
    public static final int PLAYGROUND_MANAGER_TYPE2 =2;
	
	/**
	 * 成功
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * 交易类型:1为充值,2为支付,3为收入
	 */
	public static final int RECHARGE_TRADE_TYPE = 1;
	/***
	 * 交易类型:1为充值,2为支付,3为收入
	 */
	public static final int PAY_TRADE_TYPE = 2;
	/***
	 * 交易类型:1为充值,2为支付,3为收入
	 */
	public static final int INCOME_TYPE = 3;
	/***
	 * 交易类型:1为充值,2为支付,3为收入,4为退款
	 */
	public static final int REFUND = 4;
	
	
	/***
	 * 证书类型:1为教练,2为场馆
	 */
	public static final int CERTIFICATE_COACH = 1;
	/***
	 * 证书类型:1为教练,2为场馆
	 */
	public static final int CERTIFICATE_VENUE = 2;
	
	/***
	 * 补贴类型:1为教练,2为场馆
	 */
	public static final int SUBSIDIES_COACH = 1;
	/***
	 * 补贴类型:1为教练,2为场馆
	 */
	public static final int SUBSIDIES_PlAYGROUND = 2;
	
	
	/***
	 * 补贴类型:1为月,2为季度,3为半年
	 */
	public static final int SUBSIDIES_APRIL = 1;
	/***
	 * 补贴类型:1为月,2为季度,3为半年
	 */
	public static final int SUBSIDIES_QUARTER = 2;
	/***
	 * 补贴类型:1为月,2为季度,3为半年
	 */
	public static final int SUBSIDIES_HALFYEAR = 3;
	
	/***
	 * 场馆审核状态:1.申请2.通过3.拒绝4.驳回 
	 */
	public static final int AUDITSTATUS_APPLY = 1;
	/***
	 * 场馆审核状态:1.申请2.通过3.拒绝4.驳回 
	 */
	public static final int AUDITSTATUS_THROUGH = 2;
	/***
	 * 场馆审核状态:1.申请2.通过3.拒绝4.驳回 
	 */
	public static final int AUDITSTATUS_REFUSE = 3;
	/***
	 * 场馆审核状态:1.申请2.通过3.拒绝4.驳回 
	 */
	public static final int AUDITSTATUS_REJECT = 4;
	
	/***
	 * 服务类型:1为教练,2为场馆
	 */
	public static final int SERVICE_COACH = 1;
	/***
	 * 服务类型:1为教练,2为场馆
	 */
	public static final int SERVICE_VENUE = 2;
	
	/***
	 * 模版类型:1为显示 2为不显示	
	 */
	public static final int MODULE_SHOW = 1;
	/***
	 * 模版类型:1为显示 2为不显示	
	 */
	public static final int MODULE_NONE = 0;
	
	/**
	 * 0
	 * 是否是教练0,为用户1,为教练
	 */
	public static final int IS_COACH0=0;
	
	/**
	 * 1
	 * 是否是教练0,为用户1,为教练
	 */
	public static final int IS_COACH1=1;
	
	/*
	 * 教练的默认开始工作时间
	 */
	public static final int COACH_STARTTEACHTIME=6;
	/**
	 * 教练的默认结束工作时间
	 */
	public static final int COACH_ENDTEACHTIME=24;
    
	public enum AuthType {
		/**
		 * 后台登录权限
		 */
		ADMIN_LOGIN(1),
		/**
		 * 正在被邀请
		 */
		AJAX(2);
		private int type;

		private AuthType(int str) {
			type = str;
		}

		public static AuthType typeOf(int type) {
			for (AuthType c : AuthType.values()) {
				if (c.type == type) {
					return c;
				}
			}
			return null;
		}

		public int getValue() {
			return type;
		}
	}

	public enum ResponseCode {
		SUCCESS("成功", 0), EMPTY_DATA("没有数据", 1000), ERROR_MSG("出错", 1001), REPEATE_DATA(
				"重复数据", 1002);
		private String name;
		private int type;

		private ResponseCode(String name, int type) {
			this.name = name;
			this.type = type;
		}

		public static String getName(int type) {
			for (ResponseCode c : ResponseCode.values()) {
				if (c.getType() == type) {
					return c.name;
				}
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}
	}

	/**
	 * 移动端的响应状态
	 *  
	 * @author strawxdl
	 * 
	 */
	public enum ResponseStatus {
		SUCCESS("成功", 1000), EMPTY("没有数据", 1001), ERROT_MSG("出错", 1002), NO_LOGIN(
				"未登录", 1003), SAFE_INFO_NO_AUTH("安全信息未认证", 1004);
		private String msg;
		private int status;

		private ResponseStatus(String msg, int status) {
			this.msg = msg;
			this.status = status;
		}

		public static ResponseStatus statusOf(int status) {
			for (ResponseStatus c : ResponseStatus.values()) {
				if (c.getStatus() == status) {
					return c;
				}
			}
			return null;
		}

		public String getMsg() {
			return msg;
		}

		public int getStatus() {
			return status;
		}
	}

	
	public enum OrderEvaluationStatus
	{
		NEW("未评论的", 0), HAVE_EVALUATION("已评论的", 1)
		;

		private String msg;
		private int status;

		private OrderEvaluationStatus(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}

	/**
	 * 返回说明.
	 * @author Administrator
	 *
	 */
	public enum OperationResult
	{
		SUCCESS("", 0)
		,USER_NOT_EXIST("账户不存在", 1), INVALID_PASSWORD("密码错误", 2), UNKNOWN_MISTAKE("未知错误", 7), USED_USER_PHONE("账户已注册",9)
		,SEND_CODE_FAILED("验证码发送失败",10), INVALID_CODE("验证码错误",11), NONEXISTENT_PRODUCT("贷品不存在", 12)
		,ORDER_NOT_EXIST("订单不存在",13),NOT_LOGIN("请先登录", 14)
		,Withdraw_WAIT_FOR_CHECK("您已经成功提交提现审核", 15), Withdraw_FAIL("提交提现审核失败", 16), STORE_NOT_ENOUGH("库存不足", 17)
		,LOSE_EFFICACY_CODE("验证码失效", 18),LOCK_ACCOUNT("用户锁定,联系客服", 19)
		,INVALID_PAGE_PARAMETER("页面参数错误", 20)
		,INVALID_PARAMETER("参数错误",21)
		,ORDER_PAID("订单已支付,请勿重复支付", 22)
		,NO_MORE_DATA("没有更多数据", 23)
		,SYSTEM_PARAMETER_ERROR("系统参数错误", 24)
		,INCOMPLETE_INFORMATION("个人信息不完整", 25)
		,WAIT_FOR_CHECK("请等待管理员审核", 26)
		,WRONG_ID_NUMBER("身份证号码不正确", 27),STATUS_EDITED_NO_SUBMIT("状态已修改,请勿重复提交", 28)
		,NO_DATA_NOW("暂无数据", 29)
		,SYSTEM_ERROR("系统错误", 30)
		,LACK_FOR_BALANCE("账户余额不足", 31),COLLECT_SUCCESS("收藏成功", 32),COLLECT_CANCLE("取消收藏", 33)
		,NOSTPID("无场地时间价格ID",34),DELETE("订单已作废",35),SETDATETABLE("设置日程时间表",36)
		,ORDER_PAID_OR_NO_NUll("订单已支付或者订单不存在", 37),IS_VIP("您已经是VIP请勿重复充值",38);


		private String msg;
		private int status;

		private OperationResult(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public static String msgOf(int status) {
			for (OperationResult r : OperationResult.values()) {
				if (r.getStatus() == status) {
					return r.getMsg();
				}
			}
			return null;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	
	/**
	 * 发送类型:0所有用户,1手动输入,2用户等级筛选(n以下,n-m区间,n以上),3,用户,4教练,5场馆主,6活动....
	 */
	public enum SEND_TYPE
	{
		ALL_USER("消息", 0)
		,HANDLE_USER("云推送", 1)
		,LEVEL_USER("云推送", 2)
		,JUST_USER("云推送", 3)
		,JUST_COACH("云推送", 4)
		,JUST_PGM("云推送", 5)
		,ACTIVE("云推送", 6)
		;
		private String msg;
		private int status;
		private SEND_TYPE(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}
		public static String msgOf(int status) {
			for (SEND_TYPE r : SEND_TYPE.values()) {
				if (r.getStatus() == status) {
					return r.getMsg();
				}
			}
			return null;
		}
		public String getMsg() {return msg;}
		public int getStatus() {return status;}
	}

	/**
	 * 0为消息,1为云推送
	 */
	public enum MES_CLOUD_TYPE
	{
		MES("消息", 0)
		,CLOUD("云推送", 1)
		,PHONEMES("短信", 2)
		;
		private String msg;
		private int status;
		private MES_CLOUD_TYPE(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}
		public static String msgOf(int status) {
			for (MES_CLOUD_TYPE r : MES_CLOUD_TYPE.values()) {
				if (r.getStatus() == status) {
					return r.getMsg();
				}
			}
			return null;
		}
		public String getMsg() {return msg;}
		public int getStatus() {return status;}
	}
	
	
	public enum EvaluationCategory
	{
		GOOD("好评", 0)
		,BAD("差评", 1)
		;


		private String msg;
		private int status;

		private EvaluationCategory(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public static String msgOf(int status) {
			for (EvaluationCategory r : EvaluationCategory.values()) {
				if (r.getStatus() == status) {
					return r.getMsg();
				}
			}
			return null;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	
	public enum O_STATUS
	{
		/**
		 * 审核状态：1:待审核 2：通过 3:未通过
		 */
		WAIT_FOR_CHECK("待审核", 1),PASS_FOR_CHECK("通过", 2),NOPASS_FOR_CHECK("未通过", 3);


		private String msg;
		private int status;

		private O_STATUS(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public static String msgOf(int status) {
			for (O_STATUS r : O_STATUS.values()) {
				if (r.getStatus() == status) {
					return r.getMsg();
				}
			}
			return null;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}

	public enum MessageReadStatus
	{
		NOT_READ("未阅读的", 0), HAVE_READ("已阅读的", 1)
		;

		private String msg;
		private int status;

		private MessageReadStatus(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}

	public enum WithdrawOrderResult
	{
		/**
		 * 提现状态 1:审核中 2：转账中 3：成功 4：失败
		 */
		WAIT_FOR_CHECK("审核中",1),TRANSFER("转账中", 2),SUCCESS("成功", 3)
		,FAIL("失败", 4);


		private String msg;
		private int status;

		private WithdrawOrderResult(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public static String msgOf(int status) {
			for (WithdrawOrderResult r : WithdrawOrderResult.values()) {
				if (r.getStatus() == status) {
					return r.getMsg();
				}
			}
			return null;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}

	 /**      
     * 未支付1;  部分支付 2;  已支付 3;  执行中4;	已完成5;		退款申请中 6;  部分退款 7;  全额退款 8;  退款失败9;  作废10;  已评价11; 现场支付12(不计入补贴)*/
	public enum OrderStatus
	{
		 /** 状态  
	     * 未支付1;  部分支付 2;  已支付 3;  执行中4;	已完成5;		退款申请中 6;  部分退款 7;  全额退款 8;  退款失败9;  作废10;  已评价11; 现场支付12(不计入补贴)*/
		PAY_STAY_PAID("未支付",1),PAY_PART("部分支付",2),PAY_PAID("已支付",3), EXECUTION_ING("执行中",4),BASE_FINISH("已完成",5),
		PAY_REFUND_APPLY("退款申请中",6),PAY_REFUND_PART("部分退款",7),PAY_REFUND_ALL("全额退款",8),PAY_REFUND_FAIL("退款失败",9),
		DELETE("作废",10),COMMENTED("已评价",11),LIVE_PAY("现场支付",12);
		
		private String msg;
		private int status;

		private OrderStatus(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public static String msgOf(int status) {
			for (OrderStatus r : OrderStatus.values()) {
				if (r.getStatus() == status) {
					return r.getMsg();
				}
			}
			return null;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}

	/**
	 * 提现方式,账号类型,提现时用到,1为银行账号;2为支付宝账号
	 * @author Administrator
	 *
	 */
	public enum WithdrawType
	{
		BANKACCOUNT("银行账号", 1), APLIYACCOUNT("支付宝账号", 2)
		;

		private String msg;
		private int status;

		private WithdrawType(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	
	/**
	 * 支付方式:	1微信,2支付宝
	 * @author Administrator
	 *
	 */
	public enum PayType
	{
		BALANCE("余额支付", 0),WECHATPAY("微信支付", 1), APLIYPAY("支付宝支付", 2)
		;

		private String msg;
		private int status;

		private PayType(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	

	/***
	 * 日记信息标识  ==========
	 */
	public static final String logInfoSer= "    ==========    ";
	
	/**
	 * 异常信息标识   >
	 */
	public static final String exceptionMsgSplit = " > ";
	
	
	/**
	 * 1课程,2培训
	 */
	public enum CourseType
	{
		COURSE("课程", 1), TRAINING("培训", 2)
		;

		private String msg;
		private int status;

		private CourseType(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	
	
	/**
	 * 操作角色
	 * 1用户,2平台,3场馆
	 * @author lxc
	 *
	 */
	public enum AUTHOD_TYPE
	{
		//1用户
		WEUSER("用户", 1),
		/**
		 * 2平台
		 */
		MANAGER("平台", 2),
		/**
		 * 3场馆
		 */
		PLAYGROUNDMANAGER("场馆", 3)
		;

		private String msg;
		private int status;

		private AUTHOD_TYPE(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	
	/**
	 * 数据类型
	 * 1场馆,2教练,3活动,4培训,5赛事类型
	 * @author lxc
	 *
	 */
	public enum DATATYPE
	{
		//1场馆类型
		PlaygroundType("场馆订单类型", 1),
		/**
		 * 2教练类型
		 */
		CoachType("教练订单类型", 2),
		/**
		 * 3活动类型
		 */
		ActivityType("活动订单类型", 3),
		/**
		 * 4课程/培训类型
		 */
		CourseType("活动类型", 4),
		/**
		 * 5赛事类型
		 */
		EventsType("赛事",5)
		;

		private String msg;
		private int status;

		private DATATYPE(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	
	
	/**
	 * 广告类型,
	 * 展示的位置(1首页,2场馆列表,3网球圈,4赛事,5首页中间悬浮广告)
	 * @author lxc
	 *
	 */
	public enum ADTYPE
	{
		//1首页
		INDEX("首页", 1),
		/**
		 * 2场馆列表
		 */
		PLAYGROUNDLIST("场馆列表", 2),
		/**
		 * 3网球圈
		 */
		TENNISCIRCLE("网球圈", 3),
		/**
		 * 4赛事
		 */
		EVENTS("赛事", 4),
		/**
		 * 5首页中间悬浮广告
		 */
		FLOATING("首页中间悬浮广告", 5),
		/**
		 * 5首页中间悬浮广告
		 */
		COACH("教练列表轮播图", 6),
		/**
		 * 7微信端轮播图
		 */
		WECHAT("微信端首页轮播图", 7)
		;

		private String msg;
		private int status;

		private ADTYPE(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	
	/**
	 * 红包类型:1代金券,2现金,3积分
	 * @author lxc
	 */
	public enum RewardType
	{
		//1代金券
		CASHCOUPON("代金券", 1),
		/**
		 * 2现金
		 */
		CASH("现金", 2),
		/**
		 * 3积分
		 */
		INTEGRAL("积分", 3)
		;

		private String msg;
		private int status;

		private RewardType(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	
	/**
	 * 作用或者用途:1支付后随机奖励,2支付后固定奖励积分,3登录奖励,4评论奖励,等等
	 * @author lxc
	 */
	public enum RedBagPurpose
	{
		//1支付后随机奖励
		PAIDREWARDRANDOM("支付后随机奖励", 1),
		/**
		 * 2支付后固定奖励积分
		 */
		PAIDREWARD("支付后固定奖励积分", 2),
		/**
		 * 3登录奖励
		 */
		LOGINREWARD("登录奖励", 3),
		/**
		 * 4评论奖励
		 */
		COMMENTREWARD("评论奖励", 4)
		;

		private String msg;
		private int status;

		private RedBagPurpose(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	
	/**
	 * 交易类型,1用户支付,2网上充值，3网上提现，
	 * @author lxc
	 */
	public enum TradeRecodeBusinessType
	{
		//1用户支付
		USERPAID("用户支付", 1),
		/**
		 * 2网上充值
		 */
		RECHARGE("网上充值", 2),
		/**
		 * 3网上提现
		 */
		WITHDRAW("网上提现", 3)
		;

		private String msg;
		private int status;

		private TradeRecodeBusinessType(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
	
	
	/**
	 * 教练类型
	 * 1，自由教练     2，驻场教练      3，运营商
	 * @author lxc
	 *
	 */
	public enum COACHTYPE
	{
		/**
		 * 1自由教练
		 */
		FREECOACH("自由教练", 1),
		/**
		 * 2教练类型
		 */
		INNERCOACH("驻场教练", 2),
		/**
		 * 3运营商教练
		 */
		CARRIEROPERATORCOACH("运营商教练", 3)
		;

		private String msg;
		private int status;

		private COACHTYPE(String msg, int status)
		{
			this.msg = msg;
			this.status = status;
		}

		public String getMsg() {
			return msg;
		}
		public int getStatus() {
			return status;
		}
	}
}
