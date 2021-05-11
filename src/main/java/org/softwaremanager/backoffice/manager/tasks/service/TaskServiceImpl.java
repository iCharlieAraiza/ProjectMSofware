package org.softwaremanager.backoffice.manager.tasks.service;

import org.softwaremanager.backoffice.auth.service.UserService;
import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.projects.domain.dto.ProjectDto;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.softwaremanager.backoffice.manager.tasks.domain.dto.TaskDto;
import org.softwaremanager.backoffice.manager.tasks.repository.TaskRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    final TaskRepository repository;
    final UserService userService;

    public TaskServiceImpl(TaskRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<TaskDto> findLast50inOrdDesc() {
        return null;
    }

    @Override
    public List<TaskDto> findLast20ByProjectinOrdDesc(Project project){
        List<Task> taskList = repository.findTop20ByProjectOrderByIdDesc(project);
        List<TaskDto> taskDtoList = new ArrayList<>();

        if(taskList==null){
            return taskDtoList;
        }

        taskList.forEach(task -> {

            TaskDto taskDto = new TaskDto(task);
            /*
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setName(task.getName());
            taskDto.setDescription(task.getDescription());
            taskDto.setStatusCheck(task.getStatusCheck());
            taskDto.setPriority(task.getPriority());
            taskDto.setPlanTime(task.getPlanTime());
            taskDto.setActualTime(task.getActualTime());
            taskDto.setStartDate(task.getStartDate());
            taskDto.setFinishDate(task.getFinishDate());
            taskDto.setProject(task.getProject());
            */
            taskDto.setUserInfoDto(userService.findByEmail(task.getUser().getEmail()));

            taskDtoList.add(taskDto);
        });

        return taskDtoList;
    }

    @Override
    public List<Task> findByProject(Project project, Pageable pageable) {
        return repository.findByProject(project, pageable);
    }

    @Override
    public List<TaskDto> findByProjectDto(Project project, Pageable pageable) {
        List<Task> taskList = repository.findByProject(project, pageable);

        if(taskList==null){
            return null;
        }

        List<TaskDto> taskDtoList = new ArrayList<>();

        taskList.forEach(task -> {
            TaskDto taskDto = new TaskDto(task);
            taskDto.setUserInfoDto(userService.findByEmail(task.getUser().getEmail()));
            taskDtoList.add(taskDto);
            });

        return taskDtoList;
    }
}
