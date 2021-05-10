package org.softwaremanager.backoffice.manager.projects.service;

import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService{
    Page<Project> getAll(Pageable pageable);
}
