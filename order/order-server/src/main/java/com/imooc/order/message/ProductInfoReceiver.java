package com.imooc.order.message;

import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.message.ProductInfoSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hellozjf
 */
@Component
@EnableBinding(value = {ProductInfoSink.class})
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @StreamListener(ProductInfoSink.INPUT)
    public void productInfoInput(List<ProductInfoOutput> productInfoOutputList) {
        log.info("productInfoList input: {}", productInfoOutputList);

        // 存储到redis中
        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
            stringRedisTemplate.opsForValue().set(
                    String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock()));
        }
    }
}
