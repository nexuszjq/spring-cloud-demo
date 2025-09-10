# Spring Cloud Alibaba Demo

该示例项目演示了 Spring Cloud Alibaba 常用组件的基本使用，包含如下技术栈：

- **Nacos**：服务发现与配置管理
- **OpenFeign + LoadBalancer**：服务调用与负载均衡
- **Spring Cloud Gateway**：统一网关入口
- **Sentinel**：熔断限流
- **Spring Cloud Stream + Kafka**：消息驱动
- **Seata**：分布式事务
- **SkyWalking**：可观测性（需以 Java Agent 方式接入）
- **ELK**：集中日志管理（示例中使用 Logstash TCP 输出）

## 模块说明

| 模块 | 端口 | 说明 |
| ---- | ---- | ---- |
| gateway-service | 8080 | 网关服务，转发请求到具体业务服务 |
| product-service | 8081 | 商品服务，提供商品查询和购买接口，并向 Kafka 发送消息 |
| order-service | 8082 | 订单服务，调用商品服务创建订单，并演示 Seata 分布式事务 |

## 快速启动

以下组件可以通过 Docker 快速启动：

```bash
# Nacos
docker run -d --name nacos -p 8848:8848 nacos/nacos-server:2.2.3

# Kafka（单节点）
docker run -d --name kafka -p 9092:9092 -e KAFKA_ZOOKEEPER_CONNECT=host.docker.internal:2181 \ 
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \ 
  bitnami/kafka:3.5

# Sentinel 控制台
docker run -d --name sentinel-dashboard -p 8858:8858 bladex/sentinel-dashboard:1.8.6

# Seata Server
docker run -d --name seata -p 8091:8091 seataio/seata-server:1.6.1

# Logstash（ELK）
docker run -d --name logstash -p 5044:5044 logstash:7.17.10
```

启动各组件后，可依次启动 `product-service`、`order-service`、`gateway-service` 三个应用。示例中为了便于演示，Nacos、Seata 等组件默认未启用快速失败，即使未启动相应组件也能运行，但相关功能将不可用。

## 常用接口

- `GET http://localhost:8081/product/1`：查询商品
- `POST http://localhost:8081/product/buy/1`：购买商品，向 Kafka 发送一条消息
- `GET http://localhost:8082/order/1`：创建订单，通过 Feign 调用商品服务
- 以上接口均可通过网关访问，如 `http://localhost:8080/product/1`

## SkyWalking & ELK

项目未直接集成 SkyWalking Agent，运行时可通过在 JVM 启动参数中添加 `-javaagent:/path/skywalking-agent.jar` 的方式接入。日志通过 Logback 输出到控制台并可转发到 Logstash，进一步对接 Elasticsearch 和 Kibana 即可构建完整的 ELK 日志系统。

## 参考

- [Spring Cloud Alibaba 官方文档](https://github.com/alibaba/spring-cloud-alibaba)
- [Spring Cloud Stream 文档](https://spring.io/projects/spring-cloud-stream)
- [Seata 官方文档](https://seata.io)
