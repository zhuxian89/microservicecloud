package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.produce.MessageProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kafka 生产者 Controller
 *
 * @author 小马哥 QQ 1191971402
 * @copyright 咕泡学院出品
 * @since 2017/11/12
 */

@RestController
public class ProducerController {

    @Autowired
    private  MessageProducerBean messageProducerBean;



    /**
     * 通过{@link MessageProducerBean} 发送
     * @param message
     * @return
     */

    @GetMapping("/message/send")
    public Boolean send(@RequestParam String message) {
        messageProducerBean.send(message);
        return true;
    }

}
