package org.softwaremanager.backoffice.manager.tasks.service;

import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.softwaremanager.backoffice.manager.tasks.domain.dto.TaskDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    TaskDto findById(Long id);
    List<TaskDto> findLast50inOrdDesc();
    List<TaskDto> findLast20ByProjectinOrdDesc(Project project);
    List<TaskDto> findAll(Pageable pageable);
    List<TaskDto> findByProjectDto(Project project, Pageable pageable);
    List<TaskDto> findTop5ByProjectOrderByIdDesc(Project project);
}
