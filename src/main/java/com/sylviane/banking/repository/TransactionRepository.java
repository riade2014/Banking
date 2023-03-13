package com.sylviane.banking.repository;

import com.sylviane.banking.dto.TransactionSumDetails;
import com.sylviane.banking.models.Transaction;
import com.sylviane.banking.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserId(Integer userId);

    //@Query("select sum(t.amount) from Transaction t where t.user.id_user = :userId")
    @Query("select sum(t.amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") Integer userId);

    //@Query("select max(abs(t.amount)) as amount from Transaction t where t.user.id_user = :userId and t.type = :transactionType")
    @Query("select max(abs(t.amount)) as amount from Transaction t where t.user.id = :userId and t.type = :transactionType")
    BigDecimal findHighestAccountByTransactionType(Integer userId, TransactionType transactionType);

    //@Query("select t.creationDate, sum(t.amount) from Transaction t where t.user.id_user = :userId and t.creationDate between :start and :end group by t.creationDate")
    @Query(
            "select t.transactionDate as transactionDate, sum(t.amount) from Transaction t where t.user.id = :userId and t.creationDate between"
            + ":start and :end group by t.transactionDate")
    List<TransactionSumDetails> findSumTransactionByDate(LocalDateTime start, LocalDateTime end, Integer userId);
}
