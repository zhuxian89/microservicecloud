package com.atguigu.springcloud.consumer;

import com.atguigu.springcloud.messaging.GuPaoMessageProcess;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;


@Component
public class MessageConsumerBean {

    @StreamListener(GuPaoMessageProcess.CHANNEL_NAME)
    public void onMessage1(String message){
        System.out.println("@StreamListener gupao: " + message);
    }

   }
