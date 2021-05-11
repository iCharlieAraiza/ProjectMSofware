package org.softwaremanager.backoffice.manager.projects.controller;

import org.softwaremanager.backoffice.component.Paginate;
import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.projects.repository.ProjectRepository;
import org.softwaremanager.backoffice.manager.tasks.domain.dto.TaskDto;
import org.softwaremanager.backoffice.manager.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectRestController {

    final ProjectRepository repository;
    @Autowired
    TaskService taskService;

    public ProjectRestController(ProjectRepository repository) {
        this.repository = repository;
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity< List<Project> > showProjects(@RequestParam(required = false, name = "p") Integer p){

        int page = p == null || p<0 ? 0 : p;
        int size = 15;

        PageRequest pageRequest = new Paginate().
                page(page).
                size(size).
                sortBy("id").
                descending().
                build();

        Page<Project> pageProject = repository.findAll( pageRequest );
        return ResponseEntity.ok( pageProject.getContent() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> showProjectById(@PathVariable("id") Long id){
        if(repository.findById(id).isPresent()){
            return ResponseEntity.ok( repository.findById(id).get() );
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDto>> showTasksById(@RequestParam(required = false, name = "p") Integer p,
                                                       @PathVariable("id") Long id ){

        Project project = repository.findById(id).get();

        int page = p == null || p<0 ? 0 : p;
        int size = 20;

        Pageable pageRequest = PageRequest.of( page, size, Sort.by("id").descending());

        List<TaskDto> taskDtoList =  taskService.findByProjectDto(project, pageRequest);
        if(taskDtoList !=null){
            return ResponseEntity.ok( taskDtoList);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
