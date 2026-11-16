package com.fooddelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id", "menu_item_id"}))
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotNull private Long customerId;
    @ManyToOne(optional = false) private MenuItem menuItem;
    @Min(1) private int quantity;
}
