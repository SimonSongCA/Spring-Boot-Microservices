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
import java.util.Optional;

@RestController()
public class UserJPAController {
  // Introduce the 'UserRepository' to 'UserJPAController'
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostRepository postRepository;
  
  // GET /users
  // retrieveAllUsers
  @GetMapping("/jpa/users")
  public List<User> retrieveAllUsers() {
    // The 'userRepository' has its own 'findAll()' method from the embedded database already.
    // The method actually finds all the data within the H2 database, and the data is
    // auto-generated within 'data.sql' under 'src/main/resources'
    return userRepository.findAll();
  }
  
  // GET
  // retrieveUser(int id)
  @GetMapping("/jpa/users/{id}")
  // Use 'EntityModel' for HATEOAS. This kind of return type can include HATEOAS links within the
  // return body
  public EntityModel<User> retrieveUser(@PathVariable int id) {
    // Notice that the expression of finding a user within the H2 db is different.
    Optional<User> user = userRepository.findById(id);
    
    // Also notice that checking the existence of a user within H2 is also different.
    if (!user.isPresent()) {
      throw new UserNotFoundException("id-" + id);
    }
    // Use 'user.get()' instead of 'user' to get the actual information of a user within H2
    // Add HATEOAS & Build a link within the return body
    EntityModel<User> model = EntityModel.of(user.get());
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
  @PostMapping("/jpa/users")
  // Add '@Valid' in front of the parameter to trigger input validations
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    // do the mapping
    User savedUser = userRepository.save(user);
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
  @DeleteMapping("/jpa/users/{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable int id) {
    try {
      userRepository.deleteById(id);
    }
    catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
    return null;
  }
  
  // GET
  // Retrieve all the posts for a specific user
  @GetMapping("/jpa/users/{id}/posts")
  public List<Post> getPosts(@PathVariable int id) {
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("id-" + id);
    }
    return userOptional.get().getPosts();
  }
  
  // POST
  // Post a specific post for a user
  @PostMapping("/jpa/users/{id}/posts")
  public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
    // retrieve the user
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("id-" + id);
    }
    User user = userOptional.get();
    // Map the use to the post
    post.setUser(user);
    // Save the post for the user
    postRepository.save(post);
    // Do the URI mapping
    URI location =
            ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(post.getId())
                    .toUri();
    // return the response as a 'ResponseEntity<Object>'
    return ResponseEntity.created(location).build();
  }
}
