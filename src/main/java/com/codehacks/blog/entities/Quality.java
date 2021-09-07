package com.codehacks.blog.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Qualities")
@NamedQuery(name="Quality.byUser", query = "select qual from Quality qual where qual.user.id = :userId")
public class Quality implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    @Column(name = "id")
    private Long id;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Quality() {
    }

    public Quality(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Quality other = (Quality) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Quality{" + "id=" + id + '}';
    }
}
