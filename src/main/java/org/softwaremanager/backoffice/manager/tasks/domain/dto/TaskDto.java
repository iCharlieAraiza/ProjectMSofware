package org.softwaremanager.backoffice.manager.tasks.domain.dto;

import lombok.Data;
import org.softwaremanager.backoffice.auth.web.dto.UserInfoDto;
import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;

import java.io.Serializable;
import java.util.Date;

@Data
public class TaskDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String statusCheck;
    private String priority;
    private Integer planTime;
    private Integer actualTime;
    private Date startDate;
    private Date finishDate;
    private Project project;
    private UserInfoDto userInfoDto;

    public TaskDto(){}

    public TaskDto(Task task){
        id = task.getId();
        name = task.getName();
        description = task.getDescription();
        statusCheck = task.getStatusCheck();
        priority = task.getPriority();
        planTime = task.getPlanTime();
        actualTime = task.getActualTime();
        startDate = task.getStartDate();
        finishDate = task.getFinishDate();
        project = task.getProject();
    }
}
