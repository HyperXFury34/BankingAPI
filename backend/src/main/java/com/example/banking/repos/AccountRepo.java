package com.example.banking.repos;
import com.example.banking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountRepo extends JpaRepository<Account, Long> {}
