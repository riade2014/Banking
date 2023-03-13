package com.sylviane.banking.controllers;

import com.sylviane.banking.dto.TransactionSumDetails;
import com.sylviane.banking.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
   private final StatisticsService service;

   @GetMapping("/sum-by-id/{user-id}")
   public ResponseEntity<List<TransactionSumDetails>> findSumTransactionByDate(
           @PathVariable("user-id") Integer userId,
           @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate startDate ,
           @RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate endDate
   ){
        return ResponseEntity.ok(service.findSumTransactionByDate(startDate,endDate,userId));
   }

    @GetMapping("/account-balance/{user-id}")
   public ResponseEntity<BigDecimal> getAccountBalance(
           @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.getAccountBalance(userId));
    }

    @GetMapping("/highest-transfert/{user-id}")
    public ResponseEntity<BigDecimal> highestTransfert(
           @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.highestTransfert(userId));
    }

    @GetMapping("/highest-deposit/{user-id}")
    public ResponseEntity<BigDecimal> highestDeposit(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.highestDeposit(userId));
    }
}
