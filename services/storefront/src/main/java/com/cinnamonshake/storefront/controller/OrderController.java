package com.cinnamonshake.storefront.controller;

import com.cinnamonshake.storefront.dto.OrderRequest;
import com.cinnamonshake.storefront.dto.OrderResponse;
import com.cinnamonshake.storefront.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // TEMP: hardcoded user (replace with JWT later)
    private final Long USER_ID = 1L;

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(USER_ID, request);
    }

    @PostMapping("/{id}/pay")
    public OrderResponse payOrder(@PathVariable Long id) {
        return orderService.payOrder(id);
    }
}