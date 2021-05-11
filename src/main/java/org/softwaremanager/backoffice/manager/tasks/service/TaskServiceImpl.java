package org.softwaremanager.backoffice.manager.tasks.service;

import org.softwaremanager.backoffice.auth.service.UserService;
import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.softwaremanager.backoffice.manager.tasks.domain.dto.TaskDto;
import org.softwaremanager.backoffice.manager.tasks.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    final TaskRepository repository;
    final UserService userService;

    public TaskServiceImpl(TaskRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public TaskDto findById(Long id) {
        Optional<Task> task = repository.findById(id);

        if(task.isEmpty()){
            return null;
        }else {
            TaskDto taskDto = new TaskDto(task.get());
            taskDto.setUserInfoDto(userService.findByEmail(task.get().getUser().getEmail()));
            return taskDto;        }
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
            taskDto.setUserInfoDto(userService.findByEmail(task.getUser().getEmail()));

            taskDtoList.add(taskDto);
        });

        return taskDtoList;
    }

    @Override
    public List<TaskDto> findAll(Pageable pageable) {
        List<Task> taskList = repository.findAll( pageable).getContent();
        List<TaskDto> taskDtoList = new ArrayList<>();

        taskList.forEach(task -> {
            TaskDto taskDto = new TaskDto(task);
            taskDto.setUserInfoDto(userService.findByEmail(task.getUser().getEmail()));
            taskDtoList.add(taskDto);
        });
        return taskDtoList;
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

    @Override
    public List<TaskDto> findTop5ByProjectOrderByIdDesc(Project project) {
        List<Task> taskList = repository.findTop5ByProjectOrderByIdDesc(project);

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
