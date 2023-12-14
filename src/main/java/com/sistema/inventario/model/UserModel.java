package com.sistema.inventario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema.inventario.util.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private List<com.sistema.inventario.model.AddressModel> address;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be a valid number of 10 digits")
    private String phone;
    @NotNull(message = "Password is required")
    @Size(min = 8, max = 255,message = "password min 8 characters and max 255")
    private String password;
    @NotBlank(message = "Document is required")
    @Column(unique = true, nullable = false)
    @Size(min= 5, max = 20, message = "Document must be between 5 and 20 characters")
    private String document;
    @Enumerated( EnumType.STRING)
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
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