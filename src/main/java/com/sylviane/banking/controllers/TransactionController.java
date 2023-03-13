package com.sylviane.banking.controllers;

import com.sylviane.banking.dto.TransactionDTO;
import com.sylviane.banking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @PathVariable TransactionDTO transactionDTO
    ){
        return ResponseEntity.ok(service.save(transactionDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransactionDTO> findById(
            @PathVariable("transaction-id") Integer transactionId
    ){
        return ResponseEntity.ok(service.findById(transactionId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<TransactionDTO>> findAllByUserId(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("transaction-id") Integer transactionId
    ){
        service.delete(transactionId);
        return ResponseEntity.accepted().build();
    }
}
