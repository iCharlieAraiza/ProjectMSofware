package org.softwaremanager.backoffice.manager.tasks.domain;

import lombok.Data;
import org.softwaremanager.backoffice.auth.domain.User;
import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.tasks.domain.dto.TaskDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Lob
    private String description;
    private String statusCheck = "INCOMPLETE";
    private String priority;
    private Integer planTime;
    private Integer actualTime;
    private Date startDate;
    @Temporal(TemporalType.TIME)
    private Date finishDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Task(TaskDto taskDto){
        id = taskDto.getId();
        name = taskDto.getName();
        description = taskDto.getDescription();
        statusCheck = taskDto.getStatusCheck();
        priority = taskDto.getPriority();
        planTime = taskDto.getPlanTime();
        actualTime = taskDto.getActualTime();
        startDate = taskDto.getStartDate();
        finishDate = taskDto.getFinishDate();
    }

    public Task(){
    }

    public Task(String name) {
        this.name = name;
    }


}