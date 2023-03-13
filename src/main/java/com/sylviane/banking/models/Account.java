package com.sylviane.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account extends AbstractEntity {
    /*@Id
    @GeneratedValue*/
    private Integer id;
    private String iban;
    private LocalDateTime creatingDate;
    private LocalDateTime lastUpdated;
    @OneToOne
    @JoinColumn(name = "id_user")//on peut créer un utilisateur sans créer de compte
    private User user;
}
