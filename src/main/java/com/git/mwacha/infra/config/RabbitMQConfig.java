package com.git.mwacha.infra.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Value("${spring.rabbitmq.producer}")
  private String producerQueueName;

  @Bean
  public Queue producerQueue() {
    return new Queue(producerQueueName, true);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange("inventory-exchange");
  }

  @Bean
  public Binding producerBinding(Queue producerQueue, TopicExchange exchange) {
    return BindingBuilder.bind(producerQueue).to(exchange).with("inventory.producer.*");
  }

  @Bean
  public Binding consumerBinding(Queue consumerQueue, TopicExchange exchange) {
    return BindingBuilder.bind(consumerQueue).to(exchange).with("inventory.consumer.*");
  }
}
