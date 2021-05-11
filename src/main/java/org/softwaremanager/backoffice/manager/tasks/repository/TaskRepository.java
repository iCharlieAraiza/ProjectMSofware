package org.softwaremanager.backoffice.manager.tasks.repository;

import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
    List<Task> findByProject(Project project, Pageable pageable);
    List<Task> findTop5ByOrderByIdDesc();
    List<Task> findTop20ByProjectOrderByIdDesc(Project project);
}
