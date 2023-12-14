package com.sistema.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "item")
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Item name cannot be empty")
    @Size(min = 3, message = "Item name must be more than 3 characters")
    private String name;
    @NotBlank(message = "Item description cannot be empty")
    @Size(max = 300, message = "Only 300 characters are allowed in the description")
    private String description;
    @NotNull(message = "Item quantity cannot be empty")
    @Min(value = 0, message = "Quantity must be equal or greater than 0")
    private int quantity;
    @NotNull(message = "Item price cannot be empty")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
    @NotBlank(message = "Provider cannot be empty")
    private String provider;
    @NotBlank(message = "Status cannot be empty")
    private String status;
    @NotNull(message = "Category cannot be null")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;
}
