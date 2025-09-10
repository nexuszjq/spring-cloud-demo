package com.example.order.client;

import com.example.common.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 调用商品服务的Feign客户端。
 */
@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/product/{id}")
    ProductDTO findById(@PathVariable("id") Long id);
}
