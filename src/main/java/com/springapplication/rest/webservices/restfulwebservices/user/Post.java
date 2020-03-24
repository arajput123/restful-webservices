package com.springapplication.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    public Post() {
    }

    public Post(Integer id, String post, User user) {
        this.id = id;
        this.post = post;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
}


