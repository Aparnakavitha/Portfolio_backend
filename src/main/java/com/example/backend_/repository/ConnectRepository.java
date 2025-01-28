package com.example.backend_.repository;

import com.example.backend_.entity.ConnectForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConnectRepository extends JpaRepository<ConnectForm, Long> {
    // Custom query to find the latest ConnectForm by ID
    Optional<ConnectForm> findTopByOrderByIdDesc();
}
