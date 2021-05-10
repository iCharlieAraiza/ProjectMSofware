package org.softwaremanager.backoffice.manager.projects.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@EqualsAndHashCode(exclude="tasks")

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_project")
    private ProjectDetails projectDetails;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Task> tasks = new HashSet<>();


    private String status_project = "ACTIVE";

    public Project(){
        this.startDate = new Date();
    }

    public Project(Long id, String name, String description, ProjectDetails projectDetails, String status_project) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = new Date();
        this.projectDetails = projectDetails;
        this.status_project = status_project;
    }
}
