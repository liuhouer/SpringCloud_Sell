package com.imooc.order.message;

import com.imooc.order.dto.OrderDTO;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.message.ProductInfoSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author hellozjf
 */
@Component
@EnableBinding(value = {Sink.class, Processor2.class})
@Slf4j
public class StreamReceiver {

//    @StreamListener(Sink.INPUT)
//    public void process(String message) {
//        log.info("StreamReceiver: {}", message);
//    }

    /**
     * 接收OrderDTO对象
     * @param message
     */
    @StreamListener(Sink.INPUT)
    @SendTo(Processor2.OUTPUT2)
    public String input(OrderDTO message) {
        log.info("input: {}", message);
        return "received.";
    }

    @StreamListener(Processor2.INPUT2)
    public void input2(String message) {
        log.info("input2: {}", message);
    }
}
