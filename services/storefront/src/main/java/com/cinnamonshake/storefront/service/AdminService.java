package com.cinnamonshake.storefront.service;

import com.cinnamonshake.storefront.dto.ProductRequest;
import com.cinnamonshake.storefront.entity.Product;
import com.cinnamonshake.storefront.entity.User;
import com.cinnamonshake.storefront.repository.ProductRepository;
import com.cinnamonshake.storefront.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // 🔹 Add Product
    public Product addProduct(ProductRequest req) {

        Product product = Product.builder()
                .name(req.getName())
                .price(req.getPrice())
                .capacity(req.getCapacity())
                .batteryType(req.getBatteryType())
                .ratedCycles(req.getRatedCycles())
                .description(req.getDescription())
                .build();

        return productRepository.save(product);
    }

    // 🔹 Update Product Info
    public Product updateProduct(Long productId, ProductRequest req) {

    Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    if (req.getName() != null) product.setName(req.getName());
    if (req.getPrice() != null) product.setPrice(req.getPrice());
    if (req.getCapacity() != null) product.setCapacity(req.getCapacity());
    if (req.getBatteryType() != null) product.setBatteryType(req.getBatteryType());
    if (req.getRatedCycles() != null) product.setRatedCycles(req.getRatedCycles());
    if (req.getDescription() != null) product.setDescription(req.getDescription());

    return productRepository.save(product);
}

    // 🔹 Deactivate User
    public String deactivateUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole("DEACTIVATED");

        userRepository.save(user);

        return "User deactivated";
    }
}