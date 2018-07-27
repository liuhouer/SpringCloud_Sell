package com.imooc.order.service;

import com.imooc.order.dto.OrderDTO;

/**
 * @author hellozjf
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
