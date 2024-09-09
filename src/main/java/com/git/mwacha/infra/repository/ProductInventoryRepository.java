package com.git.mwacha.infra.repository;

import com.git.mwacha.domain.entities.ProductInventory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, UUID> {}
