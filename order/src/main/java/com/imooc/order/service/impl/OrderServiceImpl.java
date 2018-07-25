package com.imooc.order.service.impl;

import com.imooc.order.constant.OrderStatusEnum;
import com.imooc.order.constant.PayStatusEnum;
import com.imooc.order.dataobject.OrderMaster;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.repository.OrderDetailRepository;
import com.imooc.order.repository.OrderMasterRepository;
import com.imooc.order.service.OrderService;
import com.imooc.order.util.KeyUtils;
import com.imooc.order.util.ConverterUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author hellozjf
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        // TODO 2. 查询商品信息（调用商品服务）
        // TODO 3. 计算总价
        // TODO 4. 扣库存（调用商品服务）
        // 5. 订单入库
        orderDTO.setOrderId(KeyUtils.genUniqueKey());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
