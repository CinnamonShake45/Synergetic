package com.cinnamonshake.storefront.service;

import com.cinnamonshake.storefront.dto.*;
import com.cinnamonshake.storefront.entity.*;
import com.cinnamonshake.storefront.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    // 🔹 CREATE ORDER
    public OrderResponse createOrder(String username, OrderRequest request) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus("PENDING");

        double total = 0;
        List<OrderItem> items = new ArrayList<>();

        for (ItemRequest itemReq : request.getItems()) {

            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(product.getPrice());

            total += product.getPrice() * itemReq.getQuantity();
            items.add(item);
        }

        order.setItems(items);
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        return mapToResponse(savedOrder, List.of());
    }

    // 🔹 PAY ORDER + PROVISION DEVICES
    @Transactional
    public OrderResponse payOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getStatus().equals("PENDING")) {
            throw new RuntimeException("Order already processed");
        }

        // Simulate payment success
        boolean paymentSuccess = true;

        if (!paymentSuccess) {
            order.setStatus("FAILED");
            return mapToResponse(order, List.of());
        }

        order.setStatus("PAID");

        List<String> serials = new ArrayList<>();

        for (OrderItem item : order.getItems()) {

            Product product = item.getProduct();

            for (int i = 0; i < item.getQuantity(); i++) {

                String serial = generateSerial();

                Device device = new Device();
                device.setUser(order.getUser());
                device.setSerialNumber(serial);
                device.setStatus("ACTIVE");

                device.setCapacity(product.getCapacity());
                device.setBatteryType(product.getBatteryType());
                device.setActivatedAt(LocalDateTime.now());

                deviceRepository.save(device);

                serials.add(serial);
            }
        }

        return mapToResponse(order, serials);
    }

    // 🔹 SERIAL GENERATOR
    private String generateSerial() {
        return "BMS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // 🔹 MAPPER
    private OrderResponse mapToResponse(Order order, List<String> serials) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .deviceSerials(serials)
                .build();
    }
}