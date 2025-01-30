package com.example.backend_.Service;

import com.example.backend_.entity.SkillEntity;
import com.example.backend_.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<SkillEntity> getAllSkills() {
        return skillRepository.findAll();
    }

    public SkillEntity saveSkill(SkillEntity skill) {
        return skillRepository.save(skill);
    }

    public SkillEntity getSkillById(UUID id) {
        return skillRepository.findById(id).orElse(null);
    }

    public boolean deleteSkill(UUID id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public SkillEntity updateSkill(UUID id, String name, MultipartFile image) throws IOException {
        SkillEntity existingSkill = skillRepository.findById(id).orElse(null);
        if (existingSkill != null) {
            existingSkill.setName(name);
            if (image != null && !image.isEmpty()) {
                existingSkill.setImage(image.getBytes());
            }
            return skillRepository.save(existingSkill);
        }
        return null;
    }
}
