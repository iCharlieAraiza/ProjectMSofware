package org.softwaremanager.backoffice.manager.bootstrap;

import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.projects.domain.ProjectDetails;
import org.softwaremanager.backoffice.manager.projects.repository.ProjectRepository;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.softwaremanager.backoffice.manager.tasks.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    final ProjectRepository projectRepository;
    final TaskRepository taskRepository;

    public BootStrapData(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public void run(String... args) {
        //showTasks();
        //findTaskByProject();
        //createNewProject();
        //showProjects();
    }
}