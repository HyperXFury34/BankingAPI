package com.example.banking.repos;
import com.example.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepo extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
