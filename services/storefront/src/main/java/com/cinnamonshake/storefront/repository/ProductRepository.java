package com.cinnamonshake.storefront.repository;

import com.cinnamonshake.storefront.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}