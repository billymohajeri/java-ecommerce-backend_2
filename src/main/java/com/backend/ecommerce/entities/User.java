package com.backend.ecommerce.entities;

import com.backend.ecommerce.entities.enums.AuthenticationRole;
import com.backend.ecommerce.shared.utilities.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "ecomapp")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false, columnDefinition = "varchar(50)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "varchar(50)")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "varchar(70)")
    private String email;

    @Column(name = "password", columnDefinition = "text")
    private String password;

    @Column(name = "phone", unique = true)
    private String phoneNumber;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = Constants.SERVER_DATE_FORMAT)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private AuthenticationRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
