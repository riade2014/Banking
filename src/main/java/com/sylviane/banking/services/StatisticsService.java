package com.sylviane.banking.services;

import com.sylviane.banking.dto.TransactionSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<TransactionSumDetails> findSumTransactionByDate(LocalDate startDate , LocalDate endDate, Integer userId);
    BigDecimal getAccountBalance(Integer userId);
    BigDecimal highestTransfert(Integer userId);
    BigDecimal highestDeposit(Integer userId);
}
