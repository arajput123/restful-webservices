package com.springapplication.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityModel;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
//
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    // GET /users
    // retrieve all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    // retrieveUser (int id)
    // GET /users/{id}
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findUser(id);
        if(user == null) {
            throw new UserNotFoundException("id-"+id);
        }

//        EntityModel<User> resource = new EntityModel<User>(user);
//
//        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//
//        resource.add(linkTo.withRel("all-users"));

        return user;
    }

    // CREATED
    // input - user details
    // output - CREATED & Return the created URI
    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        // CREATED status code
        // /users/{new_id} savedUser.getId()
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException("id-"+id);
        }

        return user;

    }

}
