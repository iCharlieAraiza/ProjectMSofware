package org.softwaremanager.backoffice.manager.bootstrap;

import org.softwaremanager.backoffice.auth.domain.User;
import org.softwaremanager.backoffice.auth.domain.UserProfile;
import org.softwaremanager.backoffice.auth.repository.UserRepository;
import org.softwaremanager.backoffice.auth.service.UserServiceImpl;
import org.softwaremanager.backoffice.manager.projects.repository.ProjectRepository;
import org.softwaremanager.backoffice.manager.tasks.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    final ProjectRepository projectRepository;
    final TaskRepository taskRepository;
    final UserServiceImpl userService;
    final UserRepository userRepository;

    public BootStrapData(ProjectRepository projectRepository, TaskRepository taskRepository, UserServiceImpl userService, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) {

        //showUser();
    }

    public void showUser(){
        UserDetails userDetails = userService.loadUserByUsername("ejemplo_correo@gmail.com");
        System.out.println(userDetails.toString());

        UserProfile userProfile = new UserProfile();
        userProfile.setGithubUrl("https://github.com/iCharlieAraiza");
        userProfile.setTitle("Ingeniero de software");
        userProfile.setUrlAvatar("https://www.gravatar.com/avatar/94d093eda664addd6e450d7e9781bcad?s=180&d=identicon&r=PG");

        User user = userRepository.findByEmail( userDetails.getUsername() );
        user.setUserProfile( userProfile );

        userRepository.save( user );
    }
}