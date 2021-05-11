package org.softwaremanager.backoffice.manager.tasks.controller;

import org.softwaremanager.backoffice.component.Paginate;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.softwaremanager.backoffice.manager.tasks.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskRestController {
    private TaskRepository repository;

    @GetMapping
    public ResponseEntity<List<Task>> showTasks(@RequestParam(required = false, name = "p") Integer p){
        int page = p == null ? 0 : p;
        List taks = new Paginate().page(page).size(15).sortBy("id").descending().get(repository).findAll();

        return taks == null ? ResponseEntity.notFound().build() : ResponseEntity.ok( taks );
    }

}
