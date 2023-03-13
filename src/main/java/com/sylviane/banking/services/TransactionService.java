package com.sylviane.banking.services;

import com.sylviane.banking.dto.TransactionDTO;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDTO>{
    List<TransactionDTO> findAllByUserId(Integer userId);
}
