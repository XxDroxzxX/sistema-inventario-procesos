package com.sistema.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "category")
@Data

public class CategoryModel {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String nameCategory;
    private String type;
    private String description;

}
