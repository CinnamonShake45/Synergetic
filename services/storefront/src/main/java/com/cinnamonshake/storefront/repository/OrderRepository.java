package com.cinnamonshake.storefront.repository;

import com.cinnamonshake.storefront.entity.Order;
import com.cinnamonshake.storefront.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}