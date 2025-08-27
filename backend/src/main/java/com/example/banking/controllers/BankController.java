package com.example.banking.controllers;

import com.example.banking.models.Account;
import com.example.banking.repos.AccountRepo;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BankController {
  private final AccountRepo accounts;
  public BankController(AccountRepo accounts){ this.accounts = accounts; }

  @GetMapping("/accounts/{id}")
  public Account get(@PathVariable Long id){ return accounts.findById(id).orElseThrow(); }

  @PostMapping("/transfers")
  public Map<String,Object> transfer(@RequestBody Map<String,Object> body) {
    Long fromId = Long.valueOf(body.get("from").toString());
    Long toId = Long.valueOf(body.get("to").toString());
    Long amount = Long.valueOf(body.get("amount").toString());
    Account from = accounts.findById(fromId).orElseThrow();
    Account to = accounts.findById(toId).orElseThrow();
    if (from.getBalance() < amount) throw new RuntimeException("insufficient");
    from.setBalance(from.getBalance() - amount);
    to.setBalance(to.getBalance() + amount);
    accounts.save(from); accounts.save(to);
    return Map.of("ok", true);
  }
}
