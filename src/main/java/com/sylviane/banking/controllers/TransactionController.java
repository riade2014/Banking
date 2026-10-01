package com.sylviane.banking.controllers;

import com.sylviane.banking.dto.TransactionDTO;
import com.sylviane.banking.services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 4800, allowCredentials = "false")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "transaction")
public class TransactionController {
    private final TransactionService service;

    //@PostMapping("/")
    @PostMapping("/users/")
    public ResponseEntity<Integer> save(
            @PathVariable TransactionDTO transactionDTO
    ){
        return ResponseEntity.ok(service.save(transactionDTO));
    }

    //@GetMapping("/")
    @GetMapping("/users/")
    public ResponseEntity<List<TransactionDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    //@GetMapping("/{transaction-id}")
    @GetMapping("/users/{transaction-id}")
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
