package com.example.backend_.Service;


import com.example.backend_.entity.EducationEntity;
import com.example.backend_.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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

    // Get a specific education record by ID
    public EducationEntity getEducationById(UUID id) {
        return educationRepository.findById(id).orElse(null);
    }

    // Delete an education record by ID
    public boolean deleteEducation(UUID id) {
        if (educationRepository.existsById(id)) {
            educationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
