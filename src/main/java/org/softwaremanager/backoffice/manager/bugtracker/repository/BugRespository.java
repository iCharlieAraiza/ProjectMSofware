package org.softwaremanager.backoffice.manager.bugtracker.repository;

import org.softwaremanager.backoffice.manager.bugtracker.domain.Bug;
import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugRespository extends JpaRepository<Bug, Long> {
    List<Bug> findByProject(Project project, Pageable pageable);
}
