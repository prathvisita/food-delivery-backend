package com.fooddelivery.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String itemName;
    private Double unitPrice;
    private int quantity;
    @ManyToOne(optional = false) @JoinColumn(name = "order_id") private CustomerOrder order;
}
