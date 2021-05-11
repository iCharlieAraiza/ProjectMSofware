package org.softwaremanager.backoffice.manager.tasks.repository;

import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByIdAndProject(Long id, Project project);
    List<Task> findByProject(Project project);
    Page<Task> findAll(Pageable pageable);
    List<Task> findByProject(Project project, Pageable pageable);
    List<Task> findTop5ByProjectOrderByIdDesc(Project project);
    List<Task> findTop20ByProjectOrderByIdDesc(Project project);
}
