package com.sistema.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data

public class CategoryModel {


//address es category
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @jakarta.validation.constraints.NotNull(message = "street address is required")
    @Size(max = 100, message = "street address max 100 characters")

    private String nameCategory;
    @jakarta.validation.constraints.NotNull(message = "nameCategory is required")
    @Size(max = 100, message = "state max 100 characters")

    private String description;
    @jakarta.validation.constraints.NotNull(message = "description is required")
    @Size(max = 500, message = "description max 500 characters")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ().,!? ]*$", message = "descripcion can only contain alphanumeric characters, spaces, commas, periods, exclamation marks and question marks")


    private String type;
    @jakarta.validation.constraints.NotNull(message = "type is required")
    @Size(max = 100, message = "state max 100 characters")

    private ItemModel itemModel;


}
