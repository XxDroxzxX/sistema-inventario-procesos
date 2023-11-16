package com.sistema.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.math.BigDecimal;


@Entity
@Table(name="item")
@Data


public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @jakarta.validation.constraints.NotNull(message = "street address is required")
    @Size(max = 100, message = "street address max 100 characters")

    private String nombre;
    @jakarta.validation.constraints.NotNull(message = "name is required")
    @Size(max = 100, message = "state max 100 characters")

    private String descripcion;
    @jakarta.validation.constraints.NotNull(message = "description is required")
    @Size(max = 500, message = "description max 500 characters")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ().,!? ]*$", message = "descripcion can only contain alphanumeric characters, spaces, commas, periods, exclamation marks and question marks")


    private int cantidad;
    @jakarta.validation.constraints.NotNull(message = "cantidad is required")
    @Size(max = 100, message = "cantidad max 100 characters")
    @Min(value = 1, message = "cantidad must be greater than or equal to 1")
    @Max(value = 1000000, message = "cantidad cannot be greater than 1,000,000")

    private String proveedor;
    @jakarta.validation.constraints.NotNull(message = "proveedor is required")
    @Size(max = 100, message = "state max 100 characters")

    private String estado;
    @Pattern(regexp = "^[YN]$", message = "vendido must be Y or N")


    private BigDecimal precio;
    @jakarta.validation.constraints.NotNull(message = "price is required")
    @DecimalMin(value = "0.0", message = "precio cannot be negative")
    @Size(max = 100, message = "state max 100 characters")


    @ManyToOne
    @JoinColumn(name= "idCategoryModel",referencedColumnName = "id")
    private CategoryModel categoryModel;






}
