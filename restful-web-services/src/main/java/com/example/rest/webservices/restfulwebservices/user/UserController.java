package com.example.rest.webservices.restfulwebservices.user;

import com.example.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
  // The '@autowired' creates a 'UserDaoService' instance and wires it
  // automatically in here
  @Autowired
  private UserDaoService service;
  
  // GET /users
  // retrieveAllUsers
  @GetMapping("/users")
  public List<User> retrieveAllUsers() {
    return service.findAll();
  }
  
  // GET
  // retrieveUser(int id)
  @GetMapping("/users/{id}")
  // Use 'EntityModel' for HATEOAS. This kind of return type can include HATEOAS links within the
  // return body
  public EntityModel<User> retrieveUser(@PathVariable int id) {
    User user = service.findOne(id);
    
    if (user == null) {
      throw new UserNotFoundException("id-" + id);
    }
    
    // Add HATEOAS & Build a link within the return body
    EntityModel<User> model = EntityModel.of(user);
    // 'linkToUsers' will be stored in a k-v pair with 'href' being the key, and the actual link
    // being the value
    WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    // Add a key value of 'linkToUsers' k-v pair
    model.add(linkToUsers.withRel("all-users"));
    
    // Return the model
    return model;
    //    return service.findOne(id);
  }
  
  // POST
  // input - get details of a user
  // output - CREATED & Return the created URI
  @PostMapping("/users")
  // Add '@Valid' in front of the parameter to trigger input validations
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    // do the mapping
    User savedUser = service.save(user);
    // send the details of the creation(status, URI)
    URI location =
            ServletUriComponentsBuilder.fromCurrentRequest()
                                       .path("/{id}")
                                       .buildAndExpand(savedUser.getId())
                                       .toUri();
    return ResponseEntity.created(location).build();
  }
  
  // DELETE
  // Delete a user by id
  @DeleteMapping("users/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable int id) {
    User user = service.deleteById(id);
    if (user == null) {
      throw new UserNotFoundException("id-" + id);
    }
    else {
      return ResponseEntity.noContent().build();
    }
  }
  
}
