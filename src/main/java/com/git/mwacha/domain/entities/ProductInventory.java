package com.git.mwacha.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {
  @Id @UuidGenerator private UUID id;
  private String code;
  private String productName;
  private String description;
}
