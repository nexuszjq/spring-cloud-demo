package com.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单信息传输对象。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    /** 订单ID */
    private Long id;
    /** 商品ID */
    private Long productId;
    /** 商品名称 */
    private String productName;
}
