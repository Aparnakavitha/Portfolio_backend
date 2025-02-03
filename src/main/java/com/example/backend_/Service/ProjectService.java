package com.example.backend_.Service;

import com.example.backend_.entity.Project;
import com.example.backend_.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Get all projects
    public List<Project> getAllProjects() {
        try {
            List<Project> projects = projectRepository.findAll();
            if (projects == null) {
                // Log or handle this case if necessary
                projects = new ArrayList<>();
            }
            return projects;
        } catch (Exception e) {
            // Log the error (you could use a logger here)
            System.err.println("Error retrieving projects: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of an error
        }
    }

    // Get a project by ID
    public Project getProjectById(UUID id) {
        try {
            return projectRepository.findById(id).orElse(null);
        } catch (Exception e) {
            // Log the error
            System.err.println("Error retrieving project by ID: " + e.getMessage());
            return null;
        }
    }

    // Add a new project
    public Project addProject(String name, MultipartFile image, String description) throws IOException {
        try {
            Project project = new Project();
            project.setName(name);
            project.setDescription(description);

            if (image != null && !image.isEmpty()) {
                project.setImage(image.getBytes());
            }

            return projectRepository.save(project);
        } catch (IOException e) {
            System.err.println("Error saving project: " + e.getMessage());
            throw e; // Re-throw the exception to be handled in the controller
        } catch (Exception e) {
            System.err.println("Unexpected error adding project: " + e.getMessage());
            throw new RuntimeException("Error adding project: " + e.getMessage());
        }
    }

    public Project updateProject(UUID id, String name, MultipartFile image, String description) throws IOException {
        try {
            Project existingProject = getProjectById(id);
            if (existingProject != null) {
                existingProject.setName(name);
                existingProject.setDescription(description);

                if (image != null && !image.isEmpty()) {
                    existingProject.setImage(image.getBytes());
                }

                return projectRepository.save(existingProject);
            }
            return null;
        } catch (IOException e) {
            System.err.println("Error updating project: " + e.getMessage());
            throw e; // Re-throw the exception to be handled in the controller
        } catch (Exception e) {
            System.err.println("Unexpected error updating project: " + e.getMessage());
            throw new RuntimeException("Error updating project: " + e.getMessage());
        }
    }

    // Delete a project
    public boolean deleteProject(UUID id) {
        try {
            if (projectRepository.existsById(id)) {
                projectRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            // Log error
            System.err.println("Error deleting project: " + e.getMessage());
            return false;
        }
    }
}
