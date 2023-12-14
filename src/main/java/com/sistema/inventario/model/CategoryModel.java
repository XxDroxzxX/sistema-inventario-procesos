package com.sistema.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
@Table(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(unique = true)
    @NotBlank(message = "Category name cannot be empty")
    @Size(max = 100, message = "Category name must be between 3 and 100 characters")
    private String nameCategory;

    @NotNull(message = "description is required")
    @Size(max = 500, message = "description max 500 characters")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ().,!? ]*$", message = "descripcion can only contain alphanumeric characters, " +
            "spaces, commas, periods, exclamation marks and question marks")
    private String description;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    private String type;
    @NotNull(message = "type is required")
    @Size(max = 100, message = "state max 100 characters")

    @NotBlank(message = "Display order cannot be empty")
    @Pattern(regexp = "^[0-9]*$", message = "Display order must be a number")
    private String displayOrder;


}
