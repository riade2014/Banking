package com.sylviane.banking.dto;

import com.sylviane.banking.models.Transaction;
import com.sylviane.banking.models.TransactionType;
import com.sylviane.banking.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private Integer id;
    @Positive//un utilisateur ne doit pas entrer un montant négatif
    /*@Max(value = 100000)
    @Min(value = 5)*/
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String destinationIban;
    private LocalDate transactionDate;
    private Integer userId;

    public static TransactionDTO fromEntity(Transaction transaction){
        return TransactionDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .transactionDate(transaction.getTransactionDate())
                .destinationIban(transaction.getDestinationIban())
                //.userId(transaction.getUser().getId_user())
                .userId(transaction.getUser().getId())
                .build();
    }

    public static Transaction toEntity(TransactionDTO transaction){
        return Transaction.builder()
                //.id(transaction.getId_transaction())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .transactionDate(LocalDate.now())
                .destinationIban(transaction.getDestinationIban())
                .user(
                        User.builder()
                                .id(transaction.getUserId())
                                //.id_user(transaction.getUserId())
                                .build()
                )
                .build();
    }
}
