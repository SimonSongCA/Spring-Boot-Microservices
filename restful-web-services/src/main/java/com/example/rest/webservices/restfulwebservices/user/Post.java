package com.example.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {
  @Id
  @GeneratedValue
  private Integer id;
  
  // Many users have the same POST request object --> Many to One
  // Make 'fetch' to be 'FetchType.LAZY' to avoid infinite mappings. We only want to fetch the
  // user of a POST request once the POST request is called.
  @ManyToOne(fetch = FetchType.LAZY)
  // Also add 'JsonIgnore' to avoid infinite JSON
  // We only want details of the posts of a user, but we don't want the details of the user.
  // By doing this, the 'user' field at the current class will be ignored when being returned as
  // an object
  @JsonIgnore
  private User user;
  
  private String description;
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  @Override
  public String toString() {
    return "Post{" +
            "id=" + id +
            ", description='" + description + '\'' +
            '}';
  }
}
