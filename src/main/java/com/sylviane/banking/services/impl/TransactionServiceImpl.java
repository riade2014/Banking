package com.sylviane.banking.services.impl;

import com.sylviane.banking.dto.TransactionDTO;
import com.sylviane.banking.models.Transaction;
import com.sylviane.banking.models.TransactionType;
import com.sylviane.banking.repository.TransactionRepository;
import com.sylviane.banking.services.TransactionService;
import com.sylviane.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final ObjectsValidator<TransactionDTO>validator;

    @Override
    public Integer save(TransactionDTO dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDTO.toEntity(dto);
        BigDecimal transactionMultiplier = BigDecimal.valueOf(transactionType(transaction.getType()));
        BigDecimal amount = transaction.getAmount().multiply(transactionMultiplier);
        transaction.setAmount(amount);
        return repository.save(transaction).getId();
    }

    @Override
    public List<TransactionDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(TransactionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO findById(Integer id) {
        return repository.findById(id)
                .map(TransactionDTO::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune transaction trouvée pour cet ID : "+id));
    }

    @Override
    public void delete(Integer id) {
        //todo on vefrifie avant
        repository.deleteById(id);
    }

    public int transactionType(TransactionType type){
        return TransactionType.TRANSFERT == type ? -1 : 1;
    }

    @Override
    public List<TransactionDTO> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(TransactionDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
