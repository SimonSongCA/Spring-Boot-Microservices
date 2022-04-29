package com.example.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity()
public class User {
  
  public User() {
  
  }
  
  // @Id indicates that the 'id' field is a primary key
  @Id
  // @GeneratedValue indicates that this is the value that needs the database to generate
  @GeneratedValue private Integer id;
  // the 'message' parameter will appear in the 'details' part of the response body once the
  // validation fails
  
  @Size(min = 2, message = "Name length should be greater than 2 characters.")
  private String name;
  
  @Past
  private Date birthDate;
  
  // Make a One-to-Many relationship, but we only want to create a relationship of a column
  // of 'User' within 'Post'
  // Here, the value of 'mappedBy' is the name of the field in 'Post'
  @OneToMany(mappedBy = "user")
  private List<Post> posts;
  
  // Getters & Setters for 'Post'
  public List<Post> getPosts() {
    return posts;
  }
  
  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Date getBirthDate() {
    return birthDate;
  }
  
  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }
  
  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + ", birthDate=" + birthDate + '}';
  }
  
  public User(Integer id, String name, Date birthDate) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
  }
}
