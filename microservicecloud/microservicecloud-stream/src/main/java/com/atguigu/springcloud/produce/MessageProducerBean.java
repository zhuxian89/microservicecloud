package com.atguigu.springcloud.produce;

import com.atguigu.springcloud.messaging.GuPaoMessageProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 消息生成者 Bean
 */
@Component
@EnableBinding({GuPaoMessageProcess.class})
public class MessageProducerBean {


    @Autowired
    @Qualifier(GuPaoMessageProcess.CHANNEL_NAME)
    private MessageChannel output;

    /**
     * 发送消息
     * @param message 消息内容
     */
    public void send(String message){
        // 通过消息管道发送消息
        System.out.println("生产者发送消息:"+message);
        output.send(MessageBuilder.withPayload(message).build());
    }

}
