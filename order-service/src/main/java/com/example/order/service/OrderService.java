package com.example.order.service;

import com.example.common.dto.ProductDTO;
import com.example.order.client.ProductClient;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

/**
 * 订单业务逻辑，演示Seata分布式事务和消息发送。
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final StreamBridge streamBridge;

    /**
     * 创建订单并发送消息。
     */
    @GlobalTransactional
    public Order createOrder(Long productId) {
        // 调用商品服务查询商品信息
        ProductDTO product = productClient.findById(productId);
        // 保存订单到数据库
        Order order = new Order(null, productId, product.getName());
        orderRepository.save(order);
        // 发送创建订单的消息
        streamBridge.send("order-out-0", "创建订单:" + order.getId());
        return order;
    }
}
