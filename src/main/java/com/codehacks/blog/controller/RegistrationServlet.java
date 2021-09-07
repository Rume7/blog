package com.codehacks.blog.controller;

import com.codehacks.blog.dao.DataService;
import com.codehacks.blog.entities.Registration;
import java.io.IOException;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rhume
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    @Inject
    DataService dataService;
    
    private Registration registeredUser;
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String email = request.getParameter("email_address");
        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        Date dateCreated = new Date();
        
        if (password.equals(confirmPassword)) {
            this.registeredUser = new Registration(username, firstname, lastname, email, password, dateCreated);
            dataService.registerBlogger(username, firstname, lastname, email, password, dateCreated);
            
        } else {
            // Request user enter same password
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
