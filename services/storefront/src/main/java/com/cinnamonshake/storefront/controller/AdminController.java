package com.cinnamonshake.storefront.controller;

import com.cinnamonshake.storefront.dto.ProductRequest;
import com.cinnamonshake.storefront.entity.Product;
import com.cinnamonshake.storefront.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 🔹 Add product
    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequest request) {
        return adminService.addProduct(request);
    }

    // 🔹 Update product info
    @PatchMapping("/products/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request
    ) {
        return adminService.updateProduct(id, request);
    }

    // 🔹 Deactivate user
    @PostMapping("/users/{id}/deactivate")
    public String deactivateUser(@PathVariable Long id) {
        return adminService.deactivateUser(id);
    }
    
}