package com.sistema.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Table(name="item")
@Data
//metodos
public class ItemModel {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String name;
            private String description;
            private int quantity;
            private String provider;
            private String status;
            private BigDecimal price;






}
