package org.softwaremanager.backoffice.manager.tasks.controller;

import org.softwaremanager.backoffice.component.Paginate;
import org.softwaremanager.backoffice.manager.tasks.domain.dto.TaskDto;
import org.softwaremanager.backoffice.manager.tasks.repository.TaskRepository;
import org.softwaremanager.backoffice.manager.tasks.service.TaskService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskRestController {
    final private TaskRepository repository;
    final private TaskService service;

    final int SIZE = 15;

    public TaskRestController(TaskRepository repository, TaskService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> showTasks(@RequestParam(required = false, name = "p") Integer p){
        int page = p == null ? 0 : p;
        PageRequest pageRequest = new Paginate().page(page).size(SIZE).sortBy("id").descending().build();
        List<TaskDto> taskDtoList = service.findAll(pageRequest);
        return taskDtoList == null ? ResponseEntity.notFound().build() : ResponseEntity.ok( taskDtoList );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> showTask( @PathVariable("id") Long id ){
        TaskDto taskDto = service.findById(id);
        if(taskDto==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(taskDto);
        }
    }


}
