package com.example.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * 使用函数式方式监听商品消息。
 */
@Component
@Slf4j
public class MessageConsumer {

    /**
     * 监听来自Kafka的商品主题消息。
     */
    @Bean
    public Consumer<String> productInput() {
        return message -> log.info("收到商品消息: {}", message);
    }
}
