package com.misterkourouma.trainings.basedomains.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private String orderId;
    private String name;
    private int qty;
    private double price;
}
