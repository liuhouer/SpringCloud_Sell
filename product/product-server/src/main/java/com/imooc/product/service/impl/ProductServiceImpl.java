package com.imooc.product.service.impl;

import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.constant.ProductStatusEnum;
import com.imooc.product.constant.ResultEnum;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.dto.CartDTO;
import com.imooc.product.exception.ProductException;
import com.imooc.product.message.ProductInfoSink;
import com.imooc.product.message.ProductInfoSource;
import com.imooc.product.repository.ProductInfoRepository;
import com.imooc.product.service.ProductService;
import com.imooc.product.util.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author hellozjf
 */
@Service
@EnableBinding(ProductInfoSource.class)
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductInfoSource productInfoSource;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {

        List<ProductInfo> productInfoList = decreaseStockProcess(cartDTOList);

        // 发送扣库存消息
        List<ProductInfoOutput> productInfoOutputList = ConverterUtils.productInfoList2ProductInfoOutputList(productInfoList);
        productInfoSource.output().send(MessageBuilder.withPayload(productInfoOutputList).build());
    }

    @Transactional(rollbackOn = RuntimeException.class)
    public List<ProductInfo> decreaseStockProcess(List<CartDTO> cartDTOList) {

        List<ProductInfo> productInfoList = new ArrayList<>();

        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());

            // 判断商品是否存在
            if (! productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();

            // 判断库存是否足够
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            log.debug("save productInfo = {}", productInfo);
            productInfoRepository.save(productInfo);

            productInfoList.add(productInfo);
        }

        return productInfoList;
    }
}
