package com.sylviane.banking.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)//permet d'utiliser les anotations @CreatedDate et @LastModifiedDate
public class AbstractEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(//permet de fournir des informations à la colonne
           name = "createdDate",
            nullable = false,
            updatable = false
    )
    @CreatedDate//hibernate va detecter creationDate et la mettre à jour dans la bd
    private LocalDateTime creationDate;
    @LastModifiedDate
    @Column(name = "lastModificationDate")
    private LocalDateTime lastUpdated;
}
