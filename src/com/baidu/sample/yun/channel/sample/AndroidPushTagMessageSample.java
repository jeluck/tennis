package com.baidu.sample.yun.channel.sample;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class AndroidPushTagMessageSample {

    public static void main(String[] args) {

        /*
         * @brief 推送组播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
         */

        // 1. 设置developer平台的ApiKey/SecretKey
//	   String apiKey = "fm0tXAeVpniEIAEBRpKkmwoZ";						//司机端
//   String secretKey = "gtP4xTjl0tQgqPzCQdAgKZKKkHPd3L0K";			//司机端
	   String apiKey = "hgAf9NqDg42KsXmjzkfxoklk";						//司机端
String secretKey = "gnDPoUbzAxb2VGOv9dQHfsLrCPY2EhKV";			//司机端
//        String apiKey = "RENbKvSaUpTzly2H5FItQzRs";					//用户端
//        String secretKey = "HrY4D8otYEmnzy3qzAddUPh66zyZfCkh";			//用户端
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
            PushTagMessageRequest request = new PushTagMessageRequest();
            // request.setDeviceType(3); // device_type => 1: web 2: pc 3:android
                                      // 4:ios 5:wp
            //request.setTagName("13632803699");
            
            request.setDeviceType(4);
             request.setTagName("123456789");
             request.setDeployStatus(1);
            // request.setTagName("18520885160");
           // request.setMessage("Hello Channel,小戴你好.我艹");
            // 若要通知，
            request.setMessageType(1);
          request.setMessage("{\"title\":\"Notify_title_danbo\",\"description\":\"艹.\"}");

       //  request.setMessage("{\"aps\":{\"alert\":\"小易，收到说一下\"}}");
            
            // 5. 调用pushMessage接口
            PushTagMessageResponse response = channelClient
                    .pushTagMessage(request);

            // 6. 认证推送成功
            System.out.println("push amount : " + response.getSuccessAmount());

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

}
