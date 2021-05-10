package org.softwaremanager.backoffice.manager.bugtracker.domain;

import lombok.Data;
import org.softwaremanager.backoffice.manager.projects.domain.Project;

import javax.persistence.*;

@Entity
@Data
public class Bug {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_fk")
    private Project project;


    @Column()
    private String description;

}
