package org.softwaremanager.backoffice.manager.projects.domain.dto;

import lombok.Data;
import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.projects.domain.ProjectDetails;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.softwaremanager.backoffice.manager.tasks.domain.dto.TaskDto;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Data
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private ProjectDetails projectDetails;
    private String projectStatus;
    List<TaskDto> taskList;

    public ProjectDto(){
    }

    public ProjectDto(Project project){
        id = project.getId();
        name = project.getName();
        description = project.getDescription();
        startDate = project.getStartDate();
        endDate = project.getEndDate();
        projectStatus = project.getStatus_project();
        projectDetails = project.getProjectDetails();
    }

}
