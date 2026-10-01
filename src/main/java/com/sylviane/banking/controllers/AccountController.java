package com.sylviane.banking.controllers;

import com.sylviane.banking.dto.AccountDTO;
import com.sylviane.banking.services.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 4800, allowCredentials = "false")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "account")
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
