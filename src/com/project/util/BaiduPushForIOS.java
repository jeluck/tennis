package com.project.util;


import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;
import com.project.common.ConfigKey;

public class BaiduPushForIOS {

    // 1. 设置developer平台的ApiKey/SecretKey
    //private static String apiKey = "fm0tXAeVpniEIAEBRpKkmwoZ";					//司机端
    //private static String secretKey = "gtP4xTjl0tQgqPzCQdAgKZKKkHPd3L0K";		//司机端
//    private static String apiKey = "RENbKvSaUpTzly2H5FItQzRs";
//    private static String secretKey = "HrY4D8otYEmnzy3qzAddUPh66zyZfCkh";


    public static boolean IOSPushBroadcastMessageSample(String title, String msg, String userType)
    {
        if(userType == null || "".equals(userType))
        {
            return false;
        }
        if("0".equals(userType))
        {
            return IOSPushBroadcastMessageSample(ConfigKey.BAIDU_APIKEY_APP_VALUEFORIOSUSER, ConfigKey.BAIDU_SECRETKEY_APP_VALUEFORIOSUSER, title, msg);
        }
        else  if("1".equals(userType))
        {
            return IOSPushBroadcastMessageSample(ConfigKey.BAIDU_APIKEY_APP_VALUEFORIOSCOACH, ConfigKey.BAIDU_SECRETKEY_APP_VALUEFORIOSCOACH, title, msg);
        }

        return false;
    }

    /***
     * 推送给所有用户
     * @param title		标题
     * @param msg		信息内容
     * @return
     */
	public static boolean IOSPushBroadcastMessageSample(String apiKey,String secretKey,String title,String msg){
    	boolean result = true;
    	 /**
         * @brief 推送广播消息(IOS APNS) message_type = 1 (默认为0)
         */

        // 1. 设置developer平台的ApiKey/SecretKey
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. 创建请求类对象
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            request.setDeviceType(4); // device_type => 1: web 2: pc 3:android 4:ios 5:wp
            request.setDeployStatus(1); // DeployStatus => 1: Developer 2: Production
            request.setMessageType(1);//消息类型 0：消息（透传给应用的消息体）1：通知（对应设备上的消息通知）默认值为0。 
            request.setMessage("{\"aps\":{\"alert\":\""+msg+"\"}}");

            // 5. 调用pushMessage接口
            PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);

