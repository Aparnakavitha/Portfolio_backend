package com.example.backend_.Controller;

import com.example.backend_.entity.EducationEntity;
import com.example.backend_.Service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/education")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React frontend
public class EducationController {

    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    // Get all education records
    @GetMapping
    public ResponseEntity<List<EducationEntity>> getEducation() {
        List<EducationEntity> education = educationService.getAllEducation();
        return ResponseEntity.ok(education);
    }

    // Create a new education record
    @PostMapping
    public ResponseEntity<EducationEntity> createEducation(@RequestParam("name") String name,
                                                           @RequestParam("image") MultipartFile image) {
        try {
            EducationEntity education = new EducationEntity();
            education.setName(name);
            education.setImage(image.getBytes());
            EducationEntity savedEducation = educationService.saveEducation(education);
            return new ResponseEntity<>(savedEducation, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Get education record by ID
    @GetMapping("/{id}")
    public ResponseEntity<EducationEntity> getEducation(@PathVariable UUID id) {
        EducationEntity education = educationService.getEducationById(id);
        return education != null ? ResponseEntity.ok(education) : ResponseEntity.notFound().build();
    }

    // Update education record by ID
    @PutMapping("/{id}")
    public ResponseEntity<EducationEntity> updateEducation(@PathVariable UUID id,
                                                           @RequestParam("name") String name,
                                                           @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            EducationEntity updatedEducation = educationService.updateEducation(id, name, image);
            return updatedEducation != null ? ResponseEntity.ok(updatedEducation) : ResponseEntity.notFound().build();
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Delete education record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable UUID id) {
        boolean deleted = educationService.deleteEducation(id);
        if (deleted) {
            return new ResponseEntity<>("Education record deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Education record not found", HttpStatus.NOT_FOUND);
        }
    }
}
