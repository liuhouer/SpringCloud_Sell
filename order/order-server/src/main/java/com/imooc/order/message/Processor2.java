package com.imooc.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author hellozjf
 */
public interface Processor2 {
    String INPUT2 = "input2";
    String OUTPUT2 = "output2";

    @Input(Processor2.INPUT2)
    SubscribableChannel input2();

    @Output(Processor2.OUTPUT2)
    MessageChannel output2();
}
