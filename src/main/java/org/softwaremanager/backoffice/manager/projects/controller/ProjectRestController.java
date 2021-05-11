package org.softwaremanager.backoffice.manager.projects.controller;

import org.softwaremanager.backoffice.component.Paginate;
import org.softwaremanager.backoffice.manager.bugtracker.domain.Bug;
import org.softwaremanager.backoffice.manager.bugtracker.repository.BugRespository;
import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.projects.domain.dto.ProjectDto;
import org.softwaremanager.backoffice.manager.projects.repository.ProjectRepository;
import org.softwaremanager.backoffice.manager.projects.service.ProjectService;
import org.softwaremanager.backoffice.manager.tasks.domain.dto.TaskDto;
import org.softwaremanager.backoffice.manager.tasks.service.TaskService;
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

    final int SIZE = 20;
    final ProjectRepository repository;
    final ProjectService service;
    final TaskService taskService;
    final BugRespository bugRespository;

    public ProjectRestController(ProjectRepository repository, ProjectService service, TaskService taskService, BugRespository bugRespository) {
        this.repository = repository;
        this.service = service;
        this.taskService = taskService;
        this.bugRespository = bugRespository;
    }

    @GetMapping
    public ResponseEntity< List<Project> > showProjects(@RequestParam(required = false, name = "p") Integer p){
        int page = p == null || p<0 ? 0 : p;

        PageRequest pageRequest = new Paginate().
                page(page).
                size(SIZE).
                sortBy("id").
                descending().
                build();

        Page<Project> pageProject = repository.findAll( pageRequest );
        return ResponseEntity.ok( pageProject.getContent() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> showProjectById(@PathVariable("id") Long id){
        if(repository.findById(id).isPresent()){
            return ResponseEntity.ok( service.findById(id) );
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/info")
    public ResponseEntity<ProjectDto> showProjectByIdInfo(@PathVariable("id") Long id){
        if(repository.findById(id).isPresent()){
            return ResponseEntity.ok( service.findByIdDto(id) );
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Project> saveProject(@RequestBody Project project ){
        Project newProject = service.save(project);
        if(project==null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(project);
    }


    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDto>> showTasksById(
            @RequestParam(required = false, name = "p") Integer p,
            @PathVariable("id") Long id ){

        if(repository.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        int page = p == null || p<0 ? 0 : p;
        Project project = repository.findById(id).get();

        Pageable pageRequest = PageRequest.of( page, SIZE, Sort.by("id").descending());
        List<TaskDto> taskDtoList =  taskService.findByProjectDto(project, pageRequest);

        if(taskDtoList !=null){
            return ResponseEntity.ok( taskDtoList);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/bugs")
    public ResponseEntity<List<Bug>> showBugsByProject(@RequestParam(required = false, name = "p") Integer p,
                                                       @PathVariable("id") Long id){
        if(repository.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Project project = service.findById(id);
        int page = p == null || p<0 ? 0 : p;

        PageRequest pageRequest = new Paginate().
                page(page).
                size(SIZE).
                sortBy("id").
                descending().
                build();
        List<Bug> bugList = bugRespository.findByProject(project,pageRequest);
        if(bugList==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(bugList);
        }
    }

}
