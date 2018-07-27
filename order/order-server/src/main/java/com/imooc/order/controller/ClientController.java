package com.imooc.order.controller;

import com.imooc.product.client.ProductClient;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hellozjf
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("response = {}", productInfoList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String decreaseStock() {
        List<DecreaseStockInput> decreaseStockInputList = new ArrayList<>();
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("164103465734242707", 3);
        decreaseStockInputList.add(decreaseStockInput);
        log.debug("before decreaseStock");
        productClient.decreaseStock(decreaseStockInputList);
        log.debug("after decreaseStock");
        return "ok";
    }
}
