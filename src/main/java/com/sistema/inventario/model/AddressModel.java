package com.sistema.inventario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "street address is required")
    @Size(max = 100, message = "Street address must be a maximum of 100 characters")
    private String streetAddress;
    @NotBlank(message = "city is required")
    @Size(max = 100, message = "City must be a maximum of 100 characters")
    private String city;
    @NotBlank(message = "state is required")
    @Size(max = 100, message = "State must be a maximum of 100 characters")
    private String state;
    @NotBlank(message = "Postal code is required")
    @Size(min = 5, max = 10, message = "Postal code must be between 5 and 10 characters")
    private String postalCode;
    @JsonIgnore
    private Boolean status = Boolean.TRUE;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private UserModel user;

}
