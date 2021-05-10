package org.softwaremanager.backoffice.manager.projects.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ProjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;
    private String typeProject;
    private String urlRepository;
    private Integer planTime;

    public ProjectDetails() {
    }

    public ProjectDetails(Long id, String language, String typeProject, String urlRepository, Integer planTime) {
        this.id = id;
        this.language = language;
        this.typeProject = typeProject;
        this.urlRepository = urlRepository;
        this.planTime = planTime;
    }
}
