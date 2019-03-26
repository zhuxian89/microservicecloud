package com.atguigu.springcloud.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义SINK
 *
 */
public interface GuPaoMessageProcess {

    /**
     * 通道名称
     */
    String CHANNEL_NAME = "gupao";

    /*
     * 输入
     * @return
     */
    @Input(CHANNEL_NAME)
    SubscribableChannel input();

    /**
     * 输出
     * @return
     */
    @Output(CHANNEL_NAME)
    MessageChannel output();
}
