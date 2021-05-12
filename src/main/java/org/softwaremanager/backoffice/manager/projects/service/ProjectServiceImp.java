package org.softwaremanager.backoffice.manager.projects.service;

import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.projects.domain.dto.ProjectDto;
import org.softwaremanager.backoffice.manager.projects.repository.ProjectRepository;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.softwaremanager.backoffice.manager.tasks.domain.dto.TaskDto;
import org.softwaremanager.backoffice.manager.tasks.repository.TaskRepository;
import org.softwaremanager.backoffice.manager.tasks.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService{
    private ProjectRepository projectRepository;
    private TaskService taskService;

    public ProjectServiceImp(ProjectRepository projectRepository, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.taskService = taskService;
    }

    @Override
    public Page<Project> getAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public ProjectDto findByIdDto(Long id) {
        if(projectRepository.findById(id).isPresent()){
            Project project = projectRepository.findById(id).get();
            ProjectDto projectDto = new ProjectDto(project);
            List<TaskDto> taskDtoList = taskService.findTop5ByProjectOrderByIdDesc(project);
            projectDto.setTaskList( taskDtoList );
            return projectDto;
        }else{
            return null;
        }
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Project project  = new Project();
        project.setId( projectDto.getId() );
        project.setName( projectDto.getName() );
        project.setDescription( projectDto.getDescription() );
        project.setStartDate( projectDto.getStartDate() );
        project.setEndDate( projectDto.getEndDate() );
        project.setProjectDetails(projectDto.getProjectDetails() );
        project.setStatus_project( projectDto.getProjectStatus() );

        projectRepository.save(project);
        return projectDto;
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project findById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }
}
