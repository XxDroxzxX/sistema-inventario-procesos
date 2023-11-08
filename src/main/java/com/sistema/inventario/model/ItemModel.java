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
            private String nombre;
            private String descripcion;
            private int cantidad;
            private String proveedor;
            private String estado; //vendido o no vendido
            private BigDecimal precio; //moneda colombiana

            @ManyToOne
            @JoinColumn(name= "idCategoryModel",referencedColumnName = "id")
            private CategoryModel categoryModel;






}
