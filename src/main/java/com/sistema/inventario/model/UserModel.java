package com.sistema.inventario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema.inventario.util.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name is required")
    @Size(min= 1, max = 100, message = "First name must be between 1 and 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min= 1, max = 100, message = "Last name must be between 1 and 100 characters")
    private String lastName;
    @JsonIgnore
    @OneToMany(mappedBy = "user")

    private List<AddressModel> address;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")

    private String email;
    @NotBlank(message = "Phone is required")
    @Size(min= 1, max = 16, message = "Phone number must be between 1 and 16 characters")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be a valid number of 10 digits")

    private String phone;
    @NotBlank(message = "Password is required")
    @Size(min= 8, max = 20, message = "Password must be between 8 and 20 characters")

    private String password;
    @NotBlank(message = "Document is required")
    @Size(min= 5, max = 20, message = "Document must be between 5 and 20 characters")
    @Column(unique = true)

    private String document;

    Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((rol.name())));
    }
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<AddressModel> addressList;

    @Override
    public boolean isEnabled() {
        return true;
    }
}