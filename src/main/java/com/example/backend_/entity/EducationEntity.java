package com.example.backend_.entity;



import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "education")
public class EducationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name; // The name of the course or school

    @Lob
    @Column(nullable = false)
    private byte[] image; // Storing image as byte array
}

