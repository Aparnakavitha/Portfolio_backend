package com.example.backend_.repository;



import com.example.backend_.entity.MainProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MainProjectRepository extends JpaRepository<MainProject, UUID> {
}
