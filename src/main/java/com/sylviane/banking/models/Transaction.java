package com.sylviane.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction extends AbstractEntity{
    /*@Id
    @GeneratedValue*/
    //private Integer id_transaction;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String destinationIban;//pour voir vers qui on a fait un virement
    @Column(updatable = false)
    private LocalDate transactionDate;
    /*private LocalDateTime creationDate;
    private LocalDateTime lastUpdated;*/
    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;
}
