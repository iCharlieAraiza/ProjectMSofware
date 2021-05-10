package org.softwaremanager.backoffice.manager.projects.domain.dto;

import lombok.Data;
import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.projects.domain.ProjectDetails;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;

import java.util.List;

@Data
public class ProjectDto {
    Project project;
    List<Task> taskList;
}
