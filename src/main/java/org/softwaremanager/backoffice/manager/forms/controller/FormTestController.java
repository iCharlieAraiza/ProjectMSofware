package org.softwaremanager.backoffice.manager.forms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/form")
public class FormTestController {
    @GetMapping("/task")
    public String taskForm(){
        return "task-form-test";
    }
}
