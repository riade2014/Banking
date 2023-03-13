package com.sylviane.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
//@Entity//peremt à la classe d'interagir avec la base de données
@Entity
@Table(name="users")//permet de donner un nom autre que celui de la classe à la table dans la base de données
public class User extends AbstractEntity implements UserDetails {
    /*@Id
    @GeneratedValue*/ //genere automatiquement les id
    private Integer id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean active;
    @OneToOne
    private Address address;
    @OneToMany(mappedBy="user")
    private List<Transaction> transactions;
    @OneToMany(mappedBy="user")
    private List<Contact> contacts;
    @OneToOne
    private Account account;
    @OneToOne
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public boolean isEnabled() {
        return active;
    }
}
