package com.example.banking.controllers;

import com.example.banking.models.User;
import com.example.banking.repos.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserRepo users;
  private final SecretKey key;

  public AuthController(UserRepo users, @Value("${jwt.secret}") String secret) {
    this.users = users;
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  @PostMapping("/register")
  public Map<String,Object> register(@RequestBody Map<String,String> body) {
    User u = new User();
    u.setEmail(body.get("email"));
    u.setPassword(body.get("password"));
    users.save(u);
    return Map.of("id", u.getId(), "email", u.getEmail());
  }

  @PostMapping("/login")
  public Map<String,String> login(@RequestBody Map<String,String> body) {
    User u = users.findByEmail(body.get("email")).orElseThrow();
    if (!u.getPassword().equals(body.get("password"))) throw new RuntimeException("bad creds");
    String token = Jwts.builder().subject(String.valueOf(u.getId())).compact();
    return Map.of("token", token);
  }
}
