package com.sylviane.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address extends AbstractEntity{
    /*@Id
    @GeneratedValue*/
    private Integer id;
    private String street;
    private Integer zipCode;
    private String city;
    private String country;
    @OneToOne
    @JoinColumn(name = "id_user")//on peut créer un utilisateur sans créer de compte
    private User user;
}
