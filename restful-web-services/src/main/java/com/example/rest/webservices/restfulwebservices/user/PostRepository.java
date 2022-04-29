package com.example.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This is a User repository interface that serves for 'UserJPAController' and extends
// 'JpaRepository'.
// Specify the content of the interface. Here the 'User' within the '<>' specifies what we want
// to manage, and 'Integer' within the '<>' specifies the primary key of each 'User' object.
@Repository
public interface PostRepository
        extends JpaRepository<Post, Integer> {

}
