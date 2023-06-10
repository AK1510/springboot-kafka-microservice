package com.misterkourouma.trainings.orderservice.controller;

import com.misterkourouma.trainings.basedomains.dto.Order;
import com.misterkourouma.trainings.basedomains.dto.OrderEvent;
import com.misterkourouma.trainings.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @GetMapping("/orders")
    public List<String> getOrders(){
        System.out.println("getOrders");
        return List.of();
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order) {

        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = OrderEvent.builder()
                .status("PENDING")
                .message("Order status is in pending state")
                .order(order)
                .build();
        orderProducer.sendMessage(orderEvent);

        return "Order placed successfully...";
    }
}
