package com.example.backend_.repository;





import com.example.backend_.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, UUID> {
    Optional<Login> findByUsername(String username);
}