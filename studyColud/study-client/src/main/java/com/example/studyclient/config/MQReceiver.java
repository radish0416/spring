package com.example.studyclient.config;

import com.alibaba.fastjson.JSON;
import com.example.studyapi.util.RabbitKey;
import com.shule.springcloud.entities.Friends;
import com.sms.AmazonawsSMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:WuHeng
 * Date:2019/5/25 0025
 * Time:下午 2:17
 *
 * @apiNote SMS 接收者
 */
@Component
@Slf4j
public class MQReceiver {

    @Autowired
    private AmazonawsSMS amazonawsSMS;
    /**
     * 接收发送短信验证码的消息队列
     * @param str
     */
    @RabbitListener(queues = RabbitKey.SEND_AWS_SMS)
    public void process(String str) {
        try{
        log.info("mq receiver aws sms message ========={}",str);
          String[] strarray=str.split(",");
//        截取发送过来的token加验证码字符串,存入redis中
            String phone= strarray[0];
            String code= strarray[1];
            amazonawsSMS.sendSMSMessage("+86"+phone,"殊乐点餐,您的验证码为:《"+code+"》请注意保管哦!亲");
            log.info("======================发送验证码成功===================用户电话为======{}=======",phone);
        }catch (Exception e){
            log.error("==========MQ接收发送AWS短信,=======错误信息为======{}",e);
        }
    }

    //发送激活短信
    @RabbitListener(queues = RabbitKey.SEND_SMS_DOWNLOAD)
    public void acttivaUserBySMS(Friends friends) {
        try{
            log.info("发送激活短信 message ========={}", JSON.toJSONString(friends));
            amazonawsSMS.sendSMSMessage(friends.getMobile(),friends.getContext());
            log.info("======================发送激活短信成功===============");
        }catch (Exception e){
            log.error("==========MQ删除配置信息出错,错误信息为======{}",e);
        }
    }
}
