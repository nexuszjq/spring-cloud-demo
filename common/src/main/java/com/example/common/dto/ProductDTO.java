package com.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品信息传输对象，供服务之间调用使用。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    /** 商品ID */
    private Long id;
    /** 商品名称 */
    private String name;
    /** 商品价格 */
    private java.math.BigDecimal price;
}
