package com.project.util;


import java.util.ArrayList;
import java.util.List;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.DeleteTagRequest;
import com.baidu.yun.push.model.DeleteTagResponse;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;
import com.baidu.yun.push.model.QueryTagsRequest;
import com.baidu.yun.push.model.QueryTagsResponse;
import com.baidu.yun.push.model.TagInfo;
import com.project.common.ConfigKey;

public class BaiduPushForAndroid {

    // 1. 设置developer平台的ApiKey/SecretKey
    //private static String apiKey = "fm0tXAeVpniEIAEBRpKkmwoZ";					//司机端
    //private static String secretKey = "gtP4xTjl0tQgqPzCQdAgKZKKkHPd3L0K";		//司机端
//    private static String apiKey = "RENbKvSaUpTzly2H5FItQzRs";
//    private static String secretKey = "HrY4D8otYEmnzy3qzAddUPh66zyZfCkh";


    public static boolean AndroidPushBroadcastMessageSample(String title,String msg, String userType,int phoneType) throws PushClientException, PushServerException
    {
        if(userType == null || "".equals(userType))
        {
            return false;
        }
        if("0".equals(userType))
        {
            return AndroidPushBroadcastMessageSample(ConfigKey.BAIDU_APIKEY_APP_VALUEFORANDROIDUSER, ConfigKey.BAIDU_SECRETKEY_APP_VALUEFORANDROIDUSER, title, msg,phoneType);
        }
        else  if("1".equals(userType))
        {
        	return AndroidPushBroadcastMessageSample(ConfigKey.BAIDU_APIKEY_APP_VALUEFORANDROIDCOACH, ConfigKey.BAIDU_SECRETKEY_APP_VALUEFORANDROIDCOACH, title, msg,phoneType);
        } 
        return false;
    }
    
    
    public static boolean AdroidPushTagMessage(String tag, String title, String msg, String userType,int phoneType) throws PushClientException, PushServerException
    {
        if(userType == null || "".equals(userType))
        {
            return false;
        }
        
        if("0".equals(userType))
        {
        	 return AndroidPushTagMessageSample(ConfigKey.BAIDU_APIKEY_APP_VALUEFORANDROIDUSER, ConfigKey.BAIDU_SECRETKEY_APP_VALUEFORANDROIDUSER, title, tag, msg,phoneType);
        }else  if("1".equals(userType))
        {
        	 return AndroidPushTagMessageSample(ConfigKey.BAIDU_APIKEY_APP_VALUEFORANDROIDCOACH, ConfigKey.BAIDU_SECRETKEY_APP_VALUEFORANDROIDCOACH, title, tag, msg,phoneType);
        }

        return false;
       
    }
    
    
    
    

