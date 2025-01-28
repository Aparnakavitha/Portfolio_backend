package com.example.backend_.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend_.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}