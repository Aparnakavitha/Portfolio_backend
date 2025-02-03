package com.example.backend_.Service;

import com.example.backend_.entity.EducationEntity;
import com.example.backend_.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    // Get all education records
    public List<EducationEntity> getAllEducation() {
        return educationRepository.findAll();
    }

    // Save a new education record
    public EducationEntity saveEducation(EducationEntity education) {
        return educationRepository.save(education);
    }

    // Get education record by ID
    public EducationEntity getEducationById(UUID id) {
        return educationRepository.findById(id).orElse(null);
    }

    // Update education record by ID
    public EducationEntity updateEducation(UUID id, String name, MultipartFile image) throws IOException {
        Optional<EducationEntity> optionalEducation = educationRepository.findById(id);

        if (optionalEducation.isPresent()) {
            EducationEntity education = optionalEducation.get();
            education.setName(name);
            if (image != null && !image.isEmpty()) {
                education.setImage(image.getBytes()); // Update image only if provided
            }
            return educationRepository.save(education);
        }
        return null; // Return null if record is not found
    }

    // Delete education record by ID
    public boolean deleteEducation(UUID id) {
        if (educationRepository.existsById(id)) {
            educationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
