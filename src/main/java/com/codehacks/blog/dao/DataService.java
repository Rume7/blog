package com.codehacks.blog.dao;

import com.codehacks.blog.entities.Quality;
import com.codehacks.blog.entities.Registration;
import com.codehacks.blog.entities.User;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.transaction.Transactional;

/**
 * @author Rhume
 * @date Sept 7, 2021
 */

@ApplicationScoped
public class DataService {

    @PersistenceContext(unitName = "blogappPU")
    EntityManager em;

    @Inject
    Pbkdf2PasswordHash passwordHasher;
    
    @Transactional
    public User createUser(String email, String username, String password, String group) {
        User newUser = new User(email, username, passwordHasher.generate(password.toCharArray()), group);
        em.persist(newUser);
        em.flush();
        return newUser;
    }

    @Transactional
    public Quality createQuality(String description, User user) {
        Quality newQuality = new Quality(description, user);
        em.persist(newQuality);
        em.flush();
        return newQuality;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = em.createNamedQuery("User.all", User.class).getResultList();
        return allUsers;
    }
    
    public Optional<User> getUser(String username) {
        Optional<User> user = em.createNamedQuery("User.byUsername", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
        return user;
    }

    public List<Quality> getQualities(User user) {
        return em.createNamedQuery("Quality.byUser", Quality.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }
    
    @Transactional
    public Registration registerBlogger(String username, String firstname, String lastname, 
            String email, String password, Date dateCreated) {
        Registration registeredUser = new Registration(username, firstname, lastname, email, password, dateCreated);
        em.persist(registeredUser);
        em.flush();
        return registeredUser;        
    }
}
