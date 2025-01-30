package com.example.backend_.repository;



import com.example.backend_.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EducationRepository extends JpaRepository<EducationEntity, UUID> {
}

