package com.example.order.repository;

import com.example.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单仓储接口。
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