            // 6. 认证推送成功
            System.out.println("push amount : " + response.getSuccessAmount());
        } catch (ChannelClientException e) {
        	result =false;
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
        	result =false;
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
        return result;
    }
	
	/***
	 * 根据手机端的ChannelId,UserId,来推送消息
	 * @param ChannelId		手机端的ChannelId
	 * @param UserId		手机端的UserId
	 * @param msg			信息内容
	 * @return
	 */
	public static boolean IOSPushMessageSample(String apiKey,String secretKey,Long ChannelId ,String UserId, String msg){
    	boolean result = true;
    	/*
         * @brief 推送单播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
         */
    	 ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

         // 2. 创建BaiduChannelClient对象实例
         BaiduChannelClient channelClient = new BaiduChannelClient(pair);

         // 3. 若要了解交互细节，请注册YunLogHandler类
         channelClient.setChannelLogHandler(new YunLogHandler() {
             @Override
             public void onHandle(YunLogEvent event) {
                 System.out.println(event.getMessage());
             }
         });

         try {
             // 4. 创建请求类对象
             // 手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
             PushUnicastMessageRequest request = new PushUnicastMessageRequest();
             request.setDeviceType(4); // device_type => 1: web 2: pc 3:android 4:ios 5:wp
             request.setDeployStatus(1); // DeployStatus => 1: Developer 2: Production
             request.setChannelId(ChannelId);
             request.setUserId(UserId);
             request.setMessageType(1);//消息类型 0：消息（透传给应用的消息体）1：通知（对应设备上的消息通知）默认值为0。 
             request.setMessage("{\"aps\":{\"alert\":\""+msg+"\"}}");

             // 5. 调用pushMessage接口
             PushUnicastMessageResponse response = channelClient
                     .pushUnicastMessage(request);

             // 6. 认证推送成功
             System.out.println("push amount : " + response.getSuccessAmount());
         } catch (ChannelClientException e) {
        	 result =false;
             // 处理客户端错误异常
             e.printStackTrace();
         } catch (ChannelServerException e) {
        	 result =false;
             // 处理服务端错误异常
             System.out.println(String.format(
                     "request_id: %d, error_code: %d, error_message: %s",
                     e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
         }
    	return result;
    }

    public static boolean IOSPushTagMessage(String tag, String title, String msg, String userType)
    {
        if(userType == null || "".equals(userType))
        {
            return false;
        }
        if("0".equals(userType))
        {
            return IOSPushTagMessageSample(ConfigKey.BAIDU_APIKEY_APP_VALUEFORIOSUSER, ConfigKey.BAIDU_SECRETKEY_APP_VALUEFORIOSUSER, title, tag, msg,userType);
        }
        else  if("1".equals(userType))
        {
            return IOSPushTagMessageSample(ConfigKey.BAIDU_APIKEY_APP_VALUEFORIOSCOACH, ConfigKey.BAIDU_SECRETKEY_APP_VALUEFORIOSCOACH, title, tag, msg,userType);
        }

        return false;
    }
	
	/***
	 * 根据手机端的定义的tag值推送消息
	 * @param title		标题
	 * @param tag			现把手机号定义为tag
	 * @param msg			信息内容
	 * @param userType		用户类型		0用户	1司机
	 * @return
	 */
	public static boolean IOSPushTagMessageSample(String apiKey,String secretKey,String title,String tag, String msg,String userType){
		
		boolean result = true;
    	System.err.println("----------");
    	// 1. 设置developer平台的ApiKey/SecretKey
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

     // 2. 创建BaiduChannelClient对象实例
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

     // 3. 若要了解交互细节，请注册YunLogHandler类
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
        	// 4. 创建请求类对象
            // pushTagTpye = 1 for common tag pushing
            PushMsgToTagRequest request = new PushMsgToTagRequest()
                    .addTagName(tag)
                    .addMsgExpires(new Integer(3600))
                    .addMessageType(1)
                    // .addSendTime(System.currentTimeMillis() / 1000 + 70).
                    .addMessage("{\"title\":\"TEST\",\"description\":\""+msg+"\"}")
                    .addDeviceType(4);
            // 5. 调用pushMessage接口
            PushMsgToTagResponse response = pushClient.pushMsgToTag(request);
            // Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime() + ",timerId: "
                    + response.getTimerId());
         // 6. 认证推送成功
            System.out.println("tag -> " + tag);
            System.out.println("msg -> " + msg);
            System.out.println("user type -> " + userType);
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
               
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
		return result;
    }
	
	
	
	
	
	//如果是添加tag，则调用下面的方法(第二个方法)

	private static void  PushNotification(String msg,String apiKey,String secretKey,String tag) {
	  ChannelKeyPair pair = new ChannelKeyPair(apiKey,secretKey);
	  BaiduChannelClient channelClient = new BaiduChannelClient(pair);
	  channelClient.setChannelLogHandler(new YunLogHandler() {
	   @Override
	   public void onHandle(YunLogEvent event) {
	    System.out.println(event.getMessage());
	   }
	  });
	  try {
	   PushTagMessageRequest tagRequest = new PushTagMessageRequest();
	   tagRequest.setDeviceType(3);  // device_type => 1: web 2: pc 3:android 4:ios 5:wp
	   tagRequest.setDeployStatus(2);   // DeployStatus => 1: Developer 2: Production
	   tagRequest.setMessageType(1);
	   tagRequest.setTagName(tag);  //前端需要设置相应的tag
	   tagRequest.setMessage("{\"title\":\"TEST\",\"description\":\""+msg+"\"}");
	   PushTagMessageResponse response = channelClient.pushTagMessage(tagRequest);
	   System.out.println("push amount : " + response.getSuccessAmount());
	  } catch (ChannelClientException e) {
	   e.printStackTrace();
	  } catch (ChannelServerException e) {
	   System.out.println(
	     String.format("request_id: %d, error_code: %d, error_message: %s" ,
	      e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
	  }
	}
	
	 public static void main(String[] args) {
		 String apiKey =ConfigKey.BAIDU_APIKEY_APP_VALUEFORIOSUSER;						//司机端
		 String secretKey = ConfigKey.BAIDU_SECRETKEY_APP_VALUEFORIOSUSER;			//司机端
		 String title="标题";
		 String tag="Mytag";
		 String msg="小戴,jian";
		 long ChannelId=5423647869788883469l;
		 String UserId="1076317645214155257";
		 apiKey = ConfigKey.BAIDU_APIKEY_APP_VALUEFORIOSUSER;						//用户端
		 secretKey = ConfigKey.BAIDU_SECRETKEY_APP_VALUEFORIOSUSER;			//用户
		 
		 System.err.println(apiKey);
		 System.err.println(secretKey);
		 //IOSPushTagMessageSample(apiKey, secretKey, title, tag, msg,"1");
		 PushNotification(msg, apiKey, secretKey, tag);
		 //IOSPushMessageSample(apiKey, secretKey, ChannelId, UserId, msg);
		//IOSPushBroadcastMessageSample(apiKey, secretKey, title, msg);
	 }
}
