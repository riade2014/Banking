package com.sylviane.banking.repository;

import com.sylviane.banking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByIban(String iban);

    //Optional<Account> findUserId(Integer id_user);
    Optional<Account> findByUserId(Integer id_user);
}
