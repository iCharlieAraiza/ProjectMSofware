package org.softwaremanager.backoffice.auth.web;

import org.softwaremanager.backoffice.auth.domain.User;
import org.softwaremanager.backoffice.auth.domain.UserProfile;
import org.softwaremanager.backoffice.auth.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class UserDetailsController {
    final UserRepository repository;

    public UserDetailsController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String showAccount(Model model){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository.findByEmail(email);
        if(user.getUserProfile()==null){
            user.setUserProfile(new UserProfile());
        }
        model.addAttribute("user", user);

        return "/form/account-form";
    }

    @PostMapping("/saveAccount")
    public String saveAcount(@ModelAttribute("user") User user) {
        User userPassword = repository.findById(user.getId()).get();
        user.setPassword(userPassword.getPassword());
        repository.save(user);
        return "redirect:/account";
    }
}
