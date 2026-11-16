package com.fooddelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class MenuItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank private String name;
    private String description;
    @NotNull @DecimalMin("0.01") private Double price;
    private boolean available = true;
    @ManyToOne(optional = false) @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
