package com.example.backend_.entity;



import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "mainproject")
public class MainProject {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private byte[] image;

    public MainProject() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image != null ? image : new byte[0];
    }
}


