package org.softwaremanager.backoffice.manager.tasks.controller;

import org.softwaremanager.backoffice.auth.service.UserService;
import org.softwaremanager.backoffice.component.Paginate;
import org.softwaremanager.backoffice.manager.tasks.domain.dto.TaskDto;
import org.softwaremanager.backoffice.manager.tasks.service.TaskService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskRestController {
    final private TaskService service;
    final private UserService userService;

    final int SIZE = 15;

    public TaskRestController(TaskService service, UserService userService) {
        this.service = service;
        this.userService = userService;
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
        System.out.println("The Request is Ok!" + id);
        TaskDto taskDto = service.findById(id);
        if(taskDto==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(taskDto);
        }
    }

    @GetMapping("/create")
    public ResponseEntity<TaskDto> showTask( ){
        TaskDto taskDto = new TaskDto();
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();;
        taskDto.setUserInfoDto( userService.findByEmail(currentUserEmail) );
        if(taskDto==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(taskDto);
        }
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto){
        System.out.println(taskDto.toString());
        TaskDto newTask = service.save(taskDto);
        System.out.println("Se ha hecho una petición put");
        return ResponseEntity.ok(newTask);
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto){
        TaskDto newTask = service.save(taskDto);
        System.out.println("Se ha hecho una petición put");
        return ResponseEntity.ok(newTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(){
        return ResponseEntity.ok(null);
    }


}
