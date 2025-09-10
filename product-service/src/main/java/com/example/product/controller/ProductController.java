package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品接口演示，包含Sentinel限流和消息发送示例。
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final StreamBridge streamBridge;

    /**
     * 查询商品信息，使用Sentinel做保护。
     */
    @GetMapping("/{id}")
    @SentinelResource(value = "productDetail", fallback = "fallback")
    public Product detail(@PathVariable Long id) {
        return productService.findById(id);
    }

    /**
     * 模拟购买商品，向Kafka发送一条消息。
     */
    @PostMapping("/buy/{id}")
    public String buy(@PathVariable Long id) {
        Product product = productService.findById(id);
        streamBridge.send("product-out-0", "购买商品:" + product.getName());
        return "success";
    }

    /**
     * Sentinel降级回调方法。
     */
    public Product fallback(Long id) {
        return new Product(id, "默认商品", java.math.BigDecimal.ZERO);
    }
}
