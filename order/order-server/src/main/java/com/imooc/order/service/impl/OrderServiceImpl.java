package com.imooc.order.service.impl;

import com.imooc.product.client.ProductClient;
import com.imooc.order.constant.OrderStatusEnum;
import com.imooc.order.constant.PayStatusEnum;
import com.imooc.order.dataobject.OrderDetail;
import com.imooc.order.dataobject.OrderMaster;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.repository.OrderDetailRepository;
import com.imooc.order.repository.OrderMasterRepository;
import com.imooc.order.service.OrderService;
import com.imooc.order.util.KeyUtils;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hellozjf
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtils.genUniqueKey();

        // 2. 查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(e -> e.getProductId())
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        // 3. 计算总价
        BigDecimal orderAmount = new BigDecimal(0);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            // 单价 * 数量
            Integer quantity = orderDetail.getProductQuantity();
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equalsIgnoreCase(orderDetail.getProductId())) {

                    // 累加总价
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(quantity))
                            .add(orderAmount);

                    // 订单详情入库
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtils.genUniqueKey());
                    orderDetailRepository.save(orderDetail);
                    break;
                }
            }
        }

        // 4. 扣库存（调用商品服务）
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> {
                    DecreaseStockInput cartDTO = new DecreaseStockInput(e.getProductId(), e.getProductQuantity());
                    return cartDTO;
                }).collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        // 5. 订单入库
        orderDTO.setOrderId(orderId);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
