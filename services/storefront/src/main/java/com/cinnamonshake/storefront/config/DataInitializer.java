package com.cinnamonshake.storefront.config;

import com.cinnamonshake.storefront.entity.Product;
import com.cinnamonshake.storefront.entity.User;
import com.cinnamonshake.storefront.repository.ProductRepository;
import com.cinnamonshake.storefront.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedData() {
        return args -> {

            // 🔹 Seed Products
            if (productRepository.count() == 0) {

                productRepository.save(Product.builder()
                        .name("3kWh Li-ion Pack")
                        .price(8000)
                        .capacity(3.0)
                        .batteryType("Li-ion")
                        .ratedCycles(1200)
                        .description("Basic home battery pack")
                        .build());

                productRepository.save(Product.builder()
                        .name("5kWh LFP Pack")
                        .price(15000)
                        .capacity(5.0)
                        .batteryType("LFP")
                        .ratedCycles(3000)
                        .description("High durability lithium iron phosphate pack")
                        .build());

                productRepository.save(Product.builder()
                        .name("10kWh Premium Pack")
                        .price(28000)
                        .capacity(10.0)
                        .batteryType("Li-ion")
                        .ratedCycles(1500)
                        .description("High capacity premium system")
                        .build());
            }

            // 🔹 Seed Users
            if (userRepository.count() == 0) {

                userRepository.save(User.builder()
                        .username("user1")
                        .email("user1@example.com")
                        .password(passwordEncoder.encode("password"))
                        .role("USER")
                        .build());

                userRepository.save(User.builder()
                        .username("admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role("ADMIN")
                        .build());
            }
        };
    }
}