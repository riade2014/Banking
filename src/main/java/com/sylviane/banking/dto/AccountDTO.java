package com.sylviane.banking.dto;

import com.sylviane.banking.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDTO {
    private Integer id;//cet id est utilisé pour le mapping
    private String iban;
    private UserDTO user;//soit on fait appel l'utilisateur
    //soit on definit l'Id de l'utilisateur
    //private Integer userId;
    public static AccountDTO fromEntity(Account account){
        return AccountDTO.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDTO.fromEntity(account.getUser()))
                .build();
    }

    public static Account toEntity(AccountDTO account){
        return Account.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDTO.toEntity(account.getUser()))
                .build();
    }

}
