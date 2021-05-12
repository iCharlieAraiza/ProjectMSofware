package org.softwaremanager.backoffice.manager.bugtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bugs")
public class BugController {
    @GetMapping
    public String showBugs(){
        return "/form/bugs";
    }
}
