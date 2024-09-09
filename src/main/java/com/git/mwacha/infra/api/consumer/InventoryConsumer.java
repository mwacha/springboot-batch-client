package com.git.mwacha.infra.api.consumer;

import com.git.mwacha.application.product.ProductInventoryCreation;
import com.git.mwacha.core.shared.JsonMapper;
import com.git.mwacha.infra.api.streams.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryConsumer {

  private final ProductInventoryCreation creation;

  @RabbitListener(queues = "${spring.rabbitmq.consumer}")
  public void receiveInventoryUpdate(String message) {
    creation.execute(JsonMapper.toObject(message, ProductEvent.class));
  }
}
