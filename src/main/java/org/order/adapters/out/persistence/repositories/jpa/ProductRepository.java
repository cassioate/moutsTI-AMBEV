package org.order.adapters.out.persistence.repositories.jpa;

import org.order.adapters.out.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}