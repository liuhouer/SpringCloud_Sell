package com.imooc.product.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author hellozjf
 */
public interface ProductInfoSink {
    String INPUT = "productInfoInput";

    @Input(ProductInfoSink.INPUT)
    SubscribableChannel input();
}
