package com.springapplication.rest.webservices.restfulwebservices.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "User attributes details")
@Entity
public class User {

        @Id
        @GeneratedValue
        private Integer id;

        @Size(min=2, message="Name should have at least 2 characters")
        private String name;

        @Past
        @ApiModelProperty(notes = "Birth date should be in the past")
        private Date birthDate;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        private List<Post> posts;

        public User() {
        }

        public User(Integer id, String name, Date birthDate) {
            this.id = id;
            this.name = name;
            this.birthDate = birthDate;
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



}
