package org.softwaremanager.backoffice.manager.projects.controller;

import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.projects.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectRestController {

    ProjectRepository repository;

    public ProjectRestController(ProjectRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Project> showProjects(@RequestParam(required = false, name = "p") Integer p){
        int page = p == null || p<1 ? 0 : p,
                 size = 15;

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Project> pageProject = repository.findAll(pageRequest);

        return pageProject.getContent();
    }

    @GetMapping("/{id}")
    public Project showProjectById(@PathVariable("id") Long id){
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }else{
            return null;
        }
    }


}
