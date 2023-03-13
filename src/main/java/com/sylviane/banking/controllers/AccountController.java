package com.sylviane.banking.controllers;

import com.sylviane.banking.dto.AccountDTO;
import com.sylviane.banking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AccountDTO accountDTO){
        return ResponseEntity.ok(service.save(accountDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountDTO> findById(
            @PathVariable("account-id") Integer accountId
    ){
        return ResponseEntity.ok(service.findById(accountId));
    }

    @DeleteMapping("/{account-id}")
    public ResponseEntity<Void> delete(@PathVariable("account-id") Integer accountId){
        service.delete(accountId);
        return ResponseEntity.accepted().build();
    }
}
