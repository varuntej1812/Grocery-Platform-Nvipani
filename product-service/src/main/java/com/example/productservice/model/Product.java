package com.example.productservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String unit;         // e.g., "1kg"
    private Double price;
    private String shelfLife;    // e.g., "2 weeks"
    private Integer stockQuantity;
}
