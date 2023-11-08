package com.sistema.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class CategoryModel {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String nameCategory;
    private String type;
    private String description;

}
