package com.example.product.service;

import com.example.product.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 商品业务逻辑。
 */
@Service
public class ProductService {
    /**
     * 使用内存模拟数据库。
     */
    private static final Map<Long, Product> DB = new ConcurrentHashMap<>();

    static {
        DB.put(1L, new Product(1L, "苹果", BigDecimal.valueOf(3.5)));
        DB.put(2L, new Product(2L, "香蕉", BigDecimal.valueOf(2.0)));
    }

    /**
     * 根据ID查询商品。
     */
    public Product findById(Long id) {
        return DB.get(id);
    }
}
