package com.cinnamonshake.storefront.service;

import com.cinnamonshake.storefront.dto.ProductResponse;
import com.cinnamonshake.storefront.entity.Product;
import com.cinnamonshake.storefront.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapToResponse(product);
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .capacity(product.getCapacity())
                .batteryType(product.getBatteryType())
                .ratedCycles(product.getRatedCycles())
                .description(product.getDescription())
                .build();
    }
}