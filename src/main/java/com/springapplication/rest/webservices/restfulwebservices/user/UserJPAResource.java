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
import java.util.Optional;

@RestController
public class UserJPAResource {

//    @Autowired
//    private UserDaoService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // GET /users
    // retrieve all users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    // retrieveUser (int id)
    // GET /users/{id}
    @GetMapping("/jpa/users/{id}")
    public User retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException("id-"+id);
        }

//        EntityModel<User> resource = new EntityModel<User>(user);
//
//        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//
//        resource.add(linkTo.withRel("all-users"));

        return user.get();
    }

    // CREATED
    // input - user details
    // output - CREATED & Return the created URI
    @PostMapping("/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        // CREATED status code
        // /users/{new_id} savedUser.getId()
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);

//        if(user == null){
//            throw new UserNotFoundException("id-"+id);
//        }
//
//        return user;

    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable int id){
        Optional<User> user =  userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }
        return user.get().getPosts();
    }


    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity createPost(@PathVariable int id, @RequestBody Post post){

        Optional<User> userOptional =  userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }


        User user = userOptional.get();

        post.setUser(user);

        postRepository.save(post);

        User savedUser = userRepository.save(user);
        // CREATED status code
        // /users/{new_id} savedUser.getId()
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
