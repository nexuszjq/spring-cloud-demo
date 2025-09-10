package com.example.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品实体，用于模拟数据库中的商品数据。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /** 商品ID */
    private Long id;
    /** 商品名称 */
    private String name;
    /** 商品价格 */
    private java.math.BigDecimal price;
}
