package org.softwaremanager.backoffice.manager.projects.service;

import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.projects.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImp implements ProjectService{
    private ProjectRepository projectRepository;

    public ProjectServiceImp(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Page<Project> getAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }
}
