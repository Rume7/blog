package com.codehacks.blog.controller;

/**
 * @author Rhume
 * @date Sept 7, 2021
 */
import com.codehacks.blog.dao.DataService;
import com.codehacks.blog.entities.Quality;
import com.codehacks.blog.entities.User;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class HomeController {
    
    @Inject
    DataService dataService;
    
    private Optional<User> currentUser;
    private List<Quality> currentQualities;
    
    @PostConstruct
    public void initialize() {
        String username = "Sally";
        this.currentUser = dataService.getUser(username);
        this.currentUser.ifPresent(user -> {
            this.currentQualities = dataService.getQualities(user);
        });
    }
    
    public User getCurrentUser() {
        return this.currentUser.orElse(null);
    }

    public List<Quality> getCurrentQualities() {
        return currentQualities;
    }
}
