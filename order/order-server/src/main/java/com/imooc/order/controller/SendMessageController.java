package com.imooc.order.controller;

import com.imooc.order.dto.OrderDTO;
import com.imooc.order.util.KeyUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author hellozjf
 */
@RestController
@EnableBinding(Source.class)
public class SendMessageController {

    @Autowired
    private Source source;

    @Autowired
    private AmqpTemplate amqpTemplate;

//    @GetMapping("/sendMessage")
//    public void sendMessage() {
//        String message = "now " + new Date();
//        source.output().send(MessageBuilder.withPayload(message).build());
//    }

    /**
     * 发送OrderDTO对象
     */
    @GetMapping("/sendMessage")
    public void sendMessage() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(KeyUtils.genUniqueKey());
        source.output().send(MessageBuilder.withPayload(orderDTO).build());
    }

    @GetMapping("/sendMyQueue")
    public void sendMyQueue() {
        String message = "now " + new Date();
        amqpTemplate.convertAndSend("myQueue", message);
    }

    @GetMapping("/sendComputerOrder")
    public void sendComputerOrder() {
        String message = "now " + new Date();
        amqpTemplate.convertAndSend("myOrder", "computer", message);
    }

    @GetMapping("/sendFruitOrder")
    public void sendFruitOrder() {
        String message = "now " + new Date();
        amqpTemplate.convertAndSend("myOrder", "fruit", message);
    }
}
