package com.cinnamonshake.storefront.controller;

import com.cinnamonshake.storefront.dto.OrderRequest;
import com.cinnamonshake.storefront.dto.OrderResponse;
import com.cinnamonshake.storefront.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(
            @RequestBody OrderRequest request,
            Authentication authentication
    ) {
        String username = authentication.getName();
        return orderService.createOrder(username, request);
    }

    @PostMapping("/{id}/pay")
    public OrderResponse payOrder(@PathVariable Long id) {
        return orderService.payOrder(id);
    }
}