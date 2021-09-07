package com.codehacks.blog.dao;

import com.codehacks.blog.entities.User;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class DataInitializer {
    
    @Inject
    DataService dataService;
    
    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
        if (dataService.getAllUsers().isEmpty()) {
            User sally = dataService.createUser("sally@gmail.com", "Sally", "sally", "admin");
            User tom = dataService.createUser("tomtom@gmail.com", "Tom", "tom", "user");
     
            dataService.createQuality("Wonderful", sally);
            dataService.createQuality("Good Judgement", sally);
            dataService.createQuality("Team Player", sally);
            dataService.createQuality("Leadership", sally);
            
            dataService.createQuality("Diligent", tom);
            dataService.createQuality("Team Player", tom);
            dataService.createQuality("Responsible", tom);
            
        }
    }
}
