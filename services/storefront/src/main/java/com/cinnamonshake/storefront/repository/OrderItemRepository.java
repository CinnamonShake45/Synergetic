package com.cinnamonshake.storefront.repository;

import com.cinnamonshake.storefront.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}