package com.example.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.order.model.Order;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单接口，演示Feign调用和Sentinel熔断。
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 创建订单。
     */
    @GetMapping("/{productId}")
    @SentinelResource(value = "createOrder", fallback = "fallback")
    public Order create(@PathVariable Long productId) {
        return orderService.createOrder(productId);
    }

    /**
     * Sentinel降级回调。
     */
    public Order fallback(Long productId) {
        return new Order(-1L, productId, "默认商品");
    }
}
