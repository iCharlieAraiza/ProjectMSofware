package org.softwaremanager.backoffice.manager.tasks.controller;

import org.softwaremanager.backoffice.auth.domain.User;
import org.softwaremanager.backoffice.auth.repository.UserRepository;
import org.softwaremanager.backoffice.manager.projects.domain.Project;
import org.softwaremanager.backoffice.manager.projects.repository.ProjectRepository;
import org.softwaremanager.backoffice.manager.tasks.domain.Task;
import org.softwaremanager.backoffice.manager.tasks.repository.TaskRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class TasksByProjectsController {
    final private TaskRepository taskRepository;
    final private ProjectRepository projectRepository;
    final private UserRepository userRepository;

    public TasksByProjectsController(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}/tasks")
    public String showProjects(@PathVariable("id") Long id, Model model){
        if(projectRepository.findById(id).isPresent()){
            Project project = projectRepository.findById(id).get();
            //model.addAttribute("id", id);
            System.out.println(project.toString());
            model.addAttribute("project", project);
            return "form/tasks";
        }else {
            System.out.println("no se encontro");
            return "redirect:/";
        }
    }

    @GetMapping("/{id}/tasks/create")
    public String newTask(Model model){
        Task task = new Task();
        List<Project> listProjects = projectRepository.findAll();
        model.addAttribute("projects", listProjects);
        model.addAttribute("task", task);
        return "html/task-form";
    }

    @PostMapping("{id}/tasks/saveTask")
    public String saveTask(@ModelAttribute("task") Task task){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        task.setUser( user );
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("{id}/tasks/edit/{task_id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        Task task = taskRepository.findById(id).get();
        System.out.println(task);
        model.addAttribute("task", task);
        model.addAttribute("projects", projectRepository.findAll());
        return "html/task-form";
    }

    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Long id){
        Task task = taskRepository.findById(id).get();
        if(task.getStatusCheck().equals("COMPLETE")){
            task.setStatusCheck("INCOMPLETE");
        }else{
            task.setStatusCheck("COMPLETE");
        }

        taskRepository.save(task);
        return "redirect:/tasks";
    }
}