    /***
     * 推送给所有用户
     * @param title		标题
     * @param msg		信息内容
     * @return
     * @throws PushClientException 
     * @throws PushServerException 
     */
	public static boolean AndroidPushBroadcastMessageSample(String apiKey,String secretKey,String title,String msg,int phoneType) throws PushClientException, PushServerException{
    	boolean result = true;
    	 PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

			// 2. build a BaidupushClient object to access released interfaces
			BaiduPushClient pushClient = new BaiduPushClient(pair,
					BaiduPushConstants.CHANNEL_REST_URL);

			// 3. register a YunLogHandler to get detail interacting information
			// in this request.
			pushClient.setChannelLogHandler(new YunLogHandler() {
				@Override
				public void onHandle(YunLogEvent event) {
					System.out.println(event.getMessage());
				}
			});

			try {
				// 4. specify request arguments
				PushMsgToAllRequest request = new PushMsgToAllRequest()
						.addMsgExpires(new Integer(3600)).addMessageType(1)
						    .addMessage("{\"title\":\""+title+"\",\"description\":\""+msg+"\"}")
//						.addMessage("{\"title\":"+title+",\"description\":"+msg+"}") //添加透传消息
//						.addSendTime(System.currentTimeMillis() / 1000 + 120) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
						.addDeviceType(phoneType);
				// 5. http request
				PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
				// Http请求结果解析打印
				System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
						+ response.getSendTime() + ",timerId: "
						+ response.getTimerId());
			} catch (PushClientException e) {
				if (BaiduPushConstants.ERROROPTTYPE) {
					throw e;
				} else {
					e.printStackTrace();
				}
			} catch (PushServerException e) {
				if (BaiduPushConstants.ERROROPTTYPE) {
					throw e;
				} else {
					System.out.println(String.format(
							"requestId: %d, errorCode: %d, errorMessage: %s",
							e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
				}
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
	public static boolean AndroidPushMessageSample(String apiKey,String secretKey,Long ChannelId ,String UserId, String msg){
    	boolean result = true;
    	/*
         * @brief 推送单播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
         */
//    	 ISystemConfigService configService3 =(ISystemConfigService)  ServiceLocator.getBean("systemConfigServiceImpl");
// 		try {
// 			apiKey = configService3.getConfigValueByKey(Settings.BAIDU_APIKEY_APP,apiKey);
// 			secretKey = configService3.getConfigValueByKey(Settings.BAIDU_SECRETKEY_APP,secretKey);
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}

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
            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android 4:ios 5:wp
            request.setChannelId(ChannelId);
            request.setUserId(UserId);
//            request.setChannelId(3574390494121776274l);
//            request.setUserId("1067733975304836075");
            request.setMessage(msg);

            // 5. 调用pushMessage接口
            PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

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
	 * 根据手机端的定义的tag值推送消息
	 * @param title		标题
	 * @param tag			现把手机号定义为tag
	 * @param msg			信息内容
	 * @param userType		用户类型		0用户	1司机
	 * @return
	 * @throws PushClientException 
	 * @throws PushServerException 
	 */
	public static boolean AndroidPushTagMessageSample(String apiKey,String secretKey,String title,String tag, String msg,int phoneType) throws PushClientException, PushServerException{
    	boolean result = true;
    	 PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
    	 System.err.println("发送消息至---------------------------"+tag);
         // 2. build a BaidupushClient object to access released interfaces
         BaiduPushClient pushClient = new BaiduPushClient(pair,
                 BaiduPushConstants.CHANNEL_REST_URL);

         // 3. register a YunLogHandler to get detail interacting information
         // in this request.
         pushClient.setChannelLogHandler(new YunLogHandler() {
             @Override
             public void onHandle(YunLogEvent event) {
                 System.out.println(event.getMessage());
             }
         });

         try {
             // 4. specify request arguments
             // pushTagTpye = 1 for common tag pushing
             PushMsgToTagRequest request = new PushMsgToTagRequest()
                     .addTagName(tag)
                     .addMsgExpires(new Integer(3600))
                     .addMessageType(1)
                      .addMessage("{\"title\":\""+title+"\",\"description\":\""+msg+"\"}") //通知
//                      .addSendTime(System.currentTimeMillis() / 1000 + 70)
                     .addDeviceType(phoneType);
             // 5. http request
             PushMsgToTagResponse response = pushClient.pushMsgToTag(request);
             // Http请求返回值解析
             System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                     + response.getSendTime() + ",timerId: "
                     + response.getTimerId());
         } catch (PushClientException e) {
             if (BaiduPushConstants.ERROROPTTYPE) {
                 throw e;
             } else {
                 e.printStackTrace();
             }
         } catch (PushServerException e) {
             if (BaiduPushConstants.ERROROPTTYPE) {
                 throw e;
             } else {
                 System.out.println(String.format(
                         "requestId: %d, errorCode: %d, errorMsg: %s",
                         e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
             }
         }
    	return result;
    }
	

	/**
	 * 查询tag值
	 * @param apiKey
	 * @param secretKey
	 * @param tag
	 * @throws PushClientException
	 * @throws PushServerException
	 */
	public static List<String> QueryMsgStatus(String apiKey,String secretKey,String tag) 
	            throws PushClientException,PushServerException {
		// 1. get apiKey and secretKey from developer console
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        List<String> listTag =new ArrayList<String>();
        try {
            // 4. specify request arguments
            QueryTagsRequest request =null;
            if(CommonUtil.NotEmpty(tag)){
            	 request = new QueryTagsRequest()
                .addTagName(tag).addStart(0).addLimit(10)
                .addDeviceType(3);
            }else{
            	request = new QueryTagsRequest()
               .addStart(0).addLimit(10)
                .addDeviceType(3);
            }
            // 5. http request
            QueryTagsResponse response = pushClient.queryTags(request);
            // Http请求返回值解析
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("totalNum: " + response.getTotalNum() + "\n");
           
            if (null != response) {
                List<?> list = response.getTagsInfo();
                for (int i = 0; i < list.size(); i++) {
                    Object object = list.get(i);
                    if (object instanceof TagInfo) {
                        TagInfo tagInfo = (TagInfo) object;
                        strBuilder.append("List[" + i + "]: " + "tagId="
                                + tagInfo.getTagId() + ",tag="
                                + tagInfo.getTagName() + ",info="
                                + tagInfo.getInfo() + ",type="
                                + tagInfo.getType() + ",creatTime="
                                + tagInfo.getCreateTime() + "\n");
                        listTag.add(tagInfo.getTagName());
                    }
                }
               
                
                System.out.println(strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return listTag;
	    }
	
	/**
	 * 删除tag值 
	 * @param apiKey
	 * @param secretKey
	 * @param tag
	 * @throws PushClientException
	 * @throws PushServerException
	 */
	    public static void DeleteTag(String apiKey,String secretKey,String tag) 
	            throws PushClientException,PushServerException {
	        // 1. get apiKey and secretKey from developer console
	        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

	        // 2. build a BaidupushClient object to access released interfaces
	        BaiduPushClient pushClient = new BaiduPushClient(pair,
	                BaiduPushConstants.CHANNEL_REST_URL);

	        // 3. register a YunLogHandler to get detail interacting information
	        // in this request.
	        pushClient.setChannelLogHandler(new YunLogHandler() {
	            @Override
	            public void onHandle(YunLogEvent event) {
	                System.out.println(event.getMessage());
	            }
	        });

	        try {
	            // 4. specify request arguments
	            DeleteTagRequest request = new DeleteTagRequest().addTagName(
	                    tag).addDeviceType(new Integer(3));
	            // 5. http request
	            DeleteTagResponse response = pushClient.deleteTag(request);
	            // Http请求返回值解析
	            System.out.println(String.format("tagName: %s, result: %d",
	                    response.getTagName(), response.getResult()));
	        } catch (PushClientException e) {
	            if (BaiduPushConstants.ERROROPTTYPE) {
	                throw e;
	            } else {
	                e.printStackTrace();
	            }
	        } catch (PushServerException e) {
	            if (BaiduPushConstants.ERROROPTTYPE) {
	                throw e;
	            } else {
	                System.out.println(String.format(
	                        "requestId: %d, errorCode: %d, errorMsg: %s",
	                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
	            }
	        }
	    }
	
	
	
	
	public static void main(String[] args) throws PushClientException, PushServerException {
		//安卓教练端
//		 String apiKey = "VWunpeWPaGRqevO6iBG436No";						
//		 String secretKey = "M0YTT6DG7tSCR9ggLf2Mzo3GLcxnaAvu";			
		 //安卓用户端
//		 String apiKey = "BUWK1dzzPt69O6GYtsTGMB7O";						
//		 String secretKey = "nToX6xrmyF8FiWVXiORIXpKZuBmzSbfj";		
		 
		 //IOS用户端
//		 String apiKey = "VTdGRitVGW9DOCZg0w7L22B2";						
//		 String secretKey = "1073eGOym2MxAZQBULRYowF7zFjSj0p8";	
		 //IOS教练端
		 String apiKey = "EaGg71PHAaBgDm8nryds9yN3";						
		 String secretKey = "l4s7XspIXLB0sqV0VFXGiZxiqkVfEedp";		
		 String title="国钱 你真漂亮！！";
		 String tag="131470979580";
		 String msg="国钱 你真漂亮！！";
		 long ChannelId=5423647869788883469l;
		 String UserId="1076317645214155257";
		 String userType="0";
//		 AndroidPushBroadcastMessageSample(apiKey, secretKey, title, msg);
//		 AndroidPushMessageSample(apiKey, secretKey, ChannelId, UserId, msg);
//		 AndroidPushTagMessageSample(apiKey, secretKey, title, tag, msg, 3);
		List<String> tag1 =  QueryMsgStatus(apiKey, secretKey, "");
		for (String string : tag1) {
			DeleteTag(apiKey, secretKey,string);
		}
		 
//		 AndroidPushBroadcastMessageSample(title,  msg,"0");
	 }
	
	
	
}
