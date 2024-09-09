package com.git.mwacha.application.product;

import static com.git.mwacha.core.shared.JsonMapper.toJson;

import com.git.mwacha.domain.enums.ImportStatus;
import com.git.mwacha.domain.mapper.ProductMapper;
import com.git.mwacha.infra.api.streams.EventResult;
import com.git.mwacha.infra.api.streams.ProductEvent;
import com.git.mwacha.infra.repository.ProductInventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductInventoryCreation {
  private final ProductInventoryRepository productInventoryRepository;

  private final RabbitTemplate rabbitTemplate;

  @Value("${spring.rabbitmq.producer}")
  private String producerQueueName;

  @Transactional
  public void execute(final ProductEvent event) {
    final var productInventory = ProductMapper.from(event);
    try {

      productInventoryRepository.save(productInventory);

      rabbitTemplate.convertAndSend(
          producerQueueName, toJson(new EventResult(event.id(), ImportStatus.SUCCESS)));
      log.info(
          "PRODUCT SAVED ID {} AND EVENT CREATED {}",
          productInventory.getId(),
          ImportStatus.SUCCESS);
    } catch (Exception e) {
      rabbitTemplate.convertAndSend(
          producerQueueName, toJson(new EventResult(event.id(), ImportStatus.ERROR)));
      log.error(
          "PRODUCT NOT SAVED ID {} AND EVENT CREATED {}",
          productInventory.getId(),
          ImportStatus.ERROR);
    }
  }
}
