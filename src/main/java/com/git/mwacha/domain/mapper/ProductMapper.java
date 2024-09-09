package com.git.mwacha.domain.mapper;

import com.git.mwacha.domain.entities.ProductInventory;
import com.git.mwacha.infra.api.streams.ProductEvent;

public class ProductMapper {

  public static ProductInventory from(final ProductEvent event) {
    return ProductInventory.builder()
        .id(event.id())
        .productName(event.productName())
        .code(event.code())
        .description(event.description())
        .build();
  }
}
