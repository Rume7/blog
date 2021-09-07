package com.codehacks.blog.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Users")
@NamedQueries({
    @NamedQuery(name="User.all", query="select us from User us order by us.id"),
    @NamedQuery(name="User.byUsername", query="select us from User us where us.username = :username")
})
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    @Column(name="email", nullable=false, unique=true)
    private String email;
    
    @Column(name="username", nullable=false, unique=true)
    private String username;
    
    @Column(name="user_password", nullable=false)
    private String password;
    
    @Column(name="user_group", nullable=false)
    private String user_group;

    public User() {
    }

    public User(String email, String username, String password, String user_group) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.user_group = user_group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_group() {
        return user_group;
    }

    public void setUser_group(String user_group) {
        this.user_group = user_group;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + '}';
    }
}
