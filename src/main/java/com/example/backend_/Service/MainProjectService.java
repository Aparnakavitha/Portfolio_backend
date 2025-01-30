package com.example.backend_.Service;



import com.example.backend_.entity.MainProject;
import com.example.backend_.repository.MainProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MainProjectService {

    @Autowired
    private MainProjectRepository repository;

    public List<MainProject> getAllProjects() {
        return repository.findAll();
    }

    public Optional<MainProject> getProjectById(UUID id) {
        return repository.findById(id);
    }

    public MainProject addProject(MainProject project) {
        return repository.save(project);
    }

    public MainProject updateProject(UUID id, MainProject projectDetails) {
        return repository.findById(id).map(project -> {
            project.setName(projectDetails.getName());
            project.setImage(projectDetails.getImage());
            return repository.save(project);
        }).orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    public void deleteProject(UUID id) {
        repository.deleteById(id);
    }
}
