package com.imooc.order.message;

import com.imooc.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * @author hellozjf
 */
@Component
@EnableBinding(Sink.class)
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
    public void process(OrderDTO message) {
        log.info("StreamReceiver: {}", message);
    }
}
